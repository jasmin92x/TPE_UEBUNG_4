package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;
/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
public class CrypterFactory {

	public enum CrypterType{
		CAESAR,
		SUBSTITUTION,
		REVERSE,
		XOR,
		NULL
	}
	
	public static Crypter createCrypter(String key, CrypterType crypterType) throws IllegalKeyException {
		
		Crypter retVal = null;
		switch(crypterType) {
		case CAESAR:
			retVal = new CrypterCaesar(key);
			break;
		case SUBSTITUTION:
			retVal = new CrypterSubstitution(key);
			break;
		case REVERSE:
			retVal = new CrypterReverse(key);
			break;
		case XOR:
			retVal = new CrypterXOR(key);
			break;
		case NULL:
			retVal = new CrypterNull(key);
			break;
		default:
			throw new IllegalKeyException("Class '"+ crypterType + "' not implemented");
		
		}
		return retVal;
		
	}
	
}
