import java.util.ArrayList;
import java.util.Random;

/**
 * BirthdayChecker --- program to find the probability of 
 * 2 people sharing a birthday in a room of a specified size
 * @author Nick Domenico
 */
public class BirthdayChecker {

	// instance variables
	private double successes = 0;
	private double failures = 0;
	
	
	// ArrayList of Person objects
	ArrayList<Person> room = new ArrayList<Person>();
	
	
	Random rand = new Random();
	
	
	/**
	 * Resets the room ArrayList with roomSize
	 * number of people with random birthdays
	 * @param roomSize
	 */
	public void resetRoom(int roomSize) {
		room.clear(); // removes all Person objects from room
		for (int i = 0; i < roomSize; i++) {
			// adds new Person objects with birthday initialized from 1-365
			room.add(new Person(rand.nextInt(365) + 1)); 
		}
	}
	
	
	/**
	 * Compares all of the people with randomly generated 
	 * birthdays in ArrayList room "int numberOfRuns" times.
	 * If 2 people share the same birthday, adds 1 to successes,
	 * then prints the number of successes, failures, and the 
	 * probability at the end.
	 * @param int roomSize
	 * @param int numberOfRuns
	 */
	public void checkBirthdays(int roomSize, int numberOfRuns) {
		
		resetRoom(roomSize); // resets room to initialize
		
		// loops through numberOfRuns times
		for (int i = 0; i < numberOfRuns; i++) {
			
			boolean sameBirthdayFound = false;
			
			for (int j = 0; j < room.size(); j++) {
				int birthday = room.get(j).getBirthday();
				
				for (int k = 0; k < room.size(); k++) {
					
					// if 2 people share a birthday, adds to successes and ends the loops
					if (room.get(k).getBirthday() == birthday && j != k) {
						sameBirthdayFound = true;
						successes++;
						break;
					}
				}
				
				if (sameBirthdayFound == true) {
					break;
				}
			}
			
			// if nobody in room shared a birthdays, adds to failures
			if (sameBirthdayFound == false) {
				failures++;
			}
			
			resetRoom(roomSize); // resets room for next run
		}
		
		System.out.println("Number of successes: " + successes);
		System.out.println("Number of failures: " + failures);
		System.out.println("Probability of 2 people having same birthday in a room of " + roomSize + " people: " + successes + "/" + numberOfRuns + " = " + successes/numberOfRuns);
	}
	
}
