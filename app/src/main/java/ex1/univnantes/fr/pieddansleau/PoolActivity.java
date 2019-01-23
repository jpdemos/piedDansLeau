package ex1.univnantes.fr.pieddansleau;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ex1.univnantes.fr.pieddansleau.downloader.PoolScheduleDownloader;
import ex1.univnantes.fr.pieddansleau.model.PoolSchedule;


public class PoolActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
	private TextView title;
	private TextView description;
	private TextView location;
	private TextView web;
	private TextView phone;
	private Spinner  periodsSpinner;
	private TextView periodTimeSpan;
	
	private HashMap< String, TextView > periodWeekDays;
	
	private HashMap< String, List< PoolSchedule > > periods;
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.pool_details );
		Intent intent = getIntent();
		
		
		getSupportActionBar().setTitle( intent.getStringExtra( "shortname" ) );
		
		title = findViewById( R.id.title );
		title.setText( intent.getStringExtra( "fullname" ) );
		
		description = findViewById( R.id.description );
		description.setText( intent.getStringExtra( "infos" ) );
		
		location = findViewById( R.id.location );
		location.setText( intent.getStringExtra( "location" ) );
		
		web = findViewById( R.id.web );
		web.setText( intent.getStringExtra( "web" ) );
		
		phone = findViewById( R.id.phone );
		phone.setText( intent.getStringExtra( "phone" ) );
		
		periodsSpinner = findViewById( R.id.periodsSpinner );
		periodsSpinner.setOnItemSelectedListener( this );
		
		periodWeekDays = new HashMap<>();
		
		periodWeekDays.put( "lundi", (TextView) findViewById( R.id.colLundi ) );
		periodWeekDays.put( "mardi", (TextView) findViewById( R.id.colMardi ) );
		periodWeekDays.put( "mercredi", (TextView) findViewById( R.id.colMercredi ) );
		periodWeekDays.put( "jeudi", (TextView) findViewById( R.id.colJeudi ) );
		periodWeekDays.put( "vendredi", (TextView) findViewById( R.id.colVendredi ) );
		periodWeekDays.put( "samedi", (TextView) findViewById( R.id.colSamedi ) );
		periodWeekDays.put( "dimanche", (TextView) findViewById( R.id.colDimanche ) );
		
		periodTimeSpan = findViewById( R.id.periodTimeSpan );
		
		new PoolScheduleDownloader( this ).execute( intent.getStringExtra( "poolId" ) );
		
		//intent.getStringExtra( "rating"  );
		//intent.getStringExtra( "hasPataugoire" );
		//intent.getStringExtra( "hasPlongeoir" );
		
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
	}
	
	public void populate( ArrayList< PoolSchedule > schedules )
	{
		if( periods == null )
			periods = new HashMap<>();
		
		periods.clear();
		
		for( PoolSchedule schedule : schedules )
		{
			String               schedulePeriod = schedule.getPeriod();
			List< PoolSchedule > list           = periods.get( schedulePeriod );
			
			if( list == null )
			{
				periods.put( schedulePeriod, new ArrayList< PoolSchedule >() );
				list = periods.get( schedulePeriod );
			}
			
			list.add( schedule );
		}
		
		ArrayAdapter< String > adapter = new ArrayAdapter< String >( this, android.R.layout.simple_spinner_item, new ArrayList<>( periods.keySet() ) );
		adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		
		periodsSpinner.setAdapter( adapter );
	}
	
	@Override
	public void onItemSelected( AdapterView< ? > parent, View view, int position, long id )
	{
		List< PoolSchedule >              list = periods.get( ( (TextView) view ).getText() );
		HashMap< String, List< String > > days = new HashMap<>();
		
		for( PoolSchedule schedule : list )
		{
			String         dayOfTheWeek = schedule.getDayOfTheWeek().toLowerCase();
			List< String > hours        = days.get( dayOfTheWeek );
			
			if( hours == null )
			{
				days.put( dayOfTheWeek, new ArrayList< String >() );
				hours = days.get( dayOfTheWeek );
			}
			
			hours.add( schedule.getHourStart() + " - " + schedule.getHourEnd() );
			
		}
		
		// On affiche la période au dessus du tableau (date debut - date fin)
		this.periodTimeSpan.setText( list.get( 0 ).getDateStart() + " - " + list.get( 0 ).getDateEnd() );
		
		// Reset
		for( TextView dayView : periodWeekDays.values() )
			dayView.setText( "X" );
		
		for( String day : days.keySet() )
		{
			List< String > hours   = days.get( day );
			String         periods = "";
			
			for( int i = hours.size(); i > 0; i-- )
			{
				String daySchedule = hours.get( i - 1 );
				
				if( !periods.contains( daySchedule ) )
					periods += daySchedule + ( i > 1 ? "\n\n" : "" );
			}
			
			periodWeekDays.get( day ).setText( periods );
		}
	}
	
	@Override
	public void onNothingSelected( AdapterView< ? > parent )
	{
	
	}
	
	public void openMap( View view )
	{
		Uri    gmmIntentUri = Uri.parse( "geo:0,0?q=" + title.getText() );
		Intent mapIntent    = new Intent( Intent.ACTION_VIEW, gmmIntentUri );
		mapIntent.setPackage( "com.google.android.apps.maps" );
		
		startActivity( mapIntent );
	}
	
	public void openWebsite( View view )
	{
		if( !web.getText().toString().startsWith( "http" ) )
			return;
		
		Intent intent = new Intent( Intent.ACTION_VIEW );
		intent.setData( Uri.parse( web.getText().toString() ) );
		intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK ); // Dans une nouvelle tab
		
		startActivity( intent );
	}
	
	public void openPhone( View view )
	{
		if( !phone.getText().toString().startsWith( "0" ) )
			return;
		
		Intent intent = new Intent( Intent.ACTION_VIEW );
		intent.setData( Uri.parse( "tel:" + phone.getText().toString() ) );
		
		startActivity( intent );
	}
}
