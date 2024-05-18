import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class representing a linked list.
 */
public class List<E> implements Iterable<E>{
   private ListNode<E> head; // first node is the list
   private ListNode<E> tail; // last node is the list

   /* Constructor that initializes an empty list.
    * Sets both head and tail to null */
   public List() {head = tail = null;}

   /* Inserts item at end of List */
   public void add(E insertItem) {
      if (isEmpty()) { // head and tail refer to same object
         head = tail = new ListNode<E>(insertItem);
      } 
      else { // tail's nextNode refers to new node
    	  tail.setNext(new ListNode<E>(insertItem));
    	  tail = tail.getNext();
      } 
   } 
   
   /* Returns head of list */
   public ListNode<E> getHead() throws EmptyListException{
	   checkIfEmpty();
	   return head;
   }
   
   /* Returns tail of list */
   public ListNode<E> getTail() throws EmptyListException{
	   checkIfEmpty();
	   return tail;
   }


   /*  Remove first node from List */
   public E remove() throws EmptyListException {
      E removedItem = getHead().getData(); // retrieve data being removed

      // update references firstNode and lastNode 
      if (head == tail) 
         head = tail = null;
      else
         head = head.getNext();

      return removedItem; // return removed node data
   } 

   /* Determine whether list is empty; returns true if so */
   private boolean isEmpty() {return head == null;}
   
   /* Check for empty exception */
   private void checkIfEmpty() throws EmptyListException {
	    if (isEmpty())
	        throw new EmptyListException("List is empty!");
	}
   
   /* toString method - returns representation of list */
	@Override
	public String toString() {
		if (isEmpty())
			return "Empty list.";
		
		String st = "";
		int counter = 0;
		
		// while not at end of list, output current node's data
		 for (E data : this) {
		        counter++;
		        st += counter+". " + data.toString() + "\n";
		    }
		 
		return st;
	}
	
	/**
	 * Iterator implementation
	 */
	/* create instance of ListIterator */
	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	/* ListIterator Class */
	private class ListIterator implements Iterator<E> {
		private ListNode<E> current;

		public ListIterator() {current = head;}

		@Override
		public boolean hasNext() {return current != null;}

		@Override
		public E next() {
			if (!hasNext()) {throw new NoSuchElementException();}

			E data = current.getData();
			current = current.getNext();
			return data;
		}
	}

} 
