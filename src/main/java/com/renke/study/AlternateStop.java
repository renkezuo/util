package com.renke.study;

public class AlternateStop extends Object implements Runnable {
    private volatile boolean stopRequested;
    private Thread runThread;
    public void run() {
        runThread = Thread.currentThread();
        stopRequested = false;
        int count = 0;
        while ( !stopRequested ) {
            System.out.println("Running ... count=" + count);
            System.out.println( runThread.getName());
            count++;
            try {
                Thread.sleep(100);
            } catch ( InterruptedException x ) {
                Thread.currentThread().interrupt(); // re-assert interrupt
            }
        }
        System.out.println("stoped");
    }
    public void stopRequest(){
        stopRequested = true;
        if ( runThread != null ) {
            runThread.interrupt();
        }
    }
    public static void main(String[] args) {
        AlternateStop as = new AlternateStop();
        AlternateStop as2 = new AlternateStop();
        Thread t = new Thread(as);
        Thread t2 = new Thread(as2);
        t.start();
        t2.start();
        try {
            Thread.sleep(2000);
        } catch ( InterruptedException x ) {
            // ignore
        }
        as.stopRequest();
        as2.stopRequest();
    }
}
