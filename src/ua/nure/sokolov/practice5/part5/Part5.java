package ua.nure.sokolov.practice5.part5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 {
    static Thread[] threads;
    static String filePath = "part5.txt";

    public static void main(String[] args) {

        Worker worker = new Worker(10);
//        try {
//            readCharsFromFile(filePath, 0, filePath.length());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    static class Worker{
        int count;
        int countRow = 0;
        Worker(int countThread){
            for (int j = 0; j < countThread; j++) {
                new Thread() {
                    @Override
                    public void run() {
                        synchronized (filePath) {
                        for (int j = 0; j < 20; j++) {
                                try {
                                    writeData(filePath, count, countRow);
                                    count++;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            countRow++;
                        }
                    }
                }.start();
            }

        }
    }

    private static void writeData(String filePath, int d, int seek) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");

        file.seek(seek);
        String data = String.valueOf(d);

        file.write(data.getBytes());
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


