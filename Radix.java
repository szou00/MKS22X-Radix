public class Radix {

  public static void radixsort(int[]data){
    //sort the first digit

    //create linkedlist for all of them?

    //make an array of mylinkedlist for the buckets
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];

    //go through the list and find max number
    //FIND ABSOLUTE VALUE !!! REMEMBER
    int max = data[0];
    for (int i = 0; i < data.length; i++) {
      if (data[i] > max) {
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
      int b = data[i]%10; //getting their first digit
      buckets[b].add(data[i]); //adding it in to their respective buckets
    }

    //sorting out the rest of the array
    int digits = 1; //we already printed the first digit
    while (digits != md) {
      for (int x = 0; x < data.length; x++) {
        int b = data[i]%(10) //WORK ON THIS U WANT TO GET 10^X !! 
        buckets[b].add(data[i]); //adding it in to their respective buckets
      }
    }

    //trying to test out the buckets and printing them out
    for (int i = 0; i < buckets.length; i++) {
      if (buckets[i].size() != 0) {
        System.out.println(buckets[i].toString());
      }
    }

  }

}
