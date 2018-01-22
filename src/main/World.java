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
        GRADIENT,
        SMOOTH_GRADIENT
    }

    /**
     * Width of the world in bricks
     */
    public static int WIDTH = 40;
    /**
     * Height of the world in bricks
     */
    public static int HEIGHT = 40;

    Brick[][] bricks = new Brick[World.WIDTH][World.HEIGHT];
    ArrayList<CellV1> cells = new ArrayList<>();


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

    /**
     * Update the list of cells
     */
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
            	bricks[i][j] = new Brick(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            	/*
                bricks[i][j].setFood(rand.nextFloat());
                bricks[i][j].setHeat(rand.nextFloat());
                bricks[i][j].setSalinity(rand.nextFloat());
                */
            }
        }
    }

    private void PopulateBricksGradient() {
	    // TODO: implement the function
        PopulateBricksRandom();
    }

    private void PopulateBricksSmoothGradient() {
	    // TODO: implement the function
        PopulateBricksRandom();
    }

    public Brick[][] getBricks() {
        return bricks;
    }

    public ArrayList<CellV1> getCells() {
        return cells;
    }
}
