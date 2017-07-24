package model.data;

public class RegularFigureCreator implements Creator {

	//function that get coordinates and a level and create new RegularFigure object that have the coordinates that are given in the level
	@Override
	public GameObject create(int x, int y,Level lvl) {
	
		RegularFigure r_figure=new RegularFigure(x,y);//creating new RegularFigure in the(x,y) coordinates
		lvl.getFigures().add(r_figure);//insert the new Figure to the ArrayList in the level that contains all the Figures
		return r_figure;
	}

}