package model.policy;

import model.data.GameObject;
import model.data.Level;

/**
 * policy class that represents the game rules and checks if the rules allowing the user movements 
 *
 */

//class that defines a policy
public abstract class MySokobanPolicyDecleration {
	
	//function that checks if the Figure can move up
	public abstract boolean check_up(Level lvl,String direction);
	
	//function that checks if the Figure can move right
	public abstract boolean check_right(Level lvl ,String direction);
	
	//function that checks if the Figure can move left
	public abstract boolean check_left(Level lvl ,String direction);
	
	//function that checks if the Figure can move down
	public abstract boolean check_down(Level lvl ,String direction);
	
	//function that moving the Figure right
	public abstract void move_right(Level lvl);
	
	//function that moving the Figure left
	public abstract void move_left(Level lvl);
	
	//function that moving the Figure up
	public abstract void move_up(Level lvl);
	
	//function that moving the Figure down
	public abstract void move_down(Level lvl);
	
	//function that switching to objects places in the level
	public void switchobj(Level lvl,int x1,int y1, int x2, int y2)
	{
		//switching to object in the ArrayList<ArrayList<GameObject>> 
		GameObject objtemp = lvl.getLevelim().get(y2).get(x2);
		lvl.getLevelim().get(y2).set(x2, lvl.getLevelim().get(y1).get(x1));
		lvl.getLevelim().get(y1).set(x1, objtemp);
		
		//updating the GameObject object coordinates after the switching
		lvl.getLevelim().get(y2).get(x2).setX(x2);
		lvl.getLevelim().get(y2).get(x2).setY(y2);
		lvl.getLevelim().get(y1).get(x1).setX(x1);
		lvl.getLevelim().get(y1).get(x1).setY(y1);
	}
	
}