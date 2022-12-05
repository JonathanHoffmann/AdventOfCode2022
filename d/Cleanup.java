package d;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cleanup {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d/input.txt"));
        int fullyContained = 0;
        int overlap = 0;

        while (sc.hasNext()) {
            String newLine = sc.nextLine();
            newLine.replaceAll("\\s", "");
            String[] splitStrings = newLine.split("[,-]", 0);
            int e1start = Integer.parseInt(splitStrings[0]);
            int e1stop = Integer.parseInt(splitStrings[1]);
            int e2start = Integer.parseInt(splitStrings[2]);
            int e2stop = Integer.parseInt(splitStrings[3]);

            if (e1start <= e2start && e2start <= e1stop && e1start <= e2stop && e2stop <= e1stop) {
                fullyContained++;

            } else if (e2start <= e1start && e1start <= e2stop && e2start <= e1stop && e1stop <= e2stop) {
                fullyContained++;
            }

            // Task2
            if ((e1start <= e2start && e2start <= e1stop) || (e1start <= e2stop && e2stop <= e1stop)) {
                overlap++;

            } else if ((e2start <= e1start && e1start <= e2stop) || (e2start <= e1stop && e1stop <= e2stop)) {
                overlap++;
            }
        }
        System.out.println("Fully contained: " + fullyContained);
        System.out.println("Overlap: " + overlap);
    }
}
