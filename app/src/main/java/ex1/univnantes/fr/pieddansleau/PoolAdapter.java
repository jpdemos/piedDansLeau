package ex1.univnantes.fr.pieddansleau;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;


class PoolAdapter extends RecyclerView.Adapter< PoolAdapter.ViewHolder >
{
	private List< CityPool > pools;
	
	public class ViewHolder extends RecyclerView.ViewHolder
	{
		
		private CityPool pool;
		
		protected TextView  title;
		protected TextView  description;
		protected RatingBar rating;
		protected TextView  phone;
		
		public ViewHolder( View itemView )
		{
			super( itemView );
			
			title = itemView.findViewById( R.id.poolTitle );
			description = itemView.findViewById( R.id.poolDesc );
			rating = itemView.findViewById( R.id.poolRating );
			phone = itemView.findViewById( R.id.poolPhone );
		}
		
		public CityPool getPool()
		{
			return pool;
		}
		
		public void setPool( CityPool pool )
		{
			this.pool = pool;
		}
	}
	
	public PoolAdapter( List< CityPool > pools )
	{
		this.pools = pools;
	}
	
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int i )
	{
		View v = LayoutInflater.from( parent.getContext() )
				.inflate( R.layout.pool_item, parent, false );
		
		return new ViewHolder( v );
	}
	
	@Override
	public void onBindViewHolder( @NonNull ViewHolder viewHolder, int i )
	{
		CityPool pool = pools.get( i );
		viewHolder.setPool( pool );
		
		Log.i( "citypools", pool.getNom_usuel() );
		Log.i( "citypools", pool.getAdresse() );
		Log.i( "citypools", "" + pool.getRating() );
		Log.i( "citypools", pool.getTel() );
		
		viewHolder.title.setText( pool.getNom_usuel() );
		viewHolder.description.setText( pool.getAdresse() );
		viewHolder.rating.setRating( pool.getRating() );
		viewHolder.phone.setText( pool.getTel() );
		
	}
	
	@Override
	public int getItemCount()
	{
		return pools.size();
	}
	
}
