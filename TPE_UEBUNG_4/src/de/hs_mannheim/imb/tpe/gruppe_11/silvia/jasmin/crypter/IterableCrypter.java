package de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter;

import java.util.Iterator;

import de.hs_mannheim.imb.tpe.gruppe_11.silvia.jasmin.crypter.exceptions.CrypterException;

public class IterableCrypter implements Iterable<String> {
	
	Iterator<String> iterator;
	Crypter crypter;
	
	public IterableCrypter(Iterable<String> iterable, Crypter crypter) {
		iterator = iterable.iterator();
		this.crypter = crypter;
	}

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {

			@Override
			public boolean hasNext() {
				// benutze den im Constructor erhaltenen Iterable
				return iterator.hasNext();
			}

			@Override
			public String next() {
				try {
					return crypter.encrypt(iterator.next());
				} catch (CrypterException e) {
					return null;
				}
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

}
