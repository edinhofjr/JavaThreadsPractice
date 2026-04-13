package main;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Integer> buffer;
    private Behavior producingBehavior;

    public Producer(Behavior producingBehavior) {
        this.producingBehavior = producingBehavior;
    }

    public void setBuffer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    public void setProducingBehavior(Behavior producingBehavior) {
        this.producingBehavior = producingBehavior;
    }

    public void startProducing() {
        if (buffer == null) {
            throw new IllegalStateException("Buffer is not set");
        }

        if (producingBehavior == null) {
            throw new IllegalStateException("Producing behavior is not set");
        }

        while (true) {
            try {
                Integer value = producingBehavior.execute();
                buffer.put(value);
                System.out.println("[Producer] produced: " + value + " | queue size: " + buffer.size());
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void run() {
        startProducing();
    }
}
