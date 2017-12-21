public class FighterMove {
	
	private int x, y;
	private boolean douse;
	// Constructor 
	public FighterMove(int x, int y, boolean douse)
	{
		this.x = x;
		this.y = y;
		this.douse = douse;
	}
	// Gets value of boolean douse
	public boolean getDouse(){
		return douse;
	}
	public void setDouse(boolean x){
		this.douse = x;
	}
	// Gets x coordinate of Firefighter
	public int getXPos(){
		return this.x;
	}
	// Updates x coordinate of Firefighter
	public void setX(int var)
	{
		this.x = var;
	}
	// Gets y coordinate of Firefighter 
	public int getYPos(){
		return this.y;
	}
	// Updates y coordinate of Firefigther
	public void setY(int var)
	{
		this.y = var;
	}

}

