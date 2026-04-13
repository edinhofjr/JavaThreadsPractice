package main;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<Integer> buffer;

    public void setBuffer(BlockingQueue<Integer> buffer) {
        this.buffer = buffer;
    }

    public void startConsuming() {
        if (buffer == null) {
            throw new IllegalStateException("Buffer is not set");
        }

        while (true) {
            try {
                Integer value = buffer.take();
                System.out.println("[Consumer] consumed: " + value + " | queue size: " + buffer.size());
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void run() {
        startConsuming();
    }
}
