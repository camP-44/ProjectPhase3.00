package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Cameron Pazul ASU: 1213038682
// CSE 360
// HW 1

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();

            // Food
            CheckBox eggSandwich = new CheckBox("Egg Sandwich");
            CheckBox chickenSandwich = new CheckBox("Chicken Sandwich");
            CheckBox bagel = new CheckBox("Bagel");
            CheckBox potatoSalad = new CheckBox("Potato Salad");

            // Drinks
            ToggleGroup drinkGroup = new ToggleGroup();
            RadioButton coffee = new RadioButton("Coffee");
            RadioButton greenTea = new RadioButton("Green Tea");
            RadioButton blackTea = new RadioButton("Black Tea");
            RadioButton orangeJuice = new RadioButton("Orange Juice");
            coffee.setToggleGroup(drinkGroup);
            greenTea.setToggleGroup(drinkGroup);
            blackTea.setToggleGroup(drinkGroup);
            orangeJuice.setToggleGroup(drinkGroup);

            // Bill
            TextArea billText = new TextArea();
            billText.setEditable(false);

            // Buttons
            Button orderButton = new Button("Order");
            Button cancelButton = new Button("Cancel");
            Button confirmButton = new Button("Confirm");
            
            // Button Actions
            int num = 0;
            orderButton.setOnAction(e -> orderFunction(eggSandwich, chickenSandwich, bagel, potatoSalad, 
            		drinkGroup,  coffee,  greenTea,  blackTea,  orangeJuice,  billText));
            
            cancelButton.setOnAction(e -> cancelFunction(eggSandwich, chickenSandwich, bagel, potatoSalad, 
            		drinkGroup,  coffee,  greenTea,  blackTea,  orangeJuice,  billText, num));
            
            confirmButton.setOnAction(e -> confirmFunction(eggSandwich, chickenSandwich, bagel, potatoSalad, 
            		drinkGroup,  coffee,  greenTea,  blackTea,  orangeJuice,  billText));
            	
            // Layout
            GridPane gridPane = new GridPane();
            gridPane.setHgap(25);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(10));

            // Add components to the grid
            gridPane.addColumn(0, new Label("Food"), eggSandwich, chickenSandwich, bagel, potatoSalad);
            gridPane.addColumn(1, new Label("Drinks"),blackTea, greenTea, coffee, orangeJuice);
            gridPane.addColumn(2, new Label("Bill"), billText);

            // Button box
            HBox buttonBox = new HBox(10, orderButton, cancelButton, confirmButton);
            buttonBox.setPadding(new Insets(10));
            buttonBox.setAlignment(Pos.CENTER);
            
            // Deli Name
            Label deliName = new Label("Joe's Deli");
            deliName.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");
            BorderPane.setAlignment(deliName, Pos.CENTER);
            
            // Placement
            root.setTop(deliName);
            root.setCenter(gridPane);
            root.setBottom(buttonBox);

            // Scene Info
            Scene scene = new Scene(root, 800, 400);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Joe's Deli");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Order Function outputs the total order and the final price with tax
    private void orderFunction(CheckBox eggSandwich, 
    		CheckBox chickenSandwich, 
    		CheckBox bagel, 
    		CheckBox potatoSalad, 
    		ToggleGroup drinkGroup, 
    		RadioButton coffee, 
    		RadioButton greenTea, 
    		RadioButton blackTea, 
    		RadioButton orangeJuice, 
    		TextArea billTextArea) {
    	StringBuilder totalBill = new StringBuilder("Your Order:\n");
    	double totalBillCost = 0.0;
    	
    	// Bill Addition
    	if (eggSandwich.isSelected()) {
    		totalBill.append("Egg Sandwhich\n");
    		totalBillCost += 7.99;
    	}
    	if (chickenSandwich.isSelected()) {
    		totalBill.append("Chicken Sandwhich\n");
    		totalBillCost += 9.99;
    	}
    	if (bagel.isSelected()) {
    		totalBill.append("Bagel\n");
    		totalBillCost += 2.50;
    	}
    	if (potatoSalad.isSelected()) {
    		totalBill.append("Potato Salad\n");
    		totalBillCost += 4.49;
    	}
    	
    	// Bill Addition
    	RadioButton selectedDrink = (RadioButton) drinkGroup.getSelectedToggle();
    	if(selectedDrink != null) {
    		if(coffee.isSelected()) {
    			totalBill.append("Coffee\n");
    			totalBillCost += 1.99;
    		}
    		if(greenTea.isSelected()) {
    			totalBill.append("Green Tea\n");
    			totalBillCost += 0.99;
    		}
    		if(blackTea.isSelected()) {
    			totalBill.append("Black Tea\n");
    			totalBillCost += 1.25;
    		}
    		if(orangeJuice.isSelected()) {
    			totalBill.append("Orange Juice\n\n");
    			totalBillCost += 2.25;
    		}
    	}
    	
    	// Tax
    	double tax = totalBillCost * 0.07;
    	double finalCost = totalBillCost + tax;
    	
    	// Adding Final Touches to Text Area
    	totalBill.append("\nSubtotal: $").append(String.format("%.2f", totalBillCost));
    	totalBill.append("\nTax (7%): $").append(String.format("%.2f", tax));
    	totalBill.append("\nTotal: $").append(String.format("%.2f", finalCost));
    	billTextArea.setText(totalBill.toString());
    }
    
    // Cancel Function Clears the buttons and the bill
    private void cancelFunction(CheckBox eggSandwich, 
    		CheckBox chickenSandwich, 
    		CheckBox bagel, 
    		CheckBox potatoSalad, 
    		ToggleGroup drinkGroup, 
    		RadioButton coffee, 
    		RadioButton greenTea, 
    		RadioButton blackTea, 
    		RadioButton orangeJuice, 
    		TextArea billTextArea, 
    		int num) {
    	// Clear Everything
        eggSandwich.setSelected(false);
        chickenSandwich.setSelected(false);
        bagel.setSelected(false);
        potatoSalad.setSelected(false);
        drinkGroup.selectToggle(null);
        
        // For confirm clear
        if (num == 0) {
        	billTextArea.clear();
        }
    }
    
    // Confirm function outputs the total bill then confirms the order and clears the bill box
    private void confirmFunction(CheckBox eggSandwich, 
    		CheckBox chickenSandwich, CheckBox bagel, 
    		CheckBox potatoSalad, 
    		ToggleGroup drinkGroup, 
    		RadioButton coffee, 
    		RadioButton greenTea, 
    		RadioButton blackTea, 
    		RadioButton orangeJuice, 
    		TextArea billTextArea) {
    	StringBuilder totalBill = new StringBuilder("Your Order:\n");
    	double totalBillCost = 0.0;
    	
    	// Bill Addition
    	if (eggSandwich.isSelected()) {
    		totalBill.append("Egg Sandwhich\n");
    		totalBillCost += 7.99;
    	}
    	if (chickenSandwich.isSelected()) {
    		totalBill.append("Chicken Sandwhich\n");
    		totalBillCost += 9.99;
    	}
    	if (bagel.isSelected()) {
    		totalBill.append("Bagel\n");
    		totalBillCost += 2.50;
    	}
    	if (potatoSalad.isSelected()) {
    		totalBill.append("Potato Salad\n");
    		totalBillCost += 4.49;
    	}
    	
    	// Bill Addition
    	RadioButton selectedDrink = (RadioButton) drinkGroup.getSelectedToggle();
    	if(selectedDrink != null) {
    		if(coffee.isSelected()) {
    			totalBill.append("Coffee\n");
    			totalBillCost += 1.99;
    		}
    		if(greenTea.isSelected()) {
    			totalBill.append("Green Tea\n");
    			totalBillCost += 0.99;
    		}
    		if(blackTea.isSelected()) {
    			totalBill.append("Black Tea\n");
    			totalBillCost += 1.25;
    		}
    		if(orangeJuice.isSelected()) {
    			totalBill.append("Orange Juice\n\n");
    			totalBillCost += 2.25;
    		}
    	}
    	
    	// Tax
    	double tax = totalBillCost * 0.07;
    	double finalCost = totalBillCost + tax;
    	
    	// Adding Final Touches to Text Area
    	totalBill.append("\nSubtotal: $").append(String.format("%.2f", totalBillCost));
    	totalBill.append("\nTax (7%): $").append(String.format("%.2f", tax));
    	totalBill.append("\nTotal: $").append(String.format("%.2f", finalCost));
    	totalBill.append("\nOrder Confirmed, Thank you!");
    	billTextArea.setText(totalBill.toString());
        
        // Clear
        cancelFunction(eggSandwich, chickenSandwich, bagel, potatoSalad, drinkGroup,  coffee,  greenTea,  
        		blackTea,  orangeJuice,  billTextArea, 1);
    	
    }
    
    // Main
    public static void main(String[] args) {
        launch(args);
    }
}