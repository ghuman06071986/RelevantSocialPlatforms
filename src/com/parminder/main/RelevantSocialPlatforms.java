package com.parminder.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;

import com.parminder.bo.UserInfo;
import com.parminder.parser.Parser;
import com.parminder.utils.FindSimilarUsername;
import com.parminder.utils.PossibleUrls;
import com.parminder.utils.ScorePredictor;
import com.parminder.utils.UrlValidator;

/**
 * 
 * @author parminder
 *
 *         Input: https://www.instagram.com/fashiongrunge/ Output: Found:
 *         https://twitter.com/fashiongrunge/ Found:
 *         https://in.pinterest.com/fashiongrunge/ Found:
 *         https://facebook.com/fashiongrunge/ Run Successfully
 * 
 */
public class RelevantSocialPlatforms {

	/**
	 * 
	 * @param args (a valid url like https://twitter.com/{somevalue} \n
	 *             "https://facebook.com/{somevalue}
	 *             https://www.instagram.com/{somevalue} \n
	 *             "https://in.pinterest.com/{somevalue}"
	 * 
	 */
	public static void main(String... args) {

		// args[0] = "https://www.instagram.com/parminder";

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
		Map<String, Parser> validUrlsSet = PossibleUrls.getValidUrlsMap();

		if (!validUrlsSet.containsKey(extractedUrl)) {
			System.out.println("Input url must be like \n " + "https://twitter.com/{somevalue} \n "
					+ "https://facebook.com/{somevalue} \n " + "https://www.instagram.com/{somevalue} \n "
					+ "https://in.pinterest.com/{somevalue} \n ");
			System.exit(1);
		}
		Parser originalData = validUrlsSet.remove(extractedUrl);
		UserInfo originaInfo = null;
		String username;
		if ((username = UrlValidator.getId(inputUrl)) == null) {
			System.out.println("unable to fetch username");
			System.exit(1);
		}

		try {
			originaInfo = originalData.parsePage(username);
		} catch (IOException e) {
			System.out.println("Input url is not valid");

			System.exit(1);
		}
		ScorePredictor scorePredictor = new ScorePredictor();
		System.out.println("Output: ");
		for (Entry<String, Parser> parser : validUrlsSet.entrySet()) {
			try {
				FindSimilarUsername findSimilarUsername = new FindSimilarUsername();
				List<String> possibleNameArray = findSimilarUsername.findPossibleUsername(username);
				int score = 0;
				UserInfo copyInfo =null;
				for (String possibleName : possibleNameArray) {
					UserInfo newCopyInfo = parser.getValue().parsePage(username);
					int newScore = scorePredictor.getCompareScrore(originaInfo, newCopyInfo);
					if (newScore > score) {
						score= newScore;
						copyInfo = newCopyInfo;
					}
				}
				if(copyInfo != null) {
					System.out.println("Best user   for "+parser.getKey() +" is : "+copyInfo.getUserName()+" ,score : "+score);
				}else {
					System.out.println("No user  Found for "+parser.getKey() );
				}

			} catch (IOException e) {
				System.out.println("Input url is not valid");

				System.exit(1);
			}

		}
		System.out.println("Run Successfully ");

	}
}
