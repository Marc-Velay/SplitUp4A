package main;

public class Brick {

    private double food;
    private double heat;
    private double salinity;
    private double fitness;
    private double regenRate;

    public Brick(double food, double heat, double salinity) {
        this.food = food;
        this.heat = heat;
        this.salinity = salinity;
        this.computeFitness();
        this.regenRate = this.fitness/6;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
    	if(food < 0) food = 0;
    	if(food > 255) food = 255;
        this.food = food;
    }

    public double getHeat() {
        return heat;
    }

    public void setHeat(double heat) {
    	if(heat < 0) heat = 0;
    	if(heat > 255) heat = 255;
        this.heat = heat;
    }

    public double getSalinity() {
        return salinity;
    }

    public void setSalinity(double salinity) {
    	if(salinity < 0) salinity = 0;
    	if(salinity > 255) salinity = 255;
        this.salinity = salinity;
    }

    public void computeFitness() {
        // TODO: formula for the fitness
        double temp = (3*this.food-this.heat-2*this.salinity)/3;
    	if(temp < 0) temp = 0;
    	if(temp > 255) temp = 255;
    	this.fitness = temp;
    	
    }

    public double getFitness() {
        return fitness;
    }
    
    public void regenFood() {
    	this.setFood(this.getFood()+ regenRate);
    }
}
