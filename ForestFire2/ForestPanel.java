// imports needed libaries 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
// extends JPanel to create a grid of trees (forest)
class ForestPanel extends JPanel {
// instance of Landscape class
private Landscape landscape;
// instance of Cell class
private Cell[][] cells;
// initializes ForestPanel with an instance of the Cell class 
	ForestPanel(Cell[][] cellVar){
		this.setBackground (Color.white);
		cells = cellVar;
	}
// must be overriden to draw GUI
public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Max number of trees in a row or column
		int sideNum = 0;
		// cells[0] length
		int x_length = cells[0].length;
		// cells[1] length
		int y_length = cells[1].length;
		// selects larger length as max number of trees
		if (x_length >= y_length){
			sideNum = x_length;
		}
		else {
			sideNum = y_length;
		}
		// Width of panel
		int width = this.getWidth();
		// Height of panel
		int height = this.getHeight();
		// Calculates max number of pixels that could be used in constructing landscape
		int var = 0;
      	if (width < height) {
        var = width;
      	}
      	else {
        var = height;
      	}
      	// Calculates pixels in the side of each square in the grid 
      	int side = (var / sideNum);
		int type1Burned = 0;
		int type2Burned = 0;
		int totalBurned = 0;
		int burning = 0;
		int live = 0;
		// sets each tree to its corresponding color and labels with tree type
		// Green is alive 
		// Red is burning
		// Gray is burnt down 
		// Black is not tree 
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells.length; j++){
				if(cells[i][j].isTreeType(1) || cells[i][j].isTreeType(2)){
					if(cells[i][j].hasLiveTree()){
						g.setColor(Color.GREEN);
						live++;
					}
					else if (cells[i][j].hasBurningTree()){
						g.setColor(Color.RED);
						burning++;
					}
					else if(cells[i][j].getStump()) {
						g.setColor(Color.GRAY);
						totalBurned++;
						if(cells[i][j].isTreeType(1)){
							type1Burned++;
						}
						else if(cells[i][j].isTreeType(2)){
							type2Burned++;
						}
					}
					g.fillOval(j*side,i*side,side,side);
					g.setColor(Color.BLACK);
					if(cells[i][j].isTreeType(1)){
						g.drawString("1",j*side+((side/2)-(side/10)), i*side+((side/2)+(side/16)));
					}
					else if(cells[i][j].isTreeType(2)){
						g.drawString("2",j*side+((side/2)-(side/10)), i*side+((side/2)+(side/16)));
					}
				}
					else {
						g.setColor(Color.BLACK);
						g.fillRect(j*side,i*side,side,side);
					}
				}
			}
			int stringLoc = ((cells.length) * side) + 10;
			g.drawString("Type-1 Burned: " + type1Burned,stringLoc, ((side/2)-(side/10)));
			g.drawString("Type-2 Burned: " + type2Burned,stringLoc, (2*(side/2)-(side/10)));
			g.drawString("Total Burned: " + totalBurned, stringLoc, (3*(side/2)-(side/10)));
			g.drawString("Burning: " + burning, stringLoc, (4*(side/2)-(side/10)));
			g.drawString("Live: " + live, stringLoc, (5*(side/2)-(side/10)));
		}
		// sets Cells to new landscape when regenerating forest 
		public void setCells(Cell[][] cellvar){
		this.cells = cellvar;
		Graphics g = this.getGraphics();
		paintComponent(g);
		}
	}
