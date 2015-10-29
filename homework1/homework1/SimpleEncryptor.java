import java.io.Console;
public class SimpleEncryptor extends Encryptor{
	
		private String encrypted = new String();
	
	public static void main(String[] args){
		Integer argssize = new Integer(args.length);
		SimpleEncryptor encryptor = new SimpleEncryptor();
		
		if(argssize.equals(0)){
			encryptor.program();
			System.out.println(encryptor.encrypted);
		}
		else if (args[0].equals("-e") && argssize.equals(2)){	// commandline argument handler
			encryptor.textEncrypt(args[1]);
			System.out.println(encryptor.encrypted);
		}
		else if (args[0].equals("-d") && argssize.equals(3)){ // commandline argument handler
			encryptor.textDecrypt(args[1],Integer.parseInt(args[2]));
			System.out.println(encryptor.encrypted);
		}
		else { // if the three options do not work the program will show
			System.out.println("Program Faild to run, arguments invalid");
		}
	}
	
	public void program(){
		/** Program gives the user a menu
		 * the user inputs either a -e or -d 
		 * it will then run the appropriate method
		 */
		Console console = System.console();
		String Selection = new String();
		String Output = new String();
				
		System.out.printf("Welcome to the Simple Encryptor.\nType e for encryption and d for decryption\nType Choice: ");
		Selection = console.readLine();
		while(!Selection.equals("d") && !Selection.equals("e")){
			System.out.printf("Input Invalid try again\nType Choice: ");
			Selection = console.readLine();
		}
		if(Selection.equals("d")){
			
			System.out.printf("Decryption key: ");
			int key = Integer.parseInt(console.readLine());
			System.out.printf("Enter what you want to decrypt: ");
			Selection = console.readLine();
			this.textDecrypt(Selection, key);
			
		}
		else {
			
			System.out.printf("Enter what you want to encrypt: ");
			Selection = console.readLine();
			this.textEncrypt(Selection);
		}
	}
	
	public void textEncrypt(String Input){
		/** textEncrypt takes a string as input 
		 * adds the integer value of each string and then mod 128 the result
		 * this value is then used as an encryption key
		 * 4 is added to each character which is then moded by 128 to keep it within the bounds 
		 * this is then XOR with the encryption key
		 * finnaly the String encrypted is set as the message
		 */
		byte bkey = 0;
		int ikey = 0;
		String Output = new String();
		
		for(int i = 0, l = Input.length(); i < l; i++){
			ikey += (int)Input.charAt(i);
			
		}
		ikey = ikey % 128;
		bkey = (byte)ikey;
		for(int i = 0, l = Input.length(); i < l; i++){
			Output += (char)((((int)Input.charAt(i) + 4) % 128) ^ bkey); // mod 128 is to handle the casses where adding 4 goes over 127
			
		}
		encrypted = Output + ":" + ikey;
	}
	
	public void textDecrypt(String Input, int key){
		/** textDecrypt reveres everything that textEncrypt does
		 * 
		 */
		byte bkey = (byte)key;
		String Output = new String();
		
		for(int i = 0, l = Input.length(); i < l; i++){
			Output += (char)((((int)Input.charAt(i) ^ bkey) - 4) % 128);
			
		}
		
		encrypted = Output;
	}

}