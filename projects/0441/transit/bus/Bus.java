package transit.bus;

import transit.core.Stop;
import transit.core.Vehicle;
public class Bus extends Vehicle
{
protected int capacity = 35;
public Bus(String driver, double sp, BusRoute rt, Stop stop)
{
	this.driverName = driver;
	this.speed = sp;
	this.route = rt;
	this.setXCoordinate(stop.getXCoordinate());
	this.setYCoordinate(stop.getYCoordinate());
	this.setDestination(rt.getFirstStop());
	this.setIsStopped(true);
}
public Bus(String driver, double sp, BusRoute rt)
{
	this.setIdentifier((Integer.toString(Math.abs(driver.hashCode()))));
	this.driverName = driver;
	this.speed = sp;
	this.route = rt;
	this.setDestination(rt.getFirstStop());
	this.setIsStopped(true);
}
public int getCapacity()
{
	return this.capacity;
}
public String toString ()
{	
	String result = "Bus " + " (" + this.getDriverName() + ") <br/>" ;
	if (this.getIsStopped())
	{
		result += "Currently stopped ";
	}
	else
	{
		result += "Moving towards ";
	}
	return result + destination.getStopName() + "<br/>at location (" + this.getXCoordinate() + ", " + this.getYCoordinate() + ")<br/>" + this.getPassengers() + " out of " + this.getCapacity() + " seats taken.";
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
double xDiff = this.destination.getXCoordinate() - this.xCoordinate;
double yDiff = this.destination.getYCoordinate() - this.yCoordinate;

//calculate x movement
double xMove = maxDistance;
if(maxDistance > Math.abs(xDiff)) 
    xMove = Math.abs(xDiff);
if(xDiff < 0)
    xMove *= -1;

// move X
this.xCoordinate += xMove;
maxDistance -= Math.abs(xMove);

//calculate y movement
double yMove = maxDistance;
if(maxDistance > Math.abs(yDiff)) 
    yMove = Math.abs(yDiff);
if(yDiff < 0)
    yMove *= -1;

// move y
this.yCoordinate += yMove;
maxDistance -= Math.abs(yMove);

//if stopped... 
if(this.destination.getXCoordinate() - this.xCoordinate == 0 && this.destination.getYCoordinate() - this.yCoordinate == 0)
{
    this.isStopped = true;
    this.letPassengersOff();
}

return (this.speed * (minutes/60.0)) - maxDistance;
}
}


