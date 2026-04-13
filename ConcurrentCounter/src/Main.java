package src;

import src.entities.Counter;
import src.entities.Incrementers;

public class Main {
    public static void main(String[] args) {
        Counter c = new Counter();
        Incrementers[] incrementers = new Incrementers[5];

        for (int i = 0; i < incrementers.length; i++) {
            incrementers[i] = new Incrementers(c);
        }

        for (Incrementers i : incrementers) {
            i.start();
        }
    }
}
