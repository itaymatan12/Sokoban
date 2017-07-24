package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity(name="UserRecords")
public class UserRecord implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5213045175743328891L;
	@EmbeddedId
	private UserRecordsKey key;
	@Column(name = "Steps")
	private int steps;
	@Column(name = "Timer")
	private int time;
	
	
	public UserRecord() 
	{
		key = new UserRecordsKey();
	}
	
	public UserRecord(String levelname, int userid, int steps,int time) 
	{
		key = new UserRecordsKey(userid,levelname);
		this.steps = steps;
		this.time = time;
	}

	public int getUserId()
	{
		return key.getUserId();
	}
	
	public void setUserId(int userid)
	{
		key.setUserId(userid);
	}
	
	public String getLevelName()
	{
		return key.getLevelName();
	}
	
	public void setLevelName(String levelname)
	{
		key.setLevelName(levelname);;
	}
	
	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}