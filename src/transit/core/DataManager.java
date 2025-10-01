package transit.core;

import java.util.ArrayList;
import java.util.Random;

import transit.bus.Bus;
import transit.bus.BusRoute;
import transit.bus.BusStop;
import transit.train.MetroRoute;
import transit.train.MetroStation;
import transit.train.Train;

public class DataManager 
{
	public static ArrayList<Route> routes = new ArrayList<Route>();
	
	public static ArrayList<Route> getRoutes()
	{
		return routes;
	}
	public void setRoutes(ArrayList<Route> rt)
	{
		routes = rt;
	}
	public static void update(int frameRate)
	{
		int i = 0;
		for(Route route: DataManager.getRoutes())	
        {
        	route.moveAll(1);
        	route.gainPassengersAll();
        }
	}
	public static void clear()
	{
			routes = new ArrayList<Route>();
	}
	public static void randomCity()
	{
		clear();
		for(int i = 1; i <= 3; i++)
		{
			
			BusRoute route = randomBusRoute();
			for (int j = 1; j <= 3; j++)
			{
				route.addStop(randomBusStop());
				route.addDriver(randomDriverName(), randomSpeed());
			}
			MetroRoute route2 = randomMetroRoute();
			for (int j = 1; j <= 3; j++)
			{
				route2.addStop(randomMetroStation());
				route2.addDriver(randomDriverName(), randomSpeed());
			}
			routes.add(route);
			routes.add(route2);
			
			
		}
	}
	public static String randomDriverName()
	{
		String[] driverNames = new String[]
				{
						"Nissim","Lillith","Sacha","Cain",
						"Sierra","Bruno","Kaitlin","Yetta",
						"Flynn","Teagan","Ariel","Kiayada",
						"Baker","Barbara","Whilemina","Lacota", "Howard",
						"Aiko","Jana","Isabelle","Colton", "Phoebe", "Olivia", "Holden",
						"Emma", "Mason", "Allie", "Katrina", "Aidan", "Carla", "Matthew", "Paige", "Jordan", "Sara", "Bri", "Emmaleigh", "Angel", "Bea", "Leonard"
				};
		Random f = new Random();
		int randomName = f.nextInt(driverNames.length - 1);
		return driverNames[randomName];
	}	
	public static String randomRouteDesc()
	{
		String[] randomRouteDesc = new String[]
				{
						"A route that serves downtown",
						"A route connecting downtown to the eastern suburbs.",
						"A route through the Montana Mountains",
						"A route from the coast to downtown",
						"A route from the airport to center city",
						"A route that connects two suburbs "
						
					};
		Random f = new Random();
		int randomName = f.nextInt(randomRouteDesc.length - 1);
		return randomRouteDesc[randomName];
	}
	public static double randomSpeed()
	{
		Random g = new Random();
		double randomSpeed = 10 + (55 - (10)) * g.nextDouble();
		return randomSpeed;
	}
	public static Bus randomBus(BusRoute rt)
	{
		return new Bus(randomDriverName(), randomSpeed(), rt, rt.getFirstStop());
	}
	public static Train randomTrain(MetroRoute rt)
	{
		Random h = new Random();
		int randomCars = h.nextInt(100);
		return new Train(randomDriverName(), randomSpeed(), randomCars, rt, rt.getFirstStop());
		
	}
	public static BusRoute randomBusRoute()
	{
		Random h = new Random();
		int randomRouteNum = h.nextInt(100);
		
		return new BusRoute(randomRouteNum, randomRouteDesc(), randomBusStop());
		
	}
	public static BusRoute randomBusRoute(int routeNum, String desc)
	{
		Random h = new Random();
		int randomRouteNum = h.nextInt(100);
		
		return new BusRoute(routeNum, desc, randomBusStop());
		
	}
	public static MetroRoute randomMetroRoute()
	{
		Random h = new Random();
		int randomRouteNum = h.nextInt(100);
		
		return new MetroRoute(randomRouteNum, randomRouteDesc(), randomMetroStation());
		
	}
	public static MetroRoute randomMetroRoute(int routeNum, String desc)
	{
		Random h = new Random();
		int randomRouteNum = h.nextInt(100);
		
		return new MetroRoute(routeNum, desc, randomMetroStation());
		
	}
	public static BusStop randomBusStop()
	{
		String[] stopNames = new String[]
				{
						"Ponca",
						"Pendleton",
						"Dahlonega",
						"Berwick",
						"Rome",
						"Tulsa",
						"Vallejo",
						"Nanticoke",
						"Moline",
						"Vail",
						"Pharr",
						"Mentor",
						"Waco",
						"Ojai",
						"Woods",
						"Victorville",
						"Manhattan",
						"Carthage",
						"Manassas",
						"Socorro",
						"Winter",
						"Ann",
						"Smith",
						"York",
						"Yankton",
						"Westminster",
						"Canon",
						"Norman",
						"Sallisaw",
						"Grapevine",
						"Boulder",
						"Mt.Washington",
						"Lahaina",
						"Adrian",
						"Wheeling",
						"Scottsdale",
						"Miami",
						"Marion",
						"Sierra",
						"Paducah",
						"Fort Myers",
						"Heber",
						"Destination",
						"Bloomsburg",
						"Lincoln",
						"Cheboygan",
						"Bedford",
						"Dalton",
						"Tucumcari", 
						"Radiator Springs",
						"Rosewood",
						"Maplewood",
						"Las Vegas"
				};
			Random l = new Random();
			double randomX = -10 + (10 - (-10)) * l.nextDouble();
			Random k = new Random();
			double randomY = -10 + (10 - (-10)) * k.nextDouble();
			Random m = new Random();
			int randomName = m.nextInt(stopNames.length - 1);
			return new BusStop(stopNames[randomName], Math. round(randomX), Math.round(randomY)) ;	
	}
	public static MetroStation randomMetroStation()
	{
		String[] stationNames = new String[]
			{
					"Sandy",
					"North",
					"Parkersburg",
					"Western",
					"Easton",
					"Tiffin",
					"Tombstone",
					"Easton",
					"Berea",
					"Pekin",
					"Worthington",
					"Cape",
					"Bristol",
					"Gallup",
					"River",
					"Concord",
					"Chambersburg",
					"Leadville",
					"Moorhead",
					"Peterborough",
					"Newport",
					"Durango",
					"Meriden",
					"Williamstown",
					"Ouray",
					"Raton",
					"Brookings",
					"Kearney",
					"Costa",
					"Winchester",
					"Gary",
					"Laredo",
					"Branson",
					"Bikini Bottom",
					"Alliance",
					"Shelbyville",
					"Longview",
					"Inglewood",
					"Chicago",
					"Newtown",
					"Hannibal",
					"East Hillside",
					"Bozeman",
					"Kankakee",
					"Darien",
					"Fernandina",
					"Laguna",
					"Mystic",
					"Newhaven",
					"Layton",
					"Gardiner",
					"Avella",
					"Skippack",
					"Uniontown"
			};
		Random l = new Random();
		double randomX = -10 + (10 - (-10)) * l.nextDouble();
		Random k = new Random();
		double randomY = -10 + (10 - (-10)) * k.nextDouble();
		Random m = new Random();
		int randomName = m.nextInt(stationNames.length - 1);
		return new MetroStation(stationNames[randomName], randomX, randomY) ;
	
	}

}
