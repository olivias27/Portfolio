package transit.core;

public abstract class Stop 
{
	protected String stopName;
	protected int stopNumber;
	protected double xCoordinate;
	protected double yCoordinate;
	protected int passengersWaiting;
	protected Stop nextStop;
	public String getStopName()
	{
		return stopName;
	}
	public void setStopName(String name)
	{
		this.stopName = name;
	}
	public int getStopNumber()
	{
		return stopNumber;
	}
	public void setStopNumber(int number)
	{
		this.stopNumber = number;
	}
	public double getXCoordinate()
	{
		return xCoordinate;
	}
	public void setXCoordinate(double coordinate)
	{
		this.xCoordinate = coordinate;
	}
	public double getYCoordinate()
	{
		return yCoordinate;
	}
	public void setYCoordinate(double coordinate)
	{
		this.yCoordinate = coordinate;
	}
	public int getPassengersWaiting()
	{
		return passengersWaiting;
	}
	public void setPassengersWaiting(int waiting)
	{
		this.passengersWaiting = waiting;
	}
	public Stop getNextStop()
	{
		return nextStop;
	}
	public void setNextStop(Stop stop)
	{
		this.nextStop = stop;
	}
	public boolean losePassengers(int numPassengers)
	{
		setPassengersWaiting(this.getPassengersWaiting() - numPassengers);
		if (this.getPassengersWaiting() < 0)
		{
			this.setPassengersWaiting(0);
			return false;
		}
		return true;
	}
	public String toString()
	{ 
		return "Stop #" + this.getStopNumber() + ": " + this.getStopName() + "<br/>" + this.getPassengersWaiting() + " passengers waiting.<br/>";
	}
	public abstract void gainPassengers();
}
