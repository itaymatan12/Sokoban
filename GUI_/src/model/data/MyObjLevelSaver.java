package model.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//class that is a type of level saver and she save an object file 
public class MyObjLevelSaver implements LevelSaver  {

	//function that save a level to object file 
	@Override
	public void levelsave(String pathfile, Level lvl) {
		
		try {			   
			   FileOutputStream temp = new FileOutputStream(pathfile);//creating access to the file
			   
			   ObjectOutputStream o =new ObjectOutputStream(temp);//creating access to the variable that giving us access to the file
			   
			   o.writeObject(lvl);//writing to the file
			      			   
			   o.close();//closing the file
			  
		}
		catch (FileNotFoundException e1) {		
			e1.printStackTrace();			
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}		
	}
}