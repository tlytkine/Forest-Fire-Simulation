// Accounts for burn rule for type1 tree
// For type1 tree:
// 0 means no tree
// 1 means tree and standing not burning (after initialization)
// 2 means tree and standing but burning (after ignite method)

class Tree {
	boolean burning = false;
	boolean standing = false;
	boolean stump = false;
	int type = 1;
	int status = 0;
	Tree(int x){
		this.status = x;
		init(status);
	}
	public boolean getStump(){
		boolean stumpCheck = false;
		if(this.status==0){
			stumpCheck = true;
		}
		return stumpCheck;
	}
	public boolean getBurning(){
		return this.burning;
	}
	public void setBurning(boolean x){
		burning = x;
	}
	public void setStump(boolean x){
		stump = x;
	}
	public void setStanding(boolean x){
		standing = x;
	}
	public boolean getStanding(){
		return this.standing;
	}
	public int getStatus(){
		return this.status;
	}
	public String toString(){
		return "boolean burning is " + this.burning + "," + "boolean standing is " + 
		this.standing + "," + "boolean stump is " + this.stump + "," + "int status is " + this.status;
	}
	public int getType(){
		return 1;
	}
	void init(int x){
		this.status = x;
		if(status==1){ 
			this.standing = true;
			this.burning = false;
			this.stump = false;
		}

		if(status == 2){
			this.standing = true;
			this.burning = true;
			this.stump = false;
			
		}
	}
	void ignite() {
		if(this.status == 1){
			this.status = 2;
			this.standing = true;
			this.burning = true;
			this.stump = false;
		}
	}
	void update() { 
		if(this.status==2){
			this.status = 0;
			this.standing = false;
			this.burning = false;
			this.stump = true;


		}
	}
}








