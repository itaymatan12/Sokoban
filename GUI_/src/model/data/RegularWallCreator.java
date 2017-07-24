package model.data;

//function that get coordinates and a level and create new RegularWall object that have the coordinates that are given in the level
public class RegularWallCreator implements Creator {

	@Override
	public GameObject create(int x, int y,Level lvl) {
		
		RegularWall r_wall=new RegularWall(x,y);//creating new RegularWall in the(x,y) coordinates
		lvl.getWalls().add(r_wall);//insert the new Wall to the ArrayList in the level that contains all the Walls
		return  r_wall;
	}

}