package main;

public class CellV1 extends CellPrototype {
    public CellV1(int x, int y, double energy, double mutProb, double deathProb) {
        this.x = x;
        this.y = y;
        this.age = 0;
        this.energy = energy;
        this.mutProb = mutProb;
        this.deathProb = deathProb;
    }

    /**
     * Clones the cell and gives the clone a new position in the world
     * @return The cloned cell
     * @throws CloneNotSupportedException Throws CloneNotSupportedException if call to super.clone() fails.
     */
    @Override
    public CellV1 clone() throws CloneNotSupportedException {
        // TODO : determine properties of clone
        this.energy /= 2;
        return (CellV1) super.clone();
    }
    
}
