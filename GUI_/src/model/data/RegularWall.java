package model.data;

//class that defines a type of Wall
public class RegularWall extends Wall  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//constructor with values that actually calls to the values constructor of class that he extends - Wall
	public RegularWall(int x, int y) {
		 super(x, y);
		
	}
	
	//default constructor
	public RegularWall() {
		// TODO Auto-generated constructor stub
	}
}