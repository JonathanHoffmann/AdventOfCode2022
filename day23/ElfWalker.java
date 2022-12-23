package day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Utils.TwoDArrayUtils;

public class ElfWalker {
    public static final int RUNS_THROUGH_ALGO = 100000; // 10 for task 1, higher for task 2
    public static Character[][] map;

    public static void main(String[] args) throws FileNotFoundException {
        Integer direction = 0;
        Scanner sc = new Scanner(new File("day23/input.txt"));
        ArrayList<String> inputs = new ArrayList<>();
        while (sc.hasNextLine()) {
            inputs.add(sc.nextLine());
        }
        map = new Character[inputs.size() * 3][inputs.get(0).length() * 3];
        map = Utils.TwoDArrayUtils.changeCharTo(map, null, '.');

        for (int i = 0; i < inputs.size(); i++) {
            for (int j = 0; j < inputs.get(0).length(); j++) {
                map[i + inputs.size()][j + inputs.get(0).length()] = inputs.get(i).charAt(j);
            }
        }

        for (int count = 0; count < RUNS_THROUGH_ALGO; count++) {
            Boolean someoneMoved = false;

            // loop through the map
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    // Finding elf
                    if (map[i][j] == '#') {
                        Boolean elfUp = true;
                        Boolean elfDown = true;
                        Boolean elfLeft = true;
                        Boolean elfRight = true;

                        if (map[i - 1][j] == '.' && map[i - 1][j - 1] == '.' && map[i - 1][j + 1] == '.') {
                            elfUp = false;
                        }
                        if (map[i + 1][j] == '.' && map[i + 1][j - 1] == '.' && map[i + 1][j + 1] == '.') {
                            elfDown = false;
                        }
                        if (map[i][j - 1] == '.' && map[i + 1][j - 1] == '.' && map[i - 1][j - 1] == '.') {
                            elfLeft = false;
                        }
                        if (map[i][j + 1] == '.' && map[i + 1][j + 1] == '.' && map[i - 1][j + 1] == '.') {
                            elfRight = false;
                        }

                        if (elfUp || elfDown || elfLeft || elfRight) {
                            switch (direction) {
                                case 0:
                                    if (!elfUp) {
                                        map[i][j] = 'U';
                                    } else if (!elfDown) {
                                        map[i][j] = 'D';
                                    } else if (!elfLeft) {
                                        map[i][j] = 'L';
                                    } else if (!elfRight) {
                                        map[i][j] = 'R';
                                    }
                                    break;
                                case 1:
                                    if (!elfDown) {
                                        map[i][j] = 'D';
                                    } else if (!elfLeft) {
                                        map[i][j] = 'L';
                                    } else if (!elfRight) {
                                        map[i][j] = 'R';
                                    } else if (!elfUp) {
                                        map[i][j] = 'U';
                                    }
                                    break;
                                case 2:
                                    if (!elfLeft) {
                                        map[i][j] = 'L';
                                    } else if (!elfRight) {
                                        map[i][j] = 'R';
                                    } else if (!elfUp) {
                                        map[i][j] = 'U';
                                    } else if (!elfDown) {
                                        map[i][j] = 'D';
                                    }
                                    break;
                                case 3:
                                    if (!elfRight) {
                                        map[i][j] = 'R';
                                    } else if (!elfUp) {
                                        map[i][j] = 'U';
                                    } else if (!elfDown) {
                                        map[i][j] = 'D';
                                    } else if (!elfLeft) {
                                        map[i][j] = 'L';
                                    }
                                    break;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    Boolean move = true;
                    // Up
                    if (map[i][j] == 'U') {
                        if (map[i - 2][j] == 'D') {
                            move = false;
                            map[i - 2][j] = '#';
                        }
                        if (map[i - 1][j + 1] == 'L') {
                            move = false;
                            map[i - 1][j + 1] = '#';
                        }
                        if (map[i - 1][j - 1] == 'R') {
                            move = false;
                            map[i - 1][j - 1] = '#';
                        }
                        if (move) {
                            map[i][j] = '.';
                            map[i - 1][j] = '#';
                            someoneMoved = true;
                        } else {
                            map[i][j] = '#';
                        }
                    }
                    // Down
                    else if (map[i][j] == 'D') {
                        if (map[i + 2][j] == 'U') {
                            move = false;
                            map[i + 2][j] = '#';
                        }
                        if (map[i + 1][j + 1] == 'L') {
                            move = false;
                            map[i + 1][j + 1] = '#';
                        }
                        if (map[i + 1][j - 1] == 'R') {
                            move = false;
                            map[i + 1][j - 1] = '#';
                        }
                        if (move) {
                            map[i][j] = '.';
                            map[i + 1][j] = '#';
                            someoneMoved = true;
                        } else {
                            map[i][j] = '#';
                        }
                    }
                    // Left
                    else if (map[i][j] == 'L') {
                        if (map[i + 1][j - 1] == 'U') {
                            move = false;
                            map[i + 1][j - 1] = '#';
                        }
                        if (map[i - 1][j - 1] == 'D') {
                            move = false;
                            map[i - 1][j - 1] = '#';
                        }
                        if (map[i][j - 2] == 'R') {
                            move = false;
                            map[i][j - 2] = '#';
                        }
                        if (move) {
                            map[i][j] = '.';
                            map[i][j - 1] = '#';
                            someoneMoved = true;
                        } else {
                            map[i][j] = '#';
                        }
                    }
                    // Right
                    else if (map[i][j] == 'R') {
                        if (map[i + 1][j + 1] == 'U') {
                            move = false;
                            map[i + 1][j + 1] = '#';
                        }
                        if (map[i - 1][j + 1] == 'D') {
                            move = false;
                            map[i - 1][j + 1] = '#';
                        }
                        if (map[i][j + 2] == 'L') {
                            move = false;
                            map[i][j + 2] = '#';
                        }
                        if (move) {
                            map[i][j] = '.';
                            map[i][j + 1] = '#';
                            someoneMoved = true;
                        } else {
                            map[i][j] = '#';
                        }
                    }
                        
                }
            }

            try {
                TwoDArrayUtils.TwoDCharArrayToFile(map, "day23/out.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Change direction at the end
            direction++;
            if (direction == 4) {
                direction = 0;
            }

            if (!someoneMoved) {
                System.out.println("Nobody moved in turn " + (count + 1));
                break;
            } else {
                System.out.println("somebody moved in turn " + (count + 1));
            }
        }

        // Find cover area
        Integer maxI = 0;
        Integer maxJ = 0;
        Integer minI = map.length;
        Integer minJ = map[0].length;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == '#') {
                    if (i < minI) {
                        minI = i;
                    }
                    if (j < minJ) {
                        minJ = j;
                    }
                    if (i > maxI) {
                        maxI = i;
                    }
                    if (j > maxJ) {
                        maxJ = j;
                    }
                }
            }
        }

        // Count available spaces in area
        Integer total = 0;
        for (int i = minI; i < maxI + 1; i++) {
            for (int j = minJ; j < maxJ + 1; j++) {
                // System.out.println("looking at " + i + ":" + j + " Character " + map[i][j]);
                if (map[i][j] == '.') {
                    total++;
                }
            }
        }
        System.out.println("Task 1: " + total);
    }
}