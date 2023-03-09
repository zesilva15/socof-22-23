public class Taxi extends Thread{
    private RequestQueue queue;
    private int numSeats;
    public Taxi(RequestQueue queue, int numSeats) {
        this.queue = queue;
        this.numSeats = numSeats;

    }
    public void run() {
        while (true) {
            Request request = queue.remove(this.numSeats);
            System.out.println("Taxi " + this.getId() +" with "+ this.numSeats + " is serving request " + request);
            try {
                sleep(request.numPassengers * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
