class Tree2 extends Tree{

	Tree2(int x){
		super(x);
	}

	int type = 2;

	@Override
	void ignite() {
		if(this.status==2){
			this.status = 1;
			this.standing = true;
			this.burning = true;
			this.stump = false;
		}
	}
	@Override
	void update() {
		if(this.status == 1){
			this.status = 0;
			this.standing = false;
			this.burning = false;
			this.stump = true;
		}
	}
	@Override
	public boolean getStump(){
		boolean s = false;
		if(this.status==0){
			s = true;
		}
	return s;
	}
	@Override
	public int getType(){
		return 2;
	}

}
