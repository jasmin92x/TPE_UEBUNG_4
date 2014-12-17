package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;
import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;

/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
class CrypterCaesar extends CrypterBase implements Crypter {

	byte shift;
	
	public CrypterCaesar(String key) throws IllegalKeyException {
		super(key);
		shift = (byte)(this.key.charAt(0) - 'A' + 1);
	}
	
	@Override
	void validateKey(String key) throws IllegalKeyException {
		if (key == null || key.length() == 0) {
			throw new IllegalKeyException("CAESAR key must not be null or empty");
		}
		if (key.length() > 1) {
			throw new IllegalKeyException("CAESAR key must consist of exactly one character");
		}
		char keyChar = key.toUpperCase().charAt(0);
		if (keyChar < 'A' || keyChar > 'Z') {
			throw new IllegalKeyException("CAESAR key character must be between A and Z inclusively");
		}
	}
	
	@Override
	char encrypt(char c) {
		checkCharacterRange(c);
		return (char)((c - 'A' + shift) % 26 + 'A');
	}

	@Override
	char decrypt(char c) {
		checkCharacterRange(c);
		return (char)(((26 + c - 'A') - shift) % 26 + 'A');
	}

}
