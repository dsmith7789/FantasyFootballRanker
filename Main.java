/**
 * Course: 		CS400 - Fall 2020
 * Program:		Final Project
 * @author 		Dante Smith
 * Web Sources: none
 * Personal Help: none
 * 
 * Stores methods to manage the GUI for outputting players and ranks 
 * @see AVLTree.java
 */

package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {
	
	private static File fileSelection;
	private static Label titleLabel;
	private static Label fileInputLabel;
	private static Label fileInputInstructions;
	private static Label playerInputLabel;
	private static Label playerInputInstructions;
	private static Label outputLabelInstructions;
	private static Label selectedFilePath;
	private static Label outputRankLabel;
	private static Label outputNameLabel;
	private static Label outputTeamLabel;
	private static Label outputPositionLabel;
	private static Label outputPointsScoredLabel;
	private static Label fileLoadedLabel;
	
	private static VBox headerVBox;
	private static VBox fileInputVBox;
	private static VBox playerInputVBox;
	private static VBox outputBox;
	private static VBox outputRankBox;
	private static VBox outputNameBox;
	private static VBox outputTeamBox;
	private static VBox outputPositionBox;
	private static VBox outputPointsScoredBox;
	
	private static HBox fileInputHBox;
	private static HBox outputHBox;
	
	private static FileChooser fileSelector;
	
	private static Button fileChooserButton;
	private static Button loadFileButton;
	private static Button confirmPlayerButton;
	
	private static TextField playerSelectionTextField;
	private static TextField selectedFileTextField;
	private static TextField outputRankTextField;
	private static TextField outputNameTextField;
	private static TextField outputTeamTextField;
	private static TextField outputPositionTextField;
	private static TextField outputPointsScoredTextField;
	
	private static ImageView mainImage;
	
	private static AVLTree<String, Player> playerTree;
	
	private static GridPane root;
	
	private static Alert selectNoFileAlert;
	private static Alert loadNoFileAlert;
	private static Alert inputNoPlayerAlert;
	private static Alert improperFormatFileAlert;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// Define all the sub-fields 
			root = new GridPane();
			labelSetup();
			imageSetup();
			textFieldSetup();
			buttonSetup();
			alertSetup();
			
			
			fileSelector = new FileChooser();
			
			fileSelector.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
			
	        fileChooserButton = new Button("Select File");
	        fileChooserButton.setOnAction(e -> {
	            File selectedFile = fileSelector.showOpenDialog(primaryStage);
	            fileSelection = selectedFile;

	            try {
	            	selectedFileTextField.setText(fileSelection.getPath());	
	            	fileLoadedLabel.setText("New file selected. Please click 'Load File!' to finish loading.");
	            } catch (NullPointerException noFileSelected) {
	            	selectNoFileAlert.showAndWait();
	            }
	            
	        });
		
	        vBoxSetup();
			
			// Add the sub-fields to the GridPane	
			
			
			root.add(headerVBox, 1, 0);
			root.add(fileInputInstructions, 0, 1);
			root.add(playerInputInstructions, 1, 1);
			root.add(outputLabelInstructions, 2, 1);
			root.add(fileInputVBox, 0, 2);
			root.add(playerInputVBox, 1, 2);
			root.add(outputBox, 2, 2);

			Scene scene = new Scene(root,1500,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to store the steps for setting up the labels
	 */
	public static void labelSetup() {
		titleLabel  = new Label("Fantasy Football Rankings");
		titleLabel.setFont(new Font("Times New Roman", 20));
		
		fileInputLabel = new Label("File Input");
		
		playerInputLabel = new Label("Player Input");
		
		selectedFilePath = new Label("Selected File and Path");
		
		fileInputInstructions = new Label("STEP 1: Use the 'Select File' button to choose a .csv file formatted "
				+ " for analysis. Then click 'Load File!' once you've picked the file you want.");
		fileInputInstructions.setWrapText(true);
		fileInputInstructions.setMinWidth(200);
		fileInputInstructions.setMaxWidth(400);
		fileInputInstructions.setPrefWidth(400);
		fileInputInstructions.setPadding(new Insets(20));
		fileInputInstructions.setFont(new Font("Calibri", 15));
		
		playerInputInstructions = new Label("STEP 2: Type the name of the player whose rank you wish to find. Then click 'Confirm Player!' to search"
				+ " for that player in the file you specified in step 1.");
		playerInputInstructions.setWrapText(true);
		playerInputInstructions.setWrapText(true);
		playerInputInstructions.setMinWidth(200);
		playerInputInstructions.setMaxWidth(400);
		playerInputInstructions.setPrefWidth(400);
		playerInputInstructions.setPadding(new Insets(20));
		playerInputInstructions.setFont(new Font("Calibri", 15));
		
		outputLabelInstructions = new Label("STEP 3: If the player was in the file you specified, then their team, position, rank, and points scored"
				+ " will show up here.");
		outputLabelInstructions.setWrapText(true);
		outputLabelInstructions.setWrapText(true);
		outputLabelInstructions.setMinWidth(200);
		outputLabelInstructions.setMaxWidth(400);
		outputLabelInstructions.setPrefWidth(400);
		outputLabelInstructions.setPadding(new Insets(20));
		outputLabelInstructions.setFont(new Font("Calibri", 15));
		
		outputRankLabel = new Label("Rank");
		outputNameLabel = new Label("Name");
		outputTeamLabel = new Label("Team");
		outputPositionLabel = new Label("Position");
		outputPointsScoredLabel = new Label("Points Scored");
		
		fileLoadedLabel = new Label();
		
	}
	
	/**
	 * Method to store the steps for setting up the vertical boxes
	 */
	public static void vBoxSetup() {
		headerVBox = new VBox(titleLabel, mainImage);
		headerVBox.setSpacing(5);
		headerVBox.setPadding(new Insets(20));
		headerVBox.setAlignment(Pos.BASELINE_CENTER);
		
		
		fileInputHBox = new HBox(selectedFilePath, selectedFileTextField);
		fileInputHBox.setSpacing(5);
		fileInputHBox.setPadding(new Insets(10));
		
		fileInputVBox = new VBox(fileInputLabel, fileChooserButton, fileInputHBox, loadFileButton, fileLoadedLabel);
		fileInputVBox.setSpacing(5);
		fileInputVBox.setPadding(new Insets(20));
		fileInputVBox.setAlignment(Pos.BASELINE_LEFT);
		
		playerInputVBox = new VBox(playerInputLabel, playerSelectionTextField, confirmPlayerButton);
		playerInputVBox.setSpacing(5);
		playerInputVBox.setPadding(new Insets(20));
		playerInputVBox.setAlignment(Pos.BASELINE_LEFT);
		
		outputRankBox = new VBox(outputRankLabel, outputRankTextField);
		outputNameBox = new VBox(outputNameLabel, outputNameTextField);
		outputTeamBox = new VBox(outputTeamLabel, outputTeamTextField);
		outputPositionBox = new VBox(outputPositionLabel, outputPositionTextField);
		outputPointsScoredBox = new VBox(outputPointsScoredLabel, outputPointsScoredTextField);
		
		
		outputHBox = new HBox(outputRankBox, outputNameBox, outputTeamBox, outputPositionBox, outputPointsScoredBox);
		outputHBox.setAlignment(Pos.CENTER_LEFT);
		
		outputBox = new VBox(outputHBox);
		outputBox.setSpacing(5);
		outputBox.setPadding(new Insets(20));
		outputBox.setAlignment(Pos.BASELINE_LEFT);
	}
	
	/**
	 * Method to store the steps for setting up the image
	 */
	public static void imageSetup() {
		mainImage = new ImageView(new Image("Stiffarm.jpg"));
		mainImage.setFitWidth(300);
		mainImage.setPreserveRatio(true);	
	}
	
	
	/**
	 * Method to store the steps for setting up the buttons
	 */
	public static void buttonSetup() {
		loadFileButton = new Button("Load File!");
        loadFileButton.setOnAction(e -> {
            fileReader();
        });
        
		confirmPlayerButton = new Button("Confirm Player!");
        confirmPlayerButton.setOnAction(e -> {
            fileSearcher();
        });

	}
	
	/**
	 * Method to store the steps for setting up the text fields
	 */
	public static void textFieldSetup() {
		playerSelectionTextField = new TextField();
		
		selectedFileTextField = new TextField();
		selectedFileTextField.setEditable(false);
		
		outputRankTextField = new TextField();
		outputRankTextField.setEditable(false);
		
		outputNameTextField = new TextField();
		outputNameTextField.setEditable(false);
		
		outputTeamTextField = new TextField();
		outputTeamTextField.setEditable(false);
		
		outputPositionTextField = new TextField();
		outputPositionTextField.setEditable(false);
		
		outputPointsScoredTextField = new TextField();
		outputPointsScoredTextField.setEditable(false);
		
	}
	
	/**
	 * handles reading in the file
	 */
	public static void fileReader() {
		playerTree = new AVLTree<String, Player>();
		
		// parse csv
		try {
			@SuppressWarnings("resource")
			Scanner csvScnr = new Scanner(fileSelection);
			@SuppressWarnings("unused")
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
					playerTree.insert(name, player);
				} catch (DuplicateKeyException e) {
					loadNoFileAlert.showAndWait();
				} catch (IllegalKeyException e) {
					loadNoFileAlert.showAndWait();
				}

			}

			fileLoadedLabel.setText("File successfully loaded!");
			
		} catch (FileNotFoundException e1) {
			loadNoFileAlert.showAndWait();
		} catch (NullPointerException e2) {
			loadNoFileAlert.showAndWait();
		} catch (Exception e) {
			improperFormatFileAlert.showAndWait();
			fileLoadedLabel.setText("Error in loading file. Please try again.");
		}
	}
	
	/**
	 * method to handle code for searching through the tree from the loaded file to find
	 * the player that you picked.
	 */
	public static void fileSearcher() {
		String name = playerSelectionTextField.getText().toUpperCase();;
		try {
			Player player = (Player) playerTree.get(name);
			if (player != null) {
				outputRankTextField.setText(player.getRank());
				outputNameTextField.setText(player.getName());
				outputTeamTextField.setText(player.getTeam());
				outputPositionTextField.setText(player.getPosition());
				outputPointsScoredTextField.setText(String.valueOf(player.getPprPoints()));
			}
			else {
				inputNoPlayerAlert.showAndWait();
			}

		} catch (IllegalKeyException e) {
			inputNoPlayerAlert.showAndWait();
		} catch (NullPointerException e) {
			inputNoPlayerAlert.showAndWait();
		}
	}
	

	/**
	 * method to manage setting up alerts.
	 */
	public static void alertSetup() {
		selectNoFileAlert = new Alert(Alert.AlertType.WARNING);
		selectNoFileAlert.setTitle("ALERT");
		selectNoFileAlert.setContentText("You did not pick a file to load.");
		
		loadNoFileAlert = new Alert(Alert.AlertType.WARNING);
		loadNoFileAlert.setTitle("ALERT");
		loadNoFileAlert.setContentText("No file was loaded. Please select a file to load.");
		
		inputNoPlayerAlert = new Alert(Alert.AlertType.WARNING);
		inputNoPlayerAlert.setTitle("ALERT");
		inputNoPlayerAlert.setContentText("No valid player was specified. Please type a player's name.");
		
		improperFormatFileAlert = new Alert(Alert.AlertType.WARNING);
		improperFormatFileAlert.setTitle("ALERT");
		improperFormatFileAlert.setContentText("Error in formatting of .csv file. Please ensure the file is a .csv"
				+ " with first 5 columns of: Rank, Name, Team, Position, Points.");
	}
	public static void main(String[] args) {
		launch(args);
	}
}
