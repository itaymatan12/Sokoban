package entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserRecordsKey implements Serializable  {
	
	
	private int UserId;
	private String LevelName;
	
	public UserRecordsKey() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserRecordsKey(int userid, String levelname) 
	{
		this.setUserId(userid);
		this.setLevelName(levelname);
	}


	public int getUserId() {
		return UserId;
	}


	public void setUserId(int userId) {
		UserId = userId;
	}


	public String getLevelName() {
		return LevelName;
	}


	public void setLevelName(String levelName) {
		LevelName = levelName;
	}
	
	@Override
	public int hashCode() {
		return 31 * LevelName.hashCode() + UserId;
	}
	
	@Override
	public boolean equals(Object obj) {
		UserRecordsKey key = (UserRecordsKey)obj;
		
		return this.LevelName == key.LevelName && this.UserId == key.UserId;
	}

}
