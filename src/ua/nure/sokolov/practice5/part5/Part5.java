package ua.nure.sokolov.practice5.part5;

import java.io.IOException;
import java.io.RandomAccessFile;



public class Part5 {

    public static void main(String[] args) {

     Worker.main(null);

    }
}
class Worker {
    Thread[] threads;
    String filePath = "part5.txt";
    int count;
    int countRow = 0;
    Worker(int countThread){
        this.threads = new Thread[countThread];
        for (int j = 0; j < countThread; j++) {
            threads[j] = new Thread() {
                @Override
                public void run() {
                    synchronized (filePath) {
                        for (int j = 0; j < 20; j++) {
                            try {
                                writeData(filePath, String.valueOf(count), 0);
                                count++;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        countRow++;
                    }
                }
            };
        }
    }

    public void startThread(){
        for (int j = 0; j < threads.length; j++){
            threads[j].start();
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker(10);
        worker.startThread();
    }

    public static void writeData(String filePath, String d, int seek) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(seek);

        file.write((d).getBytes());
        String lineSeparator = System.getProperty("line.separator", "\n");
        file.write(lineSeparator.getBytes());
        file.close();
    }

    private static byte[] readCharsFromFile(String filePath, int seek, int size) throws IOException {

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        file.seek(seek);
        byte[] bytes = new byte[size];
        file.read(bytes);
        file.close();
        return bytes;
    }

}



