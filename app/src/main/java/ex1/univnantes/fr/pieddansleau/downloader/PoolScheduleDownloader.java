package ex1.univnantes.fr.pieddansleau.downloader;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ex1.univnantes.fr.pieddansleau.PoolActivity;
import ex1.univnantes.fr.pieddansleau.model.PoolSchedule;


public class PoolScheduleDownloader extends AsyncTask< String, Void, ArrayList< PoolSchedule > >
{
	private static final String            BASE_URL = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_piscines-nantes-metropole-horaires&rows=100";
	private              HttpURLConnection httpConnection;
	private              ProgressDialog    progress;
	private volatile     PoolActivity      screen;
	
	public PoolScheduleDownloader( PoolActivity screen )
	{
		this.screen = screen;
		this.progress = new ProgressDialog( this.screen );
	}
	
	@Override
	protected void onPreExecute()
	{
		progress.setTitle( "Please wait" );
		progress.setMessage( "Fetching remote data..." );
		progress.setProgressStyle( ProgressDialog.STYLE_SPINNER );
		progress.show();
	}
	
	@Override
	protected ArrayList< PoolSchedule > doInBackground( String... poolid )
	{
		ArrayList< PoolSchedule > fetchedData = new ArrayList<>();
		String                    stream      = null;
		
		try
		{
			URL url = new URL( BASE_URL + "&idobj%3D" + poolid[ 0 ] );
			
			this.httpConnection = (HttpURLConnection) url.openConnection();
			this.httpConnection.setRequestMethod( "GET" );
			
			InputStream    in = new BufferedInputStream( this.httpConnection.getInputStream() );
			BufferedReader r  = new BufferedReader( new InputStreamReader( in, "UTF-8" ) );
			StringBuilder  sb = new StringBuilder();
			
			String line;
			while( ( line = r.readLine() ) != null )
			{
				sb.append( line );
			}
			
			stream = sb.toString();
			
			JSONArray concepts = new JSONObject( stream ).getJSONArray( "records" );
			
			for( int i = 0; i < concepts.length(); i++ )
			{
				JSONObject fields = concepts.getJSONObject( i ).getJSONObject( "fields" );
				
				PoolSchedule schedule = new PoolSchedule();
				
				schedule.setPoolId( fields.getString( "idobj" ) );
				schedule.setPeriod( fields.getString( "periode" ) );
				schedule.setDateStart( fields.optString( "date_debut", "" ) );
				schedule.setDateEnd( fields.optString( "date_fin", "" ) );
				schedule.setHourStart( fields.optString( "heure_debut", "" ) );
				schedule.setHourEnd( fields.optString( "heure_fin", "" ) );
				schedule.setDayOfTheWeek( fields.optString( "jour", "" ) );
				
				fetchedData.add( schedule );
			}
			
		} catch( Exception e )
		{
			Log.e( "poolschedules", e.getMessage() );
		} finally
		{
			this.httpConnection.disconnect();
		}
		
		return fetchedData;
	}
	
	
	@Override
	protected void onPostExecute( ArrayList< PoolSchedule > pools )
	{
		if( progress.isShowing() ) progress.dismiss();
		
		this.screen.populate( pools );
	}
}
