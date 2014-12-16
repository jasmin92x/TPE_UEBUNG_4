package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;

	class CrypterXOR extends CrypterBase {

	public CrypterXOR(String key) throws IllegalKeyException {
		super(key);
	}

	@Override
	void validateKey(String key) throws IllegalKeyException {
		if (key == null || key.length() == 0) {
			throw new IllegalKeyException("XOR key must not be null or empty");
		}
		for (int i = 0; i < key.length(); ++i) {
			char keyChar = key.toUpperCase().charAt(i);
			if (keyChar < 'A' || keyChar > 'Z') {
				throw new IllegalKeyException("XOR key characters must be between A and Z inclusively");
			}
		}
	}
	
	@Override
	public String encrypt(String message) {
		
		if (message== null) {
			return null;
		}
		
		message = message.toUpperCase();
		StringBuffer sb = new StringBuffer();
		int gapCount = 0; //
		int keyLength = key.length();
		for (int i = 0; i < message.length(); ++i) {
			char c = message.charAt(i);
			if (c < '@' || c >'_') {
				gapCount++; // übersprungen Zeichen hochzählen
				continue; // Rest der Schleifenausführung überspringen
			}
			// eigentliche zeichenweise Verschlüsselung
			sb.append((char)(((c - '@') ^ (key.charAt((i-gapCount) % keyLength) - '@')) + '@'));
		}
		return sb.toString();
		
	}
	
	@Override
	public String decrypt(String cypherText) {
		return encrypt(cypherText);
	}

	@Override
	char encrypt(char c) {
		return 0;
	}

	@Override
	char decrypt(char c) {
		return 0;
	}


}
