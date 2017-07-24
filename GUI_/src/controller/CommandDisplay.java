package controller;

import java.util.List;
import model.Model;
import view.Display;
import view.View;

//command that responsible to display the level to the user
public class CommandDisplay implements Command {

	//data members
	private View v;
	private Model m;
	private Display d;

	//default constructor
	public CommandDisplay()
	{
	}
	
	//constructor with values
	public CommandDisplay(Model m,View v) 
	{
		this.m = m;
		this.v =v;
	}
	
	//function that calling to function display the level
	@Override
	public void execute() 
	{	
		v.displayLevel(m.get_current_level(), d);
	}
	
	//function that dismantles the input to pieces and setting the to the class data members
	@Override
	public void setParams(List<Object> params)
	{
		this.m = (Model)params.get(0);
		this.v = (View)params.get(1);
		this.d = (Display)params.get(2);
	} 	
}