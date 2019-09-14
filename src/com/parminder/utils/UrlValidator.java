package com.parminder.utils;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author parminder
 *
 */
public class UrlValidator {

	/**
	 * patter to validate for https like https://{url}/anything
	 */
	private static final Pattern validUrlPattern = Pattern.compile("^https://(([A-Za-z0-9\\-\\.])*\\.[A-Za-z]*)/{1}.*");

	/**
	 * 
	 * @param url
	 * @return if url match with pattern 
	 */
	public static String isValid(String url) {
		Matcher mather = validUrlPattern.matcher(url);
		Set<String> validUrlsSet = PossibleUrls.getValidUrlsSet();
		if (mather.find()) {
			return mather.group(1);
		}
		return null;
	}

}
