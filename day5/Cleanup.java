package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class Cleanup {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day5/input.txt"));

        ArrayList<Deque> terminal = new ArrayList<>();
        terminal.add(new ArrayDeque<Character>());
        terminal.add(new ArrayDeque<Character>());
        terminal.add(new ArrayDeque<Character>());
        terminal.add(new ArrayDeque<Character>());
        terminal.add(new ArrayDeque<Character>());
        terminal.add(new ArrayDeque<Character>());
        terminal.add(new ArrayDeque<Character>());
        terminal.add(new ArrayDeque<Character>());
        terminal.add(new ArrayDeque<Character>());

        // Startup sequence
        ArrayList<String> startup = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            startup.add(sc.nextLine());
        }
        for (int i = 7; i >= 0; i--) {
            for (int j = 1, k = 0; j < startup.get(i).length(); j += 4, k++) {
                char c = startup.get(i).charAt(j);
                if (c != ' ')
                    terminal.get(k).push(c);
            }
        }

        // Make moves
        while (sc.hasNext()) {
            String newLine = sc.nextLine();
            String[] splitStrings = newLine.split("[ ]", 0);
            for (int i = 0; i < Integer.parseInt(splitStrings[1]); i++) {
                Character c = (Character) terminal.get(Integer.parseInt(splitStrings[3]) - 1).pop();
                terminal.get(Integer.parseInt(splitStrings[5]) - 1).push(c);
            }

        }

        // Finalize
        String result = "";
        for (int i = 0; i < terminal.size(); i++) {
            result += terminal.get(i).pop();
        }
        System.out.println(result);
    }
}
