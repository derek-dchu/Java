/**
 * LeetCode OJ: Find Minimum In Rotated Sorted Array II
 */

public class FindMinimumInRotatedSortedArrayII {
	/* O(n). Since the array may contain duplicates. When we have left = middle = right,
	 * in which we can't determine the location of the minimum number, therefore we can
	 * only shrink the length by 1. Eventually, we will have O(n) runtime when array mainly
	 * contains dupliates.
	 */
	public int findMin(int[] num) {
		int l = 0, r = num.length-1, mid;
		
		while (r - l > 1) {
			/* The subarray is not rotated */
			if (num[r] > num[l]) {
				return num[l];
			}
			
			/* Instead of mid = (l + r) / 2, we eliminate the potential of overflow */
			mid = (r - l) / 2 + l;
			
			/* rotated position is on the left side */
			if (num[mid] < num[l]) {
				r = mid;
			} 
			/* rotated position is on the right side */
			else if (num[mid] > num[l]){
				l = mid+1;
			}
			else {
				l += 1;
			}
		}
		return Math.min(num[l], num[r]);
	}
	
	/* O(n) solution */
	public int bruteForce(int[] num) {
		int min = num[0];
		for (int n : num) {
			if (n < min) {
				min = n;
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		FindMinimumInRotatedSortedArrayII solution = new FindMinimumInRotatedSortedArrayII();
		
		int[] test1 = {2,2,2,2,0,1,2};
		System.out.println(solution.findMin(test1));
		int[] test2 = {0,1,1,1,1,1,1,1};
		System.out.println(solution.findMin(test2));
		int[] test3 = {2,3,0,1,1,1,1,2};
		System.out.println(solution.findMin(test3));
	}
}
