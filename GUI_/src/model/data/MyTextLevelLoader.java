package model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

//class that is a type of level loader and she load a text file 
public class MyTextLevelLoader implements LevelLoader {
	
	//Our Factory Pattern
	 private HashMap<Character, Creator> textload;//hash map that mapping Characters that represent types of GameObject to a creator that creating us this type of GameObject

	//default constructor
	public MyTextLevelLoader() {
		//HASH MAP that mapping strings to GameObject objects		
			textload = new HashMap<Character,Creator>();
			textload.put('#', new RegularWallCreator());
			textload.put('@', new RegularBoxCreator());
			textload.put('o', new RegularDestOfBoxCreator());	
			textload.put('A', new RegularFigureCreator());
			textload.put(' ', new RegularTransperentCreator());
	}

	//function that load a text file that represent a level
	@Override
	public void loadLevel(InputStream L_file,Level lvl) {
		
		Character c;
		int x=0,y=0;
				
		ArrayList<ArrayList<GameObject>> temp1 =new ArrayList<ArrayList<GameObject>>()  ;
		ArrayList<GameObject> temp2=new ArrayList<GameObject>();		
		BufferedReader br = new BufferedReader(new InputStreamReader(L_file));
		String line = new String();
					
		try {
			while((line=br.readLine())!=null)//reading from the buffer to a string
			{
				for(int i=0;i<line.length();i++)//running on the string
				{
					
					c = (Character) line.charAt(i);//getting the Character from the string
					Creator txtobjcreator = textload.get(c);//we create the appropriate GameObject to Character we got using the hash map
					if(txtobjcreator!=null)//checking if we actually got something from the hash map 
					{
						temp2.add(txtobjcreator.create(x, y, lvl));//we creating the appropriate GameObject in the Level and inserting him to ArrayList<GameObject>
					}

					++x;//Increasing the x coordinate in the level
				 }
				
				
				temp1.add(temp2);//INSERTING A ROW TO THE ArrayList<ArrayList<GameObject>>
				temp2=null;
				temp2=new ArrayList<GameObject>();
				y++;//Increasing the x coordinate in the level
				x=0;//Reset the x coordinate in the level	
			}
											
		
			lvl.setLevelim(temp1);//setting our level ArrayList<ArrayList<GameObject>> to the ArrayList<ArrayList<GameObject>> we filledle
			L_file.close();	//closing the file
	    } 
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}