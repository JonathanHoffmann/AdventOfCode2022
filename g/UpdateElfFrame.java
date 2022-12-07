package g;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;

public class UpdateElfFrame {
    public final static int MAX_FOLDER_SIZE = 100000;
    public final static int FILESYSTEM_TOTAL_SIZE = 70000000;
    public final static int UPDATE_SIZE = 30000000;

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
            if (command.equals("$ ls")) { // Nothing to do for command ls
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
                System.err.println("Unknown command");
            }
        }

        // Going to root directory "/"
        while (currentFolder.size() > 1) {
            currentFolder.pop();
        }

        // Task 1
        currentFolder.peek().findDirectoriesUnderSize(MAX_FOLDER_SIZE);
        System.out.println(
                "TASK 1:\nTotal size of directories with file sizes below " + single.getFormat().format(MAX_FOLDER_SIZE)
                        + ": " + single.getFormat().format(single.getFilesBelowSize()));

        // Task2
        int requiredSpace = UPDATE_SIZE - (FILESYSTEM_TOTAL_SIZE - currentFolder.peek().getSize());
        currentFolder.peek().findSmallestDirToDelete(requiredSpace);
        System.out.println("TASK 2:\nSmallest Directory to delete and fit the update: " + single.getSmallestToDelete());
    }
}
