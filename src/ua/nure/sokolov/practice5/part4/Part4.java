package ua.nure.sokolov.practice5.part4;


public class Part4 {

    public static void main(String[] args) {
        Worker worker = new Worker(4,100);
        long before = System.currentTimeMillis();
        final long[] after = new long[1];
        worker.performanceTaskWithoutThread();
        worker.performanceTaskInThread();

//        after[0] = System.currentTimeMillis() - before;
//        System.out.println("Time performance in thread: " + after[0]);
    }

}
class Worker{

    private Thread[] workers;
    private int[][] matrix;
    private int countThread;
    private int countColumn;

    int count = 0;
    public Worker( int M, int N) {
        this.matrix = new int[M][N];
        this.countThread = M;
        this.countColumn = N;
        for (int j = 0; j < M; j++){
            for (int k = 0; k < N; k++){
                matrix[j][k] = (int) (Math.random()*50);
            }
        }
    }

    public void performanceTaskInThread(){
        this.workers = new Thread[countThread];
        long before = System.currentTimeMillis();
        final long[] after = new long[1];
        int[] findMax = new int[5];

        for (int j = 0; j < countThread; j++){
            int finalJ = j;
            workers[j] = new Thread(){
                @Override
                public void run() {
                    synchronized (workers) {
                        try {
                            String nameThread = String.valueOf(Thread.currentThread());
                            int max = matrix[0][0];
                            for (int k = 0; k < countThread; k++) {
                                for (int t = 0; t < countColumn; t++) {
                                    if (matrix[k][t] > max) {
                                        max = matrix[k][t];
                                    }
                                    sleep(1);
                                }
                            }
                            System.out.println("Max element in matrix: " + max + " in " + nameThread);
                            after[0] = System.currentTimeMillis() - before;
                            System.out.println("Time performance in thread: " + after[0]);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }
        startThread();
    }


    public void performanceTaskWithoutThread() {
        long before = System.currentTimeMillis();
        System.out.println("Max element without used thread: " + findMaxNumberInMatrix());
        long after = System.currentTimeMillis() - before;
        System.out.println("Time performance without used thread: " + after);
        System.out.println();
    }

    public int findMaxNumberInMatrix(){
        int max = matrix[0][0];
            for (int k = 0; k < countThread; k++) {
                for (int t = 0; t < countColumn; t++) {
                    if (matrix[k][t] > max) {
                        max = matrix[k][t];
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        return max;
    }

    public void startThread(){
        for (int j = 0; j < countThread; j++){
            workers[j].start();
        }
    }

}
