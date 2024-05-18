/**
 * Class representing a node in a linked list
 */
public class ListNode<E> {
	
   private E data; // data stored in this node
   private ListNode<E> nextNode; // reference to the next node in the list

   /* Constructor that initializes a ListNode with the provided data */
   public ListNode(E data) {this(data, null);}

   /* Constructor that initializes a ListNode with the provided data
    * and refers to the specified next node */
   public ListNode(E data, ListNode<E> nextNode) {
      this.data = data;    
      this.nextNode = nextNode;  
   } 

   /* Returns reference to data in node */
   public E getData() {return data;}
   
   /* Sets the specified data in this node */
   public void setData(E data) {this.data = data;}
   
   /* Returns reference to next node in list */
   public ListNode<E> getNext() {return nextNode;}
   
   /* Sets the next node reference of this node to the specified node */
   public void setNext(ListNode<E> nextNode) {this.nextNode = nextNode;}
   
} 
