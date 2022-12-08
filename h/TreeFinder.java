package h;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TreeFinder {
    private static final int FOREST_SIZE = 99;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("h/input.txt"));
        Integer highestScenicScore = 0;
        Forest forest = new Forest(FOREST_SIZE); // Hardcoded, not nice

        // Planting the forest
        int i = 0;
        while (sc.hasNextLine()) {
            String in = sc.nextLine();
            char[] chars = in.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                String temp = "";
                temp += chars[j];
                Tree t = new Tree(Integer.parseInt(temp));
                forest.setTree(t, i, j);
            }
            i++;
        }

        for (i = 0; i < FOREST_SIZE; i++) {
            for (int j = 0; j < FOREST_SIZE; j++) {
                Integer ownSize = forest.getTree(i, j).getSize();
                Integer right = 0;
                Integer left = 0;
                Integer down = 0;
                Integer up = 0;

                // down
                for (int k = i + 1; k < FOREST_SIZE; k++) {
                    if (forest.getTree(k, j).getSize() < ownSize) {
                        down++;
                    } else {
                        k = FOREST_SIZE;
                        down++;
                    }
                }
                
                // up
                for (int k = i - 1; k >= 0; k--) {
                    if (forest.getTree(k, j).getSize() < ownSize) {
                        up++;
                    } else {
                        k = 0;
                        up++;
                    }
                }

                // right
                for (int k = j + 1; k < FOREST_SIZE; k++) {

                    if (forest.getTree(i, k).getSize() < ownSize) {
                        right++;
                    } else {
                        k = FOREST_SIZE;
                        right++;
                    }
                }

                // left
                for (int k = j - 1; k >= 0; k--) {
                    if (forest.getTree(i, k).getSize() < ownSize) {
                        left++;
                    }
                    else{
                        k=0;
                        left++;
                    }
                }

                forest.getTree(i, j).setScene(right * left * down * up);
                if (forest.getTree(i, j).getScene() > highestScenicScore) {
                    highestScenicScore = forest.getTree(i, j).getScene();
                }
            }
        }
        System.out.println("Highest scenic score for a tree: " + highestScenicScore);
    }
}
