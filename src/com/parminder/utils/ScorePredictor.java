package com.parminder.utils;

import com.parminder.bo.UserInfo;

public class ScorePredictor {

	public int getCompareScrore(UserInfo original, UserInfo compare) {

		double i = 0;
		int t = 0;
		if (original.getAddress() != null && compare.getAddress() != null) {
			i = 1 + similarity(original.getUrl(), compare.getUrl());
			t++;
		}
		if (original.getName() != null && compare.getName() != null) {
			i = i + similarity(original.getName(), compare.getName());

			t++;
		}
		if (original.getDescription() != null && compare.getDescription() != null) {
			i = i + similarity(original.getDescription(), compare.getDescription());
			t++;
		}
		if (original.getUrl() != null && compare.getUrl() != null) {
			i = i + similarity(original.getUrl(), compare.getUrl());
			t++;
		}
		return (int) (i * 100 / t);
	}

	public double similarity(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length()) { // longer should always have greater length
			longer = s2;
			shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) {
			return 1.0;
			/* both strings are zero length */ }
		/*
		 * // If you have Apache Commons Text, you can use it to calculate the edit
		 * distance: LevenshteinDistance levenshteinDistance = new
		 * LevenshteinDistance(); return (longerLength -
		 * levenshteinDistance.apply(longer, shorter)) / (double) longerLength;
		 */
		return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

	}

	// Example implementation of the Levenshtein Edit Distance
	// See http://rosettacode.org/wiki/Levenshtein_distance#Java
	public int editDistance(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();

		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0)
					costs[j] = j;
				else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (s1.charAt(i - 1) != s2.charAt(j - 1))
							newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}
			if (i > 0)
				costs[s2.length()] = lastValue;
		}
		return costs[s2.length()];
	}

}
