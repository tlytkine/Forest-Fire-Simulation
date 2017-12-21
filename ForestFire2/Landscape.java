// imports needed libararies 
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// extends JFrame and implements ActionListener for buttons and MouseListener to 
// update tree when clicked
class Landscape extends JFrame implements ActionListener, MouseListener{
	// 2D array of Cell 
	private Cell[][] cells;
    // variable used when completing run's
    private int numTreesToIgnite;
    // ignite locations 
    private int[] igniteX;
    private int[] igniteY;
    private int landscapeSize;
    // Panel that stores grid of trees 
    private ForestPanel forestPanel;
	// 2 parameter constructor for Landscape 
	Landscape(int[][] treeTypes, int size, int num, int[] x, int[] y){
		cells = new Cell[size][size];
        landscapeSize = size;
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
                // adds initial landscape to 2D array of Cell's
				cells[i][j]=new Cell(treeTypes[i][j]);
			}
		}

    numTreesToIgnite = num;
    igniteX = x;
    igniteY = y;
    // sets title of GUI window
    this.setTitle("Forest Fire Simulation");
    // sets size of frame 
    this.setSize(700,600);
    // exits frame on close
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // container to store panels for grid for forest and buttons 
    Container cPane = this.getContentPane();
    // intiates panel that stores grid of trees with contents of cells 
    forestPanel = new ForestPanel (cells);
    // adds ForestPanel to container 
    cPane.add(forestPanel);
    // makes new JPanel for buttons
    JPanel buttonPanel = new JPanel();
    // sets buttonPanel to use Border Layout in the SOUTH position (bottom of the frame)
    cPane.add(buttonPanel, BorderLayout.SOUTH);
    // button for next step
    JButton nextStepButton = new JButton("Next Step");
    // button to complete run 
    JButton completeRunButton = new JButton("Complete Run");
    // button to start new run
    JButton newRunButton = new JButton("Start New Run");
    // button to quit 
    JButton quitButton = new JButton("Quit");
    // adds actionListener to all buttons to make them functional
    nextStepButton.addActionListener(this);
    completeRunButton.addActionListener(this);
    newRunButton.addActionListener(this);
    quitButton.addActionListener(this);
    // adds all buttons to the buttonPanel
    buttonPanel.add(nextStepButton);
    buttonPanel.add(completeRunButton);
    buttonPanel.add(newRunButton);
    buttonPanel.add(quitButton);
    // adds MouseListener to the container 
    // cPane.addMouseListener(this);
    forestPanel.addMouseListener(this);

    // sets the frame as visible
    setVisible (true);
    }
    // used to implement MouseListener / ignite trees that are clicked
    int currentX = 0; int currentY = 0;
    public void mouseClicked(MouseEvent e) {
        int sideNum = 0; int x_length = cells[0].length; int y_length = cells[1].length;
        if (x_length >= y_length){
            sideNum = x_length;
        }
        else {
            sideNum = y_length;
        }
        int width = forestPanel.getWidth(); int height = forestPanel.getHeight();
        int var = 0;
        if (width < height) {
        var = width;
        }
        else {
        var = height;
        }
        int side = (var / sideNum);
        System.out.println ("mouseClicked event: " + e.paramString());
        int x = e.getX() / side;
        int y = e.getY() / side;
        Graphics g = this.getGraphics();
        if(x < cells.length && y < cells.length && cells[x][y].hasLiveTree()){
            g.setColor(Color.RED);
            g.fillOval(x*side,y*side + (side/landscapeSize),side,side);
        }
        g.setColor(Color.BLACK);
        if(cells[x][y].isTreeType(1)){
            g.drawString("1",x*side+(side/2)+(side/landscapeSize), y*(side+(side/3)+(side/3)));
        }
        else if(cells[x][y].isTreeType(2)){
            g.drawString("2",x*side+(side/2), y*(side+(side/3))+(side/3));
        }

    }

    // methods below must be implemented to use MouseListener
    public void mouseEntered (MouseEvent e) {}

    public void mouseExited (MouseEvent e) {}

    public void mousePressed (MouseEvent e) {}

    public void mouseReleased (MouseEvent e) {}
            
    // sets functions to each button (must be implemented to use action listener)
    // each method serves a specific function
    public void actionPerformed(ActionEvent e){
        String label = e.getActionCommand();
        if(label.equals("Next Step")){
            simulate();
            repaint();
        }
        else if(label.equals("Complete Run")){
            completeSimulation();
            repaint();
        }
        else if(label.equals("Start New Run")){
            regenerateForest();
            forestPanel.setCells(cells);
            repaint();
        }
        else if(label.equals("Quit")){
            System.exit(0);
        }
    }

    // creates a random new 2D array of Cells as the initial landscape
    // assigns a random number of trees to ignite and two arrays with 
    // ignite location values 
    private void regenerateForest() {
        int size = (int) UniformRandom.uniform(5,10);
        cells = new Cell[size][size];
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                cells[i][j] = new Cell((int) UniformRandom.uniform(0,2));
            }
        }
        numTreesToIgnite = (int) UniformRandom.uniform(0, size * size);
        igniteX = new int[numTreesToIgnite];
        igniteY = new int[numTreesToIgnite];
        for(int i = 0; i < numTreesToIgnite; i++){
            igniteX[i] = (int) UniformRandom.uniform(0, size - 1);
            igniteY[i] = (int) UniformRandom.uniform(0, size - 1); 
        }
    }
    // combines the 1D array's igniteX and igniteY into a 2D array igniteXY
    private int[][] getIgniteXY(){

        int igniteXY[][] = new int[igniteX.length][igniteY.length];
        igniteXY[0] = igniteX;
        igniteXY[1] = igniteY;
        return igniteXY;
    }

    // simulate the forest fire (this is one step in the simulation)
    private void simulate(){
        updateBurningTrees();
        if(numTreesToIgnite == 0) return;
        igniteTrees(numTreesToIgnite, igniteX, igniteY);
        int[][] igniteXY = getIgniteXY();
        igniteX = igniteXY[0];
        igniteY = igniteXY[1];
        numTreesToIgnite = igniteX.length;   
    }
    // complete the simulation (going through steps until no trees are left to ignite)
    private void completeSimulation() {
            simulate();
            simulate();

    } 
    
	// Returns number of trees burned 
	int getNumTreesBurned(int treeType){
		int numTreesBurned = 0;
		for(int i=0; i<cells.length; i++){
			for(int j=0; j<cells.length; j++){
				if (cells[i][j].getStump() && cells[i][j].isTreeType(treeType)) {
					numTreesBurned++;
				}
			}	
		}
		return numTreesBurned;
    }
   
    // ignites trees and neighbors based on igniteX and igniteY arrays that 
    // store locations to ignite 
	void igniteTrees(int num, int[]igniteX, int[]igniteY){

        int x_length = igniteX.length;
        int y_length = igniteY.length;
        int M = cells.length;
        int count = 0;
        while(count<num){
            int x = igniteX[count];
            int y = igniteY[count];
            if(cells[x][y]!=null){
            cells[x][y].ignite();
            count++;
            }
            if (x!=0){
            cells[x-1][y].ignite();
            }
            if (x!=M-1){
            cells[x+1][y].ignite();
            }
            if (y!=M-1){
            cells[x][y+1].ignite();
            }                    
            if (y!=0){
            cells[x][y-1].ignite();
            }
            cells[x][y].update();
            numTreesToIgnite--;
        }
    }
    // Method to update burning trees
    void updateBurningTrees(){
        int var_x = cells[0].length;
        int var_y = cells[1].length;
        for(int i = 0; i < var_x; i++){
            for (int j = 0; j < var_y; j++){
                cells[i][j].update();
            }
        }
    }
}

