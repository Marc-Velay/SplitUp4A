package main;

public abstract class Brick {
    private double food;
    private double heat;
    private double salinity;

    public Brick(double food, double heat, double salinity) {
        this.food = food;
        this.heat = heat;
        this.salinity = salinity;
    }
}
