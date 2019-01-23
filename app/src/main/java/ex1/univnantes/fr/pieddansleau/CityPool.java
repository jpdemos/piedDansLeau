package ex1.univnantes.fr.pieddansleau;


public class CityPool
{
	private String  id;
	private String  web;
	private String  adresse;
	private String  nom_complet;
	private String  nom_usuel;
	private String  tel;
	private String  infos;
	private boolean plongeoir;
	private boolean pataugeoire;
	private boolean solarium;
	private boolean access_handicap;
	private boolean hasBassinSportif;
	
	private int rating;
	
	public CityPool()
	{
	}
	
	public String getWeb()
	{
		return web;
	}
	
	public void setWeb( String web )
	{
		this.web = web;
	}
	
	public String getAdresse()
	{
		return adresse;
	}
	
	public void setAdresse( String adresse )
	{
		this.adresse = adresse;
	}
	
	public String getNom_complet()
	{
		return nom_complet;
	}
	
	public void setNom_complet( String nom_complet )
	{
		this.nom_complet = nom_complet;
	}
	
	public String getTel()
	{
		return tel;
	}
	
	public void setTel( String tel )
	{
		this.tel = tel;
	}
	
	public boolean isPlongeoir()
	{
		return plongeoir;
	}
	
	public void setPlongeoir( boolean plongeoir )
	{
		this.plongeoir = plongeoir;
	}
	
	public boolean isPataugeoire()
	{
		return pataugeoire;
	}
	
	public void setPataugeoire( boolean pataugeoire )
	{
		this.pataugeoire = pataugeoire;
	}
	
	public boolean isSolarium()
	{
		return solarium;
	}
	
	public void setSolarium( boolean solarium )
	{
		this.solarium = solarium;
	}
	
	public String getNom_usuel()
	{
		return nom_usuel;
	}
	
	public void setNom_usuel( String nom_usuel )
	{
		this.nom_usuel = nom_usuel;
	}
	
	public int getRating()
	{
		return rating;
	}
	
	public void setRating( int rating )
	{
		this.rating = rating;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId( String id )
	{
		this.id = id;
	}
	
	public String getInfos()
	{
		return infos;
	}
	
	public void setInfos( String infos )
	{
		this.infos = infos;
	}
	
	public boolean isAccess_handicap()
	{
		return access_handicap;
	}
	
	public void setAccess_handicap( boolean access_handicap )
	{
		this.access_handicap = access_handicap;
	}
	
	public boolean isHasBassinSportif()
	{
		return hasBassinSportif;
	}
	
	public void setHasBassinSportif( boolean hasBassinSportif )
	{
		this.hasBassinSportif = hasBassinSportif;
	}
}
