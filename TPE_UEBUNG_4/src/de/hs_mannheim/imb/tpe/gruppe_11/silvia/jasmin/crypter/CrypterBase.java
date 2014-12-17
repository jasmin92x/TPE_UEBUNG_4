package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import java.util.Arrays;
import java.util.List;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.CrypterException;
import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;

/**
 * Basisklasse implementiert Funktionalität, die von
 * vielen abgeleiteten Klassen genutzt werden kann. Insbesondere
 * ist es in einer abgeleiteten Klasseoft nur notwendig,
 * (wenn überhaupt) den Schlüssel zu validieren und die 
 * zeichenweise Ver- und Entschlüsselung zu implementieren.
 * 
 * Achtung: Die String-weise Ver- und Entschlüsselung beinhaltet
 * die Konvertierung in Großbuchstaben. Bei "ß" existiert eine
 * Anomalie, da dieses Zeichen beim Übergang zur Großschreibung durch
 * "SS" ersetzt wird. Wir filtern es deshalb vorher aus. Nach weiteren
 * derartigen Anomalien wurde nicht gesucht, dehalb funktioniert die 
 * Verschlüsselung auch nur dann zuverlässig, wenn wir uns auf die ersten 256 Unicode-Zeichen
 * beschränken.
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
public abstract class CrypterBase implements Crypter {

	protected String key;

	public CrypterBase(String key) throws IllegalKeyException {
		validateKey(key);
		if (key != null) {
			this.key = key.toUpperCase();
		}
	}

	abstract void validateKey(String key) throws IllegalKeyException;

	abstract char encrypt(char c);

	abstract char decrypt(char c);

	void checkCharacterRange(char c) {
		if (c < 'A' || c >'Z') {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String encrypt(String message) throws CrypterException {
		
		if (message == null) {
			return null;
		}
		
		message = message.replace("ß",  "").toUpperCase();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < message.length(); ++i) {
			try {
				sb.append(encrypt(message.charAt(i)));
			} catch (IllegalArgumentException e) { }
		}
		return sb.toString();
		
	}

	@Override
	public List<String> encrypt(List<String> messages) throws CrypterException {
		
		List<String> retVal = Arrays.asList(new String[messages.size()]);
		int i = 0;
		for (String message : messages) {
			retVal.set(i++, encrypt(message));
		}
		return retVal;
		
	}

	@Override
	public String decrypt(String cypherText) throws CrypterException {
		
		if (cypherText == null) {
			return null;
		}
		
		cypherText = cypherText.replace("ß",  "").toUpperCase();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cypherText.length(); ++i) {
			try {
				sb.append(decrypt(cypherText.charAt(i)));
			} catch (IllegalArgumentException e) { }
		}
		return sb.toString();
		
	}

	@Override
	public List<String> decrypt(List<String> cypherTexts) throws CrypterException {
		
		List<String> retVal = Arrays.asList(new String[cypherTexts.size()]);
		int i = 0;
		for (String cypherText : cypherTexts) {
			retVal.set(i++, decrypt(cypherText));
		}
		return retVal;
		
	}

}