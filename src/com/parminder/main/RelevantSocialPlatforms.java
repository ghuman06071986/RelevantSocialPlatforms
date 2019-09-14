package com.parminder.main;

import java.io.IOException;
import java.util.Set;

import com.parminder.utils.PossibleUrls;
import com.parminder.utils.UrlValidator;
/**
 * 
 * @author parminder
 *
 *Input: 
 *https://www.instagram.com/fashiongrunge/
 *Output:
 *Found: https://twitter.com/fashiongrunge/
 *Found: https://in.pinterest.com/fashiongrunge/
 *Found: https://facebook.com/fashiongrunge/
 *Run Successfully 

 */
public class RelevantSocialPlatforms {

	
	/**
	 * 
	 * @param args (a valid url like https://twitter.com/{somevalue} \n  "https://facebook.com/{somevalue} https://www.instagram.com/{somevalue} \n "https://in.pinterest.com/{somevalue}"
	 * 
	 */
	public static void main(String... args)  {

		if (args.length == 0) {
			System.out.println("Please Enter a url to proceed.");
			System.exit(1);
		}
		String inputUrl = args[0];
		System.out.println("Input: \n" + inputUrl);

		String extractedUrl;
		if ((extractedUrl = UrlValidator.isValid(inputUrl)) == null) {
			System.out.println("Input url is not valid");
			System.exit(1);
		}
		Set<String> validUrlsSet = PossibleUrls.getValidUrlsSet();
		if (!validUrlsSet.remove(extractedUrl)) {
			System.out.println("Input url must be like \n " + "https://twitter.com/{somevalue} \n "
					+ "https://facebook.com/{somevalue} \n " + "https://www.instagram.com/{somevalue} \n "
					+ "https://in.pinterest.com/{somevalue} \n ");
			System.exit(1);
		}
		System.out.println("Output: ");
		validUrlsSet.forEach((url) -> {
			String outputUrl = inputUrl.replace(extractedUrl, url);

			if (com.parminder.utils.HttpURLConnection.ValidateUrl(outputUrl)) {
				System.out.println("Found: " + outputUrl);
			} else {
				System.out.println("Not found: " + outputUrl);
			}
		});
		System.out.println("Run Successfully ");

	}
}
