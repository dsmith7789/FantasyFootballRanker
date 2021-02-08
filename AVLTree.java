package application;

/**
 * Course: 		CS400 - Fall 2020
 * Program:		Final Project
 * @author 		Dante Smith
 * Web Sources: none
 * Personal Help: none
 * 
 * Stores methods to manage adding/removing nodes from a AVL Tree with Key/Value Nodes, 
 * @see TreeADT.java
 * @param <K>
 * @param <V>
 */

public class AVLTree<K extends Comparable<K>, V> implements TreeADT<K, V> {
	// instance variable for this tree
	private KeyValueNode<K, V> root = null;

	/** 
	 * Inner class representing a node of the AVL tree. 
	 * @param <K> The Key, which will be Comparable
	 * @param <V> The Value
	 */
	@SuppressWarnings("hiding")
	private class KeyValueNode<K, V> {
		private K key;
		private V value;
		@SuppressWarnings("unused")
		private int height;
		private KeyValueNode<K, V> left, right;

		/**
		 * constructor for the key value node inner class
		 * @param key the lookup key for the node
		 * @param value the value associated with the key
		 */
		public KeyValueNode(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}

		/** 
		 *  a recursive method which finds the height 
		 *  @return the height of the tree with this KeyValueKeyValueKeyValueNode as the root
		 *          if this KeyValueNode has no children, return 0
		 */
		private int getHeight() {
			if (this.left == null && this.right == null) {
				return 0;
			}
			if (this.right == null) {
				return 1 + this.left.getHeight();
			}
			if (this.left == null) {
				return 1 + this.right.getHeight();
			}
			return 1 + Math.max(this.left.getHeight(), this.right.getHeight());
		}
		

		/**
		 * returns the balance factor of this KeyValueNode, 
		 * @return height of left child - height of right child
		 */
		public int getBalance() {
			if (this.left == null && this.right == null) {
				return 0;
			}
			if (this.left == null) {
				return 0 - this.getHeight();
			}
			if (this.right == null) {
				return this.getHeight();
			}
			
			return this.left.getHeight() - this.right.getHeight();		
		}
		
		
		/**
		 * Follows these steps to perform a left-rotation:
		 * 1. Set temporary node = grandparent node's right child
		 * 2. Set grandparent node's right child = temporary node's left child
		 * 3. Set temporary node's left child = grandparent node
		 * @param node the "grandparent" node
		 * @return the temporary node
		 */
		public KeyValueNode<K, V> rotateLeft() {
			KeyValueNode<K, V> temp = this.right;
			this.right = temp.left;
			temp.left = this;
			return temp;			
		}
		
		/**
		 * Follows these steps to perform a right-rotation:
		 * 1. Set temporary node = grandparent node's left child
		 * 2. Set grandparent node's left child = temporary node's right child
		 * 3. Set temporary node's right child = grandparent node
		 * @param node the "grandparent node"
		 * @return the temporary node
		 */
		public KeyValueNode<K, V> rotateRight() {
			KeyValueNode<K, V> temp = this.left;
			this.left = temp.right;
			temp.right = this;
			return temp;			
		}
		
	} // KeyValueNode class

	// AVL Tree fields
	private int size;
	@SuppressWarnings("unused")
	private int height;
	
	/**
	 * constructor method for the AVL tree
	 */
	public AVLTree() {
		this.size = 0;
		this.height = -1;
		this.root = null;
	}
	

	/**
	 * Print a tree sideways to show structure. This code is completed for you.
	 */
	public void printSideways() {
		System.out.println("------------------------------------------");
		recursivePrintSideways(root, "");
		System.out.println("------------------------------------------");
	}

	/**
	 * Print nodes in a tree. This code is completed for you. 
	 * You are allowed to modify this code to include balance factors or heights
	 * @param current
	 * @param indent
	 */
	private void recursivePrintSideways(KeyValueNode<K, V> current, String indent) {
		if (current != null) {
			recursivePrintSideways(current.right, indent + "    ");
			System.out.println(indent + current.key);
			recursivePrintSideways(current.left, indent + "    ");
		}
	}

	@Override
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * handles addition of a node into this AVL tree
	 * @param key the key of the node we want to insert into the tree
	 * @param value the value of the node we want to insert into the tree
	 * @throws DuplicateKeyException if adding a node with the same key as a node already in the tree
	 * @throws IllegalKeyException if the key is null
	 */
	@Override
	public void insert(K key, V value) throws DuplicateKeyException, IllegalKeyException {
		if (get(key) == null && key != null) {
			root = insert(root, key, value);
			this.size++; // if we're in this part of the insert, then we know we're inserting something.
		}
		else if (get(key) != null){
			throw new DuplicateKeyException();
		}
		else if (key == null) {
			throw new IllegalKeyException();
		}
		
	}
	
	/**
	 * private recursive helper method adds a node maintaining BST rules
	 * @param current
	 * @param key
	 * @param value
	 * @return the updated reference to current
	 */
	private KeyValueNode<K, V> insert (KeyValueNode<K, V> current, K key, V value) {
		// base case - create the new node and add it to the tree
		if (current == null) {
			current = new KeyValueNode<K, V>(key, value);
		}
		
		// if adding a node with key less than current node, go left
		else if (key.compareTo(current.key) < 0) {
			current.left = insert(current.left, key, value);
		}
		
		// if adding a node with key greater than current node, go right
		else {
			current.right = insert(current.right, key, value);
		}
		
		// Re-balance the tree
		return balance(current);
		
	}
	

	private KeyValueNode<K, V> balance(KeyValueNode<K, V> node) {
		// If Right heavy subtree
		if (node.getBalance() <= -2) {
			// Right-left case:
			if (node.right.getBalance() <= 0) {
				return rightRightCase(node);
			}
			
			// Right-left case:
			else {
				return rightLeftCase(node);
			}
		}
		
		// If Left heavy subtree:
		else if (node.getBalance() >= 2) {
			// Left-left case:
			if (node.left.getBalance() >= 0) {
				return leftLeftCase(node);
			}
			
			// Right-Left case:
			else {
				return leftRightCase(node);
			}
		}
		
		/* Otherwise, the node has a balance factor of 0, +1, or -1 
		 * so nothing needs to be done. */
		
		return node;
		
	}
	
	/**
	 * Handles re-balance in cases like this:
	 * 		 node
	 * 		/
	 * 	   temp
	 * 	  /
	 * 	 child
	 * @param node the node we will be rotating around
	 * @return the node that has taken the place of the input node
	 */
	private KeyValueNode<K, V> leftLeftCase(KeyValueNode<K, V> node) {
		return node.rotateRight();
	}
	
	/**
	 * Handles re-balance in cases like this:
	 * 		 node
	 * 		/
	 * 	   temp
	 * 	  	\
	 * 	 	 child
	 * @param node the node we will be rotating around
	 * @return the node that has taken the place of the input node
	 */
	private KeyValueNode<K, V> leftRightCase(KeyValueNode<K, V> node) {
		node.left = node.left.rotateLeft();
		return leftLeftCase(node);
	}
	
	/**
	 * Handles re-balance in cases like this:
	 *   node
	 * 	  \
	 * 	   temp
	 * 	  	\
	 * 	 	 child
	 * @param node the node we will be rotating around
	 * @return the node that has taken the place of the input node
	 */
	private KeyValueNode<K, V> rightRightCase(KeyValueNode<K, V> node) {
		return node.rotateLeft();	
	}
	
	/**
	 * Handles re-balance in cases like this:
	 *   node
	 * 	  \
	 * 	   temp
	 * 	  /
	 * 	 child
	 * @param node the node we will be rotating around
	 * @return the node that has taken the place of the input node
	 */
	private KeyValueNode<K, V> rightLeftCase(KeyValueNode<K, V> node) {
		node.right = node.right.rotateRight();
		return rightRightCase(node);	
	}

	/**
	 * handles removal of a node from the AVL tree
	 * @param key the key of the node we want to remove from the tree
	 */
	@Override
	public void delete(K key) throws IllegalKeyException {
		if (get(key) != null) {
			root = delete(root, key);
			this.size--;	// if we're in this part of the code, definitely removing a node so decrement the size
		}
		else {
			throw new IllegalKeyException();
		}
		
	}
	
	/**
	 * private recursive method to help with the removal of nodes
	 * @param current the node currently being evaluated
	 * @param key the lookup key of the node we are trying to remove
	 * @return the current node
	 */
	private KeyValueNode<K, V> delete(KeyValueNode<K, V> current, K key) {
		
		// element not found
		if (current == null) {
			return null;
		} 
		
		// if element is less than current, go left
		if (key.compareTo(current.key) < 0) { 
			current.left = delete(current.left, key);
			return balance(current);
		}
		else if (key.compareTo(current.key) > 0) {
			current.right = delete(current.right, key);
			return balance(current);
		}
		
		// // if element found, check 4 cases for removal
		if (current.key.equals(key)) {
			
			/* if the node is a leaf, we can just remove it */
			if (current.left == null && current.right == null) {
				this.size--;
				return null;
			}
			
			/* if the node only has a right child, then the right child of this node becomes 
			 * the right child of the parent node. */
			if (current.left == null) {
				this.size--;
				return current.right;
			}
			
			/* if the node only has a left child, then the left child of this node becomes 
			 * the left child of the parent node. */
			if (current.right == null) {
				this.size--;
				return current.left;
			}
			
			/* if the node has both a left and a right child, then:
			 * 1. Find the in-order predecessor of this node using a while loop
			 * 	-- this is the largest value in the left subtree of the node
			 * 2. Copy the data from the in-order predecessor into the current node
			 * 3. remove the in-order predecessor from the tree without calling remove
			 */
			
			KeyValueNode<K, V> tracker = current.left;
			KeyValueNode<K, V> trackerParent = current;
			while(tracker.right != null) {
				trackerParent = tracker;
				tracker = tracker.right;
			}
			
			// to save off the values of the tracker and the parent before copying values
			K trackerKey = tracker.key;
			K trackerParentKey = trackerParent.key;
			
			// copy the data from the in-order predecessor (tracker) into the current node
			current.key = tracker.key;
			current.value = tracker.value;
			
			// need to account for if the in-order predecessor is to the left or right of its parent
			// mostly it will be to the right, but if the tracker parent is also the node to delete, this is a case
			// where the in-order predecessor is actually to the left of its parent.
			if (trackerKey.compareTo(trackerParentKey) < 0) {
				trackerParent.left = null;
			}
			else {
				trackerParent.right = null;
			}
			
		}

		//Re-balance the tree
		return balance(current);
		
	}

	/**
	 * Given a lookup key, returns the value of the node that has the key
	 * @param key the key of the node to lookup
	 * @return value the value of the node with this key, or null if key not found
	 * @throws IllegalKeyException if the key is null 
	 */
	@Override
	public V get(K key) throws IllegalKeyException {
		if (key != null ) {
			KeyValueNode<K, V> current = get(root, key);
			if (current != null) {
				return current.value;
			}
			
			return null;
		}
		else {
			throw new IllegalKeyException();
		}
	}
	
	/**
	 * private recursive helper method for getting the value from the AVL tree given the key
	 * @param current the node we're currently on
	 * @param key the key of the node we're trying to find
	 * @return the current node
	 */
	private KeyValueNode<K, V> get(KeyValueNode<K, V> current, K key) {
		// base case, if we reach here then a node with this key is not in the tree
		if (current == null) {
			return null;
		}
		
		// if our key is less than the key of the current node, search in the left subtree:
		if (key.compareTo(current.key) < 0) {
			return get(current.left, key);
		}
		
		// if our key is greater than the key of the current node, search in the right subtree:
		if (key.compareTo(current.key) > 0) {
			return get(current.right, key);
		}
		
		
		// if the key was not less/greater (can't be null or invalid otherwise we throw exception earlier)
		// then the current node must be the one we're looking for, so we return it
		return current;
		
	}

	@Override
	public String preOrderTraversal() {
		return preOrderTraversal(root, "");
	}
	
	/**
	 * private helper method for the preOrderTraversal public method
	 * @param current the node currently being evaluated
	 * @param result the result string that will eventually get returned
	 * @return result the result string
	 */
	private String preOrderTraversal(KeyValueNode<K, V> current, String result) {
		if (current != null) {
			result += current.key + " ";
			result = preOrderTraversal(current.left, result);
			result = preOrderTraversal(current.right, result);
		}
		return result;
	}
	
	public static void main(String [] args) {

	}
}


