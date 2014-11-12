/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */


public class MaximumProductSubarray {
	
	/* Assuming all elements are integers */
	/* O(n) read through array */
	public int maxProduct(int[] A) {
		int prev = A[0];
        int curr = A[0];
        int max = A[0];
 
        for (int i = 1; i < A.length; i++) {
            if (A[i] == 0) {
            	curr = prev = 0;
            } else if (A[i] < 0 && prev > 0 && curr > 0 || A[i] < 0 && prev < 0 && curr < 0 || A[i] > 0 && prev < 0 && curr < 0) {
                curr = A[i];
                prev *= A[i];
            } else if (prev == 0 && curr == 0) {
            	prev = curr = A[i];
            } else {
                prev *= A[i];
                curr *= A[i];
            }
 
            max = Math.max(max, Math.max(curr, prev));
        }
 
        return max;
	}
	
	public static void main(String[] args) {
		MaximumProductSubarray solution = new MaximumProductSubarray();
		int[] test1 = {2,3,-2,4};
		assert solution.maxProduct(test1) == 6 : "Not match";
		int[] test2 = {-1,-2,-2,-2,-2};
		assert solution.maxProduct(test2) == 16 : "Not match";
		int[] test3 = {2,-2,0,1,-2};
		assert solution.maxProduct(test3) == 2 : "Not match";
		int[] test4 = {2,-2,2,-2,2,-4};
		assert solution.maxProduct(test4) == 32 : "Not match";
		int[] test5 = {-4,-3};
		assert solution.maxProduct(test5) == 12 : "Not match";
		int[] test6 = {0,2};
		assert solution.maxProduct(test6) == 2 : "Not match";
		int[] test7 = {-2,0,-1};
		assert solution.maxProduct(test7) == 0 : "Not match";
	}
}
