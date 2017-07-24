package model.data;

//class that defines a type of Transperent 
public class RegularTransperent extends Transperent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//constructor with values that actually calls to the values constructor of class that he extends - Transperent
	public RegularTransperent(int x, int y) {
		 super(x, y);
	}
	
	//default constructor
	public RegularTransperent() {
		// TODO Auto-generated constructor stub
	}

}