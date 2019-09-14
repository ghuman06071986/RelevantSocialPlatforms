package com.parminder.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
/**
 * 
 * @author parminder
 *
 */
public class HttpURLConnection {
/**
 * 
 * @param url
 * @return true if url exits and false if not
 */
	public static boolean ValidateUrl(String url) {
		try {
			URL obj = new URL(url);

			java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			con.getInputStream().close();
			if (responseCode == 200) {
				return true;

			} else {
				return false;

			}
		} catch (IOException e) {
			return false;
		} finally {

		}

	}

}
