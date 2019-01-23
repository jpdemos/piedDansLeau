package ex1.univnantes.fr.pieddansleau.model;


public class PoolSchedule
{
	private String poolId;
	private String period;
	private String dateStart;
	private String dateEnd;
	private String dayOfTheWeek;
	private String hourStart;
	private String hourEnd;
	
	public PoolSchedule()
	{
	}
	
	public String getPoolId()
	{
		return poolId;
	}
	
	public void setPoolId( String poolId )
	{
		this.poolId = poolId;
	}
	
	public String getPeriod()
	{
		return period;
	}
	
	public void setPeriod( String period )
	{
		this.period = period;
	}
	
	public String getDateStart()
	{
		return dateStart;
	}
	
	public void setDateStart( String dateStart )
	{
		this.dateStart = dateStart;
	}
	
	public String getDateEnd()
	{
		return dateEnd;
	}
	
	public void setDateEnd( String dateEnd )
	{
		this.dateEnd = dateEnd;
	}
	
	public String getDayOfTheWeek()
	{
		return dayOfTheWeek;
	}
	
	public void setDayOfTheWeek( String dayOfTheWeek )
	{
		this.dayOfTheWeek = dayOfTheWeek;
	}
	
	public String getHourStart()
	{
		return hourStart;
	}
	
	public void setHourStart( String hourStart )
	{
		this.hourStart = hourStart;
	}
	
	public String getHourEnd()
	{
		return hourEnd;
	}
	
	public void setHourEnd( String hourEnd )
	{
		this.hourEnd = hourEnd;
	}
}
