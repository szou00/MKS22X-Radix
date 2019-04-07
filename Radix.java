import java.lang.Math;
import java.util.Arrays;
import java.util.Random;

public class Radix {

  public static void radixsort(int[]data){
    //sort the first digit

    //create linkedlist for all of them?

    //make an array of mylinkedlist for the buckets
    @SuppressWarnings({"unchecked","rawtypes"})
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    MyLinkedList<Integer> sorted = new MyLinkedList<Integer>();

    //go through the list and find max number
    //FIND ABSOLUTE VALUE !!! REMEMBER
    int max = data[0];
    for (int i = 0; i < data.length; i++) {
      if (Math.abs(data[i]) > Math.abs(max)) {
        max = data[i];
      }
    }
    // System.out.println(max);

    //finds the number of digits in max
    int md = 1; //including the digit that's below 10
    md += (int)Math.log10(Math.abs(max));
    // System.out.println(md);

    //initializing all the buckets
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }

    //sorting out the numbers by digits and putting them into their respective buckets
    //first copy into linkedlist
    for (int x = 0; x < md; x++) {
      if (x == 0) { //if it's the first pass
        for (int z = 0; z<data.length;z++) {
          int b = data[z]%10; //getting their first digit
          // System.out.println(b);
          if (b >= 0) {
            buckets[b+10].add(data[z]); //adding for pos nums
            // System.out.println("added positive: " + b);
          }
          else {
            buckets[9-Math.abs(b)].add(data[z]); //adding for neg numbers
            // System.out.println("added neg: " + b);
          }
        }

      }
      else {
        //sorting out the rest of the array

        while (sorted.size() > 0) {
          int n = sorted.removeFront();

          int b = n/((int)Math.pow(10,x));
          b = b%10;
          // System.out.println(b);
          if (b >= 0) {
            buckets[b+10].add(n); //adding ifor pos nums
          }
          else {
            buckets[9-Math.abs(b)].add(n); //adding for neg numbers
          }

        }
      }
      merge(buckets,sorted);
    }
    // System.out.println(sorted.size());


    // //sorting out the rest of the array
    // int digits = 1; //we already sorted the first digit
    // while (digits != md) {
    //   for (int x = 0; x < sorted.size(); x++) {
    //     // System.out.println("size: " + buckets[19].size() + " x: " + x );
    //     int d = digits; //to use to divide everything
    //     int newA = sorted.get(x); //to store the values
    //     // System.out.println("d: "+d);
    //     while (d != 0) { //keep dividing while it's not zero
    //       newA = newA/10;
    //       d--; //subtract the total num of digits
    //     }
    //     int b = newA % 10; //take the last digit after dividing
    //     // System.out.println(newA + " " + md);
    //
    //     if (b >= 0) {
    //       buckets[b+10].add(sorted.get(x)); //adding ifor pos nums
    //     }
    //     else {
    //       buckets[9-Math.abs(b)].add(sorted.get(x)); //adding for neg numbers
    //     }
    //   }
    //
    //   digits++; //increase digits
    //   merge(buckets,sorted); //merge everything together
    // }

    // trying to test out the buckets and printing them out
    // for (int i = 0; i < buckets.length; i++) {
    //   // if (buckets[i].size() != 0) {
    //     System.out.println(buckets[i].toString());
    //   // }
    // }
    // System.out.println(sorted.toString());
    for (int i = 0; i < sorted.size(); i++) {
      data[i] = sorted.get(i);
    }

  }

  //adding it the 20th linked list
  // in their respective positions
  public static void merge(MyLinkedList<Integer>[] buckets, MyLinkedList<Integer> sorted) {

    //removes all the old elements in the array
    // sorted.clear();

    for (int x = 0; x < buckets.length; x++) { //going through the buckets
    //   // System.out.println("buckets " + x + " " + buckets[x].toString());
    //   while (buckets[x].size() > 0) { //while there are elements in the bucket
    //     int index = 0; //begin at the first element
    //     Integer value = buckets[x].get(index); //
    //     sorted.add(value);
    //     index++;
    //     buckets[x].remove(value);
      if (buckets[x].size() > 0) {
        sorted.extend(buckets[x]);

      }
            // }

    }

  }

  //testing from k's website
  public static void main(String[] args) {
       Random gen = new Random();
       System.out.println("Size\t\tMax Value\tradix/builtin ratio ");
       int[] MAX_LIST = {1000000000, 500, 10};
       for (int MAX : MAX_LIST) {
           for (int size = 31250; size < 2000001; size *= 2) {
               long qtime = 0;
               long btime = 0;
               //average of 5 sorts.
               for (int trial = 0; trial <= 5; trial++) {
                   int[] data1 = new int[size];
                   int[] data2 = new int[size];
                   for (int i = 0; i < data1.length; i++) {
                       data1[i] = Math.abs(gen.nextInt()) % (MAX + 1);
                       data2[i] = data1[i];
                   }
                   long t1, t2;
                   t1 = System.currentTimeMillis();
                   Radix.radixsort(data2);
                   t2 = System.currentTimeMillis();
                   qtime += t2 - t1;
                   t1 = System.currentTimeMillis();
                   Arrays.sort(data1);
                   t2 = System.currentTimeMillis();
                   btime+= t2 - t1;
                   if (!Arrays.equals(data1, data2)) {
                       System.out.println("FAIL TO SORT!");
                       System.exit(0);
                   }
               }
               System.out.println(size + "\t\t" + MAX + "\t" + 1.0 * qtime / btime);
           }
           System.out.println();
       }
   }
}
