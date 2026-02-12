package feb_12_tasks;
import java.util.*;
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
public class LinkedListTask {
	public static void main(String[] args) {
//		Q1
//		Given head, the head of a linked list, determine if the linked list has a cycle in it.
//
//		There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
//
//		Return true if there is a cycle in the linked list. Otherwise, return false.
		
		ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head.next.next.next.next.next = head.next.next;

        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        System.out.println(hasCycle);
        
//        Q2
//        Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(6);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(4);
        head1.next.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next.next = new ListNode(6);

        int val = 6;

        List<Integer> list = new ArrayList<>();

        ListNode temp = head1;
        while (temp != null) {
            if (temp.val != val) {
                list.add(temp.val);
            }
            temp = temp.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        for (int x : list) {
            curr.next = new ListNode(x);
            curr = curr.next;
        }

        ListNode result = dummy.next;

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }

		 
//        Q3
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);

        int n = 2;

        int count = 0;
        ListNode curr1 = head2;

        while (curr1 != null) {
            count++;
            curr1 = curr1.next;
        }

        if (count == n) {
            head2 = head2.next;
        } else {
            curr1 = head2;
            ListNode prev = null;

            while (count > 0) {
                if (count == n) {
                    prev.next = curr1.next;
                    break;
                } else {
                    prev = curr1;
                    curr1 = curr1.next;
                }
                count--;
            }
        }

        ListNode temp1 = head2;
        while (temp1 != null) {
            System.out.print(temp1.val + " ");
            temp1 = temp1.next;
        }

	}
}
