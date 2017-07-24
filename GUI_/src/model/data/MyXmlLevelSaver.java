package model.data;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;

//class that is a type of level saver and she save an XML file 
public class MyXmlLevelSaver implements LevelSaver 
{
	//function that save a level to XML file 
		@Override
		public void levelsave(String pathfile, Level lvl) throws IOException
		{		
				FileOutputStream temp = new FileOutputStream(pathfile);//creating access to the file
				
				XMLEncoder r = new XMLEncoder(temp);//creating access to the variable that giving us access to the file
				
				r.writeObject(lvl);//writing to the file
								
				r.close();//closing the file						
		}
}