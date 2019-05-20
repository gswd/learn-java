package com.hm707.queue;

import java.util.LinkedList;
import java.util.Queue;

class MyCircularQueue {
    private int[] elements ;
    private int head;
    private int tail;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        elements = new int[k];
        head = -1;
        tail = -1;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull())
            return false;
        if(isEmpty()) {
            head = 0;
            tail = 0;
            elements[head] = value;
            return true;
        }

        tail = (tail+1) % elements.length;
        elements[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty())
            return false;
        if(head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % elements.length;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()) return -1;
        return elements[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()) return -1;
        return elements[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head == -1 && tail == -1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (tail+1) % elements.length == head;
    }

    public static void main(String[] args) {
        //MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
				//
        //System.out.println(circularQueue.enQueue(1));  // 返回 true
				//
        //System.out.println(circularQueue.enQueue(2)); // 返回 true
        //System.out.println(circularQueue.enQueue(3));
        //System.out.println(circularQueue.enQueue(4));
        //System.out.println(circularQueue.Rear());


        // 1. Initialize a queue.
        Queue<Integer> q = new LinkedList<>();
        // 2. Get the first element - return null if queue is empty.
        System.out.println("The first element is: " + q.peek());
        // 3. Push new element.
        q.offer(5);
        q.offer(13);
        q.offer(8);
        q.offer(6);
        // 4. Pop an element.
        q.poll();
        // 5. Get the first element.
        System.out.println("The first element is: " + q.peek());
        // 7. Get the size of the queue.
        System.out.println("The size is: " + q.size());

    }
}
