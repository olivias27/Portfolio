package transit.train;

import transit.core.Stop;

public class MetroStation extends Stop 
{
	public MetroStation(String name, double x, double y)
	{
		this.stopName = name;
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.stopNumber = Math.abs(name.hashCode());
		this.nextStop = this;
	}
	public void gainPassengers()
	{
		{
			int range = 5 + (int)(Math.random() * ((200 - 5) + 1));
			this.setPassengersWaiting(range + this.getPassengersWaiting());
		}
	}
}
