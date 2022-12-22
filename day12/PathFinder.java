package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PathFinder {
    public static final char[] ALPHABET = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day12/input.txt"));
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        Integer[][] heightMap = new Integer[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            char[] stringChars = input.get(i).toCharArray();
            for (int j = 0; j < stringChars.length; j++) {
                for (int k = 0; k < ALPHABET.length; k++) {
                    if (ALPHABET[k] == stringChars[j]) {
                        heightMap[i][j] = k + 1;
                    }
                }
            }
        }

        printHeightMap(heightMap);
    }

    public static void printHeightMap(Integer[][] hm) {
        System.out.println(hm.length);
        for (int i = 0; i < hm.length; i++) {
            for (int j = 0; j < hm[0].length; j++) {
                System.out.print(hm[i][j]);
                if (hm[i][j]<  9) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }
}
