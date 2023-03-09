public class Main {
    public static void main(String[] args) {
        RequestQueue queue = new RequestQueue();
        Central central = new Central(queue);
        central.start();
        for (int i = 0; i < 5; i++) {
            Taxi taxi = new Taxi(queue,i+1);
            taxi.start();
        }
    }
}