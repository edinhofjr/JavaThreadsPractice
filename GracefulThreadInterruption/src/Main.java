package src; 

public class Main {
    
    static class Processor extends Thread {
        @Override
        public void run() {
            int item = 1;
            
            while (!this.isInterrupted()) {
                System.out.println(++item);

                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("[thread] " + getState());
                    interrupt();
                }
            }
        }        
    }

    public static void main(String[] args) throws InterruptedException {
        Processor p = new Processor();
        p.start();

        Thread.sleep(3_400);
        p.interrupt();
        System.out.println("[main] " + p.getState());
        p.join();
    }
}