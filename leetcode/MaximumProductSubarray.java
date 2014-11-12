/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */


public class MaximumProductSubarray {
	
	/* Assuming all elements are integers */
	/* O(n) read through array */
	public int maxProduct(int[] A) {
		int prevNum = A[0];
        int prevProduct = A[0];
        int maxProduct = A[0];
 
        for (int i = 1; i < A.length; i++) {
            if (A[i] == 0) {
            	prevNum = prevProduct = 0;
            } else if (prevProduct == 0 && prevNum == 0) {
            	prevProduct = prevNum = A[i];
            } else if (A[i] < 0 && prevProduct > 0 && prevNum > 0 || A[i] < 0 && prevProduct < 0 && prevNum < 0 || A[i] > 0 && prevProduct < 0 && prevNum < 0) {
                prevNum = A[i];
                prevProduct *= A[i];
            } else {
                prevProduct *= A[i];
                prevNum *= A[i];
            }
 
            maxProduct = Math.max(maxProduct, Math.max(prevNum, prevProduct));
        }
 
        return maxProduct;
	}
	
	/* Assuming all elements are integers */
	/* O(n) read through array */
	/* We keep tracking the largest product(lp) and the smallest product(sp), since smallest could become largest when meeting a new negative element */
	/* We will have 
	 * 	lp(n) = max(lp(n-1) * A[n], A[n], sp(n-1) * A[n])
	 *	sp(n) = min(lp(n-1) * A[n], A[n], sp(n-1) * A[n])
	 */
	public int maxProduct2(int[] A) {
		int lp = A[0], sp = A[0], maxProduct = A[0];
		
		for (int i = 1; i < A.length; i++) {
			int tmp_lp = lp, tmp_sp = sp;
			lp = Math.max(A[i], Math.max(tmp_lp * A[i], tmp_sp * A[i]));
			sp = Math.min(A[i], Math.min(tmp_lp * A[i], tmp_sp * A[i]));
			
			/* Update maximum product if current largest product is larger than previous maximum product */
			maxProduct = Math.max(maxProduct, lp);
		}
		
		return maxProduct;
	}
	
	
	public static void main(String[] args) {
		MaximumProductSubarray solution = new MaximumProductSubarray();
		int[] test1 = {2,3,-2,4};
		assert solution.maxProduct2(test1) == 6 : "Not match";
		int[] test2 = {-1,-2,-2,-2,-2};
		assert solution.maxProduct2(test2) == 16 : "Not match";
		int[] test3 = {2,-2,0,1,-2};
		assert solution.maxProduct2(test3) == 2 : "Not match";
		int[] test4 = {2,-2,2,-2,2,-4};
		assert solution.maxProduct2(test4) == 32 : "Not match";
		int[] test5 = {-4,-3};
		assert solution.maxProduct2(test5) == 12 : "Not match";
		int[] test6 = {0,2};
		assert solution.maxProduct2(test6) == 2 : "Not match";
		int[] test7 = {-2,0,-1};
		assert solution.maxProduct2(test7) == 0 : "Not match";
	}
}
