/* 
 * Dictionary ADT
 * 
 * Implements a Dictionary ADT in Java based on the linked list data structure
 * More details in README
 * 
 * Parts of the code are taken and altered from the examples provided 
 * on the course web page, or built based on the examples.
 */

public class Dictionary implements DictionaryInterface {

	public Dictionary() {
		head = new Node(null,null);
		head.prev = head.next = head;
		numItems = 0;
	}
	
	// used to store the keys
	// double linked list with prev and next
	private class Node {
		String key, value;
		Node prev;
		Node next;
		
		public Node(String k, String v) {
			key = k; 
			value = v; 
			next = null; 
			prev = null;
		}
	}
	
	private Node head; // reference to the first Node in ADT
	private int numItems = 0; // number of items in this ADT
	
	// findKey()
	// taken from examples and slightly altered for it to work for this ADT
	// returns a reference to the Node at position index in this ADT
	// pre: none
	private Node findKey(String target){
		
		if (head != null) {
			
			Node N = head.next;
			
			while (N != null) {
				
				// in case key is null
				if (target == null) {
					
					if (N.key == null) {
						return N;
					}
					else {
						N = N.next;
						return null;
					}
					
				}
				
				if (target.equals(N.key)) {
					return N;
				}
				
				N = N.next;
				
			}
		}
		
		return null;
	}
	
	// isEmpty()
	// pre: none
	// returns true if this Dictionary is empty, false otherwise
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	// size()
	// pre: none
	// returns the number of entries in this Dictionary
	public int size() {
		return numItems;
	}
	
	// lookup()
	// pre: none
	// returns value associated key, or null reference if no such key exists
	public String lookup(String key) {
		if (findKey(key) != null) {
			return findKey(key).value;
		}
		else {
			return null;
		}
	}
	
	// insert()
	// inserts new (key,value) pair into this Dictionary
	// pre: lookup(key)==null
	public void insert(String key, String value) throws DuplicateKeyException {
		Node node = new Node(key, value);
		if (isEmpty()) {
			node.prev = head;
			head.prev = head.next = node;
		}else {
			if (findKey(key) != null) {
				throw new DuplicateKeyException("cannot insert duplicate keys");
			}
			Node lastNode = head.prev;
			node.prev = lastNode;
			lastNode.next = node;
			head.prev = node;
		}
		
		numItems++;
		
	}
	
	// delete()
	// deletes pair with the given key
	// pre: lookup(key)!=null
	public void delete(String key) throws KeyNotFoundException{
		if (isEmpty() || findKey(key) == null) {
			throw new KeyNotFoundException("cannot delete non-existent key");
		}
		else {
			Node deleteItem = findKey(key);
			deleteItem.prev.next = deleteItem.next;
			
			if(head.prev.equals(deleteItem) ) {
				head.prev = deleteItem.prev;
			}
			numItems--;
		}
	}
	
	// makeEmpty()
	// pre: none
	public void makeEmpty() {
		head = new Node(null,null);
		head.prev = head.next = head;
		numItems = 0;
	}
	
	// toString()
	// returns a String representation of this Dictionary
	// overrides Object's toString() method
	// pre: none
	public String toString() {
		String result = "";
		
		if (isEmpty()) {
			result = "\n";
		}else {
			Node next = head.next;
			while (next != null) {
				result += next.key + " " + next.value + "\n";
				next = next.next;
			}
		}
		return result;
	}

}
