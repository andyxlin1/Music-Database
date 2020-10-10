
/**
 * Linked List class that contains the key and value in each linked list.
 *
 * @param <KeyType> The generic type of the key.
 * @param <ValueType> The generic type of the value
 */
public class HashLinks<KeyType, ValueType> {
  private HashLinks<KeyType, ValueType> next; // stores the next value for current linked list  
  private HashLinks<KeyType, ValueType> previous; // stores previous value for previous linked list
  private KeyType key; // the key associated with the linked list
  private ValueType value; // the value associated with the key

  /**
   * Public constructor that initializes the key, value, next linked list, and previous linked list.
   * @param key - the desired key to be added to the hashtable.
   * @param value - the desired value to be associated with the key.
   */
  public HashLinks(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
    this.next = null;
    this.previous = null;
  }
  /**
   * returns the key.
   * @return the key.
   */
  public KeyType getKeyType() {
    return this.key;
  }
  
  /**
   * returns the value of the key.
   * @return the key associated to the key.
   */
  public ValueType getValueType() {
    return this.value;
  }
  
  /**
   * the next linked list.
   * @return the next linked list associated to the current one.
   */
  public HashLinks<KeyType, ValueType> getNext() {
    return this.next;
  }
  
  /**
   *  Returns the previous linked list
   * @return the previous linked list associated to the current one.
   */
  public HashLinks<KeyType, ValueType> getPrevious() {
    return this.previous;
  }
  
  /**
   * sets the current linked lists next value
   * 
   * @param next - the linked list used to set the next value for the current linked list.
   */
  public void setNext(HashLinks<KeyType, ValueType> next) {
    this.next = next;
  }
  /**
   * sets the current linked lists previous value
   * 
   * @param previous - the linked list used to set the previous value for the current linked list.
   */
  public void setPrevious(HashLinks<KeyType, ValueType> previous) {
    this.previous = previous;
  }
  
}
