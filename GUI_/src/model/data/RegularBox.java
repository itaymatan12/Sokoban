package model.data;

//class that defines a type of Box  
public class RegularBox extends Box{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//default constructor
	public RegularBox() {
		// TODO Auto-generated constructor stub
	}
	
	//constructor with values that actually calls to the values constructor of class that he extends - Box
	 public RegularBox(int x ,int y)
	 {
		 super(x, y);
	 }
}