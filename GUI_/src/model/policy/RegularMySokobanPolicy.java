package model.policy;

import model.Utilities;
import model.data.Box;
import model.data.DestofBox;
import model.data.Level;
import model.data.RegularDestofBox;
import model.data.RegularTransperent;
import model.data.Transperent;
import model.data.Wall;

//class that define policy to character that  able to move just 1 step and able to push just 1 box
public class RegularMySokobanPolicy extends MySokobanPolicyDecleration 
{
	
	//default constructor
	public RegularMySokobanPolicy() {
		

	}

	//function that check if the character can move up
	@Override
	public boolean check_up(Level lvl, String direction)
	{
		//getting the character coordinates
		int x=0,y=0;
		x=lvl.getFigures().get(0).getX();
		y=lvl.getFigures().get(0).getY();
	
		
		if(lvl.getLevelim().get(y-1).get(x) instanceof Wall)
		{
			return false;	
		}
		
		else if ((lvl.getLevelim().get(y-1).get(x) instanceof Box)&&(lvl.getLevelim().get(y-2).get(x) instanceof Box))		
		{
			
			return false;
		}
		
		else if ((lvl.getLevelim().get(y-1).get(x) instanceof Box)&&(lvl.getLevelim().get(y-2).get(x) instanceof Wall))		
		{
		
			return false;
		}
				
		return true;			
	}

	//function that check if the character can move right
	@Override
	public boolean check_right(Level lvl, String direction)
	{
		int x=0,y=0;
		x=lvl.getFigures().get(0).getX();
		y=lvl.getFigures().get(0).getY();
		
		
	
		if(lvl.getLevelim().get(y).get(x+1) instanceof Wall)
		{
			return false;	
		}
		
		else if ((lvl.getLevelim().get(y).get(x+1) instanceof Box)&&(lvl.getLevelim().get(y).get(x+2) instanceof Box))		
		{
			return false;
		}
		
		else if ((lvl.getLevelim().get(y).get(x+1) instanceof Box)&&(lvl.getLevelim().get(y).get(x+2) instanceof Wall))		
		{
			return false;
		}
				
		return true;	
	}

	//function that check if the character can move left
	@Override
	public boolean check_left(Level lvl, String direction) 
	{
		int x=0,y=0;
		x=lvl.getFigures().get(0).getX();
		y=lvl.getFigures().get(0).getY();
		
		
		
		if(lvl.getLevelim().get(y).get(x-1) instanceof Wall)
		{
			return false;	
		}
		
		else if ((lvl.getLevelim().get(y).get(x-1) instanceof Box)&&(lvl.getLevelim().get(y).get(x-2) instanceof Box))		
		{
			return false;
		}
		
		else if ((lvl.getLevelim().get(y).get(x-1) instanceof Box)&&(lvl.getLevelim().get(y).get(x-2) instanceof Wall))		
		{
			return false;
		}
				
		return true;	
	}

	//function that check if the character can move down
	@Override
	public boolean check_down(Level lvl, String direction)
	{
		int x=0,y=0;	
		x=lvl.getFigures().get(0).getX();
		y=lvl.getFigures().get(0).getY();
		
		
		if(lvl.getLevelim().get(y+1).get(x) instanceof Wall)
		{
			return false;	
		}
		
		else if ((lvl.getLevelim().get(y+1).get(x) instanceof Box)&&(lvl.getLevelim().get(y+2).get(x) instanceof Box))		
		{
			return false;
		}
		
		else if ((lvl.getLevelim().get(y+1).get(x) instanceof Box)&&(lvl.getLevelim().get(y+2).get(x) instanceof Wall))		
		{
			return false;
		}
				
		return true;	
	}

	//function that moves the character right
	@Override
	public void move_right(Level lvl) 
	{		
		Utilities u = new Utilities();	
		int x=0,y=0;	
		x=lvl.getFigures().get(0).getX();
		y=lvl.getFigures().get(0).getY();
		
		if(this.check_right(lvl,"right"))
		{
			if((lvl.getLevelim().get(y).get(x+1) instanceof Box)&&(lvl.getLevelim().get(y).get(x+2) instanceof DestofBox))
			{
				if(lvl.getTargets().get(u.conv_cord_tostring(x+1, y)) instanceof DestofBox)
				{
					lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()-1);
				}
				
				lvl.getLevelim().get(y).set(x+2, new RegularTransperent());
				this.switchobj(lvl, x+1, y, x+2, y);
				this.switchobj(lvl, x, y, x+1, y);
				
				lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()+1);
				
				lvl.setSteps(lvl.getSteps()+1);//increasing the steps by 1 because we move
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			}
			
			else if((lvl.getLevelim().get(y).get(x+1) instanceof Box)&&(lvl.getLevelim().get(y).get(x+2) instanceof Transperent))
			{
				if(lvl.getTargets().get(u.conv_cord_tostring(x+1, y)) instanceof DestofBox)//the box got down from a target so we change the "box on target" flag
				{
					lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()-1);
				}
				
				this.switchobj(lvl, x+1, y, x+2, y);
				this.switchobj(lvl, x, y, x+1, y);
				
				lvl.setSteps(lvl.getSteps()+1);//increasing the steps by 1 because we move
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			}
			
			else if(lvl.getLevelim().get(y).get(x+1) instanceof Transperent)
			{
				this.switchobj(lvl, x, y, x+1, y);		
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			}
			
			else if(lvl.getLevelim().get(y).get(x+1) instanceof DestofBox)
			{
				lvl.getLevelim().get(y).set(x+1, new RegularTransperent());
				this.switchobj(lvl, x, y, x+1, y);
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			}			
		}		
	}

	//function that moves the character left
	@Override
	public void move_left(Level lvl)
	{
		Utilities u = new Utilities();	
		int x=0,y=0;	
		x=lvl.getFigures().get(0).getX();
		y=lvl.getFigures().get(0).getY();
		
		if(this.check_left(lvl,"left"))
		{
			if((lvl.getLevelim().get(y).get(x-1) instanceof Box)&&(lvl.getLevelim().get(y).get(x-2) instanceof DestofBox))
			{
				if(lvl.getTargets().get(u.conv_cord_tostring(x-1, y)) instanceof DestofBox)
				{
					lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()-1);
				}
				
				lvl.getLevelim().get(y).set(x-2, new RegularTransperent());
				this.switchobj(lvl, x-1, y, x-2, y);
				this.switchobj(lvl, x, y, x-1, y);
				
				lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()+1);
				
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			}
			
			else if((lvl.getLevelim().get(y).get(x-1) instanceof Box)&&(lvl.getLevelim().get(y).get(x-2) instanceof Transperent))
			{
				if(lvl.getTargets().get(u.conv_cord_tostring(x-1, y)) instanceof DestofBox)
				{
					lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()-1);
				}
				
				this.switchobj(lvl, x-1, y, x-2, y);
				this.switchobj(lvl, x, y, x-1, y);
				
				lvl.setSteps(lvl.getSteps()+1);

				
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			
			}
			
			else if(lvl.getLevelim().get(y).get(x-1) instanceof Transperent)
			{
				this.switchobj(lvl, x, y, x-1, y);	
				
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			}
			
			else if(lvl.getLevelim().get(y).get(x-1) instanceof DestofBox)
			{
				lvl.getLevelim().get(y).set(x-1, new RegularTransperent());
				this.switchobj(lvl, x, y, x-1, y);
				
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
				
			}			
		}	
		
		
	}

	//function that moves the character up
	@Override
	public void move_up(Level lvl)
	{
		Utilities u = new Utilities();	
		int x=0,y=0;	
		x=lvl.getFigures().get(0).getX();
		y=lvl.getFigures().get(0).getY();
		
		if(this.check_up(lvl,"up"))
		{
			if((lvl.getLevelim().get(y-1).get(x) instanceof Box)&&(lvl.getLevelim().get(y-2).get(x) instanceof DestofBox))
			{
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y-1)) instanceof DestofBox)
				{
					lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()-1);
				}
				
				lvl.getLevelim().get(y-2).set(x, new RegularTransperent());
				this.switchobj(lvl, x, y-1, x, y-2);
				this.switchobj(lvl, x, y, x, y-1);
				
				lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()+1);
				
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}	
			}
			
			else if((lvl.getLevelim().get(y-1).get(x) instanceof Box)&&(lvl.getLevelim().get(y-2).get(x) instanceof Transperent))
			{
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y-1)) instanceof DestofBox)
				{
					lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()-1);
				}
				
				this.switchobj(lvl, x, y-1, x, y-2);
				this.switchobj(lvl, x, y, x, y-1);
				
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
				
			}
			
			else if(lvl.getLevelim().get(y-1).get(x) instanceof Transperent)
			{
				this.switchobj(lvl, x, y, x, y-1);
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			}
			
			else if(lvl.getLevelim().get(y-1).get(x) instanceof DestofBox)
			{
				lvl.getLevelim().get(y-1).set(x, new RegularTransperent());
				this.switchobj(lvl, x, y, x, y-1);
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
				
			}	
		}
	
		
	}

	//function that moves the character down
	@Override
	public void move_down(Level lvl)
	{
		Utilities u = new Utilities();	
		int x=0,y=0;	
		x=lvl.getFigures().get(0).getX();
		y=lvl.getFigures().get(0).getY();
		
		if(this.check_down(lvl,"down"))
		{
			if((lvl.getLevelim().get(y+1).get(x) instanceof Box)&&(lvl.getLevelim().get(y+2).get(x) instanceof DestofBox))
			{
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y+1)) instanceof DestofBox)
				{
					lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()-1);
				}
			
				
				lvl.getLevelim().get(y+2).set(x, new RegularTransperent());
				this.switchobj(lvl, x, y+1, x, y+2);
				this.switchobj(lvl, x, y, x, y+1);
				
				lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()+1);
				
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
				
				
			}
			
			else if((lvl.getLevelim().get(y+1).get(x) instanceof Box)&&(lvl.getLevelim().get(y+2).get(x) instanceof Transperent))
			{
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y+1)) instanceof DestofBox)
				{
					lvl.setNum_box_on_targets(lvl.getNum_box_on_targets()-1);
				}
				
				this.switchobj(lvl, x, y+1, x, y+2);
				this.switchobj(lvl, x, y, x, y+1);
				
				((Box)(lvl.getLevelim().get(y+2).get(x))).setFlag(0);
				
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
				
			}
			
			else if(lvl.getLevelim().get(y+1).get(x) instanceof Transperent)
			{
				
				this.switchobj(lvl, x, y, x, y+1);	
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
			}
			
			else if(lvl.getLevelim().get(y+1).get(x) instanceof DestofBox)
			{
				lvl.getLevelim().get(y+1).set(x, new RegularTransperent());
				this.switchobj(lvl, x, y, x, y+1);
				lvl.setSteps(lvl.getSteps()+1);
				
				if(lvl.getTargets().get(u.conv_cord_tostring(x, y)) instanceof DestofBox)
				{
					lvl.getLevelim().get(y).set(x, new RegularDestofBox()); 	
				}
				
			}	
		}
	}

}


//Generic implementation to policy of character that going in straight line 	
 	/*public boolean bashingWallmove(Level lvl,String direction)
	{
		int x=0,y=0;
		
		
		
		Character c =lvl.getCharacters().get(0);
			
		x=lvl.getCharacters().get(0).getX();
		y=lvl.getCharacters().get(0).getY();
		
		
		direction=direction.toLowerCase();
		if(direction=="up")
		{
			for(int i=0;i<c.getAbletomove();i++)
			{
				if(lvl.getLevelim().get(y+1).get(x) instanceof Wall)
					return false;
				if(lvl.getLevelim().get(y+1).get(x) instanceof Box)
					num_box_in_direction++;
				y++;
			}
			
			
			return true;
		}
		else if(direction=="down")
		{
			for(int i=0;i<c.getAbletomove();i++)
			{
				if(lvl.getLevelim().get(y-1).get(x) instanceof Wall)
					return false;
				if(lvl.getLevelim().get(y+1).get(x) instanceof Box)
					num_box_in_direction++;
				y--;
			}
			return true;
			
		}
		else if(direction=="right")
		{
			for(int i=0;i<c.getAbletomove();i++)
			{
				if(lvl.getLevelim().get(y).get(x+1) instanceof Wall)
					return false;
				if(lvl.getLevelim().get(y+1).get(x) instanceof Box)
					num_box_in_direction++;
				x++;
			}
			return true;
			
		}
		else if(direction=="left")
		{	
			for(int i=0;i<c.getAbletomove();i++)
			{
				if(lvl.getLevelim().get(y).get(x-1) instanceof Wall)
					return false;
				if(lvl.getLevelim().get(y+1).get(x) instanceof Box)
					num_box_in_direction++;
				y--;
			}
			return true;
		}
				
		throw new Exception("invalid direction");		
	}
	
	
	public boolean pushingbox(Level lvl ,String direction) throws Exception
	{	
		int x=0,y=0;
			
		Character c =lvl.getCharacters().get(0);
			
		x=lvl.getCharacters().get(0).getX();
		y=lvl.getCharacters().get(0).getY();
		
		direction=direction.toLowerCase();
		
		if(!bashingWallmove(lvl, direction)|| num_box_in_direction>c.getBoxpush())
		{
			return false;
		}
		
		
		
		for(int i=0;i<c.getAbletomove();i++)
		{
			if( )
			
		}
		
		
		
		if(direction=="up")
		{
			
				if(!(lvl.getLevelim().get(y+c.getAbletomove()+num_box_in_direction).get(x) instanceof Box)||( lvl.getLevelim().get(y+c.getAbletomove()+num_box_in_direction).get(x) instanceof Wall))
				{
					return true;
				}
				else
					return false;
				
		}
		else if(direction=="down")
		{
			if(!(lvl.getLevelim().get(y-c.getAbletomove()-num_box_in_direction).get(x) instanceof Box)||( lvl.getLevelim().get(y+c.getAbletomove()-num_box_in_direction).get(x) instanceof Wall))
			{
				return true;
			}
			else
				return false;
			
		}
		else if(direction=="right")
		{
			if(!(lvl.getLevelim().get(y).get(x+c.getAbletomove()+num_box_in_direction) instanceof Box)||( lvl.getLevelim().get(y).get(x+c.getAbletomove()+num_box_in_direction) instanceof Wall))
			{
				return true;
			}
			else
				return false;
			
		}
		else if(direction=="left")
		{
			if(!(lvl.getLevelim().get(y).get(x-c.getAbletomove()+num_box_in_direction) instanceof Box)||( lvl.getLevelim().get(y).get(x+c.getAbletomove()-num_box_in_direction) instanceof Wall))
			{
				return true;
			}
			else
				return false;
			
		}
			
		
		
		throw new Exception("invalid direction");
		
		
		
	
	}

	
	public boolean pullbox(Level lvl ,String direction) throws Exception
	{
		return false;
	}
	
}*/