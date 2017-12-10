package ua.nure.sokolov.practice5.part4;


import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Part4 {

    public static void main(String[] args) {
        Worker worker = new Worker(4,100);
        worker.performanceTaskWithoutThread();
        worker.performanceTaskInThread();

    }

}
class Worker implements Callable<Integer>{
    private GetInputFile getInputFile = new GetInputFile();
    private Thread[] threads;
    private int[][] matrix;
    private int countRow;
    private int countColumn;
    public Worker( int M, int N) {
        this.matrix = new int[M][N];
        this.countRow = M;
        this.countColumn = N;

        for (int j = 0; j < M; j++){
                WriterInFile.main(new String[]{String.valueOf(N), "part4.txt"});
        }

        for (int l = 0; l < M; l++){
            for (int u = 0; u < N; u++){
                if (l == 0) {
                    matrix[l][u] = WriterInFile.list1.get(u);
                }else if (l == 1) {
                    matrix[l][u] = WriterInFile.list2.get(u);
                }else if (l == 2){
                    matrix[l][u] = WriterInFile.list3.get(u);
                }else if (l == 3){
                    matrix[l][u] = WriterInFile.list4.get(u);
                }

            }
        }

    }

    public void startThread(){
        for (int j = 0; j < countRow; j++){
            threads[j].start();
        }
    }

    public void performanceTaskInThread() {

        this.threads = new Thread[countRow];
        int[] findMax = new int[5];

        for (int j = 0; j < countRow; j++){
            int finalJ = j;
            threads[j] = new Thread(){
                @Override
                public void run() {
                    synchronized (threads) {
                        long before = System.currentTimeMillis();
                        try {
                            System.out.println("Max element with used thread: " + findMaxNumberInThread());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        long after = System.currentTimeMillis() - before;
                        System.out.println("Time performance with used thread: " + after);
                    }
                }
            };
        }
        startThread();
    }



    public int findMaxNumberInThread() throws InterruptedException{
        int max = matrix[0][0];
        int[] maxNumberInList = new int[4];
        for (Integer k : WriterInFile.list1){
            if (k > max){
                Thread.sleep(1);
                max = k;
                maxNumberInList[0] = max;
            }
        }
        for (Integer k : WriterInFile.list2){
            if (k > max){
                Thread.sleep(1);
                max = k;
                maxNumberInList[1] = max;
            }
        }
        for (Integer k : WriterInFile.list3){
            if (k > max){
                Thread.sleep(1);
                max = k;
                maxNumberInList[2] = max;
            }
        }
        for (Integer k : WriterInFile.list4){
            if (k > max){
                Thread.sleep(1);
                max = k;
                maxNumberInList[3] = max;
            }
        }
        int maxNumberInMaxList = maxNumberInList[0];
        for (int j = 0; j < maxNumberInList.length; j++){
            if (maxNumberInList[j] > maxNumberInMaxList){
                Thread.sleep(1);
                maxNumberInMaxList = maxNumberInList[j];
            }
        }
        return maxNumberInMaxList;
    }

    public void performanceTaskWithoutThread() {
        long before = System.currentTimeMillis();
        try {
            System.out.println("Max element without used thread: " + call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long after = System.currentTimeMillis() - before;
        System.out.println("Time performance without used thread: " + after);
    }

    @Override
    public Integer call() throws Exception {
        int max = matrix[0][0];
        for (int k = 0; k < countRow; k++) {
            for (int t = 0; t < countColumn; t++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (matrix[k][t] > max) {
                    max = matrix[k][t];
                }
            }
        }
        return max;
    }
}

class WriterInFile{
    static ArrayList<Integer> list = new ArrayList<Integer>();
    static ArrayList<Integer> list1 = new ArrayList<Integer>();
    static ArrayList<Integer> list2 = new ArrayList<Integer>();
    static ArrayList<Integer> list3 = new ArrayList<Integer>();
    static ArrayList<Integer> list4 = new ArrayList<Integer>();
    public static void inputList(int n) {
        Random generator = new Random();
        for (int i =0; i < n; i++) {
            list.add(generator.nextInt(1000));
        }
    }

    public static void main(String[] args) {
        if(args.length != 0) {
            inputList(Integer.parseInt(args[0]));
            try {
                File f = new File(args[1]);
                PrintWriter out = new PrintWriter(new FileWriter(f));
                int count = 0;
                for (int element: list){
                    if (count >= 0 && count < 100){
                        list1.add(element);
                        out.print(element);
                        out.print(" ");
                        if (count == 99){
                            out.print(System.lineSeparator());
                        }
                    }else if (count >= 100 && count < 200){
                        list2.add(element);
                        out.print(element);
                        out.print(" ");
                        if (count == 199){
                            out.print(System.lineSeparator());
                        }
                    }else if (count >= 200 && count < 300){
                        list3.add(element);
                        out.print(element);
                        out.print(" ");
                        if (count == 299){
                            out.print(System.lineSeparator());
                        }
                    }else if (count >= 300 && count < 400){
                        list4.add(element);
                        out.print(element);
                        out.print(" ");
                        if (count == 399){
                            out.print(System.lineSeparator());
                        }
                    }
                    count++;
                }
                out.close();
            }catch (IOException e) { e.printStackTrace();}
        }
    }
}

class GetInputFile {

    public String getInput(String fileName){
        StringBuilder sb = new StringBuilder();
        try{
            Scanner scanner=new Scanner(new File(fileName),"UTF-8");
            while(scanner.hasNextLine()){
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            return sb.toString().trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
