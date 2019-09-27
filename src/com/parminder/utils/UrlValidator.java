package com.parminder.utils;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.parminder.parser.Parser;
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
	private static final Pattern idPattern = Pattern.compile("^https://(([A-Za-z0-9\\-\\.])*\\.[A-Za-z]*)/{1}(.*)/{0,1}");

	/**
	 * 
	 * @param url
	 * @return if url match with pattern 
	 */
	public static String isValid(String url) {
		Matcher mather = validUrlPattern.matcher(url);
		Map<String,Parser> validUrlsSet = PossibleUrls.getValidUrlsMap();
		if (mather.find()) {
			return mather.group(1);
		}
		return null;
	}
	
	public static String getId(String url) {
		Matcher mather = idPattern.matcher(url);
		Map<String,Parser> validUrlsSet = PossibleUrls.getValidUrlsMap();
		if (mather.find()) {
			
			return mather.group(3);
		}
		return null;
	}

}
