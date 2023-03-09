public class Request {
    int numPassengers;
    int address;
    public Request(int numPassengers, int address) {
        this.numPassengers = numPassengers;
        this.address = address;
    }
    public String toString() {
        return "Request: " + numPassengers + " passengers to " + address;
    }
}
