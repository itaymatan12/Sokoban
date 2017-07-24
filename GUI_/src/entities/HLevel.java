package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Levels")
public class HLevel implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7985855566946436883L;
	@Id
	private String LevelName;
	
	public HLevel() {
		// TODO Auto-generated constructor stub
	}

	public HLevel(String levelname) 
	{
		this.LevelName = levelname;
	}

	public String getLevelName() {
		return LevelName;
	}

	public void setLevelName(String levelName) {
		LevelName = levelName;
	}
	
	@Override
	public String toString() 
	{
	    return "Level [LevelName=" + LevelName + "]";
	}
	
}
