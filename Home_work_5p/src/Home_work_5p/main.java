package Home_work_5p;

import java.util.Arrays;

public class main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        fillArr(arr);
        halfArray1(arr);

        fillArr(arr);
        halfArray2(arr);
    }

    public static void fillArr(float[] arr) {
        Arrays.fill(arr, 1);
    }

    public static void halfArray1( float[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Formula1 Total execution time: " + (endTime - startTime) + "ms");
        System.out.println(arr[HALF]);
    }

    public static void halfArray2( float[] arr) {
        long startTime = System.currentTimeMillis();
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);




        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + (i+HALF) / 5) * Math.cos(0.2f + (i+HALF) / 5) * Math.cos(0.4f + (i+HALF) / 2));
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);

        long endTime = System.currentTimeMillis();
        System.out.println("Formula2 Total execution time: " + (endTime - startTime) + "ms");
        System.out.println(arr[HALF]);
    }

    public static Thread read(float[] arr, int n) {
        return new Thread(() -> {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + (i+n) / 5) * Math.cos(0.2f + (i+n) / 5) * Math.cos(0.4f + (i+n) / 2));
            }
        });
    }
}
