package test;

import main.CellV1;
import main.Brick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellV1Test {

    private CellV1 cell;

    @BeforeEach
    void setUp() {
        cell = new CellV1(0, 0, 0, 0,0);
    }

    @Test
    @Disabled
    void feed() {
        Brick brick = new Brick(3.0, 1.0, 1.0);
        cell.feed(brick);
        //assertEquals();
    }

    @Test
    void doMitosis() {
    }

    @Test
    void incrementAge() {
        assertEquals(0, cell.getAge());
        for (int i = 1 ; i < 10 ; i++) {
            cell.incrementAge();
            assertEquals(i, cell.getAge());
        }
    }

    @Test
    void birth() {
        cell.birth();
        assertEquals(-25, cell.getPosX());
        assertEquals(-25, cell.getPosY());
        cell.incrementAge();
        cell.birth();
        assertEquals(0, cell.getPosX());
        assertEquals(0, cell.getPosY());
    }
}