package ex1.univnantes.fr.pieddansleau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ex1.univnantes.fr.pieddansleau.downloader.PoolDownloader;
import ex1.univnantes.fr.pieddansleau.model.CityPool;


public class MainActivity extends AppCompatActivity
{
	protected RecyclerView     mainList;
	protected List< CityPool > pools;
	//protected SharedPreferences sharedPreferences;
	protected EditText search;
	protected PoolAdapter mAdapter;
	private MainActivity screen;
	
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		mainList = findViewById( R.id.mainList );
		search = findViewById(R.id.search);

		mainList.setLayoutManager( new LinearLayoutManager( this ) );
		
		pools = new ArrayList<>();
		mAdapter = new PoolAdapter( this, pools );
		mainList.setAdapter( mAdapter );
		new PoolDownloader( this ).execute();

		screen = this;
		addTextListener();
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

	public void addTextListener(){

		search.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			public void onTextChanged(CharSequence query, int start, int before, int count) {

				query = query.toString().toLowerCase();

				final List<CityPool> filteredList = new ArrayList<>();

				for (int i = 0; i < pools.size(); i++) {

					final String text = pools.get(i).getNom_complet().toLowerCase();
					if (text.contains(query)) {

						filteredList.add(pools.get(i));
					}
				}

				mainList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
				mAdapter = new PoolAdapter(screen, filteredList);
				mainList.setAdapter(mAdapter);
				mAdapter.notifyDataSetChanged();  // data set changed
			}
		});
	}
	
}
