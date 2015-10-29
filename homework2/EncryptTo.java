import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent; 
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.FileChooser.*;
import javafx.stage.FileChooser;
import java.io.*;
import javafx.geometry.*;
import java.util.*;
/** @author David Josephs
* CS242
@since 2015-10-28
*/
public class EncryptTo extends Application {
	
	FileEncryptor encrypt = new FileEncryptor();
	File openFile;
	File saveFile;
	byte decryptKey;
	byte encryptKey;
	
	public static void main( String [] args ) { launch( args ); }
	/** This will show a stage with the button Encrypt and Decrypt.*/
	@Override
	public void start( Stage primaryStage ) { 

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		
		Button ebtn = new Button("Encrypt");
		Button dbtn = new Button("Decrypt");
		grid.add(ebtn, 0, 0);
		grid.add(dbtn, 1, 0);
		
		Scene scene = new Scene(grid, 250, 120);
		primaryStage.setTitle("Encrypt To");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		ebtn.setOnAction( event -> { 
				showFileDialog();
				fileEncrypt();
		});
		dbtn.setOnAction( event -> { 
				showFileDialog();
				fileDecrypt();
		});
	}
	/** <p>
		This will show the FileChoser for a open file and save file. Each one will set the openFile or saveFile respectively. It has the option to show either text files or all files.
		</p>
		*/

	public void showFileDialog(){
		Stage mainStage = new Stage();
		
		FileChooser fileChooser = new FileChooser();
 		fileChooser.setTitle("Get Text File");
 		fileChooser.getExtensionFilters().addAll(
        		 new ExtensionFilter("Text Files", "*.txt"),
				 new ExtensionFilter("All Files", "*.*"));
		openFile = fileChooser.showOpenDialog(mainStage);

		FileChooser saveFileChooser = new FileChooser();
		saveFileChooser.setTitle("Save File");
		saveFileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		saveFile = saveFileChooser.showSaveDialog(mainStage);
	}
	/** This will show the key that was used to encrypt the file in a new stage.*/
	public void showEncryptonKey(){
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		
		Label dKey = new Label("Your key is " + new Integer(encryptKey).toString());		
		grid.add(dKey, 0, 0);
		
		Button btn = new Button("Okay");
		grid.add(btn, 1, 0);
		
		Scene dScene = new Scene(grid, 250, 120);
		Stage dStage = new Stage();
		
		dStage.setTitle("Remember this");
		dStage.setScene(dScene);
		dStage.show();
		
		btn.setOnAction(event -> { dStage.hide(); });
	}
	/** This will ask for the decryption key in a TextField. When the button is pressed it calls fileDecrypt from FileEncryptor.*/
	public void fileDecrypt(){

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		
		TextField keyField = new TextField();
		keyField.setPromptText("Dycryption Key");
		grid.add(keyField, 0, 0);
		
		Button btn = new Button("Decrypt");
		grid.add(btn, 1, 0);
		
		Scene keyScene = new Scene(grid, 330, 120);
		Stage keyStage = new Stage();
		
		keyStage.setTitle("Decrypton Key");
		keyStage.setScene(keyScene);
		keyStage.show();
		
		btn.setOnAction(event -> {
			decryptKey = (byte)(Integer.parseInt(keyField.getText()));
			keyStage.hide();
			encrypt.fileDecrypt(openFile, saveFile, decryptKey);
		});
	}
	/** This calls fileEncrypt from FileEncryptor. Then it calls showEncryptionKey. */
	public void fileEncrypt(){
		encryptKey = FileEncryptor.fileEncryptKey(openFile);
		encrypt.fileEncrypt(openFile, saveFile, FileEncryptor.fileEncryptKey(openFile));
		showEncryptonKey();
	}
}