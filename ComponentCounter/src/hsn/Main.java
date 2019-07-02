package hsn;

import java.io.File;
import java.nio.file.Paths;

public class Main {

    /**
     * Contains general flow of Counter of homework 3 - Part1
     * @param args name of test file that is in src/testFiles
     */
    public static void main(String [] args){

        int [][] arr;  // the 2D array that keeps content of test file.
        Counter counter = new Counter();

        arr= counter.readFile(new File("src/hsn/test.txt"));

        counter.printArr(arr);

        System.out.println("White component number in the matrix above is "+counter.findWhiteComponents(arr)+'.');
    }
}
