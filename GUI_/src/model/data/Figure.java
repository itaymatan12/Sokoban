package model.data;

import java.io.Serializable;

//class that defines a Figure
public  abstract class Figure extends GameObject implements Serializable {
	//We creates the Figure class as an abstract one that extends GameObject because figure is a game object
	//And to keep the open\close we want to create a figure that have different abilities from the regular figure
	//We will create another type of figure class and implement the move functions how we want
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//making figure and all the classes the extends serializable

	
	//default constructor
	public Figure() {
		this.x=0;
		this.y=0;

	}
	
	//constructor with values
	 public Figure(int x ,int y)
	 {
		 this.x=x;
		 this.y=y;
	 }	
}