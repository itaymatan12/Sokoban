package model.data;

import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;

//class that is a type of level loader and she load a XML file 
public class MyXMLLevelLoader implements LevelLoader {

	//function that load a xml file that represent a level
	@Override
	public void loadLevel(InputStream L_file, Level l) throws IOException, ClassNotFoundException {
		XMLDecoder xml_level=new XMLDecoder(L_file);//we creating a connection between the file to us so we will be able to read from the file
		l = (Level)xml_level.readObject();//reading from the file to a level
		xml_level.close();//closing the file
	}

}