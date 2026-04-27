package transit.train;

import transit.bus.BusRoute;
import transit.bus.BusStop;
import transit.core.Stop;
import transit.core.Vehicle;

public class Train extends Vehicle
{
	public int cars;
	
	public int getCars()
	{
		return cars;
	}
	public void setCars(int car)
	{
		this.cars = car;
	}
	public Train(String driver, double sp, int cars, MetroRoute rt, Stop stop)
	{
		this.driverName = driver;
		this.speed = sp;
		this.cars = cars;
		this.route = rt;
		this.setXCoordinate(stop.getXCoordinate());
		this.setYCoordinate(stop.getYCoordinate());
		this.setDestination(rt.getFirstStop());
		this.setIsStopped(true);
	}
	public Train(String driver, double sp, int cars, MetroRoute rt)
	{
		this.setIdentifier((Integer.toString(Math.abs(driver.hashCode()))));
		this.driverName = driver;
		this.speed = sp;
		this.cars = cars;
		this.route = rt;
		this.setXCoordinate(rt.getFirstStop().getXCoordinate());
		this.setYCoordinate(rt.getFirstStop().getYCoordinate());
		this.setDestination(rt.getFirstStop());
		this.setIsStopped(true);
	}
	public  int getCapacity()
	{
		int cap = this.cars * 120;
		return cap;
	}
	public String toString()
	{	
		String result = "Train " + " (" + this.getDriverName() + ") <br/>" ;
		if (this.getIsStopped())
		{
			result += "Currently stopped ";
		}
		else
		{
			result += "Moving towards ";
		}
		return result + destination.getStopName() + "<br/> at location (" + this.getXCoordinate() + ", " + this.getYCoordinate() + ")<br/>" + this.getPassengers() + " out of " + this.getCapacity() + " seats taken.";
	}
	@Override
    public double move(int minutes)
    {
	if(this.isStopped)
	{
	    this.letPassengersOn();
	    this.isStopped = false;
	    this.destination = this.destination.getNextStop();
	}
	
	double maxDistance = this.speed * (minutes/60.0);
	double distance = Math.sqrt(Math.pow(this.xCoordinate - this.destination.getXCoordinate(), 2) + Math.pow(this.yCoordinate - this.destination.getYCoordinate(), 2));
	
	double traveled = maxDistance;
	if(distance < maxDistance)
	{
	    this.xCoordinate = this.destination.getXCoordinate();
	    this.yCoordinate = this.destination.getYCoordinate();
	    this.isStopped = true;
	    this.letPassengersOff();
	    return distance;
	}
	
	//Move along line
	// (xt,yt)=(((1−t)x0+tx1),((1−t)y0+ty1))
	double t = traveled / distance;
	this.xCoordinate = ((1-t)*this.xCoordinate) + t*this.destination.getXCoordinate();
	this.yCoordinate = ((1-t)*this.yCoordinate) + t*this.destination.getYCoordinate();
	
	return traveled;
    }
	}
