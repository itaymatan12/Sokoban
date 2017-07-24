package model.data;

public class RegularBoxCreator implements Creator {

	//function that get coordinates and a level and create new RegularBox object that have the coordinates that are given in the level
	@Override
	public GameObject create(int x, int y,Level lvl)
	{
		RegularBox r_box=new RegularBox(x,y);//creating new RegularBox in the(x,y) coordinates
		lvl.getBoxes().add(r_box);//insert the new box to the ArrayList in the level that contains all the boxes
		return  r_box;
	}

}