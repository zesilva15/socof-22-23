import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> orderQueue = new LinkedList<>();
        int numTakers = 3;
        int numHandlers = 2;

        // Create and start order taker threads
        for (int i = 0; i < numTakers; i++) {
            OrderTaker taker = new OrderTaker(orderQueue);
            new Thread(taker).start();
        }

        // Create and start order handler threads
        for (int i = 0; i < numHandlers; i++) {
            OrderHandler handler = new OrderHandler(orderQueue);
            new Thread(handler).start();
        }
    }

    static class Order {
        private int id;

        public Order(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String toString() {
            return "Order " + id;
        }
    }

    static class OrderQueue {
        private Queue<Order> queue;

        public OrderQueue() {
            queue = new LinkedList<>();
        }

        public synchronized void addOrder(Order order) {
            queue.add(order);
            System.out.println(order + " added to queue.");
            notifyAll();
        }

        public synchronized Order removeOrder() {
            while (queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Order order = queue.remove();
            System.out.println(order + " removed from queue.");
            return order;
        }
    }

    static class OrderTaker implements Runnable {
        private OrderQueue orderQueue;
        private static int nextId = 0;

        public OrderTaker(OrderQueue orderQueue) {
            this.orderQueue = orderQueue;
        }

        public void run() {
            while (true) {
                Order order = new Order(nextId++);
                orderQueue.addOrder(order);
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class OrderHandler implements Runnable {
        private OrderQueue orderQueue;

        public OrderHandler(OrderQueue orderQueue) {
            this.orderQueue = orderQueue;
        }

        public void run() {
            while (true) {
                Order order = orderQueue.removeOrder();
                // process order
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}