/**
 * Person --- program to create Person objects 
 * to be used in the BirthdayChecker class
 * @author Nick Domenico
 */
public class Person {

	// instance variable
	private int birthday = 0; // will be initialized from 1-365
	
	
	/**
	 * Creates a Person object 
	 * with a birthday
	 * @param int birthday
	 */
	public Person(int birthday) {
		this.birthday = birthday;
	}
	
	
	/**
	 * Returns the person's birthday
	 * @return int
	 */
	public int getBirthday() {
		return birthday;
	}
}
