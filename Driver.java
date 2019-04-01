public class Driver {

  public static void main(String[] args) {
    int[] test1 = {2,4,12,4,2,7};
    int[] test2 = {-2,-4,-12,-4,-2,-7};
    MyLinkedList<Integer> test1L = new MyLinkedList<Integer>();
    MyLinkedList<Integer> test2L = new MyLinkedList<Integer>();
    for (int i = 0; i < test1.length; i++) {
      test1L.add(test1[i]);
    }
    for (int i = 0; i < test2.length; i++) {
      test2L.add(test2[i]);
    }
    System.out.println(test1L);
    System.out.println(test2L);
  }
}
