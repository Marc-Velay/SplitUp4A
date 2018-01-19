package main;

public abstract class Brick {
    double food;
    double heat;
    double salinity;

    public Brick(double food, double heat, double salinity) {
        this.food = food;
        this.heat = heat;
        this.salinity = salinity;
    }
}
