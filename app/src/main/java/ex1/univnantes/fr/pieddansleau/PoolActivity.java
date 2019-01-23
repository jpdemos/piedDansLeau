package ex1.univnantes.fr.pieddansleau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class PoolActivity extends AppCompatActivity
{
	private TextView title;
	private TextView description;
	private TextView location;
	private TextView web;
	private TextView phone;
	
	
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.pool_details );
		Intent intent = getIntent();
		
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
		
		//intent.getStringExtra( "rating"  );
		//intent.getStringExtra( "hasPataugoire" );
		//intent.getStringExtra( "hasPlongeoir" );
		
		//Log.i("citypools", intent.getExtras().getString( "pool" ));
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
	}
}
