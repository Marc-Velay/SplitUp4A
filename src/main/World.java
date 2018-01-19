package main;

import java.util.ArrayList;

public class World {

    public static int WIDTH = 40;
    public static int HEIGHT = 40;

    Brick[][] bricks = new Brick[World.WIDTH][World.HEIGHT];
    CellV1[] cells = new CellV1[World.WIDTH * World.HEIGHT];


    public World() {

	}
}
