package transit.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;

import transit.bus.BusRoute;
import transit.bus.BusStop;
import transit.core.DataManager;
import transit.core.Route;
import transit.core.Stop;
import transit.core.Vehicle;
import transit.train.MetroRoute;
import transit.train.MetroStation;


public class GUI2 extends JFrame
{
	private int totalWidth =400;
	private int totalHeight = 400;
	private int vertOffSet = 100;
	private int horiOffSet = 100;
	private int numCol = 20;
	private int numRow = 20;
	private int width = totalWidth / numCol;
	private int height= totalHeight / numRow;
	private int circleWidth = 10;

	public GUI2()
	{
//		super();
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
//        this.setTitle("City Transit Runner");
//        this.pack();
//        this.setBounds(100, 100, 600, 600);
//        //this.setSize(800, 800);
//        JPanel panel = new JPanel(new GridLayout(4,5,10,10));
       
        Object[] options = {"Add Bus Route","Add Metro Route", "Add Stop", "Add Station", "Add Driver to Bus Route",  "Add Driver to Train Route", "Save Simulation", "Load Simulation", "Generate Random City", "Adjust Speed"};
        int k = JOptionPane.showOptionDialog(null,"What would you like to do?" ,
        "Transit Manager",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[2]);
        
	}
	public void addBusRoute()
	{
		//add bus route
    	String routeDesc = JOptionPane.showInputDialog("What do you want your route description to be?");
    	BusRoute rt = DataManager.randomBusRoute(routeDesc);
    	for (int i = 0; i <= 3; i++)
    	{
    		BusStop bt = DataManager.randomBusStop();
    		rt.addStop(bt);
    	}
    
    	DataManager.routes.add(rt);
	}
	public void addMetroRoute()
	 {
    	//add metro route
    	String routeDesc = JOptionPane.showInputDialog("What do you want your route description to be?");
    	MetroRoute mrt = DataManager.randomMetroRoute(routeDesc);
    	for (int i = 0; i <= 3; i++)
    	{
    		MetroStation mbt = DataManager.randomMetroStation();
    		mrt.addStop(mbt);
    	}
    	DataManager.routes.add(mrt);
    }
	public void addStop()
	 {
    	//add stop
    	int routeNum = Integer.parseInt(JOptionPane.showInputDialog("What route number do you want to add the stop to?"));
    	String stopName = JOptionPane.showInputDialog("What's the stop name?");
    	double xcord = Double.parseDouble(JOptionPane.showInputDialog("What's the x-coordinate?"));
    	double ycord = Double.parseDouble(JOptionPane.showInputDialog("What's the y-coordinate?"));
    	for (int i = 0; i < DataManager.routes.size() ; i++)
    	{
    		if (routeNum == DataManager.routes.get(i).getRouteNumber())
    		{
    			DataManager.routes.get(i).addStop(stopName, xcord, ycord);
    			break;
    		}
    	}
    }
	public void addStation()
	  {
    	//add station to an existing route
    	int routeNum = Integer.parseInt(JOptionPane.showInputDialog("What route number do you want to add the station to?"));
    	String stationName = JOptionPane.showInputDialog("What's the station name?");
    	double xcord = Double.parseDouble(JOptionPane.showInputDialog("What's the x-coordinate? (Must be between -10 and 10)"));
    	double ycord = Double.parseDouble(JOptionPane.showInputDialog("What's the y-coordinate? (Must be between -10 and 10)" ));
    	for (int i = 0; i < DataManager.routes.size() ; i++)
    	{
    		if (routeNum == DataManager.routes.get(i).getRouteNumber())
    		{
    			DataManager.routes.get(i).addStop(stationName, xcord, ycord);
    			break;
    		}
    	}
    }
	public void addBusDriver()
	{
    	//Add Driver to an existing bus route
    	int routeNum = Integer.parseInt(JOptionPane.showInputDialog("What bus route number do you want to add the station to?"));
    	String driverName = JOptionPane.showInputDialog("What do you want to name your driver?");
    	for (int i = 0; i < DataManager.routes.size() ; i++)
    	{
    		if (routeNum == DataManager.routes.get(i).getRouteNumber())
    		{
    			DataManager.routes.get(i).addDriver(driverName, DataManager.randomSpeed());
    			break;
    		}
    	}
    	
    	
    }
	public void addTrainDriver()
	{
    	//Add Driver to an existing route
    	int routeNum = Integer.parseInt(JOptionPane.showInputDialog("What train route number do you want to add the station to?"));
    	String driverName = JOptionPane.showInputDialog("What do you want to name your driver?");
    	for (int i = 0; i < DataManager.routes.size() ; i++)
    	{
    		if (routeNum == DataManager.routes.get(i).getRouteNumber())
    		{
    			DataManager.routes.get(i).addDriver(driverName, DataManager.randomSpeed());
    			break;
    		}
    	}
    		
    }
	public void generateRandomCity()
	{
		
	}
	public void adjustSpeed()
	{
		
	}
	public void loadSimulation()
	{
		
	}
	public void saveSimulation()
	{
		
	}

}


