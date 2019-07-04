package hsn;

public class Main {

    public static void main(String[] args) {
        ClockwiseIterator cwItr;

        // sample matrix
        int [][] matrix = { {1,2,3,4},
                            {5,6,7,8},
                            {9,10,11,12},
                            {13,14,15,16}
                            };

        // ClockwiseIterator must be initialize with a 2D array of integer
        // with this initialization 2D array is iterated recursively in clockwise
        // and a new data type is instantiate in this order.
        cwItr = new ClockwiseIterator(matrix);

        System.out.println("Clockwise iterator's hasNext(), next(), getCurrData() methods are being tested.");
        System.out.println("Numbers are being printed as clockwise...");
        while(cwItr.hasNext()){
            System.out.print(cwItr.next()+" ");
        }
        System.out.println("\n---------------------------------------------");
        System.out.println("Clockwise iterator's reset method is being tesed.");

        cwItr.reset();
        System.out.println("---------------------------------------------");
        // clockwise taken data is being printed using iterator's hasNext() and next() methods.
        System.out.println("Clockwise iterator's print method is tested.");
        System.out.println("Numbers are being printed as clockwise...");
        cwItr.print();

    }
}

