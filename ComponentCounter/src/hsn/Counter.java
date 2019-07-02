package hsn;

import java.io.*;
import java.nio.file.Files;

public class Counter {

    private static final int WHITE =1;
    private static final int BLACK =0;


    /**
     * Finds white(1) components in arr
     * @param arr 2D array that contains given test file
     * @return number of white components.
     */
    public int findWhiteComponents(int[][] arr){
        int whiteCompNum=0;

        for(int r=0; r<arr.length; ++r){
            for(int c=0; c<arr[r].length; ++c){
                if(arr[r][c]==WHITE){
                    revealAndDestroyIsland(arr, new Point2D(r,c));
                    ++whiteCompNum;
                }
            }
        }
        return whiteCompNum;
    }

    /**
     * Finds white neighbours from given position.  using stack.
     * @param arr the matrix that represent binary image
     * @param pos position of first white component occurrence
     */
    private void revealAndDestroyIsland(int[][] arr, Point2D pos){
        // every white neighbour component is called target
        MyStack<Point2D> targets= new MyStack<>();
        int r, c; // row and column of matrix

        targets.push(pos); // start point
        // all 8 neighbour is being controlled.
        while(!targets.empty()){

            r=targets.peak().getR();
            c=targets.pop().getC();
            arr[r][c]=BLACK; // the cell is marked as BLACK to not to encounter with it again

            if((r-1) >= 0  && arr[r-1][c]==WHITE)
                targets.push(new Point2D(r-1,c));

            if((r+1) <arr.length && arr[r+1][c]==WHITE)
                targets.push(new Point2D(r+1,c));

            if((c-1) >= 0  && arr[r][c-1]==WHITE)
                targets.push(new Point2D(r,c-1));

            if((c+1) <arr[r].length && arr[r][c+1]==WHITE)
                targets.push(new Point2D(r,c+1));

        }

    }

    /**
     * Prints the array that contains test file.
     * @param arr contains test file
     */
    public void printArr(int[][] arr){
        System.out.println("File content is being printed...");
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[i].length; ++j) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("File content is printed.");

    }

    /**
     * Reads the given file return 2D array
     * @param testFile      // indicate test file
     * @return int[][]      // content of array
     */
    public int[][] readFile(File testFile){
        int lineNum=0;  // will be used to init arr
        int [][]arr =new int [0][0];// at first we do not know content of file, so arr will be initialize later
        String []line;  // each time keeps one line of test file
        BufferedReader br=null; // used to read the file

        try{
            lineNum = (int)(Files.lines(testFile.toPath()).count());
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try{
            int i=0;
            String temp; // to control if the file is continue, I used an intermediate string
            br = new BufferedReader(new FileReader(testFile));

            temp = br.readLine();       // to be able to init arr I needed to read file out of while once.
            line= temp.split(" ");//to know column size it is done here

            // initialization of arr
            arr = new int [lineNum][line.length];

            while((temp=br.readLine())!=null) {
                for(int j=0; j<line.length; ++j){
                    arr[i][j]=Integer.parseInt(line[j]);
                }
                ++i;
                line=temp.split(" ");
            }
        } catch (IOException nf){
            nf.printStackTrace();
        }

        return arr;
    }

    class Point2D {
        private int r, c; // row and column that indicates 2D position.

        public Point2D(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point2D() {/* Intentionally empty*/ }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        public void setR(int r) {
            this.r = r;
        }

        public void setC(int c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return '{' +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }

        @Override
        public boolean equals(Object o) {

            if (this == o)  return true;
            if (!(o instanceof Point2D))  return false;

            Point2D point2D = (Point2D) o;
            return (getR() == point2D.getR() && getC() == point2D.getC());
        }

    }
}
