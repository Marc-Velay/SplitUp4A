package main;

public abstract class CellPrototype {
    int x;
    int y;
    double energy;
    double mutProb;
    double deathProb;

    public boolean doMitosis(Brick brick) {
        // TODO: implement mitosis logic
        return true;
    }

    @Override
    public String toString() {
        return String.format("x=%d, y=%d, energy=%f, mutProb=%f, deathProb=%f\n", this.x, this.y, this.energy, this.mutProb, this.deathProb);
    }

    public CellPrototype clone() throws CloneNotSupportedException {
        return (CellPrototype) super.clone();
    }
}
