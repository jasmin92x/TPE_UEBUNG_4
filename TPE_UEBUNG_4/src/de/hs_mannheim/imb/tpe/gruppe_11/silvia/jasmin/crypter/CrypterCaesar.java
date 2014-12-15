package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.CrypterException;
import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;

/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
class CrypterCaesar extends CrypterBase implements Crypter {

	final char keyChar;
	
	CrypterCaesar(String key) throws IllegalKeyException {
		super(key);
		keyChar = key.charAt(0);
	}

	/**
	 * Zeichenweise codieren
	 * 
	 * @param key
	 * @param c
	 * @return
	 * @throws CrypterException 
	 */
	@Override
	char encrypt(char c) throws CrypterException {
		byte shift = (byte)(keyChar - 'A');
		if (c < 'A' || c > 'Z') {
			/*
			 * Wir könnten einen "unmöglichen" Wert wie 0 zurückgeben. Das wäre aber
			 * schlechter Programmerstil. Deshalb verwenden wir besser die strukturierte
			 * Ausnahmebehandlung und lösen eine CrypterException aus.
			 */
			throw new IllegalArgumentException("illegal character found");
		}
		return (char)('A' + (((c - 'A') + shift) % CHARSET_LENGTH));
	}

	/**
	 * Zeichenweise decodieren
	 * @param key
	 * @param c
	 * @return
	 * @throws CrypterException 
	 */
	@Override
	char decrypt(char c) throws CrypterException {
		byte shift = (byte)(keyChar - 'A');
		if (c < 'A' || c >'Z') {
			throw new CrypterException("illegal character found");
		}
		return (char)('A' + (((c - 'A') + CHARSET_LENGTH - shift) % CHARSET_LENGTH));
	}

	@Override
	void validateKey(String key) throws IllegalKeyException {
		if (key == null || key.length() != 1) {
			throw new IllegalKeyException("CAESAR-key darf nicht null oder leer sein");
		}
		char c = key.charAt(0);
		if (c < 'A' || c > 'Z') {
			throw new IllegalKeyException("Illegal key value '" + key + "'");
		}
	}
}
