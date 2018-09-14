import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * The goal of the program is to minimise the damage done to the shield
 * so that it is less than or equal to the shield
 * eg. Input Case is:
 * 1 CS
 * As of now it will charge, changing the damage value from 1 to 2 and then shoot
 * This will be greater than the shield so we must re-arrange it
 * In this case all we need to do is rearrange it once
 * 1 SC
 * This case succeeds
 * 
 */

public class Solution_1 {

	public static void main(String[] args) throws IOException {

		// Used to indicate whether file is read from directory or passed by argument
		boolean submit = false;

		// Scanner
		Scanner s = null;

		// Creating two arrays, the array which holds input and the solution array
		int[] shield_numbers;

		String[] line, robot_programs;

		String storage = " ";

		// Holds list read in from file

		// Reading in first line to store the first line
		int count;

		// Try catch block to catch any file not found exceptions
		try {

			// If we decide to read by passed argument we fall in here
			if (submit == true) {
				s = new Scanner(System.in);

			}

			else {

				// Otherwise we read in the file from the directory of the class
				s = new Scanner(new File(System.getProperty("user.dir") + "\\src\\input.txt"));
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		count = Integer.parseInt(s.nextLine());

		shield_numbers = new int[count];
		robot_programs = new String[count];

		for (int i = 0; i < count; i++) {
			line = s.nextLine().split(" ");
			shield_numbers[i] = Integer.parseInt(line[0]);
			robot_programs[i] = line[1];

		}

		for (int i = 0; i < count; i++) {
			storage = "Case #" + Integer.toString(i + 1) + ": ";
			storage += bubbleSort(robot_programs[i].split(""), shield_numbers[i]);

			System.out.println(storage);

		}
		
		System.exit(0);

	}

	
	/**
	 * This method uses a bubble sort to iterate through the contents of the list passed in
	 * to minimise the maximum damage dealt to the shield
	 * 
	 * We re-arrange each member of the list and upon each movement,
	 * we increase the hack counter and then check if it's less than or equal to the shield value yet
	 * If it is then we return how many hacks it took
	 * If we sort as much as possible and it still isn't less than or equal
	 * Then we return IMPOSSIBLE
	 * To generate the damage done to the shield
	 * @param sortee - list to be sorted
	 * @param shield_value - value of the shield in this scenario
	 * @return
	 */
	
	
	public static String bubbleSort(String[] sortee, int shield_value) {

		// Variables used to store two values
		String temp1 = " ", temp2 = " ";

		String return_value = " ";

		//The current damage value
		int damage_value = 1;

		//Indicates the current damage dealt
		int damage_done = 0;

		//Tells us the number of hacks made
		int hacks_done = 0;

		// Indicates whether or not
		// our list is sorted
		boolean swapped = false;

		// A counter
		int x = 0;

		/*
		 * Keeps on looping while the list isn't sorted completely
		 * 
		 */

		while (swapped == false) {
			damage_value = 1;

			damage_done = 0;

			for (int i = 0; i < sortee.length; i++) {

				
				/*
				 * If it is shoot we add the damage to the current
				 * accumulated damage
				 * Otherwise we double the current damage value
				 */
				if (sortee[i].equals("S"))
					damage_done += damage_value;

				else
					damage_value *= 2;

			}

			if (damage_done <= shield_value)
				swapped = true;

			else {
				// Increasing the counter on each loop
				x++;

				// Resetting the boolean value so we can check again
				swapped = true;

				// Iterate through the list while (sortee.length - x) is greater than 0
				for (int i = 0; i < sortee.length - x; i++) {

					/*
					 * We store the Nth value in temp1 We store the N + 1th value in temp2
					 */
					temp1 = sortee[i];
					temp2 = sortee[i + 1];

					/*
					 * If temp1 is less than temp2 this means that the list is not sorted so we swap
					 * the two values and set our boolean to true
					 */
					if (temp1.equals("C") && temp2.equals("S")) {
						sortee[i] = temp2;
						sortee[i + 1] = temp1;
						swapped = false;
						hacks_done++;
					}

				}
			}
		}

		
		/*
		 * If the overall value after we have sorted the 
		 * list is less than or equal to the shield value
		 * then it is a success. Otherwise if it is 
		 * greater then it is impossible
		 */
		if (damage_done <= damage_value)
			return_value = Integer.toString(hacks_done);

		else
			return_value = "IMPOSSIBLE";

		return return_value;
	}

}
