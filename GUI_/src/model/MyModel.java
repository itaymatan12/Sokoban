package model;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import controller.Controller;
import controller.Exit;
import controller.RegularExit;
import entities.DbManager;
import entities.HLevel;
import entities.User;
import entities.UserRecord;
import model.data.Level;
import model.data.LevelLoader;
import model.data.LevelSaver;
import model.policy.MySokobanPolicyDecleration;
import model.policy.RegularMySokobanPolicy;
import view.Display2Dimention;

public class MyModel extends Observable implements Model {
	
	//data members
	private Level lvl;
	private int count;
	private HashMap<String, Exit>exit;//hash map that mapping string that represent a exit type object to the appropriate exit object 
	private MySokobanPolicyDecleration policy;
	private DbManager manager;
	private List<User> UsersList;
	private List<UserRecord> levelscores;
	private List<UserRecord> userscores;
	
	//default constructor
	public MyModel()
	{
		this.levelscores = null;
		this.userscores = null;
		manager = new DbManager();
		count=0;
		exit = new HashMap<String, Exit>();
		this.lvl =new Level();	
		this.exit.put("regularexit", new RegularExit());
		this.policy = new RegularMySokobanPolicy();
		this.UsersList = manager.getAllUsers();
	}

	//constructor with values
	public MyModel(Level lvl,MySokobanPolicyDecleration policy)
	{
		exit = new HashMap<String, Exit>();
		this.lvl =lvl;
		this.policy = policy;
	    //Hash map that contains types of Exit's objects
		exit.put("regularexit", new RegularExit());
		this.UsersList = manager.getAllUsers();
	}

	//load function
	@Override
	public void loadlevel(LevelLoader l,InputStream f) throws Exception
	{
		//if we already loaded
		if(count>0)
		{
			//cleaning the level properties
			lvl.removeallfields();
		}
		
		l.loadLevel(f,lvl);//loading the level	
		this.setChanged();//setting the change
		LinkedList<Object> params = new LinkedList<Object>();
		
		//displaying the change to the user
		params.add("display");
		params.add(new Display2Dimention());//2 Dimension display
		this.notifyObservers(params);
		count++;
	}

	//save function
	@Override
	public void savelevel(LevelSaver s,String path) throws Exception
	{
		s.levelsave(path, lvl);//saving the level	
		this.setChanged();
		LinkedList<Object> params = new LinkedList<Object>();
		//displaying the change to the user
		params.add("display");
		params.add(new Display2Dimention());//2 Dimension display
		this.notifyObservers(params);
	}

	//exit function
	@Override
	public void exitlevel(Exit ex,Controller c)
	{  
		/*
		if(s!=null)
		{
			//s.stop();//stoping the server thread
		}
		*/
		c.Stop();//stoping the controller thread
		
		ex.exit();//exiting the game
	}

	//move function
	@Override
	public void move(String direction) throws Exception 
	{    
		String valid=new String();
		valid="";
		direction = direction.toLowerCase();
		//activating the moves function respectively to the direction
		if(direction.equals("up"))
		{
			valid="up";
			policy.move_up(lvl);
			
		}
		
		else if(direction.equals("right"))
		{
			valid="right";
			policy.move_right(lvl);	
			
			
		}
		
		else if(direction.equals("left"))
		{
			valid="left";
			policy.move_left(lvl);	
			
		}
		
		else if(direction.equals("down"))
		{
			valid="down";
			policy.move_down(lvl);
			
		}
		
		else if(valid.equals(""))
		{	throw new Exception("invalid direction");	}//if the user entered a 	
		
		
		this.setChanged();
		LinkedList<Object> params = new LinkedList<Object>();
		//displaying the change to the user
		params.add("display");
		params.add(new Display2Dimention());//2 Dimension display
		this.notifyObservers(params);
		

	}
	
	//Getters and Setters
	public Level getLvl() {
		return lvl;
	}

	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}

	public MySokobanPolicyDecleration getPolicy() {
		return policy;
	}

	public void setPolicy(MySokobanPolicyDecleration policy) {
		this.policy = policy;
	}

	@Override
	public Level get_current_level() {		
		return this.getLvl();
	}
    
	@Override
	public void CleanLevel() {
		this.lvl.removeallfields();
		
	}
	
    @Override
	public void AddLevel(String LevelName)
	{	
		if(!manager.LevelisExist((LevelName)))
		{
			manager.addLevel(new HLevel(LevelName));
		}	
	}

    @Override
	public void AddUser(String UserId)
	{
    	int userid = Integer.parseInt(UserId);
		manager.addUser(new User(userid));
		this.UsersList = manager.getAllUsers();
	}

	@Override
	public void AddUserRecord(String LevelName, String UserId, String Steps, String Time) 
	{
		int userid = Integer.parseInt(UserId);
		int step = Integer.parseInt(Steps);
		int time = Integer.parseInt(Time);
		
		manager.addRecord(new UserRecord(LevelName,userid,step,time));
	}

	@Override
	public List<User> getUsers() 
	{

		return this.UsersList;
	}

	@Override
	public void sortLevelBySteps(String LevelName)
	{
		levelscores = manager.sortLevelScoresBySteps(LevelName);
		
	}

	@Override
	public void sortLevelByTime(String LevelName) 
	{
		levelscores = manager.sortLevelScoresByTime(LevelName);
		
	}

	@Override
	public List<UserRecord> getLevelScores() 
	{
		return this.levelscores;
	}

	@Override
	public List<UserRecord> getUserScores(String userid) 
	{
		this.userscores = manager.getUserScores(userid);
		
		return this.userscores;

	}

	@Override
	public List<UserRecord> getUserScoresSortBySteps(String userid) 
	{
		this.userscores = manager.sorUserScoresBySteps(userid);
		
		return this.userscores;
	}

	@Override
	public List<UserRecord> getUserScoresSortByTime(String userid) 
	{
		this.userscores = manager.sorUserScoresByTime(Integer.parseInt(userid));
		
		return this.userscores;
	}

	@Override
	public List<UserRecord> getUserScoresSortByLexicalOrder(String userid) {
		this.userscores = manager.sorUserScoresByLexicalOrder(Integer.parseInt(userid));
		
		return this.userscores;
	}

}
