package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="Users")
public class User  implements Serializable
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4523846991655688748L;

	@Id
	private int UserId;
	
	@OneToMany
	@JoinColumn(name="UserId")
	private List<UserRecord> levels ;
	
	public User(int userid) 
	{
		this.UserId = userid;
		levels = new ArrayList<UserRecord>();
	}
	
	public User() 
	{
		levels = new ArrayList<UserRecord>();
	}
	
	
	
	@Override
	public String toString() 
	{
	    return "User [UserId=" + UserId + "]";
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public List<UserRecord> getLevels() {
		return levels;
	}

	public void setLevels(List<UserRecord> levels) {
		this.levels = levels;
	}
}