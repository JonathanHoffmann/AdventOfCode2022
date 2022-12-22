package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Utils.TwoDArrayUtils;

public class SandSim {
    public static Character[][] map;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day14/input.txt"));
        Integer maxX = 0;
        Integer maxY = 0;
        ArrayList<Coordinate> rocks = new ArrayList<>();
        while (sc.hasNextLine()) {
            Coordinate cNew;
            Coordinate cDest = null;
            Coordinate cOrig = null;
            String in = sc.nextLine();
            // System.out.println("Read new line: " + in);
            in = in.replaceAll(" ", "");
            String[] splitIn = in.split("->");

            for (String s : splitIn) {
                String[] intsAsString = s.split(",");
                int X = Integer.parseInt(intsAsString[0]);
                int Y = Integer.parseInt(intsAsString[1]);
                if (X > maxX) {
                    maxX = X;
                }
                if (Y > maxY) {
                    maxY = Y;
                }

                cNew = new Coordinate(X, Y);
                if (cOrig == null) {
                    cOrig = cNew;
                } else if (cDest == null) {
                    cDest = cNew;
                } else {
                    cOrig = cDest;
                    cDest = cNew;
                }

                if (cDest != null) {
                    rocks.addAll(cDest.coordsInBetween(cOrig));
                }

            }
        }
        map = new Character[maxY + 1][maxX + 5];
        // Empty spots in array
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[j][i] = '.';
            }
        }
        for (Coordinate c : rocks) {
            map[c.getY()][c.getX()] = '#';
        }

        while (placeSandCheck(500, 0));

        try {
            TwoDArrayUtils.TwoDCharArrayToFile(map, "day14/task1Drawing.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(TwoDArrayUtils.countCharOccurence(map,'o'));
    }

    public static Boolean placeSandCheck(int x, int y) {
        if (y == map.length - 1) {
            // Sand outside below map
            return false;
        }
        // Down
        if (map[y + 1][x] == '.') {
            return placeSandCheck(x, y + 1);
        }
        // left
        if (map[y + 1][x - 1] == '.') {
            return placeSandCheck(x - 1, y + 1);
        }
        // right
        if (map[y + 1][x + 1] == '.') {
            return placeSandCheck(x + 1, y + 1);
        }
        placeSand(x, y);
        return true;
    }

    public static Boolean placeSand(int x, int y) {
        map[y][x] = 'o';
        return true;
    }
}
