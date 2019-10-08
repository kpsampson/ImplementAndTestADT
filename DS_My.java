///////////////////////////////////////////////////////////////////////////////
//
// Title:           p1 Implement and Test an ADT
//
// Files: 			DS_My.java, DataStructureADTTest.java, TestDS_My.java
// 
// Course:          CS 400, 001, Fall 2019
//
// Author:          Kylie Sampson
// Email:           kpsampson@wisc.edu  
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class is a linked list data structure. The nodes of a linked list store
 * a reference to the next node, forming a chain. The reference to the head and
 * last node are saved. To find other nodes you can iterate through using each
 * reference to the next.
 * 
 * @author Kylie Sampson
 *
 */
public class DS_My implements DataStructureADT {

	/**
	 * Class for the StorePairs object. Stores the key and value in private fields.
	 * 
	 * @author Kylie Sampson
	 *
	 * @param <V>
	 * @param <K>
	 */
	private class StorePairs<V, K> {
		private K key;
		private V value;
		private StorePairs next;

		/**
		 * Constructor for a pair node. Key and value are passed in and assigned to the
		 * node's private fields.
		 * 
		 * @param key   - value used to find nodes, pair with value
		 * @param value - value stored with key as a pair
		 */
		private StorePairs(K key, V value) {
			this.key = key;
			this.value = value;
		}

		private void setNext(StorePairs next) {
			this.next = next; // Reference to the next node in the linked list
		}

		private Object getKey() {
			return this.key; // getter method for key
		}

		private Object getValue() { // getter method for value
			return this.value;
		}

		private StorePairs getNext() {
			return this.next; // getter method for next node, returns StorePair object
		}
	}

	// Private fields
	private StorePairs head; // first node added, reference to the start of the linked list
	private StorePairs last; // last node added, reference to the end of the list
	int size = 0; // tracks how many nodes are in the list

	/**
	 * Constructor for the data structure
	 */
	public DS_My() {
		head = null;
	}

	/**
	 * Insert adds a new node to the end of the linked list. If it is the first node
	 * added, it is assigned to the head. If it is not the first, it is assigned to
	 * the 'next' field of the previous node. Exceptions are thrown if k is already
	 * in the list or it is null.
	 * 
	 * @param k - used to identify nodes
	 * @param v - value to be stored as a pair with k
	 */
	@Override
	public void insert(Comparable k, Object v) {
		if (k == null) // key value cannot be null
			throw new IllegalArgumentException("null key");
		if (contains(k)) // duplicate keys are not allowed
			throw new RuntimeException("duplicate key");
		if (head == null) { // if this is the first element inserted
			this.head = new StorePairs(k, v); // create the new pair and assign it to be the head
			this.last = this.head; // Saves it as the last value created. This will give reference to where the
									// next new pair should be added
			size++; // increment size for added pair
		} else {
			StorePairs add = new StorePairs(k, v); // creates new node/pair
			this.last.setNext(add); // links the new pair to the last pair created
			this.last = add; // save reference for next pair added
			size++;
		}
	}

	/**
	 * Removes the pair from the linked list. The reference to it is reassigned to
	 * the deleted node's next node.
	 * 
	 * @param k - used to identify the node being removed
	 * @return true - the node was successfully deleted
	 * @return false- the node was not deleted
	 */
	@Override
	public boolean remove(Comparable k) {
		StorePairs current;
		StorePairs hold;
		if (k == null)
			throw new IllegalArgumentException("null key");
		if (!this.contains(k)) // checks if the key is in the list, if not false is returned
			return false;
		if (head.getKey().equals(k)) {
			head = head.next; // loses the reference to the old head, it is garbage collected
			size--; // size decremented
			return true;
		}
		current = head;
		for (int i = 0; i < size; i++) { // iterates through the length of the list
			if (current.next.getKey().equals(k)) { // if the key is found on the list
				hold = current.next.next; // holds the node-being-deleted's reference to the next node
				current.setNext(hold); // reassigns the 'hold' to the old reference of the node-being-deleted, the
										// other reference is lost and the node is removed
				size--;
				return true; // node was removed
			}
		}
		return false;
	}

	/**
	 * Iterates through the list. Each k value of the list nodes is compared to the
	 * k passed in. If an equal k value is found the method returns true, false
	 * otherwise.
	 * 
	 * @param k - key value for the node being searched for
	 * @return true - if the node is in the list
	 * @return false - if the node is not in the list
	 */
	@Override
	public boolean contains(Comparable k) {
		StorePairs current = this.head;
		for (int i = 0; i < size(); i++) { // based on the size of the list
			if (current.getKey().equals(k)) {
				return true;
			} else if (current.getNext() != null) { // ensures that there is a next node
				current = current.getNext(); // iterates to the next node in the list
			}
		}
		return false;
	}

	/**
	 * This method returns the value paired with the key.
	 * 
	 * @param k - key to find corresponding value
	 * @return value - value paired with the k node
	 */
	@Override
	public Object get(Comparable k) {
		StorePairs current = head;
		if (k == null)
			throw new IllegalArgumentException("null key");
		for (int i = 0; i < size; i++) {
			if (current.getKey().equals(k)) { // searches for key equal to k
				return current.value; // if found, the value is returned
			} else if (current.getNext() != null) {
				current = current.getNext(); // iterates through for k
			}
		}
		return null; // if the value is not found
	}

	@Override
	public int size() {
		return this.size; // returns size(an int) from size private field
	}
}