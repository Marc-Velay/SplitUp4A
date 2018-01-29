package main;

public abstract class CellPrototype implements Cloneable {

    /**
     * Width of a cell
     */
    public static int WIDTH=10;
    /**
     * Height of a cell
     */
    public static int HEIGHT=10;

    /**
     * Horizontal position of the cell
     */
    int x;
    /**
     * Vertical position of the cell
     */
    int y;
    /**
     * Age of the cell
     */
    int age;
    /**
     * Energy level of the cell
     *
     * Helps to determine whether or not the cell should divide
     */
    double energy;
    /**
     * Probability of the cell mutating
     */
    double mutProb;
    /**
     * Probability of the cell dying
     */
    double deathProb;

    /**
     * Modifies the energy of the cell according to its current brick
     */
    public void feed(Brick brick) {
        // TODO: decide how the energy of the cell should be changed
        //brick.setFood(newFoodValue)
        //this.energy = newValue
    }

    /**
     * Returns whether or not the cell will divide
     *
     * @return Boolean indicating if the cell should divide
     */
    public boolean doMitosis() {
        // TODO: implement mitosis logic
        return true;
    }

    /**
     * Increments the age of the cell by 1
     */
    public void incrementAge() {
        this.age++;
    }

    /**
     * Sets the newly cloned cell's parameters
     */
    public void birth() {
        this.x = this.age % 2 == 0 ? this.x - CellPrototype.HEIGHT : this.x + CellPrototype.HEIGHT;
        this.y = this.age % 2 == 0 ? this.y - CellPrototype.WIDTH : this.y + CellPrototype.WIDTH;
    }

    @Override
    public String toString() {
        return String.format("x=%d, y=%d, age=%d, energy=%f, mutProb=%f, deathProb=%f\n", this.x, this.y, this.age, this.energy, this.mutProb, this.deathProb);
    }

    public CellPrototype clone() throws CloneNotSupportedException {
        return (CellPrototype) super.clone();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
