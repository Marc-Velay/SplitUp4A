package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public abstract class CellPrototype extends JPanel implements Cloneable {

    /**
     * Radius of a cell
     */
    public static int RADIUS=20;

    /**
     * Horizontal position of the cell
     */
    int x;
    /**
     * Vertical position of the cell
     */
    int y;
    /**
     * Age of the cell
     */
    int age;
    /**
     * Energy level of the cell
     *
     * Helps to determine whether or not the cell should divide
     */
    double energy;
    /**
     * Probability of the cell mutating
     */
    double mutProb;
    /**
     * Probability of the cell dying
     */
    double deathProb;

    /**
     * Modifies the energy of the cell according to its current brick
     */
    public void feed(Brick brick) {
        // TODO: decide how the energy of the cell should be changed
        //brick.setFood(newFoodValue)
        //this.energy = newValue
    }

    /**
     * Returns whether or not the cell will divide
     *
     * @return Boolean indicating if the cell should divide
     */
    public boolean doMitosis() {
        // TODO: implement mitosis logic
        return true;
    }

    /**
     * Increments the age of the cell by 1
     */
    public void incrementAge() {
        this.age++;
    }

    /**
     * Sets the newly cloned cell's parameters
     */
    public void birth() {
        this.x = this.age % 2 == 0 ? this.x - CellPrototype.RADIUS : this.x + CellPrototype.RADIUS;
        this.y = this.age % 2 == 0 ? this.y - CellPrototype.RADIUS : this.y + CellPrototype.RADIUS;
    }

    @Override
    public String toString() {
        return String.format("x=%d, y=%d, age=%d, energy=%f, mutProb=%f, deathProb=%f\n", this.x, this.y, this.age, this.energy, this.mutProb, this.deathProb);
    }
    
    public void paintComponent(Graphics g){
		this.setOpaque(true);
		int k=(int)(128+Math.random()*(255-128)); 
	    g.setColor(new Color(255-k,255-k,255));
	    g.fillOval(this.x, this.y, CellPrototype.RADIUS, CellPrototype.RADIUS);
	    
	  }    

    public CellPrototype clone() throws CloneNotSupportedException {
        return (CellPrototype) super.clone();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
