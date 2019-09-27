package com.parminder.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.parminder.parser.Parser;

public class FindSimilarUsername {

	
	private static Pattern similarUsernameRegex =  Pattern.compile("(\\D*)(\\d*)");
	
	
	public  List<String> findPossibleUsername(String userName) {
	
		Matcher mather = similarUsernameRegex.matcher(userName);
		Map<String,Parser> validUrlsSet = PossibleUrls.getValidUrlsMap();
		ArrayList<String> usernameArray = new ArrayList<>(); 
		 while(mather.find()) {
			 if(!mather.group(1).isBlank()) {
				 usernameArray.add(mather.group(1));
			 }
			 if(!mather.group(2).isBlank()) {
				 usernameArray.add(mather.group(2));
			 }
	        }
		 usernameArray.add("_");
		 List<String>  results = new ArrayList<>();
		// System.out.println(usernameArray);
		 return permute(usernameArray.toArray(new String[usernameArray.size()]), 0, usernameArray.size()-1,results);
		
	}
	
	 /** 
     * permutation function 
     * @param str string to calculate permutation for 
     * @param l starting index 
     * @param r end index 
     */
    private  List<String>  permute(String[] str, int l, int r,List<String>  results) 
    { 
        if (l == r) 
        	results.add(String.join("",str)); 
        else { 
            for (int i = l; i <= r; i++) { 
                str = swap(str, l, i); 
                permute(str, l + 1, r,results); 
                str = swap(str, l, i); 
            } 
        } 
        return results;
    } 
  
    /** 
     * Swap Characters at position 
     * @param a string value 
     * @param i position 1 
     * @param j position 2 
     * @return swapped string 
     */
    public static String[] swap(String[] charArray, int i, int j) 
    { 
        String temp; 
        temp = charArray[i]; 
        charArray[i] = charArray[j]; 
        charArray[j] = temp; 
        return charArray; 
    } 
}
