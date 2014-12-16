package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import java.util.Arrays;
import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;

public class CrypterSubstitution extends CrypterBase {

	char keyChars[];
	char reverseKeyChars[] = new char[26];

	public CrypterSubstitution(String key) throws IllegalKeyException {
		super(key);
		keyChars = this.key.toCharArray();
		for (int i = 0; i < 26; ++i) {
			// keyChars[i] - 65 ist die Position des Zeichens im ursprünglichen Alphabet
			reverseKeyChars[keyChars[i] - 65] = (char)(65 + i); // 65 + i ist der Zeichencode des Zeichens,
			// in das das i-te abgebildet wird bei der Entschlüsselung 
		}
	}
	
	@Override
	void validateKey(String key) throws IllegalKeyException {
		if (key == null || key.length() == 0) {
			throw new IllegalKeyException("SUBSTITUTION key must not be null or empty");
		}
		if (key.length() != 26) {
			throw new IllegalKeyException("SUBSTITUTION key must consist of exactly 26 characters");
		}
		for (int i = 0; i < key.length(); ++i) {
		char keyChar = key.toUpperCase().charAt(i);
			if (keyChar < 'A' || keyChar > 'Z') {
				throw new IllegalKeyException("SUBSTITUTION key characters must be between A and Z inclusively");
			}
		}
		boolean checked[] = new boolean[26]; // Überprüfen, dass alle Zeichen Zeichen vorkommen
		Arrays.fill(checked, false);
		for (int i = 0; i < 26; ++i) { // wenn 26 verschiedene Zeichen vorhanden sind, dann bekommen
			// wir sechsundzwanzig verschiedene Abstände zu A, von 0 - 25
			checked[key.charAt(i) - 'A'] = true;
		}
		for (int i = 0;i < 26; ++i) {
			if (!checked[i]) { // ein Zeichen kam wohl nicht vor!
				throw new IllegalKeyException("SUBSTITUTION key must consist of all characters between A and Z inclusively");
			}
		}
	}

	@Override
	char encrypt(char c) {
		super.checkCharacterRange(c);
		return keyChars[c - 'A']; // wir holen uns das Zeichen an der entsprechenden Stelle aus dem Schlüssel
	}

	@Override
	char decrypt(char c) {
		super.checkCharacterRange(c);
		return reverseKeyChars[c - 'A'];
	}

	


}
