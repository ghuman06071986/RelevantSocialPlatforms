package com.parminder.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author parminder
 *
 */
public class PossibleUrls {
	/**
	 * possible urls set
	 */
	private static Set<String> validUrlsSet = new HashSet<>();

	/**
	 * add possible urls onstart
	 */
	static {
		validUrlsSet.add("facebook.com");

		validUrlsSet.add("www.instagram.com");

		validUrlsSet.add("twitter.com");

		validUrlsSet.add("in.pinterest.com");
	}
	
	/**
	 * 
	 * @return urls set
	 */
	public static Set<String> getValidUrlsSet() {
		return validUrlsSet;
	}
	
}
