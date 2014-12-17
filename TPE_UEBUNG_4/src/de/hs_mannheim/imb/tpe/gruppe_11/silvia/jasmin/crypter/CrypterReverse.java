package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.CrypterException;
import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;

/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
class CrypterReverse extends CrypterBase {
	
	public CrypterReverse(String key) throws IllegalKeyException {
		super(key);
	}

	/* (non-Javadoc)
	 * @see de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.BaseCrypter#validateKey(java.lang.String)
	 */
	@Override
	void validateKey(String key) throws IllegalKeyException { }

	/* (non-Javadoc)
	 * @see de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.BaseCrypter#encode(char)
	 */
	@Override
	char encrypt(char c) {
		checkCharacterRange(c);
		return c;
	}

	/* (non-Javadoc)
	 * @see de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.BaseCrypter#decrypt(char)
	 */
	@Override
	char decrypt(char c) {
		return 0;
	}

	/**
	 * Wir verwenden die Basis-Implementierung encrypt(String), um eine um
	 * ungültige Zeichen bereinigte Kopie von message zu erhalten, und kehren
	 * sie anschließend um.
	 * @param message
	 * @return
	 * @throws CrypterException 
	 */

	@Override
	public String encrypt(String message) throws CrypterException {
		if (message == null) {
			return null;
		}
		return new StringBuffer(super.encrypt(message)).reverse().toString();
	}
	
	/**
	 * Wir verwenden encrypt, weil decrypt dasselbe leisten muss wie encrypt 
	 * @param cypherText
	 * @return
	 * @throws CrypterException 
	 */
	@Override
	public String decrypt(String cypherText) throws CrypterException {
		return encrypt(cypherText);
	}


}
