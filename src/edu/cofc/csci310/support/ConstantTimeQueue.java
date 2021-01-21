package edu.cofc.csci310.support;

import java.util.NoSuchElementException;


/**
 * A FIFO stack that has constant time complexity O(1) for all three queue interface methods (i.e.,
 * add, remove, and peek).
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public class ConstantTimeQueue<Object extends Comparable<Object>> implements Queue<Object> {

  /**
   * ------------------------------ DONE: ------------------------------
   *
   * Select and instantiate a new ArrayList or DoublyLinkedList that will achieve constant time
   * performance
   */
  private List<Object> list = new DoublyLinkedList<Object>(); // modify this line of code


  /**
   * Inserts the specified element at the end of the queue in constant time O(1)
   *
   * @param t element to add
   * @throws NullPointerException- if the specified element is null (queue does not permit null
   * elements)
   */
  public void add(Object t) throws NullPointerException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Note: Your add solution must be a constant
     * time O(1) operation.
     *
     */
    if (t == null) {
      throw new NullPointerException();
    }
    this.list.add(t);
  } // end add() method

  /**
   * Retrieves and removes the head of the queue in constant time O(1).
   *
   * @return the head of the queue
   * @throws NoSuchElementException - if this queue is empty
   */
  public Object remove() throws NoSuchElementException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Note: Your push solution must be a constant
     * time O(1) operation.
     *
     *
     */
    if (this.list.isEmpty()) {
      throw new NoSuchElementException();
    }
    // Pops the element via the headNode
    return this.list.remove(0);
  } // end remove() method

  /**
   * Retrieves, but does not remove, the head of the queue, or returns null if this queue is empty.
   *
   * @return the head of this queue, or null if this queue is empty
   */
  public Object peek() {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Note: Your add solution must be a constant
     * time O(1) operation
     *
     */
    if (this.list.isEmpty()) {
      return null;
    }
    //Gets head directly via the headNode
    return this.list.get(0);
  } // end peek() method

  /**
   *
   * @param args
   */
  public static void main(String[] args) {

    /**
     * -------------------------------------------
     * DONE: You put your test cases here
     *
     */
    ConstantTimeQueue<Integer> testList = new ConstantTimeQueue<>();

    //Case Peek while empty
    try {
      if (testList.peek() != null) throw new Exception();
      System.out.println("Case Empty Peek: SAT");
    } catch (Exception e){
      System.out.println("Case Empty Peek: UNSAT");
    } finally {
      System.out.println("=============================================");
    }

    //Case Remove while empty
    try {
      testList.remove();
      System.out.println("Case Empty Remove: UNSAT");
    } catch (NoSuchElementException e){
      System.out.println("Case Empty Pop: SAT");
    } finally {
      System.out.println("=============================================");
    }

    //Case Add null
    try {
      testList.add(null);
      System.out.println("Case Add Null: UNSAT");
    } catch (NullPointerException e){
      System.out.println("Case Add Null: SAT");
    } finally {
      System.out.println(testList.peek());
      System.out.println("=============================================");
    }

    //Case Push and Peek while not empty
    try {
      for(int i = 0; i < 6; i++) {
        testList.add(i);
        System.out.println(testList.peek());
      }

      System.out.println("Case Push then Peek: SAT");
    } catch (Exception e){
      System.out.println("Case Push then Peek: UNSAT");
    } finally {
      System.out.println(testList.peek());
      System.out.println("=============================================");
    }

    //Case Remove then Peek while not empty
    try {
      for(int i = 5; i > 0; i--) {
        testList.remove();
        System.out.println(testList.peek());
        assert (5 - i == testList.peek());
      }

      System.out.println("Case Remove then Peek: SAT");
    } catch (Exception e){
      System.out.println("Case Remove then Peek: UNSAT");
    } finally {
      System.out.println(testList.peek());
      System.out.println("=============================================");
    }

    //Case Remove To Empty
    try{
      //Removes final element
      testList.remove();
      //Attempts remove to empty
      testList.remove();
      System.out.println("Case Remove To Empty: UNSAT");
    } catch (NoSuchElementException e){
      System.out.println("Case Remove To Empty: SAT");
    }
  } // end main() method

} // end ConstantTimeQueue class definition

