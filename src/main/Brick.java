package main;

public class Brick {

    private double food;
    private double heat;
    private double salinity;

    public Brick(double food, double heat, double salinity) {
        this.food = food;
        this.heat = heat;
        this.salinity = salinity;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getHeat() {
        return heat;
    }

    public void setHeat(double heat) {
        this.heat = heat;
    }

    public double getSalinity() {
        return salinity;
    }

    public void setSalinity(double salinity) {
        this.salinity = salinity;
    }
}
