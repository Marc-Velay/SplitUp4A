package main;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class World {

    public enum FillMethod {
        RANDOM,
        GRADIENT,
        SMOOTH_GRADIENT
    }

    public static int WIDTH = 40;
    public static int HEIGHT = 40;
    public static int MAXFOODPOLES = 3;
    public static int GRIDSTEP = 25;

    Brick[][] bricks = new Brick[World.WIDTH][World.HEIGHT];
    ArrayList<CellV1> cells = new ArrayList<>();
    IntPair[] foodPoles = new IntPair[MAXFOODPOLES] ;


    /**
     * Initializes the World object with its initial Cell and an array of Bricks.
     *
     * @param m The method used to fill the array of bricks
     */
    public World(int x, int y, FillMethod m) {
        // TODO: determine initial values for the cell
        cells.add(new CellV1(x, y, 1.0, 0.0, 0.0));
        foodPoles[0] = new IntPair(15,15);
        foodPoles[1] = new IntPair(25,30);
        foodPoles[2] = new IntPair(30,10);
        
        switch (m) {
            case GRADIENT:
                PopulateBricksGradient();
                break;
            case SMOOTH_GRADIENT:
                PopulateBricksSmoothGradient();
                break;
            case RANDOM:
            default:
                PopulateBricksRandom();
                break;

        }
	}

	public void UpdateCells() {
        // TODO: check for ConcurrentModificationException
        /*for (CellV1 c : cells) {
            c.incrementAge();
            if (c.doMitosis()) {
                try {
                    cells.add(c.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }

        }*/
        cells.parallelStream().forEach(
                cellV1 -> {
                    cellV1.incrementAge();
                    if(cellV1.doMitosis()) {
                        try {
                            CellV1 clone = cellV1.clone();
                            clone.birth();
                            cells.add(clone);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    /**
     * Fills the array of Bricks with random values
     */
	private void PopulateBricksRandom() {
//        Random rand = new Random(System.nanoTime());
        Random rand = new Random(1337);
        for (int i = 0 ; i < WIDTH ; i++) {
            for (int j=0 ; j < HEIGHT ; j++) {
            	bricks[i][j] = new Brick(rand.nextFloat()*255, rand.nextFloat(), rand.nextFloat());
            	//bricks[i][j] = new Brick(100, 100, 100);
            }
        }
    }

    private void PopulateBricksGradient() {
	    // TODO: implement the function
        PopulateBricksRandom();    	
    }

    private void PopulateBricksSmoothGradient() {
	    // TODO: implement the function
    	int maxRadius = 7;
    	int angle = 0;
    	
        PopulateBricksRandom();
    	for(int radius = 0; radius < maxRadius; radius++) {
            Brick[][] modBricks = bricks;
            for(int pole=0; pole < foodPoles.length; pole++) {
            	while(angle < 360) {
                    int x = (int)(foodPoles[pole].x + (radius) * Math.cos(angle));
                    int y = (int)(foodPoles[pole].y + (radius) * Math.sin(angle));

                	double newFood = Math.abs(modBricks[x][y].getFood()+ (maxRadius - 2*radius/3));
                	if(newFood < 0) newFood = 0;
                	if(newFood > 255) newFood = 255;
                    System.out.println("x: " + x + " y: " + y + "new food: " + newFood);
                	this.bricks[x][y].setFood(newFood);
                	
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
