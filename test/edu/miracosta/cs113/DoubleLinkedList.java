package edu.miracosta.cs113;

import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> mHead = null;   // points to the head of the list
    	private Node<E> mTail = null; //points to the tail of the list
    	private int size = 0;    // the number of items in the list
    	
    	
  
    public void add(int index, E obj)
 { // Fill Here 
    	
	  listIterator(index).add(obj);
 }
  
  	public void addFirst(E obj)
 { 
	  // Fill Here
  	  ListIter iter = new ListIter(0);
  	  iter.set(obj);
      size++;
 }
  	
  	public void addLast(E obj) 
 { 
	  // Fill Here
	  Node<E> node = new Node<E> (obj);
      node.prev = mTail;
      mTail.next = node;
      node = mTail;
      node.next = null;
      size++;
 }

  	private Node<E> getNode(int index) 
 {
  		Node<E> node = mHead;
  		for (int i = 0; i < index && node != null; i++)
 {
  			node = node.next;
 }
  		return node;
 }
  	
  	public E get(int index) 
 { 	
  		if( index < 0 || index >= size)
 {
        throw new IndexOutOfBoundsException("Index must be between 0 and" + size());
 }
        Node<E> node = getNode(index);
      	return node.data;   	
 }  
  	
  	public E getFirst()
 { 
	  return mHead.data; 
 }
  
  	public E getLast() 
 {
	  return mTail.data; 
 }

  	public int size() 
 {
	  return size;
 }
  	
  	public E remove(int index)
 {      E returnValue = null;
  	        ListIterator<E> iter = listIterator(index);
  	        if (iter.hasNext())
 {        returnValue = iter.next();
  	            iter.remove();
 }
  	        else 
 {  
  	        throw new IndexOutOfBoundsException(); 
 }
  	        return returnValue;
 }
  	
  	public void clear()
 {
  	    	  mHead = null;
  	          mTail = null;
  	          size = 0;
 }

  	public ListIterator<E> iterator()
 { 
  		return new ListIter(0); 
 }
  	
  	public ListIterator<E> listIterator() 
 {
  		return new ListIter(0); 
 }
  	
  	public ListIterator<E> listIterator(int index)
 {
  		return new ListIter(index);
 }
  	
  	public ListIterator<E> listIterator(ListIterator<E> iter)
 {    
  		return new ListIter( (ListIter) iter);
 }

  // Inner Classes
  	private static class Node<E>
 {     
        private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
 {  
        	data = dataItem;  
 }
        
 }      // end class Node

  	public class ListIter implements ListIterator<E> 
 {
  	    // the current node
        private Node<E> nextItem;    
        // the previous node
        private Node<E> lastItemReturned;   
        private int index = 0;   
        
    // constructor for ListIter class
    public ListIter(int i) 
 {      if (i < 0 || i > size)
	 
 {    	 
	 	throw new IndexOutOfBoundsException("Invalid index " + i);
 }
        lastItemReturned = null;
 
        if (i == size)     // Special case of last item
 {   
        	index = size;     
        	nextItem = null;    
 }
        else          // start at the beginning
 {  
        	nextItem = mHead;
        for (index = 0; index < i; index++)  
 {
        	
        	nextItem = nextItem.next; 
        	
 }
            
 }			// end else
 }       // end constructor

    public ListIter(ListIter other)
 {  
    	nextItem = other.nextItem;
    	lastItemReturned = other.lastItemReturned;
        index = other.index;    
 }

    public boolean hasNext() 
 {  
    	return nextItem != null;  
 } 
    
    public boolean hasPrevious()
 {  
    	//check if head is null
    	if (size == 0)
    		return false;
    	
    	//check that current object exists
    	return (nextItem == null && size != 0) 
    			|| nextItem.prev != null;  
 } 
    
    
    public int previousIndex() 
 {  
    	return index-1;   
 } 
    
    public int nextIndex() 
 { 
    	return index;    
 } 
    
    public void set(E o) 
 { 
    	 if (lastItemReturned != null) 
 {
             lastItemReturned.data = o;
 } 
         else 
 {
             throw new IllegalStateException();
 }	
 } 
    
    public void remove()
 {
    	  // if list is empty or no item was returned
        if (lastItemReturned == null)
 {
            throw new IllegalStateException();
 }
        if (lastItemReturned == mHead) {//if we are trying to remove the head
    		mHead = nextItem;
    		mHead.prev = null;
    		
 }
        else if(lastItemReturned == mTail) {//trying to remove the tail
    		lastItemReturned.prev.next = null;
    		mTail = lastItemReturned.prev;
    		mTail.next = null;

 }
        else if (lastItemReturned != mHead && lastItemReturned != mTail){
    		lastItemReturned.next.prev = lastItemReturned.prev;
    		lastItemReturned.prev.next = lastItemReturned.next;
 }	
    		size--;
    		index--;
 }
    
    //move the iterator forward and return the next item
    public E next()
 {  
    	//if there is no next item, method is invalid
    	if (!hasNext())
 {
    		throw new NoSuchElementException();
 }
    	else
 {
    	// previous is moved up
    	lastItemReturned = nextItem;
    	// next item is moved up one spot
    	nextItem = nextItem.next;
    	// increases index
    	index++;
        return lastItemReturned.data; 
 }
 }

    public E previous() 
 {  
    	if (!hasPrevious())
 {
    		throw new NoSuchElementException();
 }
    	
    	if(nextItem == null)
 {
    		//iterator is the past element
    		nextItem = mTail;
 }
    	  
    	else
 {
    		nextItem = nextItem.prev;
 }
    	
    	lastItemReturned = nextItem;
    	index--;
    	return lastItemReturned.data;
 }
    

    public void add(E obj)
 {
    	  //newNode;
    	 
    	if (mHead == null)
 {
    		// add to an empty list
    		mHead = new Node<E>(obj);
    		mTail = mHead;
 }
    	
    	else if
    	//insert at head
    	(nextItem == mHead)
 {
    		//create a new node
    		Node<E> newNode = new Node<E>(obj);
    		//link it to the next item
    		newNode.next = nextItem;
    		//link nextItem to the new node
    		nextItem.prev = newNode;
    		// new node is now the head
    		mHead = newNode;
 }	
    	 else if 
    	 // insert at tail
    	 (nextItem == null)
 {
    		 //create a new node
    		 Node<E> newNode = new Node<E>(obj);
    		 // link the tail to the new node
    		 mTail.next = newNode;
    		 // link the new node to the tail
    		 newNode.prev = mTail;
    		 // the new node is the new tail
    		 mTail = newNode;
 }
    	 
    	 else 
 {
    		 //insert into the middle
    		 //create a new node
    		 Node<E> newNode = new Node<E>(obj);
    		 //link it to the nextItem.prev
    		 newNode.prev = nextItem.prev;
    		 nextItem.prev.next = newNode;
    		 // link it to the nextItem
    		 newNode.next = nextItem;
    		 nextItem.prev = newNode;
    		 
    		 
 }
    	size++;
    	index++;
    
 
    	
    	
    	}
  }// end of inner class ListIter
}// end of class DoubleLinkedList


    
    
