package src; 

public class Main {
    public static void main(String args[]) throws InterruptedException {
        ThreadStates t = new ThreadStates();
        // NEW - Initialzed but not running
        t.printState();
        t.start();

        // RUNNABLE - Initialized and running;
        t.printState();

        Thread.sleep(100);
        
        // TIMED_WAITING - Blocked
        t.printState();

        t.join();
        
        // TERMINATED - finished;
        t.printState();
        
    }
}