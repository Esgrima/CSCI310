package edu.cofc.csci310.support;

/**
 * A node with two pointers (the next node directly in front
 * of it, and the previous node directly behind it) to be used 
 * in a doubly linked list linear data structure
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 *
 * @param <Object>
 */
public class Node<Object extends Comparable<Object>> {
	
	// instance variables
	private Object data;
	private Node<Object> nextNode;
	private Node<Object> previousNode;
	
	/**
	 * 
	 * @param data
	 */
	public Node( Object data ) {
		
		setData( data );
		
	} // end constructor
	
	/**
	 * 
	 * @param data
	 */
	public void setData( Object data ) {
		
		this.data = data;
		
	} // end setData() method
	
	/**
	 * 
	 * @return
	 */
	public Object getData() {
		
		return data;
		
	} // end getData() method
	
	/**
	 * 
	 * @param nextNode
	 */
	public void setNextNode( Node<Object> nextNode ) {
		
		this.nextNode = nextNode;
		
	} // end setNextNode() method
	
	/**
	 * 
	 * @return
	 */
	public Node<Object> getNextNode() {
		
		return nextNode;
		
	} // end getNextNode() method
	
	/**
	 * 
	 * @param nextNode
	 */
	public void setPreviousNode( Node<Object> previousNode ) {
		
		this.previousNode = previousNode;
		
	} // end setPreviousNode() method
	
	/**
	 * 
	 * @return
	 */
	public Node<Object> getPreviousNode() {
		
		return previousNode;
		
	} // end getPreviousNode() method
	
	
	/**
	 * 
	 */
	public String toString() {
		
		if ( getNextNode() != null && getPreviousNode() != null ) {
		
			return String.format("%s <- %s -> %s\n", getPreviousNode().getData().toString(), getData().toString(), getNextNode().getData().toString() );
		
		} else if ( getNextNode() != null && getPreviousNode() == null ){
			
			return String.format( "NULL <- %s -> %s\n", getData().toString(), getNextNode().getData().toString() );
			
		} else if ( getNextNode() == null && getPreviousNode() != null ){
			
			return String.format( "%s <- %s -> NULL\n", getPreviousNode().getData().toString(), getData().toString() );
			
		} else {
			
			return String.format( "NULL <- %s -> NULL\n", getData().toString() );
		}
		
	} // end toString() method

} // end Node class definition

