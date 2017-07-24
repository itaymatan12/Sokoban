package model.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

//class that is a type of level loader and she load an object file 
public class MyObjectLevelLoader  implements LevelLoader{

//function that load an object file that represent a level
	@Override
	public void loadLevel(InputStream L_file, Level l) throws IOException, ClassNotFoundException {
		ObjectInputStream oIn =new ObjectInputStream(L_file);//we creating a connection between the file to us so we will be able to read from the file
		l  = (Level) oIn.readObject();//reading from the file to a level
		oIn.close();//closing the file
	}

}