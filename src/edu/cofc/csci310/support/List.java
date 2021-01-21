package edu.cofc.csci310.support;

/**
 * List interface that "closely" resembles the List interface in the java.util package
 *
 * http://docs.oracle.com/javase/8/docs/api/java/util/List.html
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public interface List<Object> {

  /**
   * Appends the specified element to the end of this list.
   *
   * @param t * @throws NullPointerException
   */
  public boolean add(Object t) throws NullPointerException;

  /**
   * Inserts the specified element at the specified position in this list.
   *
   * @throws IndexOutOfBoundsException, NullPointerException
   */
  public void add(int index, Object t) throws IndexOutOfBoundsException, NullPointerException;

  /**
   * Replaces the element at the specified position in this list with the specified element.
   *
   * @throws IndexOutOfBoundsException, NullPointerException
   */
  public Object set(int index, Object t) throws IndexOutOfBoundsException, NullPointerException;

  /**
   * Removes the element at the specified position in this list.
   */
  public Object remove(int index) throws IndexOutOfBoundsException;

  /**
   * Returns the element at the specified position in this list.
   */
  public Object get(int index) throws IndexOutOfBoundsException;

  /**
   * Returns the number of elements in this list.
   */
  public int size();

  /**
   * Returns true if this list contains no elements.
   */
  public boolean isEmpty();

  /**
   * Removes all of the elements from this list.
   */
  public void clear();

  /**
   * swaps to elements in a list at index position i and j.
   *
   * For example, if A = 1->2->3->4->5 and swap( 1, 3 ) is executed then A = 1->4->3->2->5
   */
  public void swap(int i, int j);

} // end List interface definition
