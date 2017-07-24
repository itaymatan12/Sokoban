package model.data;

//class that defines a type of Figure 
public class RegularFigure extends Figure {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//default constructor
	public RegularFigure() {
		// TODO Auto-generated constructor stub
	}
	
	////constructor with values that actually calls to the values constructor of class that he extends - Figure
	public RegularFigure(int x, int y) {
		 super(x, y);
		
	}
}