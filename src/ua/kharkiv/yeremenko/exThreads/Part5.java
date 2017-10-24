package ua.kharkiv.yeremenko.exThreads;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 {
    private static final String PATH = "file_for_Part6.txt";
    private static RandomAccessFile file;
    private static final int COUNT_THREADS = 10;

    private static void write(String st) throws IOException {
        file.write(st.getBytes());
    }

    private static String read() throws IOException {
        file = new RandomAccessFile(PATH, "r");
        StringBuilder res = new StringBuilder();
        int b = file.read();
        while(b != -1){
            res.append((char)b);
            b = file.read();
        }
        return res.toString();
    }

    public static void main(String[] args) {
        File f = new File(PATH);
        if(f.exists()) f.deleteOnExit();
        try {
            file = new RandomAccessFile(PATH, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Thread[] threads = new Thread[COUNT_THREADS];
        for (int k = 0; k < COUNT_THREADS; k++) {
            final int h = k;
            threads[k] = new Thread() {
                public void run() {
                    synchronized (threads) {
                        for (int i = 0; i < 20; i++) {
                            try {
                                write(Integer.toString(h));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            write(System.lineSeparator());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            threads[k].start();
        }
        for (Thread thread: threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println(read());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
