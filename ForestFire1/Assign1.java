
// Your name: Timothy Lytkine 

public class Assign1 {

  // A debug variable - set it to true when debugging so
  // you can print out stuff to screen during execution.
  public static boolean debug = true;

  // This method takes in a landscape (and its size M), and
  // a parameter called "fillDensity" and creates a landscape of
  // trees, by randomly generating trees in various cells.
  // IMPORTANT: DO NOT MODIFY THIS CODE.

  public static void createRandomForest (int[][] landscapeData, 
                                         int M, 
                                         double fillDensity,
                                         double type1Frac)
  {
    // Check fillDensity validity:
    if ( (fillDensity <= 0) || (fillDensity > 1) ) {
      System.out.println ("Assign1.createRandomForest(): fillDensity out of range: "
                          + fillDensity);
    }

    // Initially, clear the landscape:
    for (int i=0; i<M; i++) {
      for (int j=0; j<M; j++)
        landscapeData[i][j] = 0;
    }

    // Now fill in trees randomly:
    for (int i=0; i<M; i++) {
      for (int j=0; j<M; j++)
        if (UniformRandom.uniform () < fillDensity) {
          if (UniformRandom.uniform() < type1Frac)
            landscapeData[i][j] = 1;  // Type-1 tree.
          else
            landscapeData[i][j] = 2;  // Type-2 tree.
        }
    }

  }
  

  // Process a given landscape and identify the number of clusters found.
  // Return this value. DO NOT CHANGE ANY CODE IN THIS METHOD. YOU CAN
  // ADD CODE TO IT IF YOU WISH.

  public static Landscape processLandscapeData (int[][] landscapeData, 
                                      int M, 
                                      int numTreesToIgnite,
                                      int[] igniteX, 
                                      int[] igniteY)
  {
    Landscape L = new Landscape (landscapeData, M);

    if (debug) {
      System.out.println ("BEFORE: ");
      System.out.println (L);
    }

    L.simulateForestFire (numTreesToIgnite, igniteX, igniteY);

    if (debug) {
      System.out.println ("AFTER: ");
      System.out.println (L);
      System.out.println ("Number of type-1 trees burned: " + L.getNumTreesBurned (1));
      System.out.println ("Number of type-2 trees burned: " + L.getNumTreesBurned (2));
      System.out.println ("Number of type-1 clusters: " + L.getNumClusters (1));
    }

    return L;
  }
  

  // Use your main method for testing and estimation.

  public static void main (String[] argv) 
  {

    //////////////////////////////////////////////////////////
    // INSERT YOUR TEST CODE HERE:
    // Example of creating test code:
    int M = 5;
    int[][] landscapeData = new int [M][];
    for (int i=0; i<M; i++) {
      landscapeData[i] = new int[M];
      for (int j=0; j<M; j++) 
        landscapeData[i][j] = 0;
    }
    // Create a random landscape with fillDensity=0.7, type1Frac=0.4:
    createRandomForest (landscapeData, M, 0.7, 0.4);
    // Burn pattern:
    int numTreesToIgnite = 3;
    int[] igniteX = new int [3];
    int[] igniteY = new int [3];
    igniteX[0]=0;   igniteY[0]=1;
    igniteX[1]=1;   igniteY[1]=3;
    igniteX[2]=4;   igniteY[2]=2;

    // Simulate:
    Landscape L = processLandscapeData (landscapeData, M, numTreesToIgnite, igniteX, igniteY);

    int numType1TreesBurned = L.getNumTreesBurned (1);
    int numType2TreesBurned = L.getNumTreesBurned (2);
    int numType1Clusters = L.getNumClusters (1);

    // INSERT CODE HERE to compute averages ...

    // Reports average number of Type-1 clusters over large
    // number of random landscapes, average number of Type-1
    // clusters and the number of trees burned of each type
    double sumType1Clusters = 0;
    double sumType1TreesBurned = 0;
    double sumType2TreesBurned = 0;
    int numLandscapes = 1000;
    M = 6;
    int[][] landscapeData1 = new int [M][];
    for (int i=0; i<M; i++) {
      landscapeData1[i] = new int[M];
      for (int j=0; j<M; j++) 
        landscapeData1[i][j] = 0;
    }
    for(int i = 0; i<numLandscapes; i++){
    createRandomForest (landscapeData1, M, 0.7, 0.4);
    int numTreesToIgnite1 = 3;
    int[] igniteX1 = new int [3];
    int[] igniteY1 = new int [3];
    igniteX1[0]=0;   igniteY1[0]=1;
    igniteX1[1]=1;   igniteY1[1]=3;
    igniteX1[2]=4;   igniteY1[2]=2;
    debug = false;
    Landscape L1 = processLandscapeData (landscapeData1, M, numTreesToIgnite1, igniteX1, igniteY1);
    int numType1TreesBurned1 = L1.getNumTreesBurned (1);
    sumType1TreesBurned += numType1TreesBurned1;
    int numType2TreesBurned1 = L1.getNumTreesBurned (2);
    sumType2TreesBurned += numType2TreesBurned1;
    int numType1Clusters1 = L1.getNumClusters (1);
    sumType1Clusters += numType1Clusters1;
  }
  double averageType1Clusters = sumType1Clusters / 1000.0;
  double averageType1TreesBurned = sumType1TreesBurned / 1000.0;
  double averageType2TreesBurned = sumType2TreesBurned / 1000.0;
  System.out.println("");
  System.out.println("Average number of type 1 clusters: " + averageType1Clusters);
  System.out.println("Average number of type 1 trees burned: " + averageType1TreesBurned);
  System.out.println("Average number of type 2 trees burned: " + averageType2TreesBurned);

// "Ignite pattern" is to ignite all trees on the first row, what is the
// average number of trees of each type that burn? You are to report this
// average number of burnt trees for the following parameters:
// If your "ignite" pattern is to ignite all the trees on the first row, 
// what is the average number of trees of each type that burn? You are to report 
// this average number of burnt trees for the following parameters:

// M=4, and each value of fillDensity in the set {0.1, 0.3, 0.5, 0.7, 0.9}.
    double sumType1TreesBurnedA = 0;
    double sumType2TreesBurnedA = 0;
    numLandscapes = 1000;
    M = 4;
    int[][] landscapeDataA = new int [M][];
    for (int i=0; i<M; i++) {
      landscapeDataA[i] = new int[M];
      for (int j=0; j<M; j++) {
        landscapeDataA[i][j] = 0;
      }
    }
    for(int i1=0; i1<numLandscapes; i1++){
    for(double i = 0.1; i<=0.9; i=i+0.2){
      for(double j = 0.1; j<=0.9; j=j+0.2){
    createRandomForest (landscapeDataA, M, i, j);
    int numTreesToIgniteA = 4;
    int[] igniteXA = new int [4];
    int[] igniteYA = new int [4];
    igniteXA[0]=0;   igniteYA[0]=0;
    igniteXA[1]=0;   igniteYA[1]=1;
    igniteXA[2]=0;   igniteYA[2]=2;
    igniteXA[3]=0;   igniteYA[3]=3;
    debug = false;
    Landscape LA = processLandscapeData (landscapeDataA, M, numTreesToIgniteA, igniteXA, igniteYA);
    int numType1TreesBurnedA = LA.getNumTreesBurned (1);
    sumType1TreesBurnedA += numType1TreesBurnedA;
    int numType2TreesBurnedA = LA.getNumTreesBurned (2);
    sumType2TreesBurnedA += numType2TreesBurnedA;
  }
  }
}
  double averageType1TreesBurnedA = sumType1TreesBurnedA / 1000.0;
  double averageType2TreesBurnedA = sumType2TreesBurnedA / 1000.0;
  System.out.println("");
  System.out.println("M=4, and each value of fillDensity in the set {0.1, 0.3, 0.5, 0.7, 0.9}.");
  System.out.println("Average number of type 1 trees burned: " + averageType1TreesBurnedA);
  System.out.println("Average number of type 2 trees burned: " + averageType2TreesBurnedA);

//  M=8, and each value of fillDensity in the set {0.1, 0.3, 0.5, 0.7, 0.9}.
    double sumType1TreesBurnedB = 0;
    double sumType2TreesBurnedB = 0;
    numLandscapes = 1000;
    M = 8;
    int[][] landscapeDataB = new int [M][];
    for (int i=0; i<M; i++) {
      landscapeDataB[i] = new int[M];
      for (int j=0; j<M; j++) {
        landscapeDataB[i][j] = 0;
      }
    }
    for(int i2=0; i2<numLandscapes; i2++){
    for(double i = 0.1; i<=0.9; i=i+0.2){
      for(double j = 0.1; j<=0.9; j=j+0.2){
    createRandomForest (landscapeDataB, M, i, j);
    int numTreesToIgniteB = 8;
    int[] igniteXB = new int [8];
    int[] igniteYB = new int [8];
    igniteXB[0]=0;   igniteYB[0]=0;
    igniteXB[1]=0;   igniteYB[1]=1;
    igniteXB[2]=0;   igniteYB[2]=2;
    igniteXB[3]=0;   igniteYB[3]=3;
    igniteXB[4]=0;   igniteYB[4]=4;
    igniteXB[5]=0;   igniteYB[5]=5;
    igniteXB[6]=0;   igniteYB[6]=6;
    igniteXB[7]=0;   igniteYB[7]=7;
    debug = false;
    Landscape LB = processLandscapeData (landscapeDataB, M, numTreesToIgniteB, igniteXB, igniteYB);
    int numType1TreesBurnedB = LB.getNumTreesBurned (1);
    sumType1TreesBurnedB += numType1TreesBurnedB;
    int numType2TreesBurnedB = LB.getNumTreesBurned (2);
    sumType2TreesBurnedB += numType2TreesBurnedB;
  }
  }
}
  double averageType1TreesBurnedB = sumType1TreesBurnedB / 1000.0;
  double averageType2TreesBurnedB = sumType2TreesBurnedB / 1000.0;
  System.out.println("");
  System.out.println("M=8, and each value of fillDensity in the set {0.1, 0.3, 0.5, 0.7, 0.9}.");
  System.out.println("Average number of type 1 trees burned: " + averageType1TreesBurnedB);
  System.out.println("Average number of type 2 trees burned: " + averageType2TreesBurnedB);

  }
  
}
