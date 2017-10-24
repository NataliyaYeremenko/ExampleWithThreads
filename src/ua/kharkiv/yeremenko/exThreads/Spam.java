package ua.kharkiv.yeremenko.exThreads;

import java.io.IOException;

public class Spam extends Thread{

    private long[] timeInterval = new long[]{100, 10, 200, 300};
    private String[] messages = new String[]{"Message1", "Message2", "Message3", "Message4"};
    public void run(){
        while (!isInterrupted()) {
            for (int i = 0; i < timeInterval.length; i++) {
                try {
                    System.out.println(messages[i]);
                    sleep(timeInterval[i]);
                } catch (InterruptedException ex) {
                    return;
                }
            }
        }
    }

    public static Thread main() throws Exception {
        Thread spam = new Spam();
        spam.start();
        Thread thread = new Thread() {
            public void run() {
                byte[] buffer = new byte[10];
                int count;
                try {
                    do {
                        while ((count = System.in.read(buffer)) == -1)
                            ;
                    } while (!System.lineSeparator().equals(
                            new String(buffer, 0, count)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                spam.interrupt();
            }
        };
        thread.start();
        return thread;
    }

    public static void main(String[] args) throws InterruptedException {

    }
}
