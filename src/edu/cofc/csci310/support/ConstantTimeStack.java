package edu.cofc.csci310.support;

import java.util.EmptyStackException;

/**
 * A LIFO stack that has constant time complexity O(1) for all three stack interface methods (i.e.,
 * push, pop, and peek).
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public class ConstantTimeStack<Object extends Comparable<Object>> implements Stack<Object> {

  /**
   * ------------------------------ DONE: ------------------------------
   *
   * Select and instantiate a new ArrayList or DoublyLinkedList that will achieve constant time
   * performance
   */
  private List<Object> list = new DoublyLinkedList<>(); // modify this line of code

  /**
   * Pushes an item onto the top of this stack in constant time O(1)
   *
   * @param t the item to be pushed onto this stack.
   */
  public void push(Object t) {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Note: Your push solution must be a constant
     * time O(1) operation
     *
     */
    this.list.add(t);
  } // end push() method

  /**
   * Removes the object at the top of this stack and return the item in constant time O(1) .
   *
   * @return The item at the top of this stack
   * @throws EmptyStackException - if this stack is empty.
   */
  public Object pop() throws EmptyStackException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Note: Your pop solution must be a constant
     * time O(1) operation
     *
     */
    if (this.list.isEmpty()) {
      throw new EmptyStackException();
    }

    // Pops the element via the tailnode
    return this.list.remove(list.size() - 1);
  } // end pop() method

  /**
   * Looks at the item at the top of this stack without removing it from the stack in constant time
   * O(1)
   *
   * @return the item at the top of this stack
   * @throws EmptyStackException - if this stack is empty.
   */
  public Object peek() throws EmptyStackException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Note: Your peek solution must be a constant
     * time O(1) operation
     *
     */
    if (this.list.isEmpty()){
      throw new EmptyStackException();
    }

    return this.list.get(list.size() - 1);

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
    ConstantTimeStack<Integer> testList = new ConstantTimeStack<>();

    //Case Peek while empty
    try {
      testList.peek();
      System.out.println("Case Empty Peek: UNSAT");
    } catch (EmptyStackException e){
      System.out.println("Case Empty Peek: SAT");
    } finally {
      System.out.println("=============================================");
    }

    //Case Pop while empty
    try {
      testList.pop();
      System.out.println("Case Empty Pop: UNSAT");
    } catch (EmptyStackException e){
      System.out.println("Case Empty Pop: SAT");
    } finally {
      System.out.println("=============================================");
    }

    //Case Push then Peek while empty
    try {
      testList.push(0);
      System.out.println("Case Empty Push then Peek: SAT");
    } catch (Exception e){
      System.out.println("Case Empty Push then Peek: UNSAT");
    } finally {
      System.out.println(testList.peek());
      System.out.println("=============================================");
    }

    //Case Push and Peek while not empty
    try {
      for(int i = 1; i < 6; i++) {
        testList.push(i);
        System.out.println(testList.peek());
      }

      System.out.println("Case Push then Peek: SAT");
    } catch (Exception e){
      System.out.println("Case Push then Peek: UNSAT");
    } finally {
      System.out.println(testList.peek());
      System.out.println("=============================================");
    }

    //Case Pop and Peek while not empty
    try {
      for(int i = 5; i > 0; i--) {
        testList.pop();
        System.out.println(testList.peek());
        assert (i - 1 == testList.peek());
      }

      System.out.println("Case Pop then Peek: SAT");
    } catch (Exception e){
      System.out.println("Case Pop then Peek: UNSAT");
    } finally {
      System.out.println(testList.peek());
      System.out.println("=============================================");
    }

    //Case Pop To Empty
    try{
      //Pops final element
      testList.pop();
      //Attempts pop on empty
      testList.pop();
      System.out.println("Case Pop To Empty: UNSAT");
    } catch (EmptyStackException e){
      System.out.println("Case Pop To Empty: SAT");
    }
  } // end main method

} // end ConstantTimeStack class definition
