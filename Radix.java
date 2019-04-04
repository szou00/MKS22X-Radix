import java.lang.Math;

public class Radix {

  public static void radixsort(int[]data){
    //sort the first digit

    //create linkedlist for all of them?

    //make an array of mylinkedlist for the buckets
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    MyLinkedList<Integer> sorted = new MyLinkedList<Integer>();

    //go through the list and find max number
    //FIND ABSOLUTE VALUE !!! REMEMBER
    int max = Math.abs(data[0]);
    for (int i = 0; i < data.length; i++) {
      if (Math.abs(data[i]) > max) {
        max = data[i];
      }
    }
    // System.out.println(max);

    //finds the number of digits in max
    int md = 1; //including the digit that's below 10
    while (max > 10) {
      md++;
      max=max/10;
    }
    // System.out.println(d);

    //initializing all the buckets
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }

    //sorting out the numbers by digits and putting them into their respective buckets
    //first copy into linkedlist
    for (int x = 0; x < data.length; x++) {
      int b = data[x]%10; //getting their first digit
      if (b >= 0) {
        buckets[b+10].add(data[x]); //adding ifor pos nums
      }
      else {
        buckets[9-Math.abs(b)].add(data[x]); //adding for neg numbers
      }
    }

    merge(buckets,sorted);
    // System.out.println(sorted.size());
    //sorting out the rest of the array
    int digits = 1; //we already printed the first digit
    while (digits != md) {
      for (int x = 0; x < sorted.size(); x++) {
        // System.out.println("size: " + buckets[19].size() + " x: " + x );
        int d = digits; //to use to divide everything
        int newA = sorted.get(x); //to store the values
        // System.out.println("d: "+d);
        while (d != 0) { //keep dividing while it's not zero
          newA = newA/10;
          d--; //subtract the total num of digits
        }
        int b = newA % 10; //take the last digit after dividing
        // System.out.println(newA + " " + md);

        if (b >= 0) {
          buckets[b+10].add(sorted.get(x)); //adding ifor pos nums
        }
        else {
          buckets[9-Math.abs(b)].add(sorted.get(x)); //adding for neg numbers
        }
      }

      digits++; //increase digits
      merge(buckets,sorted); //merge everything together
    }

    //trying to test out the buckets and printing them out
    // for (int i = 0; i < buckets.length; i++) {
    //   // if (buckets[i].size() != 0) {
    //     System.out.println(buckets[i].toString());
    //   // }
    // }
    // System.out.println(sorted.toString());

  }

  //adding it the 20th linked list
  // in their respective positions
  public static void merge(MyLinkedList<Integer>[] buckets, MyLinkedList<Integer> sorted) {

    //removes all the old elements in the array
    sorted.clear();

    for (int x = 0; x < buckets.length; x++) { //going through the buckets
      while (buckets[x].size() > 0) { //while there are elements in the bucket
        // System.out.println("buckets: " + buckets[x].toString());
        int index = 0; //begin at the first element
        Integer value = buckets[x].get(index); //
        sorted.add(value);
        index++;
        buckets[x].remove(value);
      }
    }

  }

}
