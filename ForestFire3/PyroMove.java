public class PyroMove {
	
	private int x, y;
	private boolean ignite;
	// Constructor 
	public PyroMove(int x, int y, boolean ignite)
	{
		this.x = x;
		this.y = y;
		this.ignite = ignite;
	}
	// Gets value of boolean ignite
	public boolean getIgnite(){
		return this.ignite;
	}
	// Gets x coordinate of pyromaniac
	public int getXPos(){
		return this.x;
	}
	// Sets x coordinate of pyromaniac 
	public void setX(int val)
	{
		this.x = val;
	}
	// Gets y coordinate of pyromaniac
	public int getYPos(){
		return this.y;
	}
	// Sets y coordinate of pyromaniac 
	public void setY(int val)
	{
		this.y = val;

	}
}