package ua.nure.sokolov.practice5.part5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 {
    static Thread[] threads;
    static String filePath = "part5.txt";

    static class Worker{
        int count;
        Worker(int countThread){
            for (int j = 0; j < countThread; j++){
                threads[j] = new Thread(){
                        @Override
                        public void run() {
                            for (int j = 0; j < 20; j++){
                                synchronized (threads){
                                    try {
                                        writeData(filePath, count, 0);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                count++;
                            }
                        }
                    };
                threads[j].start();
                }
            }
        }

    public static void main(String[] args) {
//        try {
//            writeData(filePath, 1111111, 0);
//            System.out.println(new String(readCharsFromFile(filePath, 0, 7)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Worker worker = new Worker(10);

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


