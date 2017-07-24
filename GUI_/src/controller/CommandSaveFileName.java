package controller;

import java.util.HashMap;
import java.util.List;

import model.Model;
import model.Utilities;
import model.data.LevelSaver;
import model.data.MyObjLevelSaver;
import model.data.MyTextLevelSaver;
import model.data.MyXmlLevelSaver;

//command that responsible to save a level in a file
public class CommandSaveFileName implements Command {
	
	//data members
    private HashMap<String, LevelSaver> filesaver;//hash map that mapping string that represent the type of the file to the appropriate saver to save this file 
    private Model m;
    private String pathfile;
    
    //default constructor
    public CommandSaveFileName() 
    {
    	this.pathfile = new String();
		//Hash map that contain creator level saver
		filesaver = new HashMap<String, LevelSaver>();
		filesaver.put("txt", new MyTextLevelSaver());
		filesaver.put("xml", new MyXmlLevelSaver());
		filesaver.put("obj",new MyObjLevelSaver()); 		
	}
    
    //constructor with values
    public CommandSaveFileName(Model m,String pathfile) {
		//Hash map that contain creator level saver
		filesaver = new HashMap<String, LevelSaver>();
		filesaver.put("txt", new MyTextLevelSaver());
		filesaver.put("xml", new MyXmlLevelSaver());
		filesaver.put("obj",new MyObjLevelSaver());	 	
    	this.m= m;
    	this.pathfile = pathfile;
    }
    
  //function that use another functions to see if the path is correct and to save the file 
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
		LevelSaver levelsaver =filesaver.get(endfile);//getting the from the hash map type of the level saver that we need to use
		m.savelevel(levelsaver,pathfile);//We send the appropriate variables it to the appropriate function in the model 
	}
	
	//function that dismantles the input to pieces and setting the to the class data members
	@Override
	public void setParams(List<Object> params)
	{
		this.m = (Model)params.get(0);
		this.pathfile = (String)params.get(1);
	}
	
	//Getters and Setters
	public HashMap<String, LevelSaver> getFilesaver() {
		return filesaver;
	}

	public void setFilesaver(HashMap<String, LevelSaver> filesaver) {
		this.filesaver = filesaver;
	}

	public String getPathfile() {
		return pathfile;
	}

	public void setPathfile(String pathfile) {
		this.pathfile = pathfile;
	}
}