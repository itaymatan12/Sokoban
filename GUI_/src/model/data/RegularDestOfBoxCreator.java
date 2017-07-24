package model.data;

import model.Utilities;

public class RegularDestOfBoxCreator implements Creator {

	//function that get coordinates and a level and create new RegularDestOfBoxCreator object that have the coordinates that are given in the level
	@Override
	public GameObject create(int x, int y,Level lvl)
	{
		Utilities u = new Utilities();		
		RegularDestofBox r_destination=new RegularDestofBox (x,y);//creating new RegulaDestofBox in the(x,y) coordinates
		lvl.getDestinations().add(r_destination);//insert the new destination to the ArrayList in the level that contains all the targets
		lvl.getTargets().put(u.conv_cord_tostring(x,y) ,r_destination);//also inserting the coordinates of the new destination to a hash map that contains all the destination in the level
		return r_destination;	
	}

}