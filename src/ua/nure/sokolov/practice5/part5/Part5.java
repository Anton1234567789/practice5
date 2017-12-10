package ua.nure.sokolov.practice5.part5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 {
    static Thread[] threads;
    static String filePath = "part5.txt";

    static class Worker{
        int count;
        Worker(int countThread){
            for (int j = 0; j < countThread; j++) {
                threads[j] = new Thread() {
                    @Override
                    public void run() {
                        int n = 0;
                            for (int j = 0; j < 20; j++) {
                                try {
                                    writeData(filePath, "0"+n,n);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            count++;
                            n++;
                    }
                };
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
        Thread t = new Thread(){
            @Override
            public void run() {
                synchronized ("d") {
                    int n = 0;
                    for (int j = 0; j < 20; j++) {
                        try {
                            RandomAccessFile file = new RandomAccessFile(filePath, "rw");

                            file.seek(0);
                            file.write(("0").getBytes());
                            String lineSeparator = System.getProperty("line.separator", "\n");
//                            String updatedString = "\r\n";
                            file.write(lineSeparator.getBytes());
                            file.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    n++;
                }
            }
        };
        t.start();


        Thread t1 = new Thread(){
            @Override
            public void run() {
                synchronized ("d") {
                    int n = 0;
                    for (int j = 0; j < 20; j++) {
                        try {
                            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
                            file.seek(4);
                            file.write(("1").getBytes());
                            String lineSeparator = System.getProperty("line.separator", "\n");
//                            String updatedString = "\r\n";
                            file.write(lineSeparator.getBytes());
                            file.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    n++;

                }
            }
        };

        t1.start();



        Thread t2 = new Thread(){
            @Override
            public void run() {
                synchronized ("d") {
                    int n = 0;
                    for (int j = 0; j < 20; j++) {
                        try {
                            RandomAccessFile file = new RandomAccessFile(filePath, "rw");
                            file.seek(6);

                            file.write(("2").getBytes());
                            String lineSeparator = System.getProperty("line.separator", "\n");
//                            String updatedString = "\r\n";
                            file.write(lineSeparator.getBytes());
                            file.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    n++;
                }
            }
        };

        t2.start();


    }

    private static void writeData(String filePath, String d, int seek) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");

        file.seek(seek);
        String data = String.valueOf(d);

        file.write(data.getBytes());
        String lineSeparator = System.getProperty("line.separator", "\n");

//
//        String updatedString = "\r\n";
//        file.writeBytes(updatedString);

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


