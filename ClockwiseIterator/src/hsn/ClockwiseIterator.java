package hsn;

import java.util.Iterator;

public class ClockwiseIterator implements Iterator<Integer> {
    /** The matrix that is being iterated. */
    private Integer [] arr;
    /** Current position of iterator */
    int index =0;
    /** Enumerated types for direction */
    enum Direction {RIGHT, DOWN, LEFT, UP }

    /**
     * Takes a matrix and initialize arr field of the class. Then iterate matrix recursively.
     * @param matrix 2D array of non negative integers
     */
    public ClockwiseIterator(int [][] matrix){
        this.arr = new Integer [matrix.length * matrix[0].length];
        itrClockwise(matrix, 0,0, 0, Direction.RIGHT);
    }

    /**
     * returns the data that is seen lastly by iterator
     * @return current Integer on the list
     */
    public Integer getCurrData(){
        return arr[index];
    }

    @Override
    public boolean hasNext() {
        return !( (arr.length-1) == index );
    }

    /**
     * Returns next element if it is exits otherwise returns null.
     * @return next element of iterator
     */
    @Override
    public Integer next() {
        if(hasNext())
            return arr[++index];

        return null;
    }

    /**
     * Prints all contained data by using hasNext() and next() methods.
     */
    public void print(){
        while(hasNext()) {
            System.out.print(getCurrData() + " ");
            next();
        }
        System.out.println(getCurrData());
        index = 0; // index is reset.
    }

    /**
     * Reset iterator position as 0.
     */
    public void reset() {
        index = 0;
    }
    /**
     * Iterate give matrix in clockwise while doing that copy all data from matrix to
     * arr field as clockwise.
     * @param matrix 2D array of non negative integers
     */
    private void itrClockwise(int [][] matrix, int row, int col, int count, Direction dir) {
        arr[count] = matrix[row][col];
        ++count;
        matrix[row][col]=(-1);

        // Firstly ongoing side is being controlled, then it is tried to iterate clockwise
        if(dir == Direction.RIGHT) {
            // right side is being controlled.
            if((col+1) < matrix[row].length && matrix[row][col+1]!=(-1)) {
                itrClockwise(matrix, row, col + 1, count, Direction.RIGHT);
            }
            // down side is being controlled.
            else if((row+1) < matrix.length && matrix[row+1][col]!=(-1)) {
                itrClockwise(matrix, row + 1, col, count, Direction.DOWN);
            }
        }
        else if (dir == Direction.DOWN){
            // down side is being controlled.
            if((row+1) < matrix.length && matrix[row+1][col]!=(-1)) {
                itrClockwise(matrix, row + 1, col, count, Direction.DOWN);
            }
            // left side is being controlled.
            else if((col-1)>=0 && matrix[row][col-1]!=(-1)) {
                itrClockwise(matrix, row, col - 1, count, Direction.LEFT);
            }
        }
        else if (dir == Direction.LEFT) {
            // left side is being controlled.
            if((col-1)>=0 && matrix[row][col-1]!=(-1)) {
                itrClockwise(matrix, row, col - 1, count, Direction.LEFT);
            }
            // upper side is being controlled.
            else if((row-1)>=0 && matrix[row-1][col]!=(-1)) {
                itrClockwise(matrix, row - 1, col, count, Direction.UP);
            }
        }
        else if (dir == Direction.UP){
            // upper side is being controlled.
            if((row-1)>=0 && matrix[row-1][col]!=(-1)) {
                itrClockwise(matrix, row - 1, col, count, Direction.UP);
            }
            // right side is being controlled.
            else if((col+1) < matrix[row].length && matrix[row][col+1]!=(-1)) {
                itrClockwise(matrix, row, col + 1, count, Direction.RIGHT);
            }
        }
    }
}