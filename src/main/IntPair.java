package main;

public class IntPair {
	  final int x;
	  final int y;
	  IntPair(int x, int y) {this.x=x;this.y=y;}
	  

    @Override
    public String toString() {
        return String.format("x=%d, y=%d", this.x, this.y);
    }
}
