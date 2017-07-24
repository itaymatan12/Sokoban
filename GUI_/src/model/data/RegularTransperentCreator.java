package model.data;

public class RegularTransperentCreator implements Creator {

	//function that get coordinates and a level and create new RegularTransperent object that have the coordinates that are given in the level
	@Override
	public GameObject create(int x, int y,Level lvl) {
		
		RegularTransperent r_transperent=new RegularTransperent(x,y);//creating new RegularTransperent in the(x,y) coordinates
		lvl.getTransperents().add(r_transperent);//insert the new Transperent to the ArrayList in the level that contains all the Transperents
		return  r_transperent;
	}

}