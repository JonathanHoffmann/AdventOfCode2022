package day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Utils.TwoDArrayUtils;

public class PasswordCracker {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scMap = new Scanner(new File("day22/inputMap.txt"));
        Scanner scDir = new Scanner(new File("day22/inputDirections.txt"));

        ArrayList<String> input = new ArrayList<>();
        Integer direction = 0;
        Integer inputWidth = 0;
        Integer indexX = 0;
        Integer indexY = 0;
        Integer findX = 0;
        Integer findY = 0;
        Boolean findNew = true;
        String directionString = scDir.nextLine();
        while (scMap.hasNextLine()) {
            String in = scMap.nextLine();
            if (in.length() > inputWidth) {
                inputWidth = in.length();
            }
            input.add(in);
        }
        Character[][] map = new Character[input.size() + 1][inputWidth];
        for (int i = 0; i < input.size(); i++) {
            char[] stringChars = input.get(i).toCharArray();
            for (int j = 0; j < stringChars.length; j++) {
                map[i][j] = stringChars[j];
                if (i == 0 && indexY == 0 && stringChars[j] == '.') {
                    indexY = j;
                }
            }
        }
        System.out.println("Start X: " + indexX + " Start Y: " + indexY);

        map = TwoDArrayUtils.changeCharTo(map, null, ' ');

        try {
            TwoDArrayUtils.TwoDCharArrayToFile(map, "day22/test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Make moves
        Integer lastPos = 0;
        for (int i = 0; i < directionString.length(); i++) {
            if (directionString.charAt(i) == 'R' || directionString.charAt(i) == 'L') {
                // new dir
                Integer move = Integer.parseInt(directionString.substring(lastPos, i));
                for (int j = 0; j < move; j++) {
                    // Right
                    if (direction == 0) {
                        if (findNew) {
                            findY = indexY + 1;
                        } else {
                            findY++;
                        }
                        findNew = true;
                        if (findY >= map[0].length) {
                            findY = 0;
                        }
                        if (map[indexX][findY] == '.') {
                            indexY = findY;
                        } else if (map[indexX][findY] == ' ') {
                            findNew = false;
                            j--;
                        } else if (map[indexX][findY] == '#') {
                            j = move;
                        }
                    }

                    // Down
                    if (direction == 1) {
                        if (findNew) {
                            findX = indexX + 1;
                        } else {
                            findX++;
                        }
                        findNew = true;
                        if (findX >= map.length) {
                            findX = 0;
                        }
                        if (map[findX][indexY] == '.') {
                            indexX = findX;
                        } else if (map[findX][indexY] == ' ') {
                            findNew = false;
                            j--;
                        } else if (map[findX][indexY] == '#') {
                            j = move;
                        }
                    }

                    // Left
                    if (direction == 2) {
                        if (findNew) {
                            findY = indexY - 1;
                        } else {
                            findY--;
                        }
                        findNew = true;
                        if (findY < 0) {
                            findY = map[0].length - 1;
                        }
                        if (map[indexX][findY] == '.') {
                            indexY = findY;
                        } else if (map[indexX][findY] == ' ') {
                            findNew = false;
                            j--;
                        } else if (map[indexX][findY] == '#') {
                            j = move;
                        }
                    }

                    // Down
                    if (direction == 3) {
                        if (findNew) {
                            findX = indexX - 1;
                        } else {
                            findX--;
                        }
                        findNew = true;
                        if (findX < 0) {
                            findX = map.length - 1;
                        }
                        if (map[findX][indexY] == '.') {
                            indexX = findX;
                        } else if (map[findX][indexY] == ' ') {
                            findNew = false;
                            j--;
                        } else if (map[findX][indexY] == '#') {
                            j = move;
                        }
                    }
                }

                lastPos = i + 1;
                // Change to new direction
                if (directionString.charAt(i) == 'R' && i < directionString.length() - 1) {
                    direction++;
                    if (direction > 3) {
                        direction = 0;
                    }
                }
                if (directionString.charAt(i) == 'L' && i < directionString.length() - 1) {
                    direction--;
                    if (direction < 0) {
                        direction = 3;
                    }
                }
            }
        }
        Integer res = 1000 * (indexX + 1) + 4 * (indexY + 1) + direction;
        System.out.println("X " + (indexX + 1) + " Y " + (indexY + 1) + "\ncalc: " + res);
    }
}
