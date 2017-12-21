class Landscape {
	// 2D array of Cell 
	private Cell[][] cells;
	int M;

	public String toString() {
  		StringBuffer lString = new StringBuffer();
  		for(int i = 0; i < M; i++) {
   		  for(int j = 0; j < M; j++) {
        if(cells[i][j].getStump()==true){
        lString.append("X ");
        }
        else{
        lString.append(cells[i][j]);
    		 }
  		}
        lString.append(System.lineSeparator());
    }

 		 return lString.toString();
		}	

	// 2 parameter constructor for Landscape 
	Landscape(int[][] landscape, int size){
		M = size;
		cells = new Cell[size][size];
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				cells[i][j]=new Cell(landscape[i][j]);
			}
		}
	}

	// Returns number of trees burned 
	int getNumTreesBurned(int x){
		int count = 0;
		for(int i=0; i<M; i++){
			for(int j=0; j<M; j++){
				if ((cells[i][j].treeType() == x) && (cells[i][j].getStump() == true)) {
					count++;
				}
			}
				
		}
		return count;
    }
   

	void simulateForestFire(int numTreesToIgnite, int[]igniteX, int[]igniteY){
            int counter = numTreesToIgnite;
            int M = cells.length;
            while(counter!=0){
                    int x = igniteX[counter-1];
                    int y = igniteY[counter-1];

                    // Step 1 Ignite trees (considered burning)
                    cells[x][y].ignite();
                    // Neighbors ignited
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

                    // Step2 (updated to stumps etc.)
                    cells[x][y].update();
                    if (x!=0){
                    cells[x-1][y].update();
                    }
                    if (x!=M-1){
                    cells[x+1][y].update();
                    }
                    if (y!=M-1){
                    cells[x][y+1].update();
                    }                    
                    if (y!=0){
                    cells[x][y-1].update();
                    }
                    counter--;
                }
            }
            
        



	int getNumClusters(int num){
		int numStumps = 0;
		int numClusters = 0;
		for(int i = 0; i<M; i++){
			for (int j = 0; j<M; j++){
				if (cells[i][j].getStump()==true){
					numStumps++;
					}
				}
				if (numStumps > 1){
					numClusters++;
				}

			}
		return numClusters;
		}

	
}

