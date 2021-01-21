package edu.cofc.csci310.support;


/**
 * Doubly Linked List Data Structure
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public class DoublyLinkedList<Object extends Comparable<Object>> implements List<Object> {

  // instance variables
  private Node<Object> headNode = null;
  private Node<Object> tailNode = null;

  private int size = 0;

  /**
   * Appends the specified element to the end of this list.
   */
  public boolean add(Object t) throws NullPointerException {

    if (t == null) {
      throw new NullPointerException();
    }

    this.addNode(new Node<>(t));

    return false;
  } // end add() method

  /**
   * Implementation for public add(Object t) method
   *
   * I did this one for you :)
   */
  private void addNode(Node<Object> t) {

    if (isEmpty()) {

      this.headNode = t;
      this.tailNode = this.headNode;

    } else {

      Node<Object> node = this.getNode(size - 1);
      node.setNextNode(t);
      t.setPreviousNode(node);

      this.tailNode = t;

    }

    this.size++;

  } // end addNode() method


  /**
   * Inserts the specified element at the specified position in this list.
   *
   * @throws IndexOutOfBoundsException, NullPointerException
   */
  public void add(int index, Object t) throws IndexOutOfBoundsException, NullPointerException {

    if (t == null) {
      throw new NullPointerException();
    }

    this.addNode(index, new Node<>(t));

  } // end add() method

  /**
   *
   * Implementation for public add(int index, Object t) method
   *
   */
  private void addNode(int index, Node<Object> t) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    Node<Object> curr;

    if (index == this.size) {
      curr = this.tailNode;
    } else {
      curr = this.getNode(index);
    }

    // Insert before head
    if (index == 0) {
      t.setNextNode(this.headNode);
      this.headNode.setPreviousNode(t);
      this.headNode = t;

    } // Insert after tail(Append)
    else if (index == this.size) {
      this.addNode(t);

    } // Insert in between head and tail
    else {
      Node<Object> prev = curr.getPreviousNode();

      // Reconfigures Node pointers prev <-> new Node <-> curr
      t.setPreviousNode(prev);
      prev.setNextNode(t);
      t.setNextNode(curr);
      curr.setPreviousNode(t);
    }
    this.size++;
  } // end addNode() method

  /**
   * Replaces the element at the specified position in this list with the specified element.
   *
   * @throws IndexOutOfBoundsException, NullPointerException
   */
  public Object set(int index, Object t) throws IndexOutOfBoundsException, NullPointerException {

    if (t == null) {
      throw new NullPointerException();
    }

    this.setNode(index, new Node<>(t));

    return null;
  } // end set() method

  /**
   *
   * @param index
   * @param t
   * @throws IndexOutOfBoundsException
   */
  private void setNode(int index, Node<Object> t) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    // Stores current, previous, and next nodes
    Node<Object> curr = this.getNode(index);
    curr.setData(t.getData());

  } // end setNode() method


  /**
   * Removes the element at the specified position in this list.
   */
  public Object remove(int index) throws IndexOutOfBoundsException {

    return this.removeNode(index).getData();

  } // end remove() method

  /**
   *
   * @param index
   * @return
   * @throws IndexOutOfBoundsException
   */
  private Node<Object> removeNode(int index) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    Node<Object> node = getNode(index);

    // Remove head
    if (index == 0) {
      this.headNode = node.getNextNode();
      if (this.headNode != null) {
        this.headNode.setPreviousNode(null);
      }
    } // Remove tail
    else if (index == this.size - 1) {
      Node<Object> prev = node.getPreviousNode();
      prev.setNextNode(null);
      this.tailNode = prev;

    } // Remove body node
    else {
      Node<Object> prev = node.getPreviousNode();
      Node<Object> next = node.getNextNode();

      // Reconfigure next and previous pointers
      next.setPreviousNode(prev);
      prev.setNextNode(next);
    }

    node.setNextNode(null);
    node.setPreviousNode(null);

    size--;

    if (this.isEmpty()) {
      this.clear();
    }

    return node;

  } // end removeNode() method

  /**
   * Returns the element at the specified position in this list.
   */
  public Object get(int index) throws IndexOutOfBoundsException {

    return getNode(index).getData();

  } // end get() method

  /**
   * Implementation of get(int index) method
   */
  private Node<Object> getNode(int index) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Your implementation MUST do the following link traversals:
     *
     * 1) If the index location is <= floor( size/2 ), start traversal from head node
     * 2) if the index location is > floor( size/2), start traversal from tail node
     *
     * Your code will be reviewed by instructor to ensure the two conditions
     * are fully meet by your solution.
     *
     */
    // Checks for an invalid index
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException();
    }

    Node<Object> curr = null;

    if (this.isEmpty()) {
      return curr;
    }

    // Checks if desired node is head or tail
    if (index == 0) {
      return this.headNode;
    } else if (index == this.size - 1) {
      return this.tailNode;
    }

    double midpoint = Math.floor(this.size / 2.0);

    // Determines where to start traversal
    if (index <= midpoint) {
      curr = this.headNode;

      for (int i = 0; i != index; i++) {
        curr = curr.getNextNode();
      }
      return curr;

    } else if (index > midpoint) {
      curr = this.tailNode;

      for (int i = this.size - 1; i != index; i--) {
        curr = curr.getPreviousNode();
      }
      return curr;
    }

    return curr;
  } // end get() method

  /**
   * Returns the number of elements in this list.
   */
  public int size() {

    return this.size;

  } // end size() method

  /**
   * Returns true if this list contains no elements.
   */
  public boolean isEmpty() {

    return this.size == 0;

  } // end isEmpty() method


  /**
   * Removes all of the elements from this list.
   */
  public void clear() {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    this.headNode = null;
    this.tailNode = null;

    this.size = 0;

  } // end clear method

  /**
   * swaps to elements in a list at index position i and j.
   *
   * For example, if A = 1->2->3->4->5 and swap( 1, 3 ) is executed then A = 1->4->3->2->5
   */
  public void swap(int i, int j) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    Object temp = this.get(i);
    this.getNode(i).setData(this.get(j));
    this.getNode(j).setData(temp);
  } // end swap() method

  /**
   * Do not modify
   *
   * To help you debug your code
   */
  public void printList() {

    Node<Object> n = headNode;

    while (n != null) {

      System.out.println(n);

      n = n.getNextNode();

    }

  } // end printlist()

  /**
   * For debugging and testing purpose
   *
   * !!! Do not remove or modify !!!
   */
  public String toString() {

    StringBuilder buffer = new StringBuilder();

    buffer.append(String.format("Size=%d, A = [ ", size));

    if (!isEmpty()) {

      for (int i = 0; i < size - 1; i++) {
        buffer.append(String.format("%d, ", get(i)));
      }

      buffer.append(String.format("%d ]", get(size - 1)));

    } else {

      buffer.append("] ");
    }

    return buffer.toString();

  } // end toString()


} // end DoublyLinkedList class definition