package ua.nure.sokolov.practice5.part5;

import java.io.IOException;
import java.io.RandomAccessFile;



public class Part5 {

    public static void main(String[] args) {

     Worker.main(null);
        try {
            Worker.readCharsFromFile("part5.txt",0, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
class Worker {
    Thread[] threads;
    String filePath = "part5.txt";
    int count;
    Worker(int countThread){
        this.threads = new Thread[countThread];
        for (int j = 0; j < countThread; j++) {
            int finalJ = j;
            threads[j] = new Thread() {
                @Override
                public void run() {
                    synchronized (filePath) {
                        for (int j = 0; j < 20; j++) {
                            try {
                                if (finalJ == 0) {
                                    writeData(filePath, finalJ, 0);
                                }else if (finalJ == 1){
                                    writeData(filePath, finalJ, 21);
                                }else if (finalJ == 2){
                                    writeData(filePath, finalJ, 41);
                                }
                                else if (finalJ == 3){
                                    writeData(filePath, finalJ, 61);
                                }else if (finalJ == 4){
                                    writeData(filePath, finalJ, 81);
                                }
                                else if (finalJ == 5){
                                    writeData(filePath, finalJ, 101);
                                }
                                else if (finalJ == 6){
                                    writeData(filePath, finalJ, 121);
                                }
                                else if (finalJ == 7){
                                    writeData(filePath, finalJ, 141);
                                }
                                else if (finalJ == 8){
                                    writeData(filePath, finalJ, 161);
                                }
                                else if (finalJ == 9){
                                    writeData(filePath, finalJ, 181);
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
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

    public synchronized static void writeData(String filePath, Integer d, int seek) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(seek);
        String intStr = d.toString();
        file.write(intStr.getBytes());
//        String lineSeparator = System.getProperty("line.separator", "\n");
//        file.write(lineSeparator.getBytes());
        file.close();
    }

    public static byte[] readCharsFromFile(String filePath, int seek, int size) throws IOException {

        RandomAccessFile file = new RandomAccessFile(filePath, "r");
        file.seek(seek);
        byte[] bytes = new byte[size];
        file.read(bytes);
        file.close();
        return bytes;
    }

}



