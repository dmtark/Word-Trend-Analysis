/*
Author: Daniel Tarkoff
File name: PQHeap.java
*/

import java.util.Comparator;

public class PQHeap<T> {
	
	private Object [] maxHeap;
	private int size;
	private Comparator<T> comp;
	private int currentIndex; // this is the first null value in our array (the next position for a "node" to be inserted)


	public PQHeap(Comparator<T> comparator) {
		this.maxHeap = new Object[100];
		this.size = 0;
		this.comp = comparator;
		this.currentIndex = 0;
	}

	public void expand() {
		Object [] temp = new Object[maxHeap.length * 2];
		for (int i = 0; i < maxHeap.length; i++) {
			temp[i] = maxHeap[i];
		}
		maxHeap = temp;
	}

	public int size() {
		return this.size;
	}

	public int getParent(int index) {
		return (index - 1) / 2;
	}

	public void swap(int i, int j) {
		T temp = (T) maxHeap[i];
		maxHeap[i] = maxHeap[j];
		maxHeap[j] = temp;
	}

	public void add(T obj) {

		if (currentIndex >= maxHeap.length) {
			expand();
		}
		maxHeap[currentIndex] = obj;

		int addedIndex = currentIndex;

		while (addedIndex != 0 && comp.compare((T)maxHeap[addedIndex], (T)maxHeap[getParent(addedIndex)]) > 0) {
			swap(addedIndex, getParent(addedIndex));
			addedIndex = getParent(addedIndex);
		}

		size++;
		currentIndex++;
	}

	public int getLeft(int index) {
		return 2 * index + 1;
	}

	public int getRight(int index) {
		return 2 * index + 2;
	}

	private void maxHeapify(int position) {

		int left = getLeft(position);
		int right = getRight(position);

		while (left < size ) { // while left child exists

			int maxIndex = left;

			if (right < size && comp.compare((T)maxHeap[left], (T)maxHeap[right]) < 0) { // choosing which child to swap with
				maxIndex = right;
			}
			if (comp.compare((T)maxHeap[maxIndex], (T)maxHeap[position]) > 0) { // are we swapping at all
				swap(position, maxIndex);
				position = maxIndex;
				left = getLeft(position);
				right = getRight(position);
			}
			else { // if we're not swapping
				return;
			}
		}
	}

	public T remove() {

		if (size == 0) {
			return null;
		}

		T temp = (T)maxHeap[0];

		currentIndex--; // now we are accessing the first full index (we don't want the first null index)

		size--;

		if (size > 0) {
			swap(0, currentIndex); // swap only changes values from that index, doesn't actually change the indexes
			maxHeapify(0); // starting at 0
		}
		return temp;

	}

}

