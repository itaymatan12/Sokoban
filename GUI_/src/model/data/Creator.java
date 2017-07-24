package model.data;

//part from our FactoryPattern in MyTextLevelLoader
//interface that defines a creator that create a GameObject


/**
 * 
 * part from our FactoryPattern in MyTextLevelLoader
 * interface that defines a creator that create a GameObject
 *
 */
public interface Creator {
	
	//Deceleration on a function that every class that will implement Creator will get and implements it differently
	public GameObject create(int x,int y,Level lvl);

}