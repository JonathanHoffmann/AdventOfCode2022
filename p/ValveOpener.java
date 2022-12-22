package p;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ValveOpener {

    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("p/input.txt"));
        ArrayList<Valve> valves = new ArrayList<>();

        while (sc.hasNextLine())
        {
            String in = sc.nextLine();
            Valve v = new Valve(in.substring(6,8));
            in = in.substring(23);
            String[] strings = in.split(";");
            v.setFlowRate(Integer.parseInt(strings[0]));
            in  = strings[1].substring(24);
            System.out.println(in);
            valves.add(v);
        }
    }
    
}
