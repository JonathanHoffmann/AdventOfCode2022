package day3;

import java.util.ArrayList;

public class Rucksack {
    private ArrayList<Integer> content;
    private ArrayList<Integer> comp1;
    private ArrayList<Integer> comp2;

    public ArrayList<Integer> getContent() {
        return content;
    }

    public ArrayList<Integer> getContentComp1() {
        return comp1;
    }

    public ArrayList<Integer> getContentComp2() {
        return comp2;
    }

    public Rucksack(String s) {
        content = new ArrayList<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char inChar : s.toCharArray()) {
            int i = 0;
            for (char ch : alphabet.toCharArray()) {
                i++;
                if (ch == inChar) {
                    content.add(i);
                    break;
                }
            }
        }
        splitContentIntoCompartments();
    }

    private void splitContentIntoCompartments()
    {
        comp1 = new ArrayList<>();
        for (int i = 0; i < content.size() / 2; i++) {
            comp1.add(content.get(i));
        }
        comp2 = new ArrayList<>();
        for (int i = content.size() / 2 ; i < content.size(); i++) {
            comp2.add(content.get(i));
        }
    }
}