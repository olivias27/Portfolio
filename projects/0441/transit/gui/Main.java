package transit.gui;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import java.util.Random;
import transit.bus.Bus;
import transit.bus.BusRoute;
import transit.bus.BusStop;
import transit.train.MetroRoute;
import transit.train.MetroStation;
import transit.train.Train;
import transit.core.Route;
import transit.core.DataManager;

public class Main 
{

	public static void main(String[] args) 
	{
	
	MetroRoute mr = instantiateMetroSystem();
	BusRoute br = instantiateBusSystem();
	DataManager.routes.add(mr);
	DataManager.routes.add(br);
	GUI j = new GUI();
	}
	
	 public static MetroRoute instantiateMetroSystem()
	    {
		MetroRoute mr = DataManager.randomMetroRoute();
			for(int i = 1; i < 5; i++)
			{
				mr.addStop(DataManager.randomMetroStation());	
			}
		
		mr.addDriver(DataManager.randomDriverName(), DataManager.randomSpeed());
		return mr;
	    }
	 
	    public static BusRoute instantiateBusSystem()
	    {
	    	
		BusRoute br = DataManager.randomBusRoute();
		
		for(int i = 1; i < 5; i++)
		{
			br.addStop(DataManager.randomBusStop());
		}
		br.addDriver(DataManager.randomDriverName(), DataManager.randomSpeed());
		return br;
	    }
	    

}
