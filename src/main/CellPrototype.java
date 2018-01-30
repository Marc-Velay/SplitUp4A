package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
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
        if(brick.getFitness() > 50) {
        	this.energy += brick.getFitness()/255;
        } else {
        	this.energy -= brick.getFitness()/250;
    	} 
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
    /*
     * Checks that the spot where we try to create a cell is clear
     */
    private Boolean[] dirIsClear(ArrayList<CellV1> cells) {
    	Boolean[] clearDir = new Boolean[4];
    	Arrays.fill(clearDir, true);
    	for(int cellNum = 0; cellNum < cells.size(); cellNum++) {
    		CellV1 cell = cells.get(cellNum);
    		if(this.x < cell.x && cell.x <= this.x + CellPrototype.RADIUS+5 && this.y==cell.y) {
        		clearDir[0] = false;
        	} 
    		if(this.y < cell.y && cell.y <= this.y + CellPrototype.RADIUS+5 && this.x==cell.x) {
        		clearDir[1] = false;
        	} 
    		if(this.x > cell.x && cell.x >= this.x - CellPrototype.RADIUS-5 && this.y==cell.y) {
        		clearDir[2] = false;
        	} 
    		if(this.y > cell.y && cell.y >= this.y - CellPrototype.RADIUS-5 && this.x==cell.x) {
        		clearDir[3] = false;
        	} 
    	}
    	

    	//System.out.println(clearDir[0] + " " + clearDir[1] + " " + clearDir[2] + " " + clearDir[3]);
    	
    	return clearDir;
    }
    
    /**
     * Sets the newly cloned cell's parameters
     */
    public Boolean birth(ArrayList<CellV1> cells) {

    	Boolean birthed = false;
    	Boolean[] clearDirs = dirIsClear(cells);
    	
    	if(clearDirs[0] == true || clearDirs[2] == true) {
        	if(clearDirs[0] == true) {
        		this.x += CellPrototype.RADIUS;
        		birthed = true;
        	} else {
        		this.x -= CellPrototype.RADIUS;
        		birthed = true;
        	}
    	} else if(clearDirs[1] == true || clearDirs[3] == true) {
        	if(clearDirs[1] == true) {
        		this.y += CellPrototype.RADIUS;
        		birthed = true;
        	} else {
        		this.y -= CellPrototype.RADIUS;
        		birthed = true;
        	} 
    	}
    	
    	
        if(this.x-CellPrototype.RADIUS < 0) this.x = CellPrototype.RADIUS/2;
        if(this.y-CellPrototype.RADIUS < 0) this.y = CellPrototype.RADIUS/2;
        if(this.x+CellPrototype.RADIUS > WorldGUI.WINDOWWIDTH) this.x = WorldGUI.WINDOWWIDTH - CellPrototype.RADIUS/2;
        if(this.y+CellPrototype.RADIUS > WorldGUI.WINDOWWIDTH) this.y = WorldGUI.WINDOWWIDTH - CellPrototype.RADIUS/2;
        return birthed;
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
