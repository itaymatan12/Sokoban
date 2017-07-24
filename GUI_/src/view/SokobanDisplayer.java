package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.data.Box;
import model.data.GameObject;
import model.data.Transperent;

/**
 * our canvas that we painting on him our state of the level over and over
 * so this class allows to present to the user the level in a physical way 
 *so we can say that this class is part of the View layer in the MVC pattern and responsible to the UI(User Interface)
 */

public class SokobanDisplayer extends Canvas {
	//data members
	
	private ArrayList<ArrayList<GameObject>> levelim ;//ArrayList of ArrayList of GameObject that describes the level and give us like an image of the level
	
	//properties that we will get the images from them
	private StringProperty wallfilename;
	private StringProperty boxfilename;
	private StringProperty destinationfilename;
	private StringProperty transperentfilename;
	private StringProperty figurefilename;
	private StringProperty boxtargetfilename;
	
	private int count =0;
	
	private HashMap<String, Image> objtoim;
	
	//Images
	Image wall;
	Image box;
	Image destination;
	Image transperent;
	Image figure;
	Image boxtarget;
	
	GraphicsContext gc;
	
	
	
	//default constructor
	public SokobanDisplayer() 
	{
		wallfilename = new SimpleStringProperty();
		boxfilename = new SimpleStringProperty();
		destinationfilename = new SimpleStringProperty();
		transperentfilename = new SimpleStringProperty();
		figurefilename = new SimpleStringProperty();
		boxtargetfilename = new SimpleStringProperty();

		this.levelim = null;
		gc = getGraphicsContext2D();
			
		objtoim =new HashMap<String ,Image>();
	}
	
	//initializing the images from the properties
	 public void initimage()
	   {
		   
		    try{
		    
		    	//setting the images from the StringProperties that getting the path to the image in the fxml
				wall = new Image(new FileInputStream(wallfilename.get()));
				box = new Image(new FileInputStream(boxfilename.get()));
				destination = new Image(new FileInputStream(destinationfilename.get()));
				transperent = new Image(new FileInputStream(transperentfilename.get()));
				figure = new Image(new FileInputStream(figurefilename.get()));
				boxtarget = new Image(new FileInputStream(boxtargetfilename.get()));
			
				objtoim.put("Wall", wall);
				objtoim.put("Box", box);
				objtoim.put("DestofBox", destination);
				objtoim.put("Figure", figure);
				objtoim.put("Transperent", transperent);
				
				

				 /*
				 text.setY(50);
				 text.setFont(new Font(20));

				 text.getTransforms().add(new Rotate(30, 50, 30));
				*/
				
				
			    }
			    catch(FileNotFoundException EX)
			    {
			    	System.out.println("the image in the resources unopen");
			    }
	   }
   
	 //function that will "paint" on the canvas every move 
	public void redraw()
	{
	
		
		//checking that we have something in the level
		if(levelim != null)
		{
			//getting the biggest row in the level
			int max = levelim.get(0).size();
			
			for (int i = 1; i < levelim.size(); i++)
			{
				max = Math.max(max, levelim.get(i).size());
			}
			
			double W = getWidth();//canvas width
			double H = getHeight();//canvas height
			double w = W/max;//square in the canvas width
			double h = H/levelim.size();//square in the canvas height
			
			//every time we "erasing" the canvas so we will be able to "paint" again
			gc.clearRect(0, 0, W, H);
			
			//moving on the level 
			for(int i=0;i<levelim.size();i++)
			{
				int count_trans =0;
				
				for(int j=0;j<levelim.get(i).size();j++)
				{
					if(levelim.get(i).get(j)!= null)
					{
						
						//if there is transperent objects in the begining of the row
						if(levelim.get(i).get(j) instanceof Transperent && count_trans ==0)
						{
							gc.setFill(Color.ALICEBLUE);
							gc.fillRect(j*w, i*h, w, h);
							continue;
						}
						
						//checking if there is an object that we aren't familiar with
						else if(this.objtoim.get(levelim.get(i).get(j).getClass().getSuperclass().getSimpleName()) == null)
						{
							count_trans=1;
							gc.drawImage(transperent,j*w, i*h, w, h);
							continue;
						}
						
						//if there is a valid object
						else if(this.objtoim.get(levelim.get(i).get(j).getClass().getSuperclass().getSimpleName()) != null)
						{
							
							//if the box is on a target
							if(levelim.get(i).get(j) instanceof Box && ((Box)(levelim.get(i).get(j))).getFlag() == 1)
							{
									gc.drawImage(boxtarget,j*w, i*h, w, h);
									count_trans=1;
							}
			
							else
							{			
								//if this is a usual object
								gc.drawImage(this.objtoim.get(levelim.get(i).get(j).getClass().getSuperclass().getSimpleName()),j*w, i*h, w, h);	
								count_trans =1;
							}
	
						}
						
					}
								
				}
				
			}
			
		}
		
	}
	
	
	//Getters and Setters
	public ArrayList<ArrayList<GameObject>> getLevelim() {
		
		return levelim;
	}

	public void setLevelim(ArrayList<ArrayList<GameObject>> levelim) {
		this.levelim = levelim;
		if(count ==0)
		{
			this.initimage();
		}
		this.redraw();
		
	}



	public String getWallfilename() {
		return wallfilename.get();
	}



	public void setWallfilename(String wallfilename) {
		this.wallfilename.set(wallfilename);
	}



	public String getBoxfilename() {
		return boxfilename.get();
	}



	public void setBoxfilename(String boxfilename) {
		this.boxfilename.set(boxfilename);
	}



	public String getDestinationfilename() {
		return destinationfilename.get();
	}



	public void setDestinationfilename(String destinationfilename) {
		this.destinationfilename.set(destinationfilename);
	}



	public String getTransperentfilename() {
		return transperentfilename.get();
	}



	public void setTransperentfilename(String transperentfilename) {
		this.transperentfilename.set(transperentfilename);
	}



	public String getFigurefilename() {
		return figurefilename.get();
	}



	public void setFigurefilename(String figurefilename) {
		this.figurefilename.set(figurefilename);
	}
	
	public String getBoxtargetfilename() {
		return boxtargetfilename.get();
	}
	
	public void setBoxtargetfilename(String boxtargetfilename) {
		this.boxtargetfilename.set(boxtargetfilename);
	}
}