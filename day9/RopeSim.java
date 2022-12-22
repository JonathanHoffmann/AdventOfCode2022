package day9;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RopeSim {
    private static Character[][] visited;
    private static final Integer SPACE_SIZE = 1000;
    private static final Integer START_POS = SPACE_SIZE / 2;
    private static final Integer ROPE_LENGTH = 10;
    private static Integer[][] rope;
    private static final Boolean DEBUG_PRINTS = false;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("day9/input.txt"));
        // Rope array pos 0 up/down position
        // Rope array pos 1 left/right position
        // rope array pos 2 name of the knot
        rope = new Integer[ROPE_LENGTH][3];
        for (int i = 0; i < ROPE_LENGTH; i++) {
            rope[i][0] = START_POS;
            rope[i][1] = START_POS;
            rope[i][2] = i;
        }

        // Make and fill initial plane
        Character[][] space = new Character[SPACE_SIZE][SPACE_SIZE];
        visited = new Character[SPACE_SIZE][SPACE_SIZE];
        space = redrawSpace(space);
        visited = redrawSpace(visited);
        if (DEBUG_PRINTS) {
            printSpace(space, false);
        }

        // Follow inputs
        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            System.out.println(row);
            String[] in = row.split(" ");
            switch (in[0]) {
                case "R":
                    for (int i = 0; i < Integer.parseInt(in[1]); i++) {
                        rope[0][1]++;
                        space = redrawSpace(space);
                        if (DEBUG_PRINTS) {
                            printSpace(space, false);
                        }
                    }
                    break;
                case "L":
                    for (int i = 0; i < Integer.parseInt(in[1]); i++) {
                        rope[0][1]--;
                        space = redrawSpace(space);
                        if (DEBUG_PRINTS) {
                            printSpace(space, false);
                        }
                    }
                    break;
                case "U":
                    for (int i = 0; i < Integer.parseInt(in[1]); i++) {
                        rope[0][0]--;
                        space = redrawSpace(space);
                        if (DEBUG_PRINTS) {
                            printSpace(space, false);
                        }
                    }
                    break;
                case "D":
                    for (int i = 0; i < Integer.parseInt(in[1]); i++) {
                        rope[0][0]++;
                        space = redrawSpace(space);
                        if (DEBUG_PRINTS) {
                            printSpace(space, false);
                        }
                    }
                    break;

            }
        }

        // Finishing up
        printSpace(visited, true);
        System.out.println("Total spaces the tail visited: " + calcVisited());
    }

    // Makes a nice print of the current plane
    public static void printSpace(Character[][] s, Boolean tofile) throws IOException {
        File file = new File("day9/output.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);

        for (int i = 0; i < SPACE_SIZE; i++) {
            for (int j = 0; j < SPACE_SIZE; j++) {
                if (!tofile) {
                    System.out.print(s[i][j]);
                } else {
                    fw.write(s[i][j]);

                }
            }
            if (!tofile) {
                System.out.println();
            } else {
                fw.write("\n");
            }
        }
        if (!tofile) {
            System.out.println();
        }
        fw.close();
    }

    // Calculates the different tail positions and refills the plane
    public static Character[][] redrawSpace(Character[][] s) {
        for (int i = 0; i < SPACE_SIZE; i++) {
            for (int j = 0; j < SPACE_SIZE; j++) {
                s[i][j] = '.';
            }
        }

        // CALC tail pos
        for (int i = 0; i < ROPE_LENGTH - 1; i++) {
            int deltaI = rope[i + 1][0] - rope[i][0];
            if (deltaI < 0)
                deltaI = deltaI * -1;
            int deltaJ = rope[i + 1][1] - rope[i][1];
            if (deltaJ < 0)
                deltaJ = deltaJ * -1;
            int delta = deltaI + deltaJ;
            // Move tail vertical
            if (deltaI == 2 && deltaJ == 0) {
                if (rope[i + 1][0] > rope[i][0]) {
                    rope[i + 1][0]--;
                } else {
                    rope[i + 1][0]++;
                }
            }
            // Move tail horizontal
            else if (deltaJ == 2 && deltaI == 0) {
                if (rope[i + 1][1] > rope[i][1]) {
                    rope[i + 1][1]--;
                } else {
                    rope[i + 1][1]++;
                }
            }
            // Move tail diagonal
            else if (delta > 2) {
                if (rope[i + 1][0] > rope[i][0]) {
                    rope[i + 1][0]--;
                } else {
                    rope[i + 1][0]++;
                }
                if (rope[i + 1][1] > rope[i][1]) {
                    rope[i + 1][1]--;
                } else {
                    rope[i + 1][1]++;
                }
            }
            // draws the current member onto the plane. Since this code goes from Head to
            // tail, the tail will be displayed instead of the head, like in the examples.
            s[rope[i + 1][0]][rope[i + 1][1]] = intToChar(rope[i + 1][2]);
            s[rope[i][0]][rope[i][1]] = intToChar(rope[i][2]);
            // add tail pos to visited spaces plane
            if (i == ROPE_LENGTH - 2)
                visited[rope[i + 1][0]][rope[i + 1][1]] = 'V';
        }

        return s;
    }

    // Count the visited spaces by the tail
    public static int calcVisited() {
        int total = 0;
        for (int i = 0; i < SPACE_SIZE; i++) {
            for (int j = 0; j < SPACE_SIZE; j++) {
                if (visited[i][j] == 'V') {
                    total++;
                }
            }
        }
        return total;
    }

    // Small int to Character conversion method
    public static Character intToChar(Integer in) {
        String s = in.toString();
        return s.charAt(0);
    }
}
