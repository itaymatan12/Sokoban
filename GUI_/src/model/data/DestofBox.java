package model.data;

import java.io.Serializable;

//class that defines a destination
public abstract class DestofBox extends GameObject implements Serializable{
	//We creates the DestofBox class as an abstract one that extends GameObject because destination is a game object
	//And to keep the open\close we want to create a destination that have different abilities from the regular deatinations
	//We will create another type of destination class and implement it how we want
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//making DestofBox  and all the classes the extends serializable

	//default constructor
	public DestofBox() {
		// TODO Auto-generated constructor stub
	}
	
	//constructor with values
	public DestofBox(int x ,int y) {
		// TODO Auto-generated constructor stub
	this.x=x;
	this.y=y;
	}
}