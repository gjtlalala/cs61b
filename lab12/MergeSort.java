import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> queue = new Queue<>();
        /*while (!items.isEmpty()) {
            Queue<Item> itemqueue = new Queue<>();
            itemqueue.enqueue(items.dequeue());
            queue.enqueue(itemqueue);
        }*/
        for(Item item : items){
            Queue<Item> itemqueue = new Queue<>();
            itemqueue.enqueue(item);
            queue.enqueue(itemqueue);
        }
        return queue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue queue = new Queue<Item>();
        while (!q1.isEmpty() || !q2.isEmpty()){
            queue.enqueue(getMin(q1, q2));
        }
        return queue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if(items.size() <= 1){
            return items;
        }
        Queue<Queue<Item>> queue = makeSingleItemQueues(items);
       /* Queue<Queue<Item>> newqueue = new Queue<>();
        while (true){
            while (!queue.isEmpty()) {
                if(queue.size() == 1) {
                    newqueue.enqueue(queue.dequeue());
                }else {
                    newqueue.enqueue(mergeSortedQueues(queue.dequeue(), queue.dequeue()));
                }
            }
            if(newqueue.size() == 1) {
                return newqueue.dequeue();
            }
            queue = newqueue;
            newqueue = new Queue<>();

        }*/
        while (queue.size() > 1) {
            queue.enqueue(mergeSortedQueues(queue.dequeue(), queue.dequeue()));
        }
        return queue.dequeue();
    }
    /*public static void mergesorthelper(Queue<Queue<Item>> queue){

    }*/

    public static void main(String[] args) {
        Queue<String> students = new Queue<String>();
        students.enqueue("alice");
        students.enqueue("vanessa");
        students.enqueue("ethan");
        students.enqueue("mthan");
        students.enqueue("lthan");
        students.enqueue("cthan");
        students.enqueue("bthan");
        //students.enqueue("bthan");
        System.out.println(students);
        Queue<String> sortedqueue = MergeSort.mergeSort(students);
        System.out.println(students);

        System.out.println(sortedqueue);
    }
}
