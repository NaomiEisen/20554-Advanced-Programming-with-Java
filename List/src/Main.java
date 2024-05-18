import java.util.Scanner;
/**
 * The main class for the application.
 * Utilizes the List, Person, and Max classes to demonstrate their functionality.
 */
public class Main {
	// the size of the string list to scan from the user
	private static final int STRING_LIST_SIZE = 6;
	
	/* the main method of this application */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in); // Scanner instance
		
		/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
		 * 					Section C of the Task
		 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

		System.out.println("\t___________ Section C ___________\n");
		System.out.println("Please enter 6 strings for your list:");
		
		// create an empty String list
		List<String> stringList = new List<String>(); 
		
		// add inputed Strings to list
		for (int i = 0; i < STRING_LIST_SIZE; i++)
			stringList.add(scan.next());
		
		// print list
		System.out.println("Your list:\n" + stringList); 
		
		// reverse list
		reverseList(stringList);
		
		// print the reversed list
		System.out.println("Reversed list:\n" + stringList);
		scan.close(); // close Scanner object
		
		/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
		 * 					Section E of the Task
		 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
		
		// create list of Person Objects
		List<Person> personList = new List<Person>(); // create empty list
		
		// fill the list
		try {
			personList.add(new Person("Alice", 2009, "567-657-756"));
			personList.add(new Person("Naomi", 2002, "212-643-126"));
			personList.add(new Person("Danchik", 2001, "123456789"));
			personList.add(new Person());
		} catch (IllegalArgumentException IllegalArgumentException) {
			System.err.printf("Exception: %s%n", IllegalArgumentException);
		}
		
		// print the list
		System.out.println("\t___________ Section e ___________\n");
		System.out.println("The list of Person Objects:");
		System.out.println(personList);
		
		// print the max element of list - the oldest person
		try {
			System.out.println("The oldest person in this list is >>");
			System.out.println((new Max<Person>()).max(personList) );
		}
		catch (EmptyListException e) {
			System.out.println("Empty list!");
		}
	}
	
	/* Method that reverses the specified list */
	 static <E> void reverseList(List<E> list){
	        E element;
			try {
				element = list.remove(); // remove element
				reverseList(list); // recursively call this method
	            list.add(element); // add the removed element
	            
			} // end of list reached or list was empty - base case of recursion
			catch (EmptyListException EmptyListException) {} 
	}
} // end of class Main
