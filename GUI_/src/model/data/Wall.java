package model.data;

import java.io.Serializable;

//class that defines a Wall
public abstract class Wall extends GameObject implements Serializable {
	
	//We creates the Wall class as an abstract one that extends GameObject because Wall is a game object
	//And to keep the open\close we want to create a Wall that have different abilities from the regular Wall
	//We will create another type of Wall class and implement it how we want
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//making wall and all the classes the extends serializable

	//constructor with values
public Wall(int x, int y) {
	this.x=x;
	this.y=y;
	
}

//default constructor
public Wall(){}
}