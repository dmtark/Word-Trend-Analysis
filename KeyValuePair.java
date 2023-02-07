/*
Author: Daniel Tarkoff
File name: KeyValuePair.java
Date: 11.20.2021
Project: Word Trends
*/

public class KeyValuePair<Key, Value> {
		
	public Key k;
	public Value v;

	public KeyValuePair(Key k, Value v) {
		this.k = k;
		this.v = v;
	}

	public Key getKey() {
		return this.k;
	}

	public Value getValue() {
		return this.v;
	}

	public void setValue(Value v) {
		this.v = v;
	}

	public String toString() {
		return "[Key: " + this.k + ", Value: " + this.v + "]";
	}

	public static void main (String[] args) {

		KeyValuePair newPair = new KeyValuePair(4, 6);

		System.out.println("Should be 4: " + newPair.getKey());
		System.out.println("Should be 6: " + newPair.getValue());
		newPair.setValue(10);
		System.out.println("Should be 10: " + newPair.getValue());
	}

}