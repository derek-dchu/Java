/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 *	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 *	Find the minimum element.
 *
 *	You may assume no duplicate exists in the array.
 */

public class FindMinimumInRotatedSortedArray {
	/* O(log n) modified binary search */
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
			else {
				l = mid+1;
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
		FindMinimumInRotatedSortedArray solution = new FindMinimumInRotatedSortedArray();
		
		int[] test1 = {4,5,6,7,0,1,2};
		System.out.println(solution.findMin(test1));
		int[] test2 = {0,1,2,3,4,5,6,7};
		System.out.println(solution.findMin(test2));
		int[] test3 = {6,7,0,1,2,3,4,5};
		System.out.println(solution.findMin(test3));
	
	}
}
