package main;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Broker {
    private final BlockingQueue<Integer> queue;
    private final ArrayList<Producer> producers = new ArrayList<>();
    private final ArrayList<Consumer> consumers = new ArrayList<>();

    public Broker() {
        this.queue = new ArrayBlockingQueue<>(100);
    }

    public void debug() {
        System.out.println("[Broker] queue size: " + queue.size() + " | contents: " + queue);
    }

    public void registerProducer(Producer producer) {
        producers.add(producer);
        producer.setBuffer(queue);
    }

    public void registerConsumer(Consumer consumer) {
        consumers.add(consumer);
        consumer.setBuffer(queue);
    }

    public void start() {
        for (Producer producer : producers) {
            new Thread(producer).start();
        }

        for (Consumer consumer : consumers) {
            new Thread(consumer).start();
        }

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            debug();
        }
    }
}
