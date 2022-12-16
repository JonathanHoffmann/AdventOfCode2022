package zUtils;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TwoDArrayUtils {
    public static String TwoDCharArrayToString(Character[][] s) {
        String st = "";
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[0].length; j++) {
                st += s[i][j];
            }
            st += "\n";
        }
        return st;
    }

    public static void TwoDCharArrayToFile(Character[][] s, String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(TwoDCharArrayToString(s));
        fw.close();
    }

    public static int countCharOccurence(Character[][] s, Character c) {
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[0].length; j++) {
                if (s[i][j] == c) {
                    count++;
                }
            }
        }
        return count;
    }

}