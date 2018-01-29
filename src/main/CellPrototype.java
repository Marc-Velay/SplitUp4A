package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public abstract class CellPrototype extends JPanel implements Cloneable {

    /**
     * Radius of a cell
     */
    public static int RADIUS=25;

    /**
     * Horizontal position of the cell
     */
    int x=0;
    /**
     * Vertical position of the cell
     */
    int y=0;
    /**
     * Age of the cell
     */
    int age=0;
    /**
     * Energy level of the cell
     *
     * Helps to determine whether or not the cell should divide
     */
    double energy;
    /**
     * Probability of the cell mutating
     */
    double mutProb=0.1;
    /**
     * Probability of the cell dying
     */
    double deathProb;


    public int getPosX() {
        return this.x;
    }

    public int getPosY() {
        return this.y;
    }
    
    /**
     * Modifies the energy of the cell according to its current brick
     */
    public void feed(Brick brick) {
        // TODO: decide how the energy of the cell should be changed
        brick.setFood(brick.getFood()-255/4);
        this.energy += brick.getFood()/255;
    }
	
    /**
     * Returns whether or not the cell will divide
     *
     * @return Boolean indicating if the cell should divide
     */
    public boolean doMitosis() {
    	Random rand = new Random();
    	if(rand.nextFloat()<(this.mutProb*this.energy)) { return true; }
    	return false;
    }

    /**
     * Increments the age of the cell by 1
     */
    public void incrementAge() {
        this.age++;
    }

    /**
     * Sets the energy of the cell
     */
    public void setEnergy(double energy) {
    	if(energy < 0) energy = 0;
    	if(energy > 1) energy = 1;
        this.energy = energy;
    }

    /**
     * Sets the newly cloned cell's parameters
     */
    public void birth(ArrayList<CellV1> cells) {
        //this.x = this.age % 2 == 0 ? this.x - CellPrototype.RADIUS : this.y + CellPrototype.RADIUS;
        //this.y = this.age % 2 == 0 ? this.y - CellPrototype.RADIUS : this.x + CellPrototype.RADIUS;
    	
    	if(this.age %2 == 0) {
    		this.x += CellPrototype.RADIUS +2;
    	} else {
    		this.y += CellPrototype.RADIUS +2;
    		
    	}
    	
        if(this.x-CellPrototype.RADIUS < 0) this.x = CellPrototype.RADIUS/2;
        if(this.y-CellPrototype.RADIUS < 0) this.y = CellPrototype.RADIUS/2;
        if(this.x+CellPrototype.RADIUS > WorldGUI.WINDOWWIDTH) this.x = WorldGUI.WINDOWWIDTH - CellPrototype.RADIUS/2;
        if(this.y+CellPrototype.RADIUS > WorldGUI.WINDOWWIDTH) this.y = WorldGUI.WINDOWWIDTH - CellPrototype.RADIUS/2;
    }
    
    public void paintComponent(Graphics g){
    	this.setOpaque(true);
		//int k=(int)(128+Math.random()*(255-128)); 
    	this.setEnergy(this.energy);
	    g.setColor(new Color(255, (int)(this.energy*254),(int)(this.energy*254)));
	    g.fillOval(this.x, this.y, CellPrototype.RADIUS, CellPrototype.RADIUS);    
    }  

    @Override
    public String toString() {
        return String.format("x=%d, y=%d, age=%d, energy=%f, mutProb=%f, deathProb=%f\n", this.x, this.y, this.age, this.energy, this.mutProb, this.deathProb);
    }

    public CellPrototype clone() throws CloneNotSupportedException {
        return (CellPrototype) super.clone();
    }

}
