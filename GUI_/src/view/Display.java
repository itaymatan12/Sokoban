package view;

import model.data.Level;

//interface that define a type of display(2D,3D and more)
public interface Display {

	//every display object will implement this method differently
	public String display(Level l);
	
	
}