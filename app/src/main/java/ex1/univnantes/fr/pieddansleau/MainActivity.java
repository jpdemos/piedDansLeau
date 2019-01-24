package ex1.univnantes.fr.pieddansleau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ex1.univnantes.fr.pieddansleau.downloader.PoolDownloader;
import ex1.univnantes.fr.pieddansleau.model.CityPool;


public class MainActivity extends AppCompatActivity
{
	protected RecyclerView     mainList;
	protected List< CityPool > pools;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		mainList = findViewById( R.id.mainList );
		mainList.setLayoutManager( new LinearLayoutManager( this ) );
		
		pools = new ArrayList<>();
		
		mainList.setAdapter( new PoolAdapter( this, pools ) );
		new PoolDownloader( this ).execute();
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		this.mainList.getAdapter().notifyDataSetChanged();
	}
	
	public void populate( List< CityPool > data )
	{
		pools.clear();
		pools.addAll( data );
		this.mainList.getAdapter().notifyDataSetChanged();
	}
	
	public void openPoolDetails( View view )
	{
		Intent intent = new Intent( this, PoolActivity.class );
		
		CityPool selectedPool = ( (PoolAdapter.ViewHolder) mainList.getChildViewHolder( view ) ).getPool();
		
		intent.putExtra( "poolId", selectedPool.getId() );
		intent.putExtra( "fullname", selectedPool.getNom_complet() );
		intent.putExtra( "shortname", selectedPool.getNom_usuel() );
		intent.putExtra( "infos", selectedPool.getInfos() );
		intent.putExtra( "location", selectedPool.getAdresse() );
		intent.putExtra( "rating", selectedPool.getRating() );
		intent.putExtra( "hasPataugoire", selectedPool.isPataugeoire() );
		intent.putExtra( "hasPlongeoir", selectedPool.isPlongeoir() );
		intent.putExtra( "phone", selectedPool.getTel() );
		intent.putExtra( "web", selectedPool.getWeb() );
		
		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation( this, view, getString( R.string.transition_string ) );
		
		ActivityCompat.startActivity( this, intent, options.toBundle() );
	}
	
}
