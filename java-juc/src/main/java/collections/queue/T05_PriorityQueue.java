package collections.queue;

import java.util.PriorityQueue;

public class T05_PriorityQueue {

    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();
        q.add("b");
        q.add("a");
        q.add("d");
        q.add("c");
        q.add("e");

        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }
    }
}
