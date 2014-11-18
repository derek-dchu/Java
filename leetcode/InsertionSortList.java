/**
 * LeetCode OJ: Insertion Sort List
 */


public class InsertionSortList {
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
	
	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode dummyHead = new ListNode(0), curr, currectPost;
		dummyHead.next = head;
		curr = dummyHead;
		while (curr.next != null) {
			currectPost = dummyHead;
			while (currectPost.next.val < curr.next.val) {
				currectPost = currectPost.next;
			}
			
			if (currectPost != curr) {
				insertNode(currectPost, curr);
			} else {
				curr = curr.next;
			}
		}
		
		return dummyHead.next;
	}
	
	public void insertNode(ListNode leftPrev, ListNode rightPrev) {
		if (leftPrev == null || rightPrev == null) return;
		
		ListNode tmp = leftPrev.next;
		leftPrev.next = rightPrev.next;
		rightPrev.next = leftPrev.next.next;
		leftPrev.next.next = tmp;
	}
	
	public static void main(String[] args) {
		InsertionSortList s = new InsertionSortList();
		/* 1,2,3,4 */
		s.append(1);s.append(2);s.append(3);s.append(4);
		System.out.println(s.listToString(s.head));
		s.head = s.insertionSortList(s.head);
		System.out.println(s.listToString(s.head));
		/* 5,4,3,2,1 */
		s = new InsertionSortList();
		s.append(5);s.append(4);s.append(3);s.append(2);s.append(1);
		System.out.println(s.listToString(s.head));
		s.head = s.insertionSortList(s.head);
		System.out.println(s.listToString(s.head));
		/* 1,1,1,1,1 */
		s = new InsertionSortList();
		s.append(1);s.append(1);s.append(1);s.append(1);s.append(1);
		System.out.println(s.listToString(s.head));
		s.head = s.insertionSortList(s.head);
		System.out.println(s.listToString(s.head));
	}
}
