package com.parminder.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.parminder.parser.FacebookParser;
import com.parminder.parser.InstagramParser;
import com.parminder.parser.Parser;
import com.parminder.parser.PinterestParser;
import com.parminder.parser.TwitterParser;

/**
 * 
 * @author parminder
 *
 */
public class PossibleUrls {
	/**
	 * possible urls set
	 */
	private static Map<String,Parser> validUrlsSet = new HashMap<>();

	/**
	 * add possible urls onstart
	 */
	static {
		validUrlsSet.put("facebook.com",new FacebookParser());

		validUrlsSet.put("www.instagram.com",new InstagramParser());

		validUrlsSet.put("twitter.com",new TwitterParser());

		validUrlsSet.put("in.pinterest.com", new PinterestParser());
	}
	
	/**
	 * 
	 * @return urls set
	 */
	public static Map<String,Parser> getValidUrlsMap() {
		return validUrlsSet;
	}
	
}
