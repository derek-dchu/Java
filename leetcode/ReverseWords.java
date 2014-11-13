/*
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 */

import java.lang.StringBuilder;


public class ReverseWords {
	public String reverseWords(String s) {	
		String[] words = s.split(" ");
		
		StringBuilder strb = new StringBuilder();
		for (int i = words.length-1; i >= 0; i--) {
			if (!words[i].isEmpty()) {
				strb.append(words[i]);
				strb.append(' ');
			}
		}
		
		if (strb.length() <= 1 ) {
			return strb.toString();
		}
		return strb.substring(0, strb.length()-1);
	}
	
	public static void main(String[] args) {
		ReverseWords solution = new ReverseWords();
		String test1 = "  the  sky is blue   ";
		assert solution.reverseWords(test1).compareTo("blue is sky the") == 0 : "Not match";
		String test2 = " ";
		assert solution.reverseWords(test2).compareTo("") == 0 : "Not match";
	}
}
