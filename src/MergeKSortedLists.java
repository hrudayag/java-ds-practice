import java.util.PriorityQueue;

public class MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int value) {
            val = value;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> heap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.val, b.val)
        );

        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (!heap.isEmpty()) {
            ListNode smallest = heap.poll();
            current.next = smallest;
            current = current.next;

            if (smallest.next != null) {
                heap.offer(smallest.next);
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {

        MergeKSortedLists solver = new MergeKSortedLists();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};

        ListNode result = solver.mergeKLists(lists);

        System.out.print("Merged list: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
