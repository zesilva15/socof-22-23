import java.util.LinkedList;

public class RequestQueue {
    private LinkedList<Request> queue;
    public RequestQueue() {
        queue = new LinkedList<Request>();
    }
    synchronized void add(Request request) {
        queue.add(request);
        notifyAll();
    }

    synchronized Request remove(int nSeats) {
        while(queue.isEmpty() || queue.peek().numPassengers > nSeats){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return queue.remove();
    }
    boolean isEmpty() {
        if(queue == null){
            return true;
        }
        if (queue.size() == 0) {
            return true;
        }
        return false;
    }
}
