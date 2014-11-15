/* Sort a linked list in O(n log n) time using constant space complexity.
 * 
 */

public class SortList {
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public ListNode head = null, tail = null;
	
	public void append(int x) {
		if (this.head == null) {
			this.head = this.tail = new ListNode(x);
		} else {
			this.tail.next = new ListNode(x);
			this.tail = this.tail.next;
		}
	}
	
	public String listToString(ListNode head) {
        if( head == null ) return "Empty list.";
		ListNode curr = head;
        StringBuilder strb = new StringBuilder();
		while( curr != null ) {
			strb.append( curr.val ); strb.append( ' ' );
			curr = curr.next;
		}
		return strb.toString();
	}
	
	/* O(n log n), using merge sort */
	public ListNode sortList(ListNode head) {
		return sortListHelper(head);
	}
	
	private ListNode sortListHelper(ListNode head) {
		/* Base case */
		if (head == null || head.next == null) {
			return head;
		}
		
		/* Break from the mid node */
		ListNode mid = findMidNode(head);
		ListNode tmp = mid.next;
		mid.next = null;
		mid = tmp;
		
		/* Sort left part */
		ListNode left = sortListHelper(head);
		/* Sort right part */
		ListNode right = sortListHelper(mid);
		//System.out.println("left: " + left.val);
		//System.out.println("right: " + right.val);
		
		/* Merge */
		ListNode tail;
		if (left.val < right.val) {
			head = tail = left;
			left = left.next;
		} else {
			head = tail = right;
			right = right.next;
		}
		while (left != null && right != null) {
			if (left.val < right.val) {
				tail.next = left;
				left = left.next;
			} else {
				tail.next = right;
				right = right.next;
			}
			tail = tail.next;
		}
		
		if (left != null) {
			tail.next = left;
		} else {
			tail.next = right;
		}
		
		return head;
	}
	
	private ListNode findMidNode(ListNode head) {
		ListNode fast, slow;
		fast = head.next;
		slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		//System.out.println("mid: " + slow.val);
		return slow;
	}
	
	public static void main(String[] args) {
		SortList s = new SortList();
		/* 1,2,3,4 */
		s.append(1);s.append(2);s.append(3);s.append(4);
		System.out.println(s.listToString(s.head));
		s.head = s.sortList(s.head);
		System.out.println(s.listToString(s.head));
		/* 5,4,3,2,1 */
		s = new SortList();
		s.append(5);s.append(4);s.append(3);s.append(2);s.append(1);
		System.out.println(s.listToString(s.head));
		s.head = s.sortList(s.head);
		System.out.println(s.listToString(s.head));
		/* 1,1,1,1,1 */
		s = new SortList();
		s.append(1);s.append(1);s.append(1);s.append(1);s.append(1);
		System.out.println(s.listToString(s.head));
		s.head = s.sortList(s.head);
		System.out.println(s.listToString(s.head));
	}
}
