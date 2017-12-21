class Cell {
// Holds information about an individual cell in the landscape
// 0 = no tree, 1 = type1 tree, 2 = type2 tree
private Tree tree;
// state of tree
int state;
// t is the tree type 
Cell(int t){
	state = t;
	if (state == 0){
		tree = new Tree(state);
	}
	if (state == 1){
		tree = new Tree(state);
	}
	else if (state == 2){
		tree = new Tree2(state);
	}
}
public String toString(){
	String cellString = state + " ";
	return cellString;
}
boolean getStump(){
	return tree.getStump();
}
int treeType(){
	return tree.getType();
}
boolean isTreeType(int x){
	boolean isX = false;
	if(x==treeType()){
		isX = true;
	} 
	return isX;
}


// Ignites tree
void ignite(){
	tree.ignite();
	this.state = tree.status;
	tree.setBurning(true);
}
//
void update(){
	tree.update();
	this.state = tree.status;
	if(tree.getType()==1){
		if(tree.status==2){
			tree.setBurning(true);
		}
	}
	else if(tree.getType()==2){
		if((tree.status==2) || (tree.status==1)){
			tree.setBurning(true);
		}
	}
	else if(tree.status ==0){
		tree.setStump(true);
		tree.setStanding(false);
		tree.setBurning(false);
	}
	}


boolean hasBurningTree(){
	boolean burning = tree.getBurning();
	return burning;
}
boolean hasLiveTree(){
	boolean live  = tree.getStanding();
	return live;
}

	}
	


