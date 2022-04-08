package MyList;

public class Driver {
  static final int ITERATION = 5;

  public static void main(String[] args) {
    MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

    // check addFirst and addLast methods
    myLinkedList.addFirst(1);
    myLinkedList.addLast(12);
    myLinkedList.addFirst(14);
    myLinkedList.addLast(17);
    System.out.println(myLinkedList);
    System.out.println("size: " + myLinkedList.size());
    System.out.println();

    // check removeLast method
    for(int i=0; i<4; i++){
      myLinkedList.removeLast();
      System.out.println(myLinkedList);
      System.out.println("size: " + myLinkedList.size());
      System.out.println();
    }

    // check remove(E e) method and remove(int index) method
    for(int i=0; i<10; i++){
      myLinkedList.addLast(i);
    }
    System.out.println(myLinkedList);
    System.out.println("size: " + myLinkedList.size());
    System.out.println();

    myLinkedList.remove((Integer)5);
    System.out.println(myLinkedList);
    System.out.println("size: " + myLinkedList.size());
    System.out.println();

    myLinkedList.remove(5);
    System.out.println(myLinkedList);
    System.out.println("size: " + myLinkedList.size());
    System.out.println();

    // check lastIndexOf(E e) method
    myLinkedList.add(0, 9);
    System.out.println(myLinkedList);
    System.out.println("size: " + myLinkedList.size());
    System.out.println(myLinkedList.lastIndexOf(9));
    System.out.println();

    //check reverse method
    myLinkedList.reverse();
    System.out.println(myLinkedList);

  }
}
