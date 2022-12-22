package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class Cleanup2 {
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
        // printNiceTerminal(terminal);

        // Make moves
        while (sc.hasNext()) {
            // printNiceTerminal(terminal2);
            Deque<Character> temp = new ArrayDeque<Character>();
            String newLine = sc.nextLine();
            String[] splitStrings = newLine.split("[ ]", 0);
            for (int i = 0; i < Integer.parseInt(splitStrings[1]); i++) {
                Character c = (Character) terminal.get(Integer.parseInt(splitStrings[3]) - 1).pop();
                temp.push(c);
            }
            int tempsize = temp.size();
            for (int i = 0; i < tempsize; i++) {
                terminal.get(Integer.parseInt(splitStrings[5]) - 1).push(temp.pop());
            }
        }

        // Finalize
        String result = "";
        for (int i = 0; i < terminal.size(); i++) {
            try {
                result += terminal.get(i).pop();
            } catch (Exception e) {
                result += " ";
            }
        }
        System.out.println(result);
    }

    static void printNiceTerminal(ArrayList<Deque> a) {
        int limit = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).size() > limit)
                limit = a.get(i).size();
        }

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < a.size(); j++) {
                Character c = ' ';
                try {
                    c = (Character) a.get(j).getLast();
                    a.get(j).removeLast();
                } catch (Exception e) {
                }
                System.out.print("[" + c + "] ");

            }
            System.out.println();
        }
    }
}
