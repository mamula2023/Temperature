package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Parent root;
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
	
	public static String handle(String firstUnit, String secondUnit, String num) {
		if(firstUnit==null || secondUnit==null )
			System.out.println("Please choose both units to perform conversion");
		else if(num.trim().equals(""))
			System.out.println("Plase enter the degree");
		else if(firstUnit.equals("Celsius") && secondUnit.equals("Fahrenheit")) {
			String result = fromCtoF(num);
			return result;
		}else if(firstUnit.equals("Fahrenheit")&&secondUnit.equals("Celsius")) {
			String result = fromFtoC(num);
			return result;
		}else {
			return num;
		}
		return "";
	}
	private static String fromFtoC(String num) {
		int deg = stringToNum(num);
		double result = (deg-32)*5/9;
		String resultString = doubleToString(result);
		return resultString;
	}
	public static String fromCtoF(String num) {
		int deg = stringToNum(num);
		double result = deg*1.8+32;
		String resultString = doubleToString(result);
		return resultString;
	}
	
	private static String doubleToString(double num) {
		String result = Double.toString(num);
		return result;
	}
	
	
	private static int stringToNum(String num){
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

