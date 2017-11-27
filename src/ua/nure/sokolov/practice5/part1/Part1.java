package ua.nure.sokolov.practice5.part1;

public class Part1 {

    private static final int TIMEOUT = 500;
    private static final int COUN = 6;

    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                try {
                    for (int j = 0; j < COUN; j++) {
                        sleep(TIMEOUT);
                        Thread t = Thread.currentThread();
                        System.out.println(t);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int j = 0; j < COUN; j++) {
                        Thread.sleep(TIMEOUT);
                        Thread t = Thread.currentThread();
                        System.out.println(t);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
