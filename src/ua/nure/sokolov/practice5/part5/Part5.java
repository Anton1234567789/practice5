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
    Worker(int countThread){
        this.threads = new Thread[countThread];
        for (int j = 0; j < countThread; j++) {
            int finalJ = j;
            threads[j] = new Thread() {
                @Override
                public void run() {
                    synchronized (filePath) {
                        for (int j = 0; j < 10; j++) {
                            try {
                                if (finalJ == 0) {
                                    for (int k = 0; k < 20; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }else if (finalJ == 1){
                                    for (int k = 21; k < 41; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }else if (finalJ == 2){
                                    for (int k = 42; k < 63; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }
                                else if (finalJ == 3){
                                    for (int k = 84; k < 104; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }else if (finalJ == 4){
                                    for (int k = 105; k < 126; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }
                                else if (finalJ == 5){
                                    for (int k = 127; k < 148; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }
                                else if (finalJ == 6){
                                    for (int k = 169; k < 189; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }
                                else if (finalJ == 7){
                                    for (int k = 190; k < 211; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }
                                else if (finalJ == 8){
                                    for (int k = 212; k < 233; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
                                }
                                else if (finalJ == 9){
                                    for (int k = 234; k < 255; k++) {
                                        writeData(filePath, finalJ, k);
                                    }
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

    public  static void writeData(String filePath, Integer d, int seek) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(seek);
        String intStr = d.toString();
        file.write(intStr.getBytes());
        String lineSeparator = System.getProperty("line.separator", "\n");
        file.write(lineSeparator.getBytes());
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



