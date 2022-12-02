package b;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RockPaperScissor {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("b/input.txt"));
        Integer opponentScore = 0;
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
                    ownInt = 1;
                    break;
                case 'Y':
                    ownInt = 2;
                    break;
                case 'Z':
                    ownInt = 3;
                    break;
            }
            Integer opPlus = opponentInt + 1;
            if (opPlus == 4)
                opPlus = 1;
            Integer opMinus  = opponentInt -1;
            if (opMinus == 0)
                opMinus = 3;

            // Draw
            if (opponentInt == ownInt)
            {
                opponentScore += 3;
                ownScore += 3;
            }
            //win
            else if(opPlus == ownInt)
            {
                ownScore += 6;
            }
            //loose
            else if (opMinus == ownInt)
            {
                opponentScore += 6;
            }
            opponentScore += opponentInt;
            ownScore += ownInt;
        }
        System.out.println("Opponent score: " + opponentScore + "\nOwn score: " + ownScore);
    }
}