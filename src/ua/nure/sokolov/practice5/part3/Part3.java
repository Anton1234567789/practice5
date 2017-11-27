package ua.nure.sokolov.practice5.part3;

import static java.lang.Thread.sleep;

public class Part3 {

    private static int finalCounter = 5;
    private static int finalCounter1 = 5;

    public static void main(String[] args) {

        System.out.println("Not synchronize");
        for (int j = 0; j < 10; j++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                    finalCounter++;
                    System.out.println("first count " + finalCounter +  "; second count " + finalCounter1 + " " + (finalCounter == finalCounter1));
                    sleep(333);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finalCounter1++;
                }
            }.start();
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("synchronize");
        finalCounter = 5;
        finalCounter1 = 5;

        for (int j = 0; j < 10; j++) {
            new Thread() {
                @Override
                public void run() {
                    synchronized ("moni") {
                        System.out.println("first count " + finalCounter + "; second count " + finalCounter1 + " " + (finalCounter == finalCounter1));
                        finalCounter++;
                        try {
                            sleep(333);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finalCounter1++;
                    }
                }
            }.start();
        }
    }
}
