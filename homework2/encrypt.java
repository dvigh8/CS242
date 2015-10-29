import java.io.File;
import java.io.Console;
/** @author David Josephs
* CS242 
@since 2015-10-28
*/
public class encrypt{
		/**<p>
		This takes 4 commandline arguments
		encrypt -e string -- This encrypts the string.
		encrypt -d string key -- This decrypts the string using key.
		encrypt -fe file -o fileout -- This encrypts the contents of file to fileout.
		encrypt -fd file -o fileout key -- This decrypts the contents file to fileout using key.
		Else the program will launch an iteractive display.
		<p>
		@param args This takes the command line arguments.
		*/
		public static void main(String[] args){
		Integer argssize = new Integer(args.length);
		SimpleEncryptor textE = new SimpleEncryptor();
		FileEncryptor fileE = new FileEncryptor();
		
		if(argssize.equals(0)){
			Console console = System.console();
			String selection = new String();
			
			System.out.println("Welcome to this encrypton program.");
			System.out.printf("Type e or d for text encryption and decryption,\nand fe or fd for file encrypton and decryption\n");
			System.out.printf("Type Choice: ");
			selection = console.readLine();
			while(!selection.equals("e") && !selection.equals("d") && !selection.equals("fd") && !selection.equals("fe")){
				System.out.printf("Input Invalid try again\nType Choice: ");
				selection = console.readLine();
			}
			if(selection.equals("d")){
				
				System.out.printf("Decryption key: ");
				int key = Integer.parseInt(console.readLine());
				System.out.printf("Enter what you want to decrypt: ");
				selection = console.readLine();
				textE.textDecrypt(selection, key);
				System.out.println(textE.encrypted);
			}
			else if(selection.equals("e")){
				System.out.printf("Enter what you want to encrypt: ");
				selection = console.readLine();
				textE.textEncrypt(selection,SimpleEncryptor.encryptKey(selection));
				textE.encrypted += ":" + SimpleEncryptor.encryptKey(selection);
				System.out.println(textE.encrypted);
			}
			else if(selection.equals("fd")){
				
				System.out.printf("Decryption key: ");
				int key = Integer.parseInt(console.readLine());
				System.out.printf("Enter the file you want to decrypt: ");
				selection = console.readLine();
				int count = 0;
				while(!(new File(selection).exists())){
					System.out.printf("File does not exist\nEnter New File: ");
					selection = console.readLine();
					if(count++ == 1){
						count = -1;
						break;
					}	
				}
				if(!(count == -1)){
					File inputF = new File(selection);
					System.out.printf("Enter output file: ");
					selection = console.readLine();
					File outputF = new File(selection);
					fileE.fileDecrypt(inputF,outputF,(byte)key);
				}
				else{
					System.out.println("Too many invalid inputs program terminated");
				}			
			}
			else{
				System.out.printf("Enter the file you want to encrypt: ");
				selection = console.readLine();
				int count = 0;
				while(!(new File(selection).exists())){
					System.out.printf("File does not exist\nEnter New File: ");
					selection = console.readLine();
					if(count++ == 1){
						count = -1;
						break;
					}	
				}
				
				if(!(count == -1)){
					File inputF = new File(selection);
					System.out.printf("Enter output file: ");
					selection = console.readLine();
					File outputF = new File(selection);
					System.out.println("The decryption key is " + FileEncryptor.fileEncryptKey(inputF)); // this is put before the encrytption incase the input file is edited during the encryption
					fileE.fileEncrypt(inputF,outputF,FileEncryptor.fileEncryptKey(inputF));
				}
				else{
					System.out.println("Too many invalid inputs program terminated");
				}
			}
		}
		else if (args[0].equals("-e") && argssize.equals(2)){	// commandline argument handler
			textE.textEncrypt(args[1], SimpleEncryptor.encryptKey(args[1]));
			System.out.println(textE.encrypted + ":" + SimpleEncryptor.encryptKey(args[1]));
		}
		else if (args[0].equals("-d") && argssize.equals(3)){ // commandline argument handler
			textE.textDecrypt(args[1],Integer.parseInt(args[2]));
			System.out.println(textE.encrypted);
		}
		else if (args[0].equals("-fe") && args[2].equals("-o") && argssize.equals(4)){ // commandline argument handler
			if(!(new File(args[1]).exists())){System.out.println("File does not exist");}
			else{
			System.out.println(FileEncryptor.fileEncryptKey(new File(args[1])));
			fileE.fileEncrypt(new File(args[1]), new File(args[3]),FileEncryptor.fileEncryptKey(new File(args[1])));
			}
		}
		else if (args[0].equals("-fd") && args[2].equals("-o") && argssize.equals(5)){ // commandline argument handler
			if(!(new File(args[1]).exists())){System.out.println("File does not exist");}
			else{fileE.fileDecrypt(new File(args[1]), new File(args[3]), (byte)Integer.parseInt(args[4]));}
		}
		else { // if the three options do not work the program will show and terminate
			System.out.println("Program Faild to run, arguments invalid");
		}
	}
}
