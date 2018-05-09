/**
 * @author: WanZhiWen
 * @file: Solution_2.java
 * @time: 2018-05-09 22:04
 * <p>
 * <p>
 * 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 **/
package LinkedList;

public class Solution_2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(head.val);
        temp.next = head.next;
        int length = 1;
        while (head.next != null) {
            head = head.next;
            length++;
        }
        int i = length - n;
        for (int j = 0; j < i; j++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return temp;
    }
}
