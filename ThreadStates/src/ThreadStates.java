package src;

public class ThreadStates extends Thread {
    
    public void run() {
        try {
            sleep(3_000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void printState() {
        System.out.println(getState());
    }
}