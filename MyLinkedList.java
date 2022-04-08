package MyList;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Singly Linked-list (generic)
 */
public class MyLinkedList<E> {

  private Node<E> head, tail; // null
  private int size; // 0

  public MyLinkedList() { }

  // O(1)
  public void addFirst(E e){

    // original code -> temporary variable h necessary?
    // final Node<E> h = head;
    // final Node<E> newNode = new Node<E>(e, h);
    // head = newNode;
    // if (h==null){
    //   tail = newNode;
    // }
    // size++;

    final Node<E> newNode = new Node<E>(e, head);
    if (head==null){
      tail = newNode;
    }
    head = newNode;
    size++;
  }

  // O(1)
  public void addLast(E e){
    final Node<E> newNode = new Node<>(e, null);
    if(tail==null){
      head = newNode;
    }else{
      tail.next = newNode;
    }
    tail = newNode;
    size++;
  }

  // O(N) original code
//  public void add(int index, E e) {
//    if (index < 0 || index > size) {
//     throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
//    }
//    if (index == 0) {
//      addFirst(e);
//    } else if (index == size) {
//      addLast(e);
//    } else {
//      Node<E> cur = head;
//      for (int i = 0; i < index - 1; i++) {
//        cur = cur.next;
//      }
//      cur.next = new Node<>(e, cur.next);
//      size++;
//    }
//  }
  public void add(int index, E e){
    if(index < 0 || index > size) throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
    if(index == 0) addFirst(e);
    else if(index == size) addLast(e);
    else{
      Node<E> currNode = head;
      for(int i=0; i<index-1; i++){
        currNode = currNode.next;
      }
      Node<E> temp = currNode.next;
      currNode.next = new Node<>(e, temp);
      size++;
    }
  }

  // O(1) original code
//  public E removeFirst() {
//    final Node<E> h = head;
//    if (h == null) {
//      throw new NoSuchElementException("list is empty");
//    }
//
//    E data = h.data;
//    Node<E> next = h.next;
//    h.data = null;
//    h.next = null;
//    head = next;
//    if (next == null) {
//      // only one element in the list
//      tail = null;
//    }
//    size--;
//    return data;
//  }
  public E removeFirst() {
    if(head == null) throw new NoSuchElementException("list is empty");

    Node<E> removed = head;
    head = head.next;

    if(head==null) tail = null;

    size--;
    return removed.data;
  }

  /**
   * O(N)
   * This method can be improved by using Doubly-Linked List. O(1)
   * (Doubly-Linked List has `prev` pointer.)
   * ex) tail.prev (second last node)
   */
  public E removeLast() {
    // TODO: Implement Me;
    if(tail == null) throw new NoSuchElementException("list is empty");
    if(size == 1) return removeFirst();
    else{
      Node<E> removed = tail;
      Node<E> iterator = head;

      while(iterator.next.next!=null) iterator=iterator.next;
      tail = iterator;
      tail.next = null;
      size--;

      return removed.data;
    }
  }

  public E getFirst() {
    if (head == null) {
      throw new NoSuchElementException("list is empty");
    }
    return head.data;
  }

  // O(1)
  public E getLast() {
    if (tail == null) {
      throw new NoSuchElementException("list is empty");
    }
    return tail.data;
  }

  private Node<E> getNodeAt(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
    }
    Node<E> cur = head;
    for (int i = 0; i < index; i++) {
      cur = cur.next;
    }
    return cur;
  }

  // O(N)
  public E get(int index) {
    return getNodeAt(index).data;
  }

  // O(N)
  public E set(int index, E e) {
    Node<E> cur = getNodeAt(index);
    E oldValue = cur.data;
    cur.data = e;
    return oldValue;
  }

  // O(N)
  public int indexOf(E e) {
    int index = 0;
    if (e == null) {
      for (Node<E> x = head; x != null; x = x.next) {
        if (x.data == null) {
          return index;
        }
        index++;
      }
    } else {
      for (Node<E> x = head; x != null; x = x.next) {
        if (e.equals(x.data)) {
          return index;
        }
        index++;
      }
    }
    return -1;
  }

  /**
   * Remove the element e from the list
   * @param e
   * @return
   */
  public boolean remove(E e) {
    // TODO: Implement Me
    if(size == 0) return false;
    if(head.data.equals(e)){
      removeFirst();
      return true;
    }else if (tail.data.equals(e)){
      removeLast();
      return true;
    }else{
      Node<E> iterator = head;
      while(iterator.next != null){
        if(iterator.next.data.equals(e)){
          Node<E> removed = iterator.next;
          iterator.next = iterator.next.next;
          size--;
          return true;
        }
        iterator = iterator.next;
      }
    }
    return false;
  }

  /**
   * Remove the element at index
   * O(N)
   * @param index
   * @return
   */
  public boolean remove(int index) {
    // TODO: Implement Me
    if(size == 0 || index<0 || index>=size) return false;
    if(index == 0){
      removeFirst();
    }else if (index==size-1){
      removeLast();
    }else{
      Node<E> iterator = head;
      for(int i=0; i<index-1; i++) iterator = iterator.next;
      iterator.next = iterator.next.next;
      size--;
    }
    return true;
  }


  /**
   * Returns the index of the last occurrence of element e
   * O(N)
   *
   * ll = [1, 1, 2, 3, 1, 5, 1, 2]
   * ll.lastIndexOf(1) -> 6
   * @param e
   * @return the index of the last occurrence of element e
   */
  public int lastIndexOf(E e) {
    // TODO: Implement Me
    if(head == null) return -1;

    int index = -1;
    Node<E> iterator = head;
    for(int i=0; iterator!=null; i++){
      if(iterator.data.equals(e)) index = i;
      iterator = iterator.next;
    }
    return index;
  }

  /**
   * Reverses the current linked list
   * "A" -> "B" -> "C" -> null
   * should be
   * "C" -> "B" -> "A" -> null
   */
  public void reverse() {
    // TODO: Implement Me
    Node<E> prev = null;
    Node<E> current = head;
    Node<E> next = null;
    while(current != null){
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    head = prev;
  }

  // O(N)
  public boolean contains(E e) {
    return indexOf(e) >= 0;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Node<E> x = head; x != null; x = x.next) {
      s.append(x.data).append(" -> ");
    }
    s.append("null");
    return s.toString();
  }

  public int size() {
    return size;
  }

  // static nested class
  public static class Node<E> {
    E data;
    Node<E> next;

    public Node(E item, Node<E> next) {
      this.data = item;
      this.next = next;
    }
  }
}

