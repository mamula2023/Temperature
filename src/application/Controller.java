package application;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	ObservableList<String> units = FXCollections.observableArrayList("Celsius", "Fahrenheit");
	@FXML
	private ChoiceBox FirstChoiceBox;
	@FXML
	private ChoiceBox SecondChoiceBox;
	
	@FXML
	private TextField FirstTF;
	@FXML
	private TextField SecondTF;
	
	@FXML
	private Button convertButton;
	private Button clearButton;
	
	@FXML
	private void initialize() {
		FirstChoiceBox.setItems(units);
		SecondChoiceBox.setItems(units);
	}
	
	@FXML
	void clear(ActionEvent e)  throws URISyntaxException, IOException{
		FirstTF.setText("");
		SecondTF.setText(""); 
		
	
	}
	@FXML
	void convert(ActionEvent e)  throws URISyntaxException, IOException{
		String firstUnit = (String) FirstChoiceBox.getValue();
		String secondUnit = (String) SecondChoiceBox.getValue();
		
		String firstNum = FirstTF.getText();
		
		String result = Main.handle(firstUnit, secondUnit, firstNum);
		
		SecondTF.setText(result);
	}
	
	
	
	
}
