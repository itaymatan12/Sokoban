package controller;

import java.io.FileInputStream;

import java.util.HashMap;
import java.util.List;

import model.Model;
import model.Utilities;
import model.data.LevelLoader;
import model.data.MyObjectLevelLoader;
import model.data.MyTextLevelLoader;
import model.data.MyXMLLevelLoader;

//command that responsible to load a file
public class CommandLoadFileName implements Command {
	
	//data members
	private HashMap<String, LevelLoader>filecreators;//hash map that mapping string that represent the type of the file to the appropriate loader to load this file
	private Model m;
	private String pathfile;
	
	//default constructor
	public CommandLoadFileName()
	{
		this.pathfile = new String();
		//Hash map that contains creator level loader
		filecreators = new HashMap<String, LevelLoader>();
		filecreators.put("txt", new MyTextLevelLoader());
		filecreators.put("xml", new MyXMLLevelLoader());
		filecreators.put("obj",new MyObjectLevelLoader());		
	}

	//constructor with values
	public CommandLoadFileName(Model m,String pathfile) {
		//Hash map that contains creator level loader
		filecreators = new HashMap<String, LevelLoader>();
		filecreators.put("txt", new MyTextLevelLoader());
		filecreators.put("xml", new MyXMLLevelLoader());
		filecreators.put("obj",new MyObjectLevelLoader());	
		this.m =m;
		this.pathfile = pathfile;
	}
	
	//function that use another functions to see if the path is correct and to load the file 
	@Override
	public void execute() throws Exception 
	{
		Utilities u = new Utilities();
			
		//check if the path of the file is correct
				if(!u.correctfilename(pathfile))
				{
					throw new Exception("invalid path");//throwing an exception when there is a problem with the path that user inserted		
				}
										
				String endfile=u.end_of_file(pathfile);//getting the type of the file (Text \ XML \Object)		
				FileInputStream lvlfile;
						
				try {
						lvlfile = new FileInputStream(pathfile);//getting access to the level from FileInputStream object
						LevelLoader lvl_loader =filecreators.get(endfile);//getting the from the hash map type of the level loader that we need to use
						m.loadlevel(lvl_loader,lvlfile);//We send the appropriate variables it to the appropriate function in the model 
					} 
				catch(ClassNotFoundException e)
				{
					System.out.println("There is a problem with the opening of the file");//throwing an exception when there is a problem to open the required file
					
				}	
				
	}
	
	//function that dismantles the input to pieces and setting the to the class data members
	@Override
	public void setParams(List<Object> params) 
	{
		this.m = (Model)params.get(0);
		this.pathfile = (String)params.get(1);			
	}
	
	//Getters and Setters
	public HashMap<String, LevelLoader> getFilecreators() {
		return filecreators;
	}

	public void setFilecreators(HashMap<String, LevelLoader> filecreators) {
		this.filecreators = filecreators;
	}

	public String getPathfile() {
		return pathfile;
	}

	public void setPathfile(String pathfile) {
		this.pathfile = pathfile;
	}
}
