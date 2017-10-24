package ua.kharkiv.yeremenko.exThreads;

public class Part1 {

    public static void main(String[] args) throws InterruptedException {
            Thread thread1 = new MyThread1();
            thread1.start();
            Thread thread2 = new Thread(new MyThread2());
            thread2.start();
            Thread.sleep(5000);
            thread1.interrupt();
            thread2.interrupt();
            thread1.join();
            thread2.join();
    }
}

class MyThread1 extends Thread{
    public void run() {
        this.setName("Thread extends Thread");
        while (!isInterrupted()) {
            try {
                System.out.println(this.getName());
                sleep(500);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}

class MyThread2 implements Runnable{
    public void run() {
        Thread.currentThread().setName("Thread implements Runnable");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.currentThread().sleep(500);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}
