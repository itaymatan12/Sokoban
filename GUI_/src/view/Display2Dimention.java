package view;

import java.util.ArrayList;
import java.util.HashMap;

import model.data.GameObject;
import model.data.Level;

public class Display2Dimention implements Display {
	
	private HashMap<String,String> l_display;//hash map that mapping string that represent GameObject to string that represent the character that represent the object
	
	//default constructor
	public Display2Dimention() 
	{
		l_display = new HashMap<String,String>();
		l_display.put("RegularTransperent"," ");
		l_display.put("RegularBox","@");
		l_display.put("RegularDestofBox","o");	
		l_display.put("RegularFigure","A");
		l_display.put("RegularWall","#");
	}

	//function that sending a string that contains the level representation
	@Override
	public String display(Level l) 
	{
		int i=0,j=0;
		String txt  = new String();
		ArrayList<ArrayList<GameObject>> temp1 =new ArrayList<ArrayList<GameObject>>()  ;
		ArrayList<GameObject> temp2=new ArrayList<GameObject>();
		temp1=l.getLevelim();
		String class_name = new String();
				
			for(;i<temp1.size();i++)
			{
				temp2=temp1.get(i);
				for (; j < temp2.size(); j++) {
					
					class_name = temp2.get(j).getClass().getSimpleName();
					txt=txt + l_display.get(class_name);
						
				}
				txt= txt + "\n";
				j=0;
			}	
		
			
			return txt;//returning a string that contains the level representation
	}
}
