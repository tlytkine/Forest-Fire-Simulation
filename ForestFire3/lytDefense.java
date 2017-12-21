import java.util.Random;
// CSCI 2113 Assignment 3 
// Name: Timothy Lytkine
// lytDefense.java
// Implements Defense.java interface
public class lytDefense implements Defense {
    private FighterMove Fighter1;
	private FighterMove Fighter2;
	private FighterMove arr[] = new FighterMove[2];
	/* getInitialPosition(int i) method 
	Returns instance of FighterMove with initial position values
	If i is 1, return instance of FighterMove at (6,7)
	If i is 2, return instance of FigtherMove at (7,7) */

	public FighterMove getInitialPosition (int i){
		Fighter1 = new FighterMove(6,7,false);
		arr[0] = Fighter1;
		Fighter2 = new FighterMove(7,7,false);
		arr[1] = Fighter2;
		if(i==1) 
			return Fighter1;
		else if(i==2) 
			return Fighter2;
		else return null;
	}

    public FighterMove move (int x){
    	int i = x-1;
    	update();
    	boolean d = douseTree(arr[i].getXPos(),arr[i].getYPos());
    	if(d==true){
    		return new FighterMove(arr[i].getXPos(),arr[i].getYPos(),true);
    	}
		else if(left(i)){
    		return new FighterMove(arr[i].getXPos(),arr[i].getYPos()-1,false);
    	}
    	else if(up(i)){
    		return new FighterMove(arr[i].getXPos()+1,arr[i].getYPos(),false);
    	}
    	else if(down(i)){
    		return new FighterMove(arr[i].getXPos()-1,arr[i].getYPos(),false);
    	}
    	else if(right(i)){
    		return new FighterMove(arr[i].getXPos(),arr[i].getYPos()+1,false);
    	}
    	else {
    		return null;
    	}
    }

    public void update() {
    	for (int x=0; x<2; x++) {
      		for (int i=0; i<8; i++) {
        	   for (int j=0; j<8; j++) {
          		    if (Game.getOccupancyStatus(i,j)==x+1){
            		  arr[x].setX(i); arr[x].setY(j);
            		  break;
          		    }   
        	   }  
      	    }
        }
  	}

    // Checks whether to douse tree or not 
    public boolean douseTree(int x, int y){
    	int status = Game.getTreeStatus(x,y);
    	// If -4, 0, or 3 then douseVal is false
    	// DOUSED_TREE = -4 NO_TREE = 0 BURNT TREE=-3 
    	if ((status == -4)||(status==0)||(status==-3)||(status==1)){
    		return false;
    	}
		else {
			return true;
		}
    }

    public boolean up(int i){
    	int x = arr[i].getXPos()+1;
    	int y = arr[i].getYPos();
    	if((y>7)||(y<0)){
    		return false;
    	}
    	else if((x>7)||(x<0)){
    		return false;
    	}
    	int occupancyStatus = Game.getOccupancyStatus(x,y);
    	if((occupancyStatus==1)||(occupancyStatus==2)){
    		return false;
    	}
    	else {
    		return true;
    	}
   	}

   	public boolean down(int i){
    	int x = arr[i].getXPos()-1;
    	int y = arr[i].getYPos();
    	if((y>7)||(y<0)){
    		return false;
    	}
    	else if((x>7)||(x<0)){
    		return false;
    	}
    	int occupancyStatus = Game.getOccupancyStatus(x,y);
    	if((occupancyStatus==1)||(occupancyStatus==2)){
    		return false;
    	}
    	else {
    		return true;
    	}
   	}

   	public boolean left(int i){
    	int x = arr[i].getXPos();
    	int y = arr[i].getYPos()-1;
    	if((y>7)||(y<0)){
    		return false;
    	}
    	else if((x>7)||(x<0)){
    		return false;
    	}
    	int occupancyStatus = Game.getOccupancyStatus(x,y);
    	 if((occupancyStatus==1)||(occupancyStatus==2)){
    		return false;
    	}
    	else {
    		return true;
    	}
   	}

   	public boolean right(int i){
    	int x = arr[i].getXPos();
    	int y = arr[i].getYPos()+1;
    	if((y>7)||(y<0)){
    		return false;
    	}
    	else if((x>7)||(x<0)){
    		return false;
    	}
    	int occupancyStatus = Game.getOccupancyStatus(x,y);
    	if((occupancyStatus==1)||(occupancyStatus==2)){
    		return false;
    	}
    	else {
    		return true;
    	}
    }
}

