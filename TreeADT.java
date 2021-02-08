package application;

/**
 * Interface for a search tree.
 */
public interface TreeADT<K extends Comparable<K>, V>  {

	/**
	 * Checks for an empty tree.
	 * @return true if tree contains 0 items
	 */
	public boolean isEmpty();

	/**
	 * Adds a key, value pair to the tree.
	 * @param key
	 * @param value
	 * @throws DuplicateKeyException if the key has already been inserted into the tree
	 * @throws IllegalKeyException if the key is null
	 */
	public void insert(K key, V value) throws DuplicateKeyException, IllegalKeyException;

	/**
	 * Removes the key and its associated value from the AVL tree.
        * Does nothing if the key is not found.
	 * @param key
	 * @throws IllegalKeyException if attempt to delete null
	 */
	public void delete(K key) throws IllegalKeyException;
	
	/**
	 * Get a value in the tree given the key.
	 * @param key
	 * @return the value associated with this key, or null if key not found
	 * @throws IllegalArgumentException if the key is null
	 */
	public V get(K key) throws IllegalKeyException;

	/**
	 * Returns the AVL tree in pre-order traversal. The string that is returned will have each node's key separated
	 * by a whitespace. Example: "MKE ATL MSN LAX". This method may help you debug your implementation.
	 */
	public String preOrderTraversal();
	
	/**
	 * Prints AVL tree sideways to show structure. This method may help you debug your implementation.
	 */
	public void printSideways();
}


