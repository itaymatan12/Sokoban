package model;

import java.util.regex.Pattern;


//Class the contains all the function that are actually are tools in the project like functions we will apply on strings and more
public class Utilities {

	
	
	//The function check if the path is correct
	 public boolean correctfilename(String pathfile)
	 { 
		 String regularExpression = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?";//Expression that describes a collection of Characters 

		    Pattern.compile(regularExpression);//compiling the regular expression to a pattern

		    boolean isMatched = Pattern.matches(regularExpression,pathfile);//check if the path that we entered is match to the pattern

		    return isMatched;
		 
	 }
	 
	 //function that return the file type
	 public String end_of_file(String pathfile) throws Exception 
	 {
		 String end_file =new String();//string that represent the type of the file
		 int indexofpoint=pathfile.indexOf('.');//we check where is the dot('.') in the path because after it is the type of the file
			if(indexofpoint<0)//if there is no dot in the path 
			throw new Exception("invalid path file");//throwing an exception that telling the path is incorrect
		 
			//if there is a dot we will run on the Characters that after it that represent the type of the fule and insert them to a String
		 for (int i = indexofpoint+1; i < pathfile.length(); i++) {
				end_file=end_file+pathfile.substring(i,i+1);
			}
		 return end_file;
	 }
	 
	 //function that taking a coordinate and making it to a String that represent the coordinate
	 public String conv_cord_tostring(int x,int y)
	 {		 		 
		 return x+ "," +y; 
	 }
}
