package transit.core; 
public abstract class Route 
{
	protected int routeNumber;
	protected String routeDescription;
	protected Stop firstStop;
	public int getRouteNumber()
	{
		return routeNumber;
	}
	public void setRouteNumber(int rn)
	{
		this.routeNumber = rn;
	}
	public String getRouteDescription()
	{
		return routeDescription;
	}
	public void setRouteDescription(String rd)
	{
		this.routeDescription = rd;
	}
	public Stop getFirstStop()
	{
		return firstStop;
	}
	public void setFirstStop(Stop fs)
	{
		this.firstStop = fs;
	}
	public void gainPassengersAll()
	{
		Stop thisStop = this.getFirstStop();
		while (thisStop.getNextStop() != firstStop)
		{
			thisStop.gainPassengers();
			thisStop = thisStop.getNextStop();
		}
		thisStop.gainPassengers();
	}
	public abstract void addDriver(String name, double speed);
	public abstract void addStop(String stopName, double x, double y);
	public abstract void moveAll(int minutes);
}
