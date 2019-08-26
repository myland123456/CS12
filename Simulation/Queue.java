public class Queue implements QueueInterface {


    private class Node {
        Node next;
        Object item;

        public Node(Object item) {
            this.item = item;
            this.next = null;
        }
    }

    private int numItems;
    Node head;
    Node tail;

    public Queue() {
        numItems = 0;
        head = null;
        tail = null;
    }

    // isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
    public boolean isEmpty() {
        return (numItems == 0);
    }

    // length()
    // pre: none
    // post: returns the length of this Queue.
    public int length() {
        return numItems;
    }

    // enqueue()
    // adds newItem to back of this Queue
    // pre: none
    // post: !isEmpty()
    public void enqueue(Object newItem) {
        if (numItems == 0) {
            head = new Node(newItem);
            numItems++;
        } else if (numItems == 1) {
            tail = new Node(newItem);
            head.next = tail;
            numItems++;
        } else {
            tail.next = new Node(newItem);
            tail = tail.next;
            numItems++;
        }
    }

    // dequeue()
    // deletes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    public Object dequeue() throws QueueEmptyException {
        if (this.isEmpty()) {
            throw new QueueEmptyException();
        } else {
            Object result = head.item;
            head = head.next;
            numItems--;
            return result;
        }
    }

    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    public Object peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("queue is empty!");
        } else {
            return head.item;
        }
    }

    // dequeueAll()
    // sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    public void dequeueAll() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("queue is empty!");
        } else {
            head.next = head;
            head = null;
            numItems = 0;
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        Node temp = head;
        if (!isEmpty()) {
            if (numItems == 1) {
                sb.append(temp.item.toString());
            } else {

                while (temp.next != null) {
                    sb.append((sb.toString().equals("") ? "" : " ") + temp.item.toString());
                    temp = temp.next;
                }
                sb.append(" " + tail.item.toString() + " ");

            }

        }

        return sb.toString();
    }

}
