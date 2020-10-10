
import java.util.NoSuchElementException;

/**
 * Hashtable that utilizes an array of type HashLinks to store linked lists in each element to store
 * each key value pair and accounts for chaining
 * @author Andy Lin
 *
 * @param <KeyType> - The generic type for the key.
 * @param <ValueType> The generic type for the value.
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private int capacity; // keeps track of maximum number of elements in hashStorage
  private int size; // keeps track of current number of elements in hashStorage
  private HashLinks<KeyType, ValueType>[] hashStorage; // array that holds HashLinks of type 
                                                         //KeyType, ValueType

  /**
   * constructor that initializes size to zero, capacity to given capacity, and hashstorage
   * to a new array of type HashLinks with given capacity.
   * @param capacity - capacity to make hashStorage.
   */
  public HashTableMap(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    this.hashStorage = new HashLinks[capacity];
  }

  /**
   * default constructor that initializes size to 0, capacity to ten, and hashStorage to an array
   * of type HashLinks with capacity of ten.
   */
  public HashTableMap() {
    this.size = 0;
    this.capacity = 10;
    this.hashStorage = new HashLinks[10];
  }

  /**
   *  Adds a HashLink into hashStorage at the keys hash value, but if the key already exists no 
   *  HashLink is added. If added successfully, size is increased by one.
   *  
   *  @param key - the key to store in hashStorage
   *  @param value - the value associated with the key.
   *  
   *  @returns true if the element is added successfully, otherwise false
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    //return false if key already exists.
    if (containsKey(key)) {
      return false;
    }
    
    // stores HashLink at the absolute value of the key mod capacity.
    int indexToStore = java.lang.Math.abs(key.hashCode()) % capacity; 
    
    // if the current element is null, set the current element to a HashLink of key,value
    // and increase the size by one.
    if (hashStorage[indexToStore] == null) {
      hashStorage[indexToStore] = new HashLinks<KeyType, ValueType>(key, value);
      this.size = this.size + 1;
      if (overEighty()) { // if the load factor is over 80%, double the capacity and rehash.
        doubleHashStorage();
      }
      return true;
    } else {
      HashLinks<KeyType, ValueType> currentHashLink = hashStorage[indexToStore];
      while (currentHashLink != null) {
        if (currentHashLink.getNext() == null) { // if the element at the array is not empty,
          // traverse through each linked list at the calculated index and stop at the last HashLink
          currentHashLink.setNext(new HashLinks<KeyType, ValueType>(key, value)); // set the last
          //hashlink's next value to a new HashLink of key,value.
          currentHashLink.getNext().setPrevious(currentHashLink); // set the new HashLink's previous
          // value to the current Hash Link
          this.size = this.size + 1;
          if (overEighty()) { // if load factor over 80%, double capacity and rehash.
            doubleHashStorage();
          }
          return true;
        }
        currentHashLink = currentHashLink.getNext(); //get the next HashLink at calculated index.
      }
    }
    return false;
  }

  @Override
  /**
   * Retrieves the value of the given key in hashStorage. Throws a NoSuchElementException if the 
   * key does not exist.
   * 
   * @param key - the given key's value to retrieve from hashStorage
   * @returns - the value of the given key.
   * @throws  NoSuchElementException - If the given key does not exist  
   */
  public ValueType get(KeyType key) throws NoSuchElementException {
    for (int i = 0; i < hashStorage.length; i++) {
      if (hashStorage[i] != null) { //traverses through all non-empty elements
        HashLinks<KeyType, ValueType> currentHashLink = hashStorage[i]; // 
        while (currentHashLink != null) { //repeats until currentHashLink is null or return.
          if (currentHashLink.getKeyType().equals(key)) { // if the HashLink is equal to given key
            return currentHashLink.getValueType();        // then return the value.
          }
          currentHashLink = currentHashLink.getNext();    // get next HashLink.
        }
      }
    }
    throw new NoSuchElementException("The existing key does not exist.");
  }
  
  /**
   * returns the current number of elements in hashStorage.
   * 
   * @returns the size of hashStorage
   */
  @Override
  public int size() {
    return this.size;
  }
  
  /**
   * Checks whether or not the given key exists in hashStorage.
   * 
   * @param key - the given key to search for in hashStorage
   * @returns true if the given key exists, otherwise false
   */
  @Override
  public boolean containsKey(KeyType key) {
    for (int i = 0; i < hashStorage.length; i++) { //traverses through the hashStorage
      if (hashStorage[i] != null) {                // and checks all non-empty elements
        HashLinks<KeyType, ValueType> currentHashLink = hashStorage[i];
        while (currentHashLink != null) { //traverse through all linked lists within an element
          if (currentHashLink.getKeyType().equals(key)) { // until matching key is found or all 
                                                          // all linked lists are traversed.
            return true;
          }
          currentHashLink = currentHashLink.getNext(); // get next HashLink
        }
      }

    }
    return false;
  }
  /**
   * Removes a HashLink from hashStorage if the given key is found. Otherwise, return null.
   * 
   * @param key - the given key to remove from hashStorage
   * @returns - the value of the given key or null if the key does not exist.
   */
  @Override
  public ValueType remove(KeyType key) {
    for (int i = 0; i < hashStorage.length; i++) { // traverses through hashStorage
      if (hashStorage[i] != null) { // checks every non null element in hashStorage
        HashLinks<KeyType, ValueType> currentHashLink = hashStorage[i];
        HashLinks<KeyType, ValueType> currentHashLinkPrev = hashStorage[i].getPrevious();
        while (currentHashLink != null) {
          if (currentHashLink.getKeyType().equals(key)) {
            HashLinks<KeyType, ValueType> returnHashLink = currentHashLink; 
            if (currentHashLinkPrev != null) {
              currentHashLinkPrev.setNext(currentHashLink.getNext()); // if the previous HashLink of
              //currentHashLink does not equal null, then set the previous HashLink's next value to
              // the next value of the current HashLink and decrease size.
              size--;
              return currentHashLink.getValueType();
            } else {
              hashStorage[i] = currentHashLink.getNext(); // otherwise, set the element in
              // hashStorage to the next HashLink of the current HashLink and decrease size.
              size--;
              return currentHashLink.getValueType();
            }
          }

          currentHashLink = currentHashLink.getNext(); // get next hashlink.
        }
      }

    }
    return null;
  }
  
  /**
   * removes every HashLink within hashStorage.
   */
  @Override
  public void clear() {
    for (int i = 0; i < hashStorage.length; i++) {
      hashStorage[i] = null;
    }
    this.size = 0;
  }
  
  /**
   * Helper method that calculates whether or not the load factor is >= 80%.
   * @return true if load factor is over 80%, otherwise false.
   */
  private boolean overEighty() {
    if ((float)size / (float)capacity >= 0.8) {
      return true;
    }
    else {
      return false;
    }
    
  }
  /**
   * Makes a new hashStorage double the current capacity and rehashes the old and new hashStorage.
   */
  private void doubleHashStorage() {
    HashLinks<KeyType, ValueType>[] oldStorage = this.hashStorage;
    this.hashStorage = new HashLinks[capacity * 2];
    this.capacity = this.capacity * 2;
    for (int i = 0; i < capacity/2; i++) {
      hashStorage[i] = oldStorage[i];
    }
  }

}
