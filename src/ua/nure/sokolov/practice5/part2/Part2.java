package ua.nure.sokolov.practice5.part2;

import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Part2 {


    public static void main(String[] args) {
       Part2 part2 = new Part2();
       Spam spam = new Spam(new int[]{333,555}, new String[]{"AAAA", "bbb0"});
       spam.main();
    }

}

class Spam{
    private int countThread;
    Thread[] threadsArr;
    private int[] times;
    private String[] messages;

    public Spam(int[] times, String[] messages) {
        this.times = times;
        this.messages = messages;
        this.countThread = times.length;
        this.threadsArr = new Thread[countThread];

        for (int j = 0; j < countThread; j++){
            int finalJ = j;
            threadsArr[j] = new Thread(){
                @Override
                public void run() {
                    while (true) {
                        try {
                            sleep(times[finalJ]);
                            System.out.println(messages[finalJ]);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
        }
    }

    public void main(){
        Spam spam = new Spam(times, messages);
        spam.start();
        new Thread(){
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                String line = null;
                while (scanner.hasNextLine()){
                    line = scanner.nextLine();
                    if (line.isEmpty()){
                        spam.stop();
                    }
                }
            }
        }.start();

    }

    public void start() {
        for (int j = 0; j < countThread; j++){
            threadsArr[j].start();
        }
    }

    public void stop() {
        try {
            for (int j = 0; j < countThread; j++) {
                if (!threadsArr[j].isInterrupted()) {
                    threadsArr[j].interrupt();
                } else {
                    throw new InterruptedException();
                }
            }
        }catch (InterruptedException ex){
          return;
        }
    }
}
