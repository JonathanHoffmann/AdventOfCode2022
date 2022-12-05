package c;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("c/input.txt"));
        ArrayList<Rucksack> rucksacks = new ArrayList<>();
        Integer totalTask1 = 0;
        Integer totalTask2 = 0;

        while (sc.hasNext()) {
            String newLine = sc.nextLine();
            Rucksack r = new Rucksack(newLine);
            rucksacks.add(r);
            Boolean found = false;
            for (int i = 0; !found && i < r.getContentComp1().size(); i++) {
                for (int j = 0; !found && j < r.getContentComp2().size(); j++) {
                    if (r.getContentComp1().get(i) == r.getContentComp2().get(j)) {
                        found = true;
                        totalTask1 += r.getContentComp1().get(i);
                    }
                }
            }
        }

        //task 2
        for(int i = 0;i<rucksacks.size();i+=3)
        {
            for(int j = 0;j<rucksacks.get(i).getContent().size();j++)
            {
                Integer search = rucksacks.get(i).getContent().get(j);
                //System.out.println(searchcomp1);
                if(rucksacks.get(i+1).getContent().contains(search)&&rucksacks.get(i+2).getContent().contains(search))
                {
                    System.out.println("All 3 elves in group " + (i+3)/3 + " carry the item " + search);
                    totalTask2 += search;
                    break;
                }
            }
        }

        System.out.println("Total for Task 1: " + totalTask1);
        System.out.println("Total for Task 2: " + totalTask2);
    }
}