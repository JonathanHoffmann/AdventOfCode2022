package day8;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ForestMapper {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day8/input.txt"));
        ArrayList<ArrayList> forest = new ArrayList<>();
        Integer visibleTrees = 0;

        // Planting the forest
        while (sc.hasNextLine()) {
            String in = sc.nextLine();
            ArrayList<Tree> row = new ArrayList<Tree>();
            for (Character c : in.toCharArray()) {
                row.add(new Tree(Integer.parseInt(c.toString())));
            }
            forest.add(row);
        }

        // Searching the forest
        // left to right
        for (int i = 0; i < forest.size(); i++) {
            int highestTree = -1;
            for (int j = 0; j < forest.get(i).size(); j++) {
                Tree t = (Tree) forest.get(i).get(j);
                if (t.getSize() > highestTree) {
                    t.makeOutsideVis();
                    highestTree = t.getSize();
                }
            }
        }

        // top down
        for (int j = 0; j < forest.size(); j++) {
            int highestTree = -1;
            for (int i = 0; i < forest.get(j).size(); i++) {
                Tree t = (Tree) forest.get(i).get(j);

                if (t.getSize() > highestTree) {
                    t.makeOutsideVis();
                    highestTree = t.getSize();
                }
            }
        }

        // right to left
        for (int i = forest.size() - 1; i >= 0; i--) {
            int highestTree = -1;
            for (int j = forest.get(i).size() - 1; j >= 0; j--) {
                Tree t = (Tree) forest.get(i).get(j);
                if (t.getSize() > highestTree) {
                    t.makeOutsideVis();
                    highestTree = t.getSize();
                }
            }
        }

        // Bottom up
        for (int i = forest.size() - 1; i >= 0; i--) {
            int highestTree = -1;
            for (int j = forest.get(i).size() - 1; j >= 0; j--) {
                Tree t = (Tree) forest.get(j).get(i);
                if (t.getSize() > highestTree) {
                    t.makeOutsideVis();
                    highestTree = t.getSize();
                }
            }
        }

        // count vis
        for (int i = 0; i < forest.size(); i++) {
            for (int j = forest.get(i).size() - 1; j >= 0; j--) {
                Tree t = (Tree) forest.get(i).get(j);
                if (t.getVis()) {
                    visibleTrees++;
                }
            }
        }
        System.out.println(visibleTrees);
    }
}
