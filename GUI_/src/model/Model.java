package model;

import java.io.InputStream;
import java.util.List;

import controller.Controller;
import controller.Exit;
import entities.User;
import entities.UserRecord;
import model.data.Level;
import model.data.LevelLoader;
import model.data.LevelSaver;

/**
 * the Model class of the Model layer in the MVC pattern that responsible
 * to the game logic 
 *
 */

public interface Model {
	
	//load level function
	public void loadlevel(LevelLoader l,InputStream f) throws Exception;
	
	//save level function
	public void savelevel(LevelSaver s,String path) throws Exception; 
	
	//exit level function
	public void exitlevel(Exit ex,Controller c);
	
	//move function
	public void  move(String direction) throws Exception;
	
	//clean level function
	public void CleanLevel();

	//function that returns the current level
	public Level get_current_level();
	
	//adding a level to our DB
	public void AddLevel(String LevelName);
	
	//adding a user to our DB
	public void AddUser(String UserId);
	
	//adding a user record to our DB
	public void AddUserRecord(String LevelName,String UserId,String Steps,String Time);
	
	public List<User> getUsers();
	
	public void sortLevelBySteps(String LevelName);

	public void sortLevelByTime(String LevelName);
	
	public List<UserRecord> getLevelScores();
	
	
	public List<UserRecord> getUserScores(String userid);
	
	public List<UserRecord> getUserScoresSortBySteps(String userid);
	
	public List<UserRecord> getUserScoresSortByTime(String userid);
	
	public List<UserRecord> getUserScoresSortByLexicalOrder(String userid);
	
} 