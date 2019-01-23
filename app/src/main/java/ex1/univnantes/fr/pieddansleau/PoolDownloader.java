package ex1.univnantes.fr.pieddansleau;

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


public class PoolDownloader extends AsyncTask< String, Void, ArrayList< CityPool > >
{
	private static final String            BASE_URL = "https://data.nantesmetropole.fr/api/records/1.0/search/?dataset=244400404_piscines-nantes-metropole&rows=100";
	private              HttpURLConnection httpConnection;
	private              ProgressDialog    progress;
	private volatile     MainActivity      screen;
	
	public PoolDownloader( MainActivity screen )
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
	protected ArrayList< CityPool > doInBackground( String... params )
	{
		ArrayList< CityPool > fetchedData = new ArrayList<>();
		String                stream      = null;
		
		try
		{
			URL url = new URL( BASE_URL );
			
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
				
				CityPool pool = new CityPool();
				
				pool.setId( fields.getString( "idobj" ) );
				pool.setNom_complet( fields.getString( "nom_complet" ) );
				pool.setNom_usuel( fields.getString( "nom_usuel" ) );
				pool.setInfos( fields.optString( "infos_complementaires" ) );
				pool.setAdresse( fields.getString( "adresse" ) );
				pool.setWeb( fields.optString( "web", "Aucun site internet" ) );
				pool.setTel( fields.optString( "tel", "Aucun numero de téléphone" ) );
				
				pool.setPataugeoire( fields.optString( "pataugeoire", "" ).equals( "OUI" ) );
				pool.setSolarium( fields.optString( "solarium", "" ).equals( "OUI" ) );
				pool.setAccess_handicap( fields.optString( "accessibilite_handicap", "" ).equals( "OUI" ) );
				pool.setHasBassinSportif( fields.optString( "bassin_sportif", "" ).equals( "OUI" ) );
				pool.setPlongeoir( fields.optString( "plongeoir", "" ).equals( "OUI" ) );
				
				fetchedData.add( pool );
			}
			
		} catch( Exception e )
		{
			Log.e( "citypools", e.getMessage() );
		} finally
		{
			this.httpConnection.disconnect();
		}
		
		return fetchedData;
	}
	
	@Override
	protected void onPostExecute( ArrayList< CityPool > pools )
	{
		if( progress.isShowing() ) progress.dismiss();
		
		this.screen.populate( pools );
	}
}
