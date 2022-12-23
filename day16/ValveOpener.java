package day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ValveOpener {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("day16/inputEx.txt"));
        ArrayList<Valve> valves = new ArrayList<>();

        while (sc.hasNextLine()) {
            String in = sc.nextLine();
            Valve v = new Valve(in.substring(6, 8));
            in = in.substring(23);
            String[] strings = in.split(";");
            v.setFlowRate(Integer.parseInt(strings[0]));
            in = strings[1].substring(24);
            v.setPartners(in);
            valves.add(v);
        }
        // Connect valves
        for (Valve v : valves) {
            String partnerstring = v.getPartners();
            String[] partners = partnerstring.split(", ");
            for (String s : partners) {
                for (Valve vSearch : valves) {
                    if (s.equals(vSearch.getName())) {
                        v.addConnection(vSearch);
                    }
                }
            }
        }

        // Simulation
        Integer currentValve = 0; //0 ex ; 28 real
        Integer currentOutflow= 0;
        Integer totalOutflow=0;
        Valve v = valves.get(currentValve);
        for (int time = 30; time >=0; time--) {
            totalOutflow+=currentOutflow;
            if(v.getFlowRate()>0&&v.isOpened()==false)
            {
                v.open();
                currentOutflow+=v.getFlowRate();
            } 
            else
           {} 
        }
    }

}
