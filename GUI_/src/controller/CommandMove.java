package controller;

import java.util.List;

import model.Model;

//command that responsible to move the Figure in the Level
public class CommandMove implements Command {
	
	//data members
	private Model m;
	private String direction;
	
	//default constructor
	public CommandMove() {
		this.direction = new String();
	}
	
	//constructor with values
	public CommandMove(Model m,String direction) 
	{
		this.m =m;
		this.direction = direction;
	}

	//function that calls to functions that moves the character in the level 
	@Override
	public void execute() throws Exception 
	{
		m.move(direction);//We send the appropriate variables it to the appropriate function in the model 
		
		/*
		//activating the moves function respectively to the direction
		if(this.direction.equals("up"))
		{
			policy.move_up(lvl);
			return;
		}
		
		else if(this.direction.equals("right"))
		{
			policy.move_right(lvl);	
			return;
		}
		
		else if(this.direction.equals("left"))
		{
			policy.move_left(lvl);	
			return;
		}
		
		else if(this.direction.equals("down"))
		{
			policy.move_down(lvl);
			return;
		}
		throw new Exception("invalid direction");	//if the user entered a direction that doesn't exist we will throw this Exception
	    */
	}

	//function that dismantles the input to pieces and setting the to the class data members
	@Override
	public void setParams(List<Object> params) 
	{
		this.m = (Model)params.get(0);
		this.direction = (String)params.get(1);		
		
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}