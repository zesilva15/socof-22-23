public class Central extends Thread{
    private RequestQueue queue;
    public Central(RequestQueue queue) {
        this.queue = queue;
    }
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int numPassengers = (int)(Math.random() * 5);
            int address = (int)(Math.random() * 100);
            Request request = new Request(numPassengers, address);
            queue.add(request);
            System.out.println("Central " + this.getId() + " is adding request " + request);
        }
    }
}
