package g;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;

public class Task {
    public static void main(String[] args) throws FileNotFoundException {

        // Startup, make initial file system
        Scanner sc = new Scanner(new File("g/input.txt"));
        Singleton single = new Singleton();
        String backCD = "$ cd ..";
        Deque<ElfDirectory> currentFolder = new LinkedList<>();
        currentFolder.push(new ElfDirectory("/", single));
        single.setSmallestToDelete(currentFolder.peek());
        sc.nextLine(); // Skip "$ cd /"
        while (sc.hasNext()) {
            String command = sc.nextLine();
            String firstChar = command.substring(0, 1);
            String regexNum = "[0-9]";
            if (command.equals("$ ls")) {
            } else if (command.equals(backCD)) {
                currentFolder.pop();
            } else if (command.startsWith("dir", 0)) {
                currentFolder.peek().addContent(new ElfDirectory(command.substring(4), single));
            } else if (command.startsWith("$ cd", 0)) {
                currentFolder.push(currentFolder.peek().findFolder(command.substring(5)));
            } else if (firstChar.matches(regexNum)) {
                String[] splitStrings = command.split("[ ]", 0);
                int size = Integer.parseInt(splitStrings[0]);
                currentFolder.peek().addContent(new ElfFile(size));
            } else {
                System.out.println("unknown command");
            }
        }

        // Indexing sizes and final output
        // Going to root directory "/"
        while (currentFolder.size() > 1) {
            currentFolder.pop();
        }
        System.out.println("Total size = " + currentFolder.peek().getSize());
        System.out.println("Total size of directories with file sizes below 10 000 000: " + single.getFilesBelowSize());
        System.out.println("Smallest Directory to delete and fit the update: " + single.getSmallestToDelete().getName()
                + " size: " + single.getSmallestToDelete().getSize());
    }
}
