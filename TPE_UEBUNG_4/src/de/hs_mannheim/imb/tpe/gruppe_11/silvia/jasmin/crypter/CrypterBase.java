package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import java.util.Arrays;
import java.util.List;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.CrypterException;
import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;

/**
 * Basisklasse implementiert Funktionalit�t, die von
 * vielen abgeleiteten Klassen genutzt werden kann. Insbesondere
 * ist es in einer abgeleiteten Klasseoft nur notwendig,
 * (wenn �berhaupt) den Schl�ssel zu validieren und die 
 * zeichenweise Ver- und Entschl�sselung zu implementieren.
 * 
 * Achtung: Die String-weise Ver- und Entschl�sselung beinhaltet
 * die Konvertierung in Gro�buchstaben. Bei "�" existiert eine
 * Anomalie, da dieses Zeichen beim �bergang zur Gro�schreibung durch
 * "SS" ersetzt wird. Wir filtern es deshalb vorher aus. Nach weiteren
 * derartigen Anomalien wurde nicht gesucht, dehalb funktioniert die 
 * Verschl�sselung auch nur dann zuverl�ssig, wenn wir uns auf die ersten 256 Unicode-Zeichen
 * beschr�nken.
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
		
		message = message.replace("�",  "").toUpperCase();
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
		
		cypherText = cypherText.replace("�",  "").toUpperCase();
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