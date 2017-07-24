package controller;

import java.util.List;

/**
 * interface that defines command that doing something like to move the Figure or to load a level an more...
 *
 */

//interface that defines command
public interface Command {
	

//function that all the commands that implement command interface have	
	public abstract void execute() throws Exception;

	public void setParams(List<Object> params);

}
