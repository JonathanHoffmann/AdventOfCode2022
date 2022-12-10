package j;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignalCalc {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("j/input.txt"));
        int x = 1;
        int outputClock = 20;
        int total = 0;
        Boolean wait = false;
        String row = "";
        int printrow = 0;

        for (int i = 1; sc.hasNextLine(); i++) {
            // Draw pixels
            if (x == i - printrow * 40) {
                System.out.print("#");
            } else if (x == (i - 1) - printrow * 40) {
                System.out.print("#");
            } else if (x == (i - 2) - printrow * 40) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }
            if (i % 40 == 0 && i > 10) {
                System.out.println();
                printrow++;
            }

            // Calculate frequency
            if (i == outputClock) {
                total += x * i;
                outputClock += 40;
            }
            if (wait) {
                wait = false;
                String[] split = row.split(" ");
                x += Integer.parseInt(split[1]);
            } else {
                row = sc.nextLine();

                if (row.startsWith("addx")) {
                    wait = true;
                }
            }
        }
        System.out.println("Frequency total: " + total);
    }
}
