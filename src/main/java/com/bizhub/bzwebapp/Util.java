package com.bizhub.bzwebapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;

public class Util {

	private static final String HEX_ALPHABET[] = { "0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


	/**
	 * Check if a string is empty (blank)
	 * 
	 * @param s
	 *            the string to check
	 * @return true if s is null or its trimmed length is 0; false otherwise.
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0 || s.trim().length() == 0;
	}

	/**
	 * Join elements of a collection into a string.
	 * 
	 * @param collection
	 *            the collection to join
	 * @param delimiter
	 *            the delimiter used between the elements of the collection
	 * @return a joined (merged) string of elements from the collection with the
	 *         delimiter between each one.
	 */
	public static String join(Collection<?> collection, String delimiter) {
		StringBuilder out = new StringBuilder();
		for (Iterator<?> i = collection.iterator(); i.hasNext();) {
			out.append(i.next());
			if (i.hasNext()) {
				out.append(delimiter);
			}
		}
		return out.toString();
	}
}

