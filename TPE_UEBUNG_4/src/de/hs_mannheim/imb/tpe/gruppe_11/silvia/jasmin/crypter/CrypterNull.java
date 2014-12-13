package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.CrypterException;
import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.IllegalKeyException;
/**
 * 
 * @author Jasmin Cano, Silvia Yildiz
 *
 */
public class CrypterNull extends BaseCrypter {

	CrypterNull(String key) throws IllegalKeyException {
		super(key);
	}

	@Override
	void validateKey(String key) throws IllegalKeyException { }

	@Override
	char encode(char c) throws CrypterException {
		if (c < 'A' || c > 'Z') {
			/*
			 * Wir k�nnten einen "unm�glichen" Wert wie 0 zur�ckgeben. Das w�re aber
			 * schlechter Programmerstil. Deshalb verwenden wir besser die strukturierte
			 * Ausnahmebehandlung und l�sen eine CrypterException aus.
			 */
			throw new CrypterException("illegal character found");
		}
		return c;
	}

	@Override
	char decrypt(char c) throws CrypterException {
		if (c < 'A' || c > 'Z') {
			/*
			 * Wir k�nnten einen "unm�glichen" Wert wie 0 zur�ckgeben. Das w�re aber
			 * schlechter Programmerstil. Deshalb verwenden wir besser die strukturierte
			 * Ausnahmebehandlung und l�sen eine CrypterException aus.
			 */
			throw new CrypterException("illegal character found");
		}
		return c;
	}

}
