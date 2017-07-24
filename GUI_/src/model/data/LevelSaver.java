package model.data;

import java.io.IOException;

//interface that represent a level saver 
public interface LevelSaver {
	//function that saves level properties in a file
  public  void levelsave(String pathfile,Level lvl) throws IOException;
}