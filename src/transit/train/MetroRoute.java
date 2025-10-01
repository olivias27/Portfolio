package transit.train;
import java.util.ArrayList;
import transit.core.Stop;
import transit.bus.BusStop;
import transit.core.Route;
public class MetroRoute extends Route
{
	public ArrayList<Train> trains = new ArrayList<Train>();
	
	public ArrayList<Train> getTrains()
	{
		return trains;
	}
	public void setTrains(ArrayList<Train> t)
	{
		this.trains = t;
	}
	public MetroRoute(int routeNum, String routeDesc, MetroStation first)
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
		result = result + "Metro Route # " + this.getRouteNumber() +": " + this.getRouteDescription() + "<br/> <br/>Stations: <br/>";
		while(thisStop.getNextStop() != firstStop) 
		{
			result = result + thisStop.toString();
			thisStop = thisStop.getNextStop();
		}
		result = result + thisStop.toString() +  "<br/>" ;
		result = result + "Trains: <br/>";
		for(int i = 0; i < trains.size(); i++)
		{
			result = result + trains.get(i).toString();
		}
		return result;
	}
	public void addStop(String stopName, double x, double y)
	{
		Stop newStop = new MetroStation(stopName, x, y);
		newStop.setNextStop(firstStop);
		Stop thisStation = this.firstStop;
		while(thisStation.getNextStop() != firstStop) 
		{
			thisStation = thisStation.getNextStop();
		}
		thisStation.setNextStop(newStop);
	}
	public void addStop(MetroStation station)
	{
		Stop newStop = station;
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
		Train newTrain = new Train(name, speed, 3, this, this.getFirstStop());
		trains.add(newTrain);
	}
	  public void moveAll(int minutes)
	    {
		for(Train t: trains)
		{
		    t.move(minutes);
		}
	    }
}
