import java.io.Console;
/** @author David Josephs
* CS242
@since 2015-10-28
*/
public class SimpleEncryptor extends Encryptor{
	
		public String encrypted = new String();	
	/** 
		<p>
		 * 4 is added to each character which is then moded by 128 to keep it within the bounds.
		 * This is then XOR with the encryption key.
		 Finnaly the String encrypted is set equal to the encrypted text.
		 </p>
		 *@param Input Takes any string as input.
		 @param bkey This is used as the encryption key.
		 */
	public void textEncrypt(String Input, byte bkey){
		
		String Output = new String();
		for(int i = 0, l = Input.length(); i < l; i++){
			Output += (char)((((int)Input.charAt(i) + 4) % 128) ^ bkey); // mod 128 is to handle the casses where adding 4 goes over 127
			
		}
		encrypted = Output;
	}
	/** This function does everything that textEncrypt does to the string Input but in opposte order.
		@param Input Takes any string as input.
		@param key this is an int that is the casted to a byte value used as the decryption key*/
	public void textDecrypt(String Input, int key){
		byte bkey = (byte)key;
		String Output = new String();
		
		for(int i = 0, l = Input.length(); i < l; i++){
			Output += (char)((((int)Input.charAt(i) ^ bkey) - 4) % 128);
		}
		encrypted = Output;
	}
	/**  <p>
		 Input takes the Input adds the integer value of each char in the string together and then mod 128 the result.
		 This value is then used as an encryption key.
		 </p>
		 @param Input Takes any string as input.
		 @return A byte is returned so it can be used as the second argument for the textEncrypt function
		 */
	public static byte encryptKey(String Input){
		int ikey = 0;
		
		for(int i = 0, l = Input.length(); i < l; i++){
			ikey += (int)Input.charAt(i);
		}
		ikey = ikey % 128;
		
		return (byte)ikey;
	}
}