package main;

public class CellV1 extends CellPrototype {
    public CellV1(int x, int y, double energy, double mutProb, double deathProb) {
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.mutProb = mutProb;
        this.deathProb = deathProb;
    }

    /**
     * Clones the cell and gives the clone a new position in the world
     * @return The cloned cell
     * @throws CloneNotSupportedException
     */
    @Override
    public CellV1 clone() throws CloneNotSupportedException {
        // TODO : determine properties of clone
        int x = System.nanoTime() % 2 == 0 ? this.x - CellPrototype.HEIGHT : this.x + CellPrototype.HEIGHT ;
        int y = System.nanoTime() % 2 == 0 ? this.y - CellPrototype.WIDTH : this.y + CellPrototype.WIDTH ;
        return new CellV1(x, y, this.energy / 2, this.mutProb, this.deathProb);
    }
}
