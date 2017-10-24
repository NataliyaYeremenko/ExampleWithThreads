package ua.kharkiv.yeremenko.exThreads;

public class Part3 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread();
        thread1.start();
        Thread thread2 = new MyThread();
        thread2.start();
        Thread.sleep(2000);
        thread1.interrupt();
        thread2.interrupt();
        thread1.join();
        thread2.join();
    }
}

class Counter{
    private static int count1 = 0;
    private static int count2 = 0;

    public int getCount1() {
        return count1;
    }

    public int getCount2() {
        return count2;
    }

    public void increment1(){
        count1++;
    }
    public void increment2(){
        count2++;
    }
}

class MyThread extends Thread{
    Counter counter = new Counter();
    public void run() {
            while (!isInterrupted()) {
                    try {
                        synchronized (this) {
                            System.out.println("counter 1 = " + counter.getCount1() + ", counter 2 = " + counter.getCount2() +
                                    " compareResult: " + (counter.getCount1() == counter.getCount2()));
                            counter.increment1();
                            sleep(10);
                            counter.increment2();
                        }
                    } catch (InterruptedException ex) {
                        return;
                    }
            }
    }
}