package n;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import zUtils.TwoDArrayUtils;

public class SandSimTask2 {
    public static Character[][] map;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("n/input.txt"));
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
        map = new Character[maxY + 3][maxX + 500];
        // Empty spots in array
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[j][i] = '.';
            }
        }
        System.out.println(map[0].length);
        for (int i = 0; i < map[0].length - 1; i++) {
            map[map.length - 1][i] = 'I';
        }
        for (Coordinate c : rocks) {
            map[c.getY()][c.getX()] = '#';
        }
        int length = map[0].length;
        int height = map.length + 1;
        Character[][] tempMap = new Character[height][length];
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                tempMap[j + 1][i] = map[j][i];
            }
        }
        map = tempMap;
        for (int i = 0; i < tempMap[0].length - 1; i++) {
            tempMap[0][i] = '.';
        }

        while (placeSandCheck(500, 0))
            ;

        try {
            TwoDArrayUtils.TwoDCharArrayToFile(map, "n/task2Drawing.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(TwoDArrayUtils.countCharOccurence(map, 'o'));
    }

    public static Boolean placeSandCheck(int x, int y) {
        if (y == 0 && map[1][x] != '.') {
            return false;
            //return placeSandCheck(x + 1, y + 1);
            // Sand outside below map
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
