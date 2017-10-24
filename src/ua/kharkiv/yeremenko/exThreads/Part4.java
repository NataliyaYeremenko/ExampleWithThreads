package ua.kharkiv.yeremenko.exThreads;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Part4 {
    private final static int M = 4;
    private final static int N = 10;
    private static int[][] array = new int[M][N];
    private static ArrayList<Integer> maxArray = new ArrayList<>();

    private static int random(int min, int max) {
            max -= min;
            return (int) (Math.random() * ++max) + min;
    }

    private static void findMaxThread(int[][] array) throws InterruptedException {
        long timer = -System.currentTimeMillis();
        final int[][] finalArray = array;
        Thread[] threads = new Thread[M];
        for (int k = 0; k < M; k++){
            final int h = k;
            threads[k] = new Thread(){
                public void run(){
                    int max = 0;
                    for (int i = 0; i < N; i++){
                        if (max < finalArray[h][i]){
                            max = finalArray[h][i];
                            try {
                                sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    maxArray.add(max);
                }
            };
            threads[k].start();
        }
        for (Thread thread: threads){
            thread.join();
        }
        int maxMax = 0;
        for (int i = 0; i < M; i++){
            if (maxMax < maxArray.get(i)){
                maxMax = maxArray.get(i);
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        timer += System.currentTimeMillis();
        System.out.println("Max = " + maxMax + ", time (using theads): " + timer + "ms");
    }

    private static void findMax(int[][] array){
        long timer = -System.currentTimeMillis();
        int max = 0;
        int maxMax = 0;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if (max < array[i][j]) {
                    max = array[i][j];
                }
            }
            if(maxMax < max){
                maxMax = max;
            }
        }
        timer += System.currentTimeMillis();
        System.out.println("Max = " + maxMax + ", time (without threads): " + timer + "ms");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=============================================");
        System.out.println("Input array:");
        for (int i = 0; i < M; i++){
            for (int j = 0; j < N; j++){
                array[i][j]=random(0,100);
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=============================================");
        findMaxThread(array);
        System.out.println("=============================================");
        findMax(array);
    }
}


