import java.util.Random;
// CSCI 2113 Assignment 3 
// Name: Timothy Lytkine
// lytOffense.java
// Implements Offense.java interface
public class lytOffense implements Offense {
	PyroMove pyromaniac;
	boolean leftVar; boolean rightVar;
	boolean upVar; boolean downVar;
	int downCount; int upCount;
	int leftCount; int rightCount;
	/* getInitialPosition() method
	Returns instance of PyroMove with the initial position of (0, 0) */
	public PyroMove getInitialPosition (){
		pyromaniac = new PyroMove(0,0,false);
		boolean leftVar = false; 
		boolean rightVar = false;
		boolean upVar = false;
		boolean downVar = false;
		return pyromaniac;
	}

	/* move() method
	Returns instance of PyroMove with desired x and y values and 
	decision of whether to ignite tree or not.
	CANNOT MOVE AND IGNITE AT THE SAME TIME (igniting takes up turn) 
	Returns null if not possible to move*/
	public PyroMove move (){	
		int var1 = pyromaniac.getXPos();
		int var2 = pyromaniac.getYPos();
		if(igniteTree(var1,var2)){
			pyromaniac = new PyroMove(var1,var2,true);
			return pyromaniac;
		}
		else if(down() && (upVar==false)&&(downCount<8)){
			downVar = true; upVar = false; leftVar = false; rightVar = false;
			downCount++; rightCount=0; leftCount=0; 
			pyromaniac = new PyroMove(var1-1,var2,false);
			return pyromaniac;
		}
		else if(right() && (leftVar==false)&&(rightCount<8)){
			downVar = false; upVar = false; leftVar = false; rightVar = true;
			rightCount++; downCount=0; upCount=0;
			pyromaniac = new PyroMove(var1,var2+1,false);
			return pyromaniac;
		}
		else if(up() && (downVar==false) && (upCount<4)){
			downVar = false; upVar = true; leftVar = false; rightVar = false;
			upCount++; rightCount=0; leftCount=0; 
			pyromaniac = new PyroMove(var1+1,var2,false);
			return pyromaniac;
		}
		else if(left() && (rightVar==false) && (leftCount<4)){
			downVar = false; upVar = false; leftVar = true; rightVar = false;
			leftCount++; upCount=0; downCount=0;
			pyromaniac = new PyroMove(var1,var2-1,false);
			return pyromaniac;
		}
		else {
			return null;
		}
	}

	// If up or down true, changes y value
	public boolean up(){
		int x = pyromaniac.getXPos()+1;
		int y = pyromaniac.getYPos();
    	if((y>7)||(y<0)){
    		return false;
    	}
    	else if((x>7)||(x<0)){
    		return false;
    	}
    	int occupancyStatus = Game.getOccupancyStatus(x,y);
    	// Must not be firefighter or other pyro
    	if ((occupancyStatus==1) || (occupancyStatus==2)){
    		return false;
    	}
	    else {
    		return true;
    	}	
	}

	public boolean down(){
		int x = pyromaniac.getXPos()-1;
		int y = pyromaniac.getYPos();
    	if((y>7)||(y<0)){
    		return false;
    	}
    	else if((x>7)||(x<0)){
    		return false;
    	}
    	int occupancyStatus = Game.getOccupancyStatus(x,y);
    	// Must not be firefighter 
    	if ((occupancyStatus==1) || (occupancyStatus==2)){
    		return false;
    	}
	    else {
    		return true;
    	}
	}

	// If left or right true, changes x value 
	public boolean left(){
		int x = pyromaniac.getXPos();
		int y = pyromaniac.getYPos()-1;
    	if((y>7)||(y<0)){
    		return false;
    	}
    	else if((x>7)||(x<0)){
    		return false;
    	}
    	int occupancyStatus = Game.getOccupancyStatus(x,y);
    	// Must not be firefighter 
    	if ((occupancyStatus==1) || (occupancyStatus==2)){
    		return false;
    	}
	    else {
    		return true;
    	}
	}

	public boolean right(){
		int x = pyromaniac.getXPos();
		int y = pyromaniac.getYPos()+1;
    	if((y>7)||(y<0)){
    		return false;
    	}
    	else if((x>7)||(x<0)){
    		return false;
    	}
    	int occupancyStatus = Game.getOccupancyStatus(x,y);
    	// Must not be firefighter 
    	if ((occupancyStatus==1) || (occupancyStatus==2)){
    		return false;
    	}
	    else {
    		return true;
    	}
	}
	
	public boolean igniteTree(int x, int y){
		int status = Game.getTreeStatus(x,y);
		if(status==1){
			return true;
		}
		else {
			return false;
		}
	}
}



