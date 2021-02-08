package application;

/**
 * Course: 		CS400 - Fall 2020
 * Program:		Program 2 AVL Airports
 * @author 		Dante Smith
 * Web Sources: none
 * Personal Help: none
 * 
 * Stores methods to test the AVL Tree with Key/Value Nodes, 
 * @see AVLTree.java
 * @param <K>
 * @param <V>
 */

public class AVLTester {
	/**
	 * tests the following methods from the AVLTree class on an empty tree:
	 *		preOrderTraversal()
	 *		get()
	 * @return false if any of the tests fail, true if all the tests pass
	 */
	public static boolean testEmpty() {
		AVLTree<Integer, String> tree = new AVLTree<Integer, String>();		

		if (! tree.preOrderTraversal().trim().equals("")) {
			System.out.println("tree preOrderTraversal is giving an incorrect result on an empty tree");
			return false;
		}
		try {
			if (tree.get(1) != null) {
				System.out.println("tree get is giving an incorrect result on an empty tree");
				return false;
			}
		} catch (IllegalKeyException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * tests the insert() method on the AVLTree class
	 * @return false if any of the tests fail, true if all the tests pass
	 */
	public static boolean testAdd() {
		
		/* integer, String type tree */
		AVLTree<Integer, String> tree = new AVLTree<Integer, String>();
		try {
			tree.insert(9, "Joe Burrow");
			tree.insert(85, "Tee Higgins");
			tree.insert(18,  "AJ Green");
			tree.insert(28, "Joe Mixon");
			tree.insert(83,"Tyler Boyd");
			tree.insert(25, "Giovani Bernard");
			
			if (tree.isEmpty()) {
				System.out.println("Tree is returning as empty after adding nodes.");
				return false;
			}

			String correct = "28 18 9 25 83 85 ";
			
			if (!(tree.preOrderTraversal().equals(correct))) {
				System.out.println("Incorrect pre-order traversal when adding nodes.");
				return false;
			}

		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		} catch (IllegalKeyException e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	/**
	 * tests the delete() method on the AVLTree class
	 * @return false if any of the tests fail, true if all the tests pass
	 */
	public static boolean testDelete() {
		
		/* integer, String type tree */
		AVLTree<Integer, String> tree = new AVLTree<Integer, String>();
		try {
			tree.insert(12, "Aaron Rodgers");
			tree.insert(33, "Aaron Jones");
			tree.insert(17,  "Davante Adams");
			tree.insert(13, "Allen Lazard");
			tree.insert(83,"Marquez Valdes-Scantling");
			tree.insert(23, "Jaire Alexander");
			
			tree.delete(17);
			tree.delete(12);
			
			String correct = "33 13 23 83 ";
			if (!(tree.preOrderTraversal().equals(correct))) {
				System.out.println("Incorrect pre-order traversal when deleting nodes.");
				return false;
			}

		} catch (DuplicateKeyException e) {
			e.printStackTrace();
		} catch (IllegalKeyException e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	public static void main(String [] args){
		
		System.out.println("Tests of empty tree returned " + testEmpty());
		System.out.println("Tests of insert method returned " + testAdd());
		System.out.println("Tests of delete method returned " + testDelete());

	}


}

