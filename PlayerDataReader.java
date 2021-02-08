/**
 * Course: 		CS400 - Fall 2020
 * Program:		Final Project
 * @author 		Dante Smith
 * Web Sources: none
 * Personal Help: none
 * 
 * Stores methods to manage adding/removing NFL players to a AVL Tree with Key/Value Nodes, 
 * @see AVLTree.java
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayerDataReader {
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		AVLTree<String, Player> tree = new AVLTree<String, Player>();
		
		// parse csv
		try {
			Scanner csvScnr = new Scanner(new File("C:\\Users\\smith\\eclipse-workspace\\CS400_Final Project\\FantasyPPR.csv"));
			String headerRow = csvScnr.nextLine(); // read past the header row of the CSV file
			
			while (csvScnr.hasNextLine()) {
				String row = csvScnr.nextLine();
				String[] data = row.split(",");

				String rank = data[0];
				String name = data[1];
				String team = data[2];
				String position = data[3];
				double points = Double.parseDouble(data[4]);

				Player player = new Player(rank, name, team, position, points);
				try {
					tree.insert(name, player);
				} catch (DuplicateKeyException e) {
					e.printStackTrace();
				} catch (IllegalKeyException e) {
					e.printStackTrace();
				}


			}
			
		} catch (FileNotFoundException e1) {
			System.out.println("File not found.");
		}
		
		// prompt user to look up player
		boolean validPlayer = true;
		Scanner scnr = new Scanner(System.in);
		while (validPlayer) {
			System.out.println("Enter name of player: ");
			String name = scnr.nextLine().toUpperCase();
			try {
				Player player = tree.get(name);
				if (player != null) {
					System.out.println(player.toString());
				}
				else {
					validPlayer = false;
				}
			} catch (IllegalKeyException e) {
				System.out.println("Cannot enter a null player.");
				e.printStackTrace();
			}
		}
	}

}
