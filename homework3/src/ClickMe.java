import javafx.stage.Stage;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.io.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;

public class ClickMe extends Application{
	
	//public Stage mainStage;
	File sendFile;
	public static void main(String [] args){launch( args );}
	
	@Override
	public void start (Stage primaryStage){
		
		Label label = new Label("Welcome to the Encrypted Text and File Messenger");
		TextArea textInput = new TextArea();
		Button grabSend = new Button("Send");
		Button grabFile = new Button("Send File");
		GridPane grid = new GridPane();
		grid.add(label, 0, 0);
		grid.add(textInput, 0, 1);
		grid.add(grabSend, 0, 2);
		grid.add(grabFile, 1, 2);
		Scene scene = new Scene(grid, 500, 400);
		
		primaryStage.setTitle("Encrypted Messenger");
		primaryStage.setScene(scene);
		primaryStage.show();
		grabSend.setOnAction(event -> {
			String text = new String(textInput.getText());
			System.out.println(text);
		});
		grabFile.setOnAction(event -> {
			showFileDialog();
			System.out.println(sendFile);
			
		});
			
	}

	public void showFileDialog(){
		Stage mainStage = new Stage();
		
		FileChooser fileChooser = new FileChooser();
 		fileChooser.setTitle("Get Text File");
 		fileChooser.getExtensionFilters().addAll(
        		 new ExtensionFilter("Text Files", "*.txt"),
				 new ExtensionFilter("All Files", "*.*"));
		sendFile = fileChooser.showOpenDialog(mainStage);
	}


}
