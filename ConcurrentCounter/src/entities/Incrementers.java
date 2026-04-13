package src.entities;

import src.Log;

public class Incrementers extends Thread {
    Counter counter;

    public Incrementers(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (true) {
            synchronized(counter) {
                if (counter.getValue() >= 100) break; 
            
                counter.increment();
                
                Log.info("incrementou para " + counter.getValue());
            }
        }
    }
}
