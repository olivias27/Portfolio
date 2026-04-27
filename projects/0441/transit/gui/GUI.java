package transit.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import transit.bus.Bus;
import transit.bus.BusRoute;
import transit.bus.BusStop;
import transit.core.DataManager;
import transit.core.Route;
import transit.core.Stop;
import transit.core.Vehicle;
import transit.train.MetroRoute;
import transit.train.MetroStation;
import transit.train.Train;


public class GUI extends JFrame 
{
	private int gridWidth =400;
	private int gridHeight = 400;
	private int vertOffSet = 50;
	private int horiOffSet = 100;
	private int numCol = 20;
	private int numRow = 20;
	private int width = gridWidth / numCol;
	private int height= gridHeight / numRow;
	private int frameRate = 1;
	JFrame f;
	private int cbSelect;

	public GUI()
	{
		
		this.getContentPane().setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new FlowLayout());
        panel.setSize(650, 300);
        panel.setLocation(0, 500);
        JButton buttonKey = new JButton("Route Key");
        buttonKey.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                   routeKey();
            }
        });
        panel.add(buttonKey);
        JButton button = new JButton("Add Bus Route");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                   addBusRoute();
            }
        });
        panel.add(button);
        JButton button1 = new JButton("Add Metro Route");
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                   addMetroRoute();
            }
        });
        panel.add(button1);
        JButton button2 = new JButton("Add Bus Stop");
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                   addStop();
            }
        });
        panel.add(button2);
        JButton button3 = new JButton("Add Train Station");
        button3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                   addStation();
            }
        });
        panel.add(button3);
        JButton button4 = new JButton("Add Bus Driver");
        button4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                   addBusDriver();
            }
        });
        panel.add(button4);
        JButton button5 = new JButton("Add Train Conductor");
        button5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                   addTrainDriver();
            }
        });
        panel.add(button5);
        JButton button6 = new JButton("Generate Random City");
        button6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                   DataManager.randomCity();
            }
        });
        panel.add(button6);
        	 JButton button7 = new JButton("Speed Up");
             button7.addActionListener(new ActionListener()
             {
                 public void actionPerformed(ActionEvent e) 
                 {
                        speedUp();
                 }
             });
             panel.add(button7);
             JButton button8 = new JButton("Slow Down");
             button8.addActionListener(new ActionListener()
             {
                 public void actionPerformed(ActionEvent e) 
                 {
                        slowDown();
                 }
             });
             panel.add(button8);
         JButton button10 = new JButton("Get Vehicle Info");
         button10.addActionListener(new ActionListener()
         {
             public void actionPerformed(ActionEvent e) 
             {
                    metroInfo();
             }
         });
        panel.add(button10);
        this.add(panel);
        this.pack();
        this.setBounds(100, 100, 650, 2000);
        this.setVisible(true);
        loop();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		paintGrid(g);
		for(Route route : DataManager.getRoutes())
		{
			paintRoute(route, g);
		}
		
	}
	
	public void paintGrid(Graphics g)
	{
		//draw grid
		for(int row=0; row < numRow; row++)
		{
			for(int col=0 ; col < numCol ; col++)
		    {
		        try 
		        {
		        	g.drawRect((row * width) + horiOffSet ,(col * height) + vertOffSet , width, height);
		        }
		        catch (NullPointerException e)
		        {
		        	e.getMessage();
		        	e.printStackTrace();
		        }
		    }
		}
		//draw origin
		this.drawCircle(g, 0.0 , 0.0 , 6, Color.black);
	}
	public void drawCircle(Graphics g, double x, double y, int circleWidth, Color c)
	{
		
		g.setColor(c);
		g.fillOval(translateX(x)-(circleWidth/2), translateY(y)-(circleWidth/2), circleWidth, circleWidth);
	}
	public void drawString(Graphics g, String s, double x, double y)
	{
		
		g.drawString(s, translateX(x), translateY(y));
	}
	
	public void paintRoute(Route route, Graphics g)
	{
		Stop currentStop = route.getFirstStop();
		while(currentStop.getNextStop() != route.getFirstStop())
		{
			if (route instanceof BusRoute)
			{
				paintStop(currentStop, g);
			}
			else 
			{
				paintStation(currentStop, g);
			}
			currentStop = currentStop.getNextStop();
		}
		if (route instanceof BusRoute)
		{
			paintStop(currentStop, g);
			BusRoute br = (BusRoute) route;
			for(int i = 0; i < br.getBuses().size() ; i++)
			{
				paintVehicle(br.getBuses().get(i) , g);
			}
		}
		else 
		{
			paintStation(currentStop, g);
			MetroRoute mr = (MetroRoute) route;
			for(int i = 0; i < mr.getTrains().size() ; i++)
			{
				paintVehicle(mr.getTrains().get(i) , g);
			}
		}
		
	}
	public void paintStop(Stop stop, Graphics g)
	{
		this.drawCircle(g, stop.getXCoordinate(), stop.getYCoordinate(), 10, Color.BLUE);
		this.drawString(g, stop.getStopName(), stop.getXCoordinate()-0.5, stop.getYCoordinate()+0.5);
	}
	
	public void paintStation(Stop stop, Graphics g)
	{
		this.drawCircle(g, stop.getXCoordinate(), stop.getYCoordinate(), 10, Color.RED);
		this.drawString(g, stop.getStopName(), stop.getXCoordinate()-0.5, stop.getYCoordinate()+0.5);
	}
	public void paintVehicle(Vehicle vehicle, Graphics g)
	{
		if (vehicle instanceof Bus)
		{
			this.drawCircle(g, vehicle.getXCoordinate(), vehicle.getYCoordinate(), 10 , Color.DARK_GRAY);
			this.drawString(g, vehicle.getDriverName() ,vehicle.getXCoordinate()-0.5, vehicle.getYCoordinate()- 1);
		}
		else
		{
			this.drawCircle(g, vehicle.getXCoordinate(), vehicle.getYCoordinate(), 10 , Color.MAGENTA);
			this.drawString(g, vehicle.getDriverName() ,vehicle.getXCoordinate()-0.5, vehicle.getYCoordinate()- 1);
		}
	}
	public void update()
	{
	DataManager.update(frameRate);	
	}
	public void loop()
	{
		while(this.getGraphics() != null)
        {
	        this.update();
	        this.paint(this.getGraphics());
	        try 
	        {
				TimeUnit.MILLISECONDS.sleep(1000/ frameRate);
			} 
	        catch (InterruptedException e1) 
	        {
				e1.printStackTrace();
			}
        }	
	}
	public int originXCord()
	{
		return (gridWidth/2) + horiOffSet;
	}
	public int originYCord()
	{
		return (gridHeight/2) + vertOffSet;
	}
	public int translateX(double x)
	{
		return (int) (originXCord() + (width * x));
	}
	public int translateY(double y)
	{
		return (int) (originYCord() - (width * y));
	}
	public void addBusRoute()
	{
		//add bus route
	int routeNum = Integer.parseInt(JOptionPane.showInputDialog("What do you want your route number to be?"));
    	String routeDesc = JOptionPane.showInputDialog("What do you want your route description to be?");
    	BusRoute rt = DataManager.randomBusRoute(routeNum, routeDesc);
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
	int routeNum = Integer.parseInt(JOptionPane.showInputDialog("What do you want your route number to be?"));
    	String routeDesc = JOptionPane.showInputDialog("What do you want your route description to be?");
    	MetroRoute mrt = DataManager.randomMetroRoute(routeNum, routeDesc);
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
		ArrayList<String> tempArray =  new ArrayList<String>();
		for (Route route : DataManager.getRoutes())
		{
			if(route instanceof BusRoute)
			{
				tempArray.add("" + route.getRouteNumber());
			}
		}
		JComboBox cb = new JComboBox(tempArray.toArray());
		cb.setSelectedIndex(0);
		int selection = JOptionPane.showOptionDialog(null, cb, "Select Route", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		cbSelect = Integer.parseInt((String)cb.getSelectedItem());
    	String stopName = JOptionPane.showInputDialog("What's the stop name?");
    	double xcord = Double.parseDouble(JOptionPane.showInputDialog("What's the x-coordinate?"));
    	double ycord = Double.parseDouble(JOptionPane.showInputDialog("What's the y-coordinate?"));
    	if( -10 > xcord || xcord > 10)
    	{
    		JOptionPane.showMessageDialog(null,"X-Coordinate not valid! Try again!","ERROR",JOptionPane.ERROR_MESSAGE);
    		xcord = Double.parseDouble(JOptionPane.showInputDialog("What's the x-coordinate?"));
    	}
    	else if ( -10 > ycord || ycord > 10)
    	{
    		JOptionPane.showMessageDialog(null,"Y-Coordinate not valid! Try again!","ERROR",JOptionPane.ERROR_MESSAGE);
    		ycord = Double.parseDouble(JOptionPane.showInputDialog("What's the x-coordinate?"));
    	}
    	else 
    	{
    		for (int i = 0; i < DataManager.routes.size() ; i++)
        	{
        		if (this.cbSelect == DataManager.routes.get(i).getRouteNumber())
        		{
        			DataManager.routes.get(i).addStop(stopName, xcord, ycord);
        			break;
        		}
        	}
    		DataManager.routes.get(this.cbSelect).addStop(stopName, xcord, ycord);
    	}
    	
    }
    
	public void addStation()
	  {
    	//add station to an existing route
		ArrayList<String> tempArray =  new ArrayList<String>();
		for (Route route : DataManager.getRoutes())
		{
			if(route instanceof MetroRoute)
			{
				tempArray.add("" + route.getRouteNumber());
			}
		}
		JComboBox cb = new JComboBox(tempArray.toArray());
		cb.setSelectedIndex(0);
		int selection = JOptionPane.showOptionDialog(null, cb, "Select Route", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		cbSelect = Integer.parseInt((String)cb.getSelectedItem());
    	String stationName = JOptionPane.showInputDialog("What's the station name?");
    	double xcord = Double.parseDouble(JOptionPane.showInputDialog("What's the x-coordinate? (Must be between -10 and 10)"));
    	double ycord = Double.parseDouble(JOptionPane.showInputDialog("What's the y-coordinate? (Must be between -10 and 10)" ));
    	if( -10 > xcord || xcord > 10)
    	{
    		JOptionPane.showMessageDialog(null,"X-Coordinate not valid! Try again!","ERROR",JOptionPane.ERROR_MESSAGE);
    		xcord = Double.parseDouble(JOptionPane.showInputDialog("What's the x-coordinate?"));
    	}
    	else if ( -10 > ycord || ycord > 10)
    	{
    		JOptionPane.showMessageDialog(null,"Y-Coordinate not valid! Try again!","ERROR",JOptionPane.ERROR_MESSAGE);
    		ycord = Double.parseDouble(JOptionPane.showInputDialog("What's the x-coordinate?"));
    	}
    	else 
    	{
    		for (int i = 0; i < DataManager.routes.size() ; i++)
        	{
        		if (this.cbSelect == DataManager.routes.get(i).getRouteNumber())
        		{
        			DataManager.routes.get(i).addStop(stationName, xcord, ycord);
        			break;
        		}
        	}
    	}
    	DataManager.routes.get(this.cbSelect).addStop(stationName, xcord, ycord);
    }
	public void addBusDriver()
	{
    	//Add Driver to an existing bus route
		ArrayList<String> tempArray =  new ArrayList<String>();
		for (Route route : DataManager.getRoutes())
		{
			if(route instanceof BusRoute)
			{
				tempArray.add("" + route.getRouteNumber());
			}
		}
		JComboBox cb = new JComboBox(tempArray.toArray());
		cb.setSelectedIndex(0);
		int selection = JOptionPane.showOptionDialog(null, cb, "Select Route", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		cbSelect = Integer.parseInt((String)cb.getSelectedItem());
    	String driverName = JOptionPane.showInputDialog("What do you want to name your driver?");
    	for (int i = 0; i < DataManager.routes.size() ; i++)
    	{
    		if (this.cbSelect == DataManager.routes.get(i).getRouteNumber())
    		{
    			DataManager.routes.get(i).addDriver(driverName, DataManager.randomSpeed());
    			break;
    		}
    	}
    	
    	DataManager.routes.get(this.cbSelect).addDriver(driverName, DataManager.randomSpeed());
    }
	public void addTrainDriver()
	{
    	//Add Driver to an existing route
		ArrayList<String> tempArray =  new ArrayList<String>();
		for (Route route : DataManager.getRoutes())
		{
			if(route instanceof MetroRoute)
			{
				tempArray.add("" + route.getRouteNumber());
			}
		}
		JComboBox cb = new JComboBox(tempArray.toArray());
		cb.setSelectedIndex(0);
		int selection = JOptionPane.showOptionDialog(null, cb, "Select Route", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		cbSelect = Integer.parseInt((String)cb.getSelectedItem());
    	String driverName = JOptionPane.showInputDialog("What do you want to name your conductor?");
    	for (int i = 0; i < DataManager.routes.size() ; i++)
    	{
    		if (this.cbSelect == DataManager.routes.get(i).getRouteNumber())
    		{
    			DataManager.routes.get(i).addDriver(driverName, DataManager.randomSpeed());
    			break;
    		}
    	}
    	DataManager.routes.get(this.cbSelect).addDriver(driverName, DataManager.randomSpeed());
    		
    }
	public void speedUp()
	{
		frameRate = frameRate + 1;
	}
	public void slowDown()
	{
		if (frameRate > 1)
		{
			frameRate = frameRate - 1;
		}
		else
		{
			frameRate = 1;
		}
	
	}
	public void routeKey()
	{
		f = new JFrame();
		JOptionPane.showMessageDialog(f, "Blue dot is a bus stop, red is a train station, dark gray is a bus, and pink is a train. The black dot at the center is the origin");
	}
	public void metroInfo()
	{
	for (Route route : DataManager.getRoutes())
	     {
	    JPanel l = new JPanel();
       	JLabel tempLabel = new JLabel();
       		if(route instanceof BusRoute)
       		{
       			tempLabel.setText( "<html>" + route.toString() + "</html>");
       			
       		}
       		else if (route instanceof MetroRoute)
       		{
       			tempLabel.setText( "<html>" + route.toString() + "</html>");
       		}
		JOptionPane.showMessageDialog(f,tempLabel);
	     }
	}
	
}



