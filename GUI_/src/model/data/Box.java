package model.data;

import java.io.Serializable;

//class that defines a box
public abstract class Box extends GameObject implements Serializable {
	//We creates the Box class as an abstract one that extends GameObject because box is a game object
	//And to keep the open\close we want to create a box that have different abilities from the regular box
	//We will create another type of box class and implement it how we want

	protected int flag;
	
	private static final long serialVersionUID = 1L;//making box and all the classes the extends serializable
	
//default constructor
public Box() {
	// TODO Auto-generated constructor stub
}

//constructor with values
 public Box(int x ,int y)
 {
	 this.x=x;
	 this.y=y;	 
 }

public int getFlag() {
	return flag;
}

public void setFlag(int flag) {
	this.flag = flag;
}
 
}