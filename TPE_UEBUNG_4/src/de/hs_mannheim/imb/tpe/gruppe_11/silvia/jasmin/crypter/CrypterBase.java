package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import java.util.ArrayList;
import java.util.List;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.CrypterException;
import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;
/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
public abstract class CrypterBase implements Crypter {

	static final byte CHARSET_LENGTH = 26;
	protected final String key;

	/**
	 * Die Basisklasse verfügt nicht über genügend Information, um den übergeben
	 * key auf seine Zulässigkeit zu prüfen. Jede abgeleitete Klasse müsste das tun,
	 * bevor sie den key an super() übergibt. Da aber super() die erste Anweisung
	 * sein muss, wenn man sie verwendet, funktioniert das so nicht. Die Lösung: 
	 * Wir delegieren die Überprüfung an eine eigenständige Methode. Die Basisklasse 
	 * definiert nur die Methodensignatur, d.h. eine abstrakte Methode, und verwendet sie.
	 * Die abgeleiteten Klassen müssen diese Methode dann implementieren.
	 * @param key
	 * @throws IllegalKeyException
	 */
	CrypterBase(String key) throws IllegalKeyException {
		validateKey(key);
		this.key = key;
	}
	
	/**
	 * Gültigkeitsprüfung
	 * abstrakt, weil die Basisklasse die Bedürfnisse der abgeleiteten
	 * Klassen nicht kennen kann
	 * @param key
	 * @throws IllegalKeyException
	 */
	abstract void validateKey(String key) throws IllegalKeyException;

	/**
	 * Zeichenweise codieren
	 * 
	 * @param key
	 * @param c
	 * @return
	 * @throws CrypterException 
	 */
	abstract char encrypt(char c) throws CrypterException;

	/**
	 * Zeichenweise decodieren
	 * @param key
	 * @param c
	 * @return
	 * @throws CrypterException 
	 */
	abstract char decrypt(char c) throws CrypterException;

	/**
	 * Ganzen String auf einmal codieren unter Verwendung von zeichenweiser Codierung
	 * @param key
	 * @param text
	 * @return
	 * @throws CrypterException 
	 */
	@Override
	public String encrypt(String text) throws CrypterException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < text.length(); ++i) {
			try {
				sb.append(encrypt(text.toUpperCase().charAt(i))); //anhängen=append
			}
			catch(IllegalArgumentException e) {
				/*
				 * In Wirklichkeit würde man vielleicht etwas in eine Protokolldatei schreiben!
				 * Da wir unzulässige Zeichen ignorieren wollen, brauchen wir hier sonst überhaupt nichts machen.
				 * Wichtig ist aber, dass wir die Ausnahme abfangen, da sonst das Programm abgebrochen wird.
				 */
			}
		}
		return sb.toString();
	}

	/**
	 * Ganzen String auf einmal decodieren unter Verwendung von zeichenweiser Decodierung
	 * @param key
	 * @param text
	 * @return
	 * @throws CrypterException 
	 */
	@Override
	public String decrypt(String text) throws CrypterException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < text.length(); ++i) {
			try {
				sb.append(decrypt(text.toUpperCase().charAt(i)));
			}
			catch(IllegalArgumentException e) {
				/*
				 * In Wirklichkeit würde man vielleicht etwas in eine Protokolldatei schreiben!
				 * Da wir unzulässige Zeichen ignorieren wollen, brauchen wir hier sonst überhaupt nichts machen.
				 * Wichtig ist aber, dass wir die Ausnahme abfangen, da sonst das Programm abgebrochen wird.
				 */
			}
		}
		return sb.toString();
	}

	/**
	 * Der tatsächliche Datentyp von messages ist unbekannt. Deshalb geben wir einfach
	 * eine ArrayList zurück. Um den exakten Typ der übergebenen Liste zu ermitteln und daraus
	 * einen entsprechenden Rückgabewert zu erstellen, wäre wesentlich mehr Aufwand (Reflection!)
	 * nötig, weshalb wir darauf verzichten - nicht verlangt!
	 */
	@Override
	public List<String> encrypt(List<String> messages) throws CrypterException {
		List<String> retVal = new ArrayList<>();
		for (String message : messages) { // für jede Message in der List messages
			retVal.add(encrypt(message));
		}
		return retVal;
	}

	@Override
	public List<String> decrypt(List<String> cypherTexte) throws CrypterException {
		List<String> retVal = new ArrayList<>();
		for (String cypherText : cypherTexte) { // für jede verschlüsselte Message in der List cypherTexte
			retVal.add(decrypt(cypherText));
		}
		return retVal;
	}
	
	void checkCharacterRange(char c) {
		if (c < 'A' || c >'Z') {
			throw new IllegalArgumentException();
		}
	}

}