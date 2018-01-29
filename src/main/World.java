package main;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class World {

    /**
     * The method used to fill the array of bricks.
     */
    public enum FillMethod {
        RANDOM,
        GRADIENT
    }

    /**
     * Width of the world in bricks
     */
    public static int WIDTH = 50;
    /**
     * Height of the world in bricks
     */
    public static int HEIGHT = 50;
    
    public static int MAXFOODPOLES = 3;
    public static int MAXHEATPOLES = 1;
    public static int MAXSALTPOLES = 1;
    
    public static int GRIDSTEP = 25;

    Brick[][] bricks = new Brick[World.WIDTH][World.HEIGHT];
    ArrayList<CellV1> cells = new ArrayList<>();
    IntPair[] foodPoles = new IntPair[MAXFOODPOLES] ;
    IntPair[] heatPoles = new IntPair[MAXHEATPOLES] ;
    IntPair[] saltPoles = new IntPair[MAXSALTPOLES] ;


    /**
     * Initializes the World object with its initial Cell and an array of Bricks.
     *
     * @param x Horizontal position of the first cell
     * @param y Vertical position of the first cell
     * @param m The method used to fill the array of bricks
     */
    public World(int x, int y, FillMethod m) {
        // TODO: determine initial values for the cell
        cells.add(new CellV1(x, y, 1.0, 0.0, 0.0));
        foodPoles[0] = new IntPair(12,25);
        foodPoles[1] = new IntPair(27,38);
        foodPoles[2] = new IntPair(35,15);
        
        heatPoles[0] = new IntPair(15,15);
        
        saltPoles[0] = new IntPair(37,37);
        
        switch (m) {
            case GRADIENT:
                populateBricksGradient();
                break;
            case RANDOM:
            default:
                populateBricksRandom();
                break;

        }
	}

    /**
     * Updates the list of cells
     */
	public void updateCells() {
        ListIterator<CellV1> iter = cells.listIterator();
        int brickX, brickY;
        while (iter.hasNext()) {
            CellV1 cell = iter.next();
            
            brickX = cell.getPosX() / GRIDSTEP;
            brickY = cell.getPosY() / GRIDSTEP;
            
            cell.incrementAge();
            cell.feed(bricks[brickX][brickY]);
            if (cell.doMitosis()) {
                try {
                    CellV1 clone = cell.clone();
                    clone.birth();
                    iter.add(clone);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * Fills the array of Bricks with random values
     */
	private void populateBricksRandom() {
//        Random rand = new Random(System.nanoTime());
        Random rand = new Random(1337);
        for (int i = 0 ; i < WIDTH ; i++) {
            for (int j=0 ; j < HEIGHT ; j++) {
            	bricks[i][j] = new Brick(rand.nextFloat()*150, rand.nextFloat()*80, rand.nextFloat()*100);
            	//bricks[i][j] = new Brick(0,0,0);
            }
        }
    }

    private void populateBricksGradient() {
    	int maxRadius = 13;
    	int angle = 0;
    	
        populateBricksRandom();
        //Creates concentric circles with decreasing amounts of food to generate food sources
    	for(int radius = 0; radius < maxRadius; radius++) {
            Brick[][] modBricks = bricks;
            for(int pole=0; pole < foodPoles.length; pole++) {
            	while(angle < 360) {
                    int x = (int)(foodPoles[pole].x + (radius) * Math.cos(angle));
                    int y = (int)(foodPoles[pole].y + (radius) * Math.sin(angle));

                	double newFood = Math.abs(modBricks[x][y].getFood()+ (maxRadius - radius)*(1.2));
                	this.bricks[x][y].setFood(newFood);
                	this.bricks[x][y].computeFitness();
                	
                    angle += 1;
                }
        		angle = 0;            	
            }
    	}

        //Creates concentric circles with decreasing amounts of heat to generate heat sources
    	for(int radius = 0; radius < maxRadius; radius++) {
            Brick[][] modBricks = bricks;
            for(int pole=0; pole < heatPoles.length; pole++) {
            	while(angle < 360) {
            		
                    int x = (int)(heatPoles[pole].x + (radius) * Math.cos(angle));
                    int y = (int)(heatPoles[pole].y + (radius) * Math.sin(angle));

                	double newHeat = Math.abs(modBricks[x][y].getHeat()+ (maxRadius - radius)*(1.2));
                	this.bricks[x][y].setHeat(newHeat);
                	this.bricks[x][y].computeFitness();
                	
                    angle += 1;
                }
        		angle = 0;            	
            }
    	}
        //Creates concentric circles with decreasing amounts of salt to generate salt sources
    	for(int radius = 0; radius < maxRadius; radius++) {
            Brick[][] modBricks = bricks;
            for(int pole=0; pole < saltPoles.length; pole++) {
            	while(angle < 360) {
            		
                    int x = (int)(saltPoles[pole].x + (radius) * Math.cos(angle));
                    int y = (int)(saltPoles[pole].y + (radius) * Math.sin(angle));

                	double newSalinity = Math.abs(modBricks[x][y].getSalinity()+ (maxRadius - radius)*(1.2));
                	this.bricks[x][y].setSalinity(newSalinity);
                	this.bricks[x][y].computeFitness();
                	
                    angle += 1;
                }
        		angle = 0;            	
            }
    	}
    }


    public Brick[][] getBricks() {
        return bricks;
    }

    public Brick getBrick(int x, int y) {
        return bricks[x][y];
    }

    public ArrayList<CellV1> getCells() {
        return cells;
    }
}
