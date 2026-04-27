package transit.bus;
import java.util.ArrayList;
import transit.core.*;
import transit.train.MetroStation;
import transit.train.Train;
public class BusRoute extends Route
{
ArrayList<Bus> buses = new ArrayList<Bus>();
public ArrayList<Bus> getBuses()
{
	return buses;
}
public void setBuses(ArrayList<Bus> b)
{
	this.buses = b;
}
public BusRoute(int routeNum, String routeDesc, BusStop first)
{
	this.routeNumber = routeNum;
	this.routeDescription = routeDesc;
	this.firstStop = first;
	this.firstStop.setNextStop(first);
}

public String toString()
{
	String result = "";
	Stop thisStop = this.firstStop;
	result = result + "Bus Route #" + this.getRouteNumber() +": " + this.getRouteDescription() + "\n" + " <br/> <br/>Stops: <br/>";
	
	while(thisStop.getNextStop() != firstStop) 
	{
		result = result + thisStop.toString();
		thisStop = thisStop.getNextStop();
	}
	result = result + thisStop.toString();
	result = result + "<br/>Buses: <br/>";
	for(int i = 0; i < buses.size(); i++)
	{
		result = result + buses.get(i).toString();
	}
	return result;
}
public void addStop(String stopName, double x, double y)
{
	Stop newStop = new BusStop(stopName, x, y);
	newStop.setNextStop(firstStop);
	Stop thisStop = this.firstStop;
	while(thisStop.getNextStop() != firstStop) 
	{
		thisStop = thisStop.getNextStop();
	}
	thisStop.setNextStop(newStop);
}
public void addStop(BusStop stop)
{
	Stop newStop = stop;
	newStop.setNextStop(firstStop);
	Stop thisStop = this.firstStop;
	while(thisStop.getNextStop() != firstStop) 
	{
		thisStop = thisStop.getNextStop();
	}
	thisStop.setNextStop(newStop);
}
public void addDriver(String name, double speed)
{
	Bus newBus = new Bus(name, speed, this, this.getFirstStop());
	buses.add(newBus);
}
public void moveAll(int minutes)
{
for(Bus b : buses)
{
    b.move(minutes);
}
}

}
