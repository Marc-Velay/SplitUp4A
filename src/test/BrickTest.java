package test;

import main.Brick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {
    private Brick brick;

    @BeforeEach
    public void setUp() {
        brick = new Brick(3.0, 1.0, 1.0);
    }

    @Test
    public void computeFitness() {
        double fitness = 2.0;
        assertEquals(fitness, brick.getFitness());
    }

    @Test
    public void regenFood() {
        brick.regenFood();
        assertEquals(3.5, brick.getFood());
        brick.regenFood();
        assertEquals(4.0, brick.getFood());
    }
}