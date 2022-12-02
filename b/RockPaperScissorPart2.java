package b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RockPaperScissorPart2 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("b/input.txt"));
        Integer ownScore = 0;
        while (sc.hasNextLine()) {
            //Load row and break into columns
            String row = sc.nextLine();
            Character opponent = row.charAt(0);
            Character own = row.charAt(row.length() - 1);
            Integer opponentInt = 0;
            Integer ownInt = 0;

            //translate to 1-3
            switch (opponent) {
                case 'A':
                    opponentInt = 1;
                    break;
                case 'B':
                    opponentInt = 2;
                    break;
                case 'C':
                    opponentInt = 3;
                    break;
            }
            switch (own) {
                case 'X':
                    ownInt = 0;
                    break;
                case 'Y':
                    ownInt = 3;
                    break;
                case 'Z':
                    ownInt = 6;
                    break;
            }
            Integer opPlus = opponentInt + 1;
            if (opPlus == 4)
                opPlus = 1;
            Integer opMinus  = opponentInt -1;
            if (opMinus == 0)
                opMinus = 3;

            // Draw
            if (ownInt == 3)
            {
                ownScore += opponentInt;
            }
            //win
            else if(ownInt == 6)
            {
                ownScore += opPlus;
            }
            //loose
            else if (ownInt == 0)
            {
                ownScore += opMinus;
            }
            ownScore += ownInt;
        }
        System.out.println("Own score: " + ownScore);
    }
}