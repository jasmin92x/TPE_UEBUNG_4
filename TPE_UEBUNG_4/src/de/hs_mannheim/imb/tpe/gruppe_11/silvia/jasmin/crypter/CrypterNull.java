package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;
/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
class CrypterNull extends CrypterBase {

	public CrypterNull(String key) throws IllegalKeyException {
		super(key);
	}

	@Override
	void validateKey(String key) throws IllegalKeyException { }

	@Override
	char encrypt(char c) {
		checkCharacterRange(c);
		return c;
	}

	@Override
	char decrypt(char c) {
		checkCharacterRange(c);
		return c;
	}

}