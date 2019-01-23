package ex1.univnantes.fr.pieddansleau;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import ex1.univnantes.fr.pieddansleau.model.CityPool;


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
		protected ImageView tobo;
		protected ImageView handicap;
		protected ImageView bassSportif;
		
		public ViewHolder( View itemView )
		{
			super( itemView );
			
			title = itemView.findViewById( R.id.poolTitle );
			description = itemView.findViewById( R.id.poolDesc );
			rating = itemView.findViewById( R.id.poolRating );
			phone = itemView.findViewById( R.id.poolPhone );
			
			tobo = itemView.findViewById( R.id.toboView );
			handicap = itemView.findViewById( R.id.handiView );
			bassSportif = itemView.findViewById( R.id.bassSportifView );
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
		
		viewHolder.title.setText( pool.getNom_usuel() );
		viewHolder.description.setText( pool.getAdresse() );
		viewHolder.rating.setRating( pool.getRating() );
		viewHolder.phone.setText( pool.getTel() );
		
		viewHolder.tobo.setVisibility( pool.isPlongeoir() ? View.VISIBLE : View.INVISIBLE );
		viewHolder.handicap.setVisibility( pool.isAccess_handicap() ? View.VISIBLE : View.INVISIBLE );
		viewHolder.bassSportif.setVisibility( pool.isHasBassinSportif() ? View.VISIBLE : View.INVISIBLE );
	}
	
	@Override
	public int getItemCount()
	{
		return pools.size();
	}
	
}
