/*
Author: Daniel Tarkoff
File name: LinkedList.java
Date: 11.20.2021
Section: A
Project: Word Trends
Course: CS 231
*/

import java.util.Iterator; // defines the Iterator interface
import java.util.ArrayList;
import java.util.Collections; // contains a shuffle method

public class LinkedList<T> implements Iterable<T> {

	// think about line analogy - remembering who is in front of you in line
	// who's in front, who's in back -- ask the first person who's behind you
	// doesn't "store" next Node<T>...

	private Node<T> head; // every method needs to have access to head

	private int size; // size of the linked list

	public LinkedList() { // initializes the fields so that it is an empty list
		this.head = null;
		this.size = 0;
	}

	public void clear() { // empties the list (resets the field so it is an empty list)
		this.head = null;
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	public void addFirst(T item) {
		Node<T> newNode = new Node<T>(item);
		newNode.setNext(this.head);
		this.head = newNode;
		size++;
	}

	public void addLast(T item) {
		if (size == 0) {
			addFirst(item); // if there's nothing, just add to the front
		}
		else {
			Node<T> newNode = new Node<T>(item);
			Node<T> current = this.head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(newNode);
			size++;
		}
	}

	public void add(int index, T item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0) {
			addFirst(item);
		}
		else if (index == size) {
			addLast(item);
		}
		else {
			Node<T> newNode = new Node<T>(item);
			Node<T> current = this.head;
			for (int i = 0; i < index - 1; i++) { // index - 1 becuase we want the place right before current
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			// setting current's next to the new Node<T>
			current.setNext(newNode);
			size++;
		}
	}

	public T remove (int index) { // returns the value in the Node<T> that we remove
		if (index < 0 || index >= size) { // can't remove from the actual index because nothing exists there
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0) {
			T value = this.head.getThing();
			this.head = head.getNext();
			size--;
			return value;
		}
		else {
			Node<T> current = this.head;
			for (int i = 0; i < index - 1; i++) { // index - 1 becuase we want the place right before current
				current = current.getNext();
			}
			T value = current.getNext().getThing();
			current.setNext(current.getNext().getNext());
			size--;
			return value;
		}
	}

	// Return a new LLIterator pointing to the head of the list
      public Iterator<T> iterator() {
          return new LLIterator<T>(this.head);
      }

    public ArrayList<T> toArrayList() {
    	ArrayList<T> arr = new ArrayList<T>();
    	for (T thing: this) {
    		arr.add(thing);
    	}
    	return arr;
    }


    public ArrayList<T> toShuffledList() {
    	ArrayList<T> arr = this.toArrayList();
    	Collections.shuffle(arr);
    	return arr;
    }

	public static void main(String[] args) {
		LinkedList<String> myList = new LinkedList<>();
		myList.add(0, "Hello");
		myList.addFirst("first");
		myList.addLast("last");
		// for (String thing: myList) {
		// 	System.out.println(thing);
		// }

		System.out.println(myList.toShuffledList());

		// System.out.println(myList.remove(0));
	}



}

class Node<T> {
		// class inside of a class - only really useful to the LinkedList class
	Node<T> next;
	T item; // current value
	// this is the container field - same as an instance variable in this case

	public Node(T item) { // initializes next to null and container field to item
		this.next = null;
		this.item = item;
	}

	public T getThing() {
		return this.item;
	}

	public void setNext(Node<T> n) { // sets next to the given Node<T>
		this.next = n;
	}

	public Node<T> getNext() {
		return this.next;
	}

} // end of Node<T> class

class LLIterator<T> implements Iterator<T> {
	Node<T> current;
	public LLIterator(Node head) {
		current = head;
	}
	public boolean hasNext() {
		return current != null;
	}	
	public T next() {
		T value = current.getThing();
		current = current.getNext();
		return value;
	}
	public void remove() {
		// not necessary
	}

}



