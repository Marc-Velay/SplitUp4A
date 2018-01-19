package main;

public class CellV1 extends CellPrototype {
    public CellV1(int x, int y, double energy, double mutProb, double deathProb) {
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.mutProb = mutProb;
        this.deathProb = deathProb;
    }

    @Override
    public CellV1 clone() throws CloneNotSupportedException {
        return (CellV1) super.clone();
    }
}
