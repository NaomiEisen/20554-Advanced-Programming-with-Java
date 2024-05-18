/**
 * Class that finds the max element in a generic list of comparable objects.
 */
public class Max<E> {

	/*  Method that finds the max element in the specified list using the compareTo
	 * method. throws EmptyListException if list is empty. */
	public Comparable<E> max(List<? extends Comparable<E>> list) throws EmptyListException {
		Comparable<E> max = list.getHead().getData(); // set first element to max
		for (Comparable<E> data : list) { // iterate through list
			if (max.compareTo((E) data) < 0)
				max = data; // if bigger element is found, save it in max
		}
		return max; // return the max element
	}
} // end of class Max
