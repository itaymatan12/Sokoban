package model.data;

import java.io.Serializable;

//class that defines a transperent
public abstract class Transperent extends GameObject implements Serializable {
	//We creates the transperent class as an abstract one that extends GameObject because transperent is a game object
	//And to keep the open\close we want to create a transperent that have different abilities from the regular transperent
	//We will create another type of transperent class and implement it how we want

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//making transperent and all the classes the extends serializable

	//constructor with values
	public Transperent(int x ,int y) {
		this.x=x;
		this.y=y;
	}
	
	//default constructor
	public Transperent() {
		// TODO Auto-generated constructor stub
	}
}