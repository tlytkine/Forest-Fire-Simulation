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


// Ignites tree
void ignite(){
	tree.ignite();
	this.state = tree.status;
}
//
void update(){
	tree.update();
	this.state = tree.status;

	}
	}
	


