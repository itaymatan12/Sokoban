package model.data;


/**
 * 
 * class that defines an object in the game like (wall \ box \ Figure \ Transperent \ Destination) and more if we add to the game 
 *
 */
//class that defines an object in the game like (wall \ box \ Figure \ Transperent \ Destination) and more if we add to the game 
public abstract  class GameObject {
	//Coordinates of the position,data members
	
	//protected data members so we can get access to them in clases that extends this class
	protected int x;
	protected int y;
	
	//default constructor
	public GameObject() {
		// TODO Auto-generated constructor stub
	}
	
	//Getters and Setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}