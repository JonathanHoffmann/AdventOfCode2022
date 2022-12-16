package n;

import java.util.ArrayList;

public class Coordinate {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        //System.out.println("New coordinate: X: " + x + " Y: " + y);
    }

    public ArrayList<Coordinate> coordsInBetween(Coordinate c) {
        ArrayList<Coordinate> coords = new ArrayList<>();
        coords.add(this);
        coords.add(c);
        int x2 = c.getX();
        int y2 = c.getY();
        while (x > x2) {
            x2++;
            coords.add(new Coordinate(x2, y2));
        }
        while (x < x2) {
            x2--;
            coords.add(new Coordinate(x2, y2));
        }
        while (y > y2) {
            y2++;
            coords.add(new Coordinate(x2, y2));
        }
        while (y < y2) {
            y2--;
            coords.add(new Coordinate(x2, y2));
        }
        return coords;
    }
}
