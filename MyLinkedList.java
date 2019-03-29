import java.util.List;
import java.util.NoSuchElementException;

public class MyLinkedList<E> {
  private int size;
  private Node start,end;

  //Node class
  private class Node{
     private E data;
     private Node next,prev;

     //constructors
     public Node(int x) {
       data = x;
     }
     public Node(int x, Node nextN, Node prevN) {
       data = x;
       next = nextN;
       prev = prevN;
     }

     //methods to get the private variables
     public E getData() {
       return data;
     }
     public Node getNext() {
       return next;
     }
     public Node getPrev() {
       return prev;
     }

     //methods to set the variables
     public void setData(E newData) {
       data = newData;
     }
     public void setPrev(Node newPrev) {
       prev = newPrev;
     }
     public void setNext(Node newNext) {
       next = newNext;
     }

    }
    //end of Node class

   //creates a new, empty list
   //sets size to 0
   public MyLinkedList(){
     size = 0;
   }

   //returns size of list
   public int size() {
     return size;
   }

   //adds the value to the end of the list
   public boolean add(E value) {
     Node newVal = new Node(value); //creates a new node with that value
     if (size() == 0) { //special case: if size is 0
       start = newVal; //the node becomes the start
       end = newVal;
       size += 1; //increases the size by one
       return true;
     }
     if (size() == 1) { //special case: if size is 1 and end is still null
         end = newVal; //the node becomes the end
         start.setNext(newVal); //sets the next of start to the node
         end.setPrev(start); //sets the node's prev to start
         size += 1; //size increases by one
         return true;
      }
     else { //all other cases
         end.setNext(newVal); //the end gets the new node as next
         newVal.setPrev(end); //the node gets the previous end as its previous node
         end = newVal; //the new Node becomes the end
         size += 1; //size increases by one
         return true;
     }
   }

   //getting the node at a specific index
   private Node getNthNode(int index) {
     Node current = start; //this node keeps track of the current node as the loop continues
     for (int i = 0; i < size(); i++) {
       if (i < index) {
         current = current.getNext(); //while the loop hasn't reached the desired index, the next node becomes current
       }
     }
     return current;
   }

   //returns the value at a specific index
   public E get(int index) {
     if (index < 0 || index > size()) { //for exceptions
       throw new IndexOutOfBoundsException();
     }
     return getNthNode(index).getData(); //finds the node at the desired index and returns its value
   }

   //sets the value of the node at a certain index to a new value
   public E set(int index,E value) {
     if (index < 0 || index > size()) { //for exceptions
       throw new IndexOutOfBoundsException();
     }
     Node current = getNthNode(index); //finds the node at the desired index
     Integer currentData = current.getData(); //stores the original value of the node
     current.setData(value); //sets the original value to the new one
     return currentData; //returns the old value
   }

   //checks to see if the list contains a value
   public boolean contains(E value) {
     Node current = start;
     for (int i = 0; i < size; i++) { //loops through to see if a node's value is equal to the desired value
       if (current.getData().equals(value)) {
         return true;
       }
       current = current.getNext(); //if the node's value isn't equal, the next node becomes current
     }
     return false;
   }

   //finds the first index of a value within the list, if it is in the list
   //returns -1 if it isn't
   public int indexOf(E value) {
     Node current = start;
     for (int i = 0; i < size; i++) { //loops through the list to see if a node's value is equal to the desired value
       if (current.getData().equals(value)) {
         return i; //if it is, returns the index of that node
       }
       current = current.getNext(); //if it isn't, the next node becomes current
     }
     return -1;
   }

   //adds a new value to a speicifc index of the list
   public void add(int index,E value) {
     if(index < 0 || index > size()){
      throw new IndexOutOfBoundsException();
    }
     Node newNode = new Node(value); //creates a new node with the value
     size += 1; //increases the size by one to allow for the new node
     if (index == 0) { //special case: adding to the beg of the list
       newNode.setNext(start); //sets the new node's next to the old start
       start.setPrev(newNode); //the old start sets the new node to its prev
       start = newNode; //the new node becomes start
     }
     else {
       if (index == size() - 1) { //special case: adding to the end of the list
         end.setNext(newNode); //sets the next of the old end to the new node
         newNode.setPrev(end); //sets the new node's prev to the old end
         end = newNode; //the new node becomes end
       }
       else { //all other cases
         Node prevNode = getNthNode(index-1); //new nodes created for easier visualization
         Node nextNode = getNthNode(index);
         prevNode.setNext(newNode); //sets the next of the node right before the index to the new node
         newNode.setPrev(prevNode); //sets the prev of the new node to the node right before the index
         newNode.setNext(nextNode); //sets the next of the new node to the node originally at the index
         nextNode.setPrev(newNode); //sets the prev of the node originally at the index to the new node
       }
     }
   }

   //removes the node of a specific index
   //returns the value of the removed node
   public E remove(int index) {
     if(index < 0 || index > size()){ //throws an excpetion if index is not within the list
      throw new IndexOutOfBoundsException();
    }
    else {
      Node removed = getNthNode(index); //finds the node that is going to be removed
      if (index == 0) { //special case: removing from the beg of the list
        start = removed.getNext(); //start gets the node after the node that is about to be removed
        start.setPrev(null); //the new start sets its prev to null
        size -= 1; //size decreases by one
      }
      else {
        if (index < size() - 1) {
          removed.getPrev().setNext(removed.getNext()); //the next of the node before the removed node gets the node after the removed node
          removed.getNext().setPrev(removed.getPrev()); //the prev of the node after the removed node gets the node before the removed node
          size -= 1; //size deceases by one
        }
        else { //special case: removing from the end of the list
          end = removed.getPrev(); //the node before the removed node becomes the new end
          end.setNext(null); //the next of the new end is set to null
          size -= 1; //size decreases by one
        }
      }
      return removed.getData(); //the value of the removed node is returned
    }
   }

   //removes the first occurence of a value in the list
   //returns true if removed successfully, false if not
   public boolean remove(Integer value) {
     if (!contains(value)) { //throws an exception if the value can't be found
       return false;
     }
     else { //checks to see if the list contains the value before proceeding
       remove(indexOf(value)); //finds the first index of the value within the list and uses the previous remove function to remove the node of the value
       return true;
     }
   }

   public void clear() {
     size = 0;
     start = null;
     end = null;
   }
   //in O(1) runtime, move the elements from other onto the end of this
   //The size of other is reduced to 0
   public void extend(MyLinkedList other){
      size += other.size(); //size becomes the combined sizes of both original lists
      this.end.setNext(other.start); //the next of the end of the first list gets the start of the second list
      other.start.setPrev(this.end); //the prev of the start of the second list gets the end of the first list
      end = other.end; //the end of the second list becomes the end of the combined list
      other.size = 0; //the other list is made empty
      other.end = null;
      other.start = null;
    }

   //prints out the list
   public String toString() {
     String ans = "["; //a string created to print out the list
     Node current = start; //keeps track of the current node
     for (int i = 0; i < size; i++) {
       if (i == 0) { //if it's the beginning of the list
         ans += start.getData(); //the start node is added to the string
         current = start.getNext(); //next node becomes current
       }
       else {
         if (i < size - 1) {
           ans += ", " + current.getData(); //a comma is added between each new value added to the list
           current = current.getNext();
         }
         if (i == size - 1) { //last value of the list, there are no more nodes to search for
           ans += ", " + current.getData(); //adds the value of the last node to the list
         }
       }
     }
     return ans  + "]"; //closes string with brackets
   }

  public static void main(String[] args) {
 }
}
