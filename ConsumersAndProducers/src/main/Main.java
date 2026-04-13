package main;

public class Main {
    public static void main(String[] args) {
        Broker broker = new Broker();

        broker.registerProducer(new Producer(() -> 1));
        broker.registerProducer(new Producer(() -> 3));
        broker.registerProducer(new Producer(() -> 2));
        broker.registerConsumer(new Consumer());

        broker.start();
    }
}
