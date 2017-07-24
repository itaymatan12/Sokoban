package model.data;

import java.io.IOException;
import java.io.InputStream;

//interface that represent a level loader 
public interface LevelLoader {
	/**
	 *1. The separation between the data representer(Level to the data creator(LevelLoader) is that the data creator(LevelLoader)
	 *creates the data from the source and and returned him in data representer(Level) 
	 *2.The separation allows the open\close principle because if we want to change the data representer(Level)we don't do it from 
	 *him, we do it from the data creator(LevelLoader) so the principle is able
	 *3.The separation allows Liskov Substitution principle because if we want to use a base class like LevelLoader we can use it  
	 *as a MyTextLevelLoader or XMLLevelLader and more because they implements LevelLoader function
	 *4.We chose InputStream and not FileInputStream because LevelLoader is a interface of classes that loads level and there can be 
	 *classes that loads level from the Internet or from another source so we need to be able to load a level not just from a file.
	 */
	//function that loading file that represent a level
	public void loadLevel(InputStream L_file,Level l) throws IOException, ClassNotFoundException ;
}