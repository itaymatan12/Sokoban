package model.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//class that is a type of level saver and she save an Text file 
public class MyTextLevelSaver implements LevelSaver{

	private HashMap<String, Character> savers;//has map that mapping string that represent GameObject type to Character that represent this object 
	
	//default constructor
	public MyTextLevelSaver() {
		//HASH MAP that mapping strings to Characters		
		savers=new HashMap<String, Character>();
		savers.put( "RegularFigure",'A');
		savers.put("RegularWall",'#');
		savers.put("RegularBox",'@');
		savers.put("RegularDestofBox",'o');
		savers.put("RegularTransperent",' ');
	}
	
	//function that save a level to text file 
	@Override
	public void  levelsave(String pathfile,Level lvl) {
		
		int i=0,j=0;
		ArrayList<ArrayList<GameObject>> temp1 =new ArrayList<ArrayList<GameObject>>()  ;
		ArrayList<GameObject> temp2=new ArrayList<GameObject>();
		temp1=lvl.getLevelim();//inserting to ArrayList<ArrayList<GameObject>> the level "image"
	
		FileOutputStream lvlrepresent=null;
		
		try {
			
			 lvlrepresent= new FileOutputStream(pathfile);//creating access to the file
			if(temp1.size()==0)
			{
				throw new Exception("level is empty");//if the level is empty we don't save and throe this exception
			}
			for(;i<temp1.size();i++)//running on the ArrayList<ArrayList<GameObject>> that represent the level "image"
			{
				
				temp2=temp1.get(i);//inserting a ArrayList from ArrayList<ArrayList<GameObject>> to ArrayList<GameObject>
				for (; j < temp2.size(); j++)//running on ArrayList<GameObject>
				{				
					lvlrepresent.write((int)savers.get(temp2.get(j).getClass().getSimpleName()));//we insert to the hash map the object and write to the file the received Character			
				}			
				j=0;//resting the //running on ArrayList<GameObject> because we want to entered to it another place from the ArrayList<ArrayList<GameObject>> and to run on it too
				lvlrepresent.write('\n');//row down
			}					
		} 
		
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {		
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
}