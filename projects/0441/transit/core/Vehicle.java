package transit.core;
import java.math.*;
public abstract class Vehicle 
{
	protected String identifier;
	protected String driverName;
	protected int passengers;
	protected double speed;
	protected double xCoordinate;
	protected double yCoordinate;
	protected Route route;
	protected Stop destination;
	protected boolean isStopped;
	public String getIdentifier()
	{
		return this.identifier;
	}
	public void setIdentifier(String identify)
	{
		this.identifier = identify;
	}
	public String getDriverName()
	{
		return this.driverName;
	}
	public void setDriverName(String driver)
	{
		this.driverName = driver;
	}	
	public int getPassengers()
	{
		return this.passengers;
	}
	public void setPassengers(int pass)
	{
		this.passengers = pass;
	}	
	public double getSpeed()
	{
		return this.speed;
	}
	public void setSpeed(int sp)
	{
		this.speed = sp;
	}	
	public Route getRoute()
	{
		return this.route;
	}
	public void setRoute(Route rt)
	{
		this.route = rt;
	}	
	public double getXCoordinate()
	{
		return this.xCoordinate;
	}
	public void setXCoordinate(double x)
	{
		this.xCoordinate = x;
	}	
	public double getYCoordinate()
	{
		return this.yCoordinate;
	}
	public void setYCoordinate(double y)
	{
		this.yCoordinate = y;
	}		
	public Stop getDestination()
	{
		return this.destination;
	}
	public void setDestination(Stop des)
	{
		this.destination = des;
	}	
	public boolean getIsStopped()
	{
		return this.isStopped;
	}
	public void setIsStopped(boolean stop)
	{
		this.isStopped = stop;
	}	
public void thankTheDriver()
{
	System.out.println("Thank you " + this.getDriverName());
}
public int letPassengersOff()
{
	int pass = this.getPassengers();
	int passLetOff = (int)(Math.random() * (pass + 1));
	if (passLetOff > pass)
	{
		passLetOff = pass;
	}
	this.setPassengers(pass - passLetOff);
	return passLetOff;
}
public int letPassengersOn()
{
	Stop currentStop = this.getRoute().getFirstStop();
	while (currentStop.getXCoordinate() != this.getXCoordinate() && currentStop.getYCoordinate() != this.getYCoordinate())
	{
		currentStop = currentStop.getNextStop();
	}
	int numStopPassengers = currentStop.getPassengersWaiting();
	int currentCapacity = this.getCapacity() - this.getPassengers();
	if (numStopPassengers + this.getPassengers() > this.getCapacity())
	{
		this.setPassengers(this.getCapacity());
		currentStop.setPassengersWaiting(numStopPassengers - currentCapacity);
		return currentCapacity;
	}
	else
	{
		this.setPassengers(this.getPassengers()+ numStopPassengers);
		currentStop.setPassengersWaiting(0);
		return numStopPassengers;
	}
	
}
public abstract int getCapacity();
public abstract double move(int minutes);	
}
