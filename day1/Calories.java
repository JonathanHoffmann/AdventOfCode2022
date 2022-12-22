package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Calories {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("day1/input.txt"));
		ArrayList<Integer> elfs = new ArrayList<Integer>();
		Integer elfTotal = 0;
		while (sc.hasNext()) {
			String row = sc.nextLine();
			if (row.equals("")) {
				elfs.add(elfTotal);
				elfTotal = 0;
			} else {
				elfTotal += Integer.parseInt(row);
			}
		}
		elfs.sort(null);

		System.out.println("Fattest elf: " + elfs.get(elfs.size() - 1) + "\nHeavy Elf: " + elfs.get(elfs.size() - 2)
				+ "\nChubby Elf: " + elfs.get(elfs.size() - 3) + "\nTotal of 3 most loaded elfs: "
				+ (elfs.get(elfs.size() - 1) + elfs.get(elfs.size() - 2) + elfs.get(elfs.size() - 3)));
	}
}