/*
Author: Daniel Tarkoff
File name: HashMap.java
*/

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HashMap<K,V> implements MapSet<K,V> {

    private LinkedList<KeyValuePair<K, V>>[] hashTable;
    private Comparator<K> comp;
    private int capacity;
    private int filled;
    private int collisionCount; // A collision occurs the first time a key is inserted and there is a different key already at the key's hashed location.


    public HashMap(Comparator incomp) {
        this(incomp, 10); // calls the other constructor with a default value (with a capacity)
    }

    // adds or updates a key-value pair
    // If there is already a pair with new_key in the map, then update
    // the pair's value to new_value.
    // If there is not already a pair with new_key, then
    // add pair with new_key and new_value.
    // returns the old value or null if no old value existed


    public HashMap( Comparator incomp, int capacity ) {
        this.comp = incomp;
        this.capacity = capacity;
        hashTable = new LinkedList[capacity];
        clear();
    }

    public V put(K new_key, V new_value) { // this is where the chaining stuff comes in
        int position = Math.abs(new_key.hashCode() % capacity); // gives index in hashMap of where we put this

        /* 3 cases:
        1. totally new position, no collision - create a new value there
        2. position is used and key was used; replace the old key's value to the new value (not a collision)
        3. a collision: two separate keys that collide at the same spot
        */

        if (filled > 5 * capacity) { // if the size of the linkedList is greater than 5, expand it
            expand();
        }

        if ((hashTable[position]).size() == 0) { // if it's empty, add a new kvp here
            (hashTable[position]).addLast(new KeyValuePair<K, V>(new_key, new_value));
            filled++;
            return null;
        }

        for (KeyValuePair<K,V> kvp: (LinkedList<KeyValuePair<K,V>>)hashTable[position]) {
            if (kvp.getKey().equals(new_key)) {
                V oldValue = (V)kvp.getValue();
                kvp.setValue(new_value);
                return oldValue;
            }
        }
        // if you loop through the linkedList and haven't found the key, add the kvp to the linkedList
        ((LinkedList)hashTable[position]).addLast(new KeyValuePair(new_key, new_value));
        collisionCount++;
        filled++;
        return null;
    }

    public void expand() {
        ArrayList<KeyValuePair<K,V>> oldPairs = entrySet();
        capacity *= 2;
        clear(); // makes a new array using the new capacity (which is double)
        for (KeyValuePair<K,V> kvp: oldPairs) {
            this.put(kvp.getKey(), kvp.getValue());
        }
    }
    
    // Returns true if the map contains a key-value pair with the given key
    public boolean containsKey( K key ) {
        int position = Math.abs(key.hashCode() % capacity); // gives index in hashMap of where we put this
        for (KeyValuePair<K,V> kvp: hashTable[position]) {
            if (kvp.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    
    public V get( K key ) {
        int position = Math.abs(key.hashCode() % capacity);
        for (KeyValuePair<K,V> kvp: hashTable[position]) {
            if (kvp.getKey().equals(key)) {
                return (V)kvp.getValue();
            }
        }
        return null;
    }


    public ArrayList<K> keySet() {
        ArrayList<K> keyList = new ArrayList<K>();
        for (LinkedList<KeyValuePair<K,V>> linkedList: hashTable) {
            for (KeyValuePair<K,V> kvp: linkedList) {
                keyList.add(kvp.getKey());
            }
        }
        
        return keyList;
    }


    public ArrayList<V> values() {
        ArrayList<V> valueList = new ArrayList<V>();
        for (LinkedList<KeyValuePair<K,V>> linkedList: hashTable) {
            for (KeyValuePair<K,V> kvp: linkedList) {
                valueList.add(kvp.getValue());
            }
        }
        return valueList;
    }
    

    public ArrayList<KeyValuePair<K,V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> kvpList = new ArrayList<KeyValuePair<K, V>>();
        for (LinkedList<KeyValuePair<K,V>> linkedList: hashTable) {
            for (KeyValuePair<K,V> kvp: linkedList) {
                kvpList.add(kvp);
            }
        }
        return kvpList;
    }


    public int size() {
        return filled;
    }        

    public void clear() { // not just clearing it, but creates a new array
        hashTable = new LinkedList[capacity];
        this.filled = 0;
        this.collisionCount = 0;
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = new LinkedList<KeyValuePair<K,V>>(); // starts with empty linkedLists rather than null
        }
    }

    public int getCollisions() {
        return collisionCount;
    }

    public static void main (String[] args) {

        HashMap<String, Integer> map = new HashMap<>(null, 2);

        // testing if the array expands
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        System.out.println("Capacity now: " + map.capacity);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);
        map.put("eleven", 11);
        map.put("twelve", 12);


        System.out.println(map.entrySet());
        System.out.println(map.get("one"));
        System.out.println("Should return null: " + map.get("thirteen"));
        System.out.println(map.size());
        System.out.println("Capacity now: " + map.capacity);
        System.out.println("Collision count: " + map.collisionCount);
    }
}





