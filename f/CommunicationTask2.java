package f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CommunicationTask2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("f/input.txt"));

        ArrayList<Character> q = new ArrayList<>();
        String input = sc.nextLine();
        int transmitionStart = 0;
        while (input.length() > 0) {
            if (q.size() < 14) {
                q.add(input.charAt(0));
                input = input.substring(1);
            } else {
                Set<Character> set = new HashSet<>();
                set.addAll(q);
                if (set.size() == 14) {
                    input = "";
                    System.out.println("Transmition starts at " + transmitionStart);
                } else {
                    q.remove(0);
                    q.add(input.charAt(0));
                    input = input.substring(1);
                }
            }
            transmitionStart++;
        }
    }
}
