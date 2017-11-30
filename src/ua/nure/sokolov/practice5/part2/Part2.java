package ua.nure.sokolov.practice5.part2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class Part2 {


    public static void main(String[] args) {
        final byte[] bytes = System.lineSeparator().getBytes();

        InputStream is = new InputStream() {
            int cur;
            @Override
            public int read() throws IOException {
                if (cur == 0){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
                return cur < bytes.length ? bytes[cur++] : 1;
            }
        };
        System.setIn(is);
        Spam.main(null);
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

    public static void main(String[] args) {
        Spam spam = new Spam(new int[]{333,555}, new String[]{"AAAA", "bbb0"});
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
