
public class QueueTest {
	
	public static void main(String[] args) {
		Queue queue = new Queue();
		System.out.println(queue.isEmpty());
		System.out.println(queue.length());
		queue.enqueue(new Job(1,2));
		System.out.println(queue.isEmpty());
		System.out.println(queue.length());
		System.out.println("toString:" + queue.toString());
		
		try {
			System.out.println(queue.peek());
		} catch (QueueEmptyException e) {
			System.out.println("nope");
		}
		
		try {
			System.out.println(queue.peek());
		} catch (QueueEmptyException e) {
			System.out.println("nope");
		}
		
		queue.enqueue(new Job(2,3));
		queue.enqueue(new Job(3,4));
		queue.enqueue(new Job(4,5));
		System.out.println("toString:" + queue.toString());
		
		try {
			queue.dequeue();
		} catch (QueueEmptyException e) {
			System.out.println("nope");
		}
		
		try {
			queue.dequeueAll();
		} catch (QueueEmptyException e) {
			System.out.println("nope");
		}
	}

}
