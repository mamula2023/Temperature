package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	public static AnchorPane root;
	public static Label label;
	@Override
	public void start(Stage stage) {
		try {
			root = FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	} 
	
	/*
	 * this method adds label to window, if needed
	 */
	public static void addMessage(String msg) {	
		label = new Label(msg);
		label.setLayoutX(50);
		label.setLayoutY(50);
		label.setTextFill(Color.RED);
		
		root.getChildren().add(label);
		
	}
	
	/*
	 * this is main method to handle convert button click;
	 * it checks if data is valid and then calls methods, which calculate result;
	 */
	public static String handle(String firstUnit, String secondUnit, String num) {
		String result = "";
		if(label!=null)
			label.setText("");
		if(firstUnit==null || secondUnit==null ) 
			addMessage("Please choose both units to perform conversion");
		else if(num.trim().equals("")) 
			addMessage("Please enter the degree");
		else if(firstUnit.equals("Celsius") && secondUnit.equals("Fahrenheit")) 
			result = fromCtoF(num);
		else if(firstUnit.equals("Fahrenheit")&&secondUnit.equals("Celsius")) 
			result = fromFtoC(num);
		else 
			result = num;
		
		return result;
	}
	
	
	private static String fromFtoC(String num) {
		double deg = stringToDouble(num);
		double result = (deg-32)*5/9;
		String resultString = doubleToString(result);
		return resultString;
	}
	
	
	public static String fromCtoF(String num) {
		double deg = stringToDouble(num);
		double result = deg*1.8+32;
		String resultString = doubleToString(result);
		return resultString;
	}
	
	private static String doubleToString(double num) {
		String result = Double.toString(num);
		int index = -1;
		for(int i = 0; i<result.length(); i++) {
			if(result.charAt(i)=='.') {
				index = i;
			}
		}
		
		if(index!=-1 && result.length()>index+2) {
			result = result.substring(0, index+3);
		}
		
		
		
		return result;
	}
	
	
	
	
	
	private static double stringToDouble(String num) {
		double result = 0;
		boolean separator = false;
		int index = -1;
		for(int i = 0; i<num.length(); i++) {
			char c = num.charAt(i);
			
			if((c>='0'&&c<='9')||(c=='.'||c==',')) {
				if(!separator&&(c=='.'||c==',')) {
					if(i==num.length()-1)
						num+='0';
					separator = true;
					index = i;
				}else if(separator &&((c=='.')||c==','))
					throw new NumberFormatException("There should be only one separator");
				
			}else 
				throw new NumberFormatException("There should be only numbers and one separator ('.' or ',') if necessary!");
		}
		
		if(separator) {
			int wholePart = stringToInt(num.substring(0, index));
			int decimalPart = stringToInt(num.substring(index+1));
			Double decimal = decimalPart/(Math.pow(10, num.substring(index+1).length()));
			result = wholePart+decimal;
			
			
		}else 
			result = stringToInt(num);
		

		return result;
	}
	

	
	private static int stringToInt(String num){
		boolean neg = num.charAt(0)=='-';
		int result = 0;
		for(int i = 0; i<num.length(); i++) {
			char dig = num.charAt(i);
			int digit = 0;
			if(dig>='0' && dig<='9') {
				digit = (int) (dig-'0');
				result = result*10+digit;
			}else if(dig=='-' && i==0) 
				continue;
			else {
				result = 0;
				break;
			}
		}
		
		if(neg) 
			result = -result;
		
		
		
		return result;
	}
}

