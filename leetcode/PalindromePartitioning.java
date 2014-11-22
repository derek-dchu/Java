/**
 *  Leetcode: Palindrome Partitioning 
 */

import java.util.*;


public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
        List<List<String>> answer = new ArrayList<>();
        if (s == null) {
        	return answer;
        }
        
        List<String> partition = new ArrayList<>();
        helper(s, partition, 0, answer);
        
        return answer;
	}
        
    private void helper(String s, List<String> partition, int nextPart, List<List<String>> answer) {
    	if (nextPart == s.length()) {
    		answer.add(new ArrayList<String>(partition));
    	}
    	
    	for (int i = nextPart + 1; i <= s.length(); i++) {
    		String nextPartStr = s.substring(nextPart, i);
    		if (!isPalindrome(nextPartStr)) {
    			continue;
    		}
    		
    		partition.add(nextPartStr);
    		helper(s, partition, i, answer);
    		partition.remove(partition.size() - 1);
    	}
    }
    
    public boolean isPalindrome(String s) {
		if (s == null) return false;
		if (s.length() <= 1) return true;
		
		int l = 0, r = s.length()-1;
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		
		return true;
	}
   
    public static void main(String[] args) {
    	PalindromePartitioning solution = new PalindromePartitioning();
    	String test1 = "bbb";
    	List<List<String>> trueAnswer = new ArrayList<>();
    	List<String> p1 = new ArrayList<>();
    	p1.add("b");p1.add("b");p1.add("b");
    	List<String> p2 = new ArrayList<>();
    	p2.add("bb");p2.add("b");
    	List<String> p3 = new ArrayList<>();
    	p3.add("b");p3.add("bb");
    	List<String> p4 = new ArrayList<>();
    	p4.add("bbb");
    	trueAnswer.add(p1);trueAnswer.add(p2);trueAnswer.add(p3);trueAnswer.add(p4);
    	
    	List<List<String>> answer = solution.partition(test1);
    	
    	for (List<String> p: trueAnswer) {
    		assert answer.containsAll(p) == true : "Not match";
    	}
    }
}
