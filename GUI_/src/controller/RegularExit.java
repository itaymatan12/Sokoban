package controller;

//Regular exit class that just exiting the game
public class RegularExit implements Exit {

	//function that just exiting the game without more actions 
	
	public RegularExit() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void exit() {
		System.exit(0);
	}
}
