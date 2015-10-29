/** @author David Josephs
 CS242
 @since 2015-10-28
*/
public abstract class Encryptor {
	
	public String encrypted = new String(); // a string to store the enctrypted or decrypted message in 
	/** Will encrypt text using the given key
		@param Input The content to be encrypted
		@param key What is used to entrypt the input.*/
	public void textEncrypt(String Input, byte key){} 
	/** Will decrypt text using the given key
		@param Input The content to be decrypted
		@param key What is used to decrypt the input.*/
	public void textDecrypt(String Input, int key){}

}