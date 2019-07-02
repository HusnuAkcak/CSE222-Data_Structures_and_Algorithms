package hsn;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("You need to give a file name that contains a graph to execute this program.");
            return;
        }


        MatrixGraph mg = readFile(args[0]);
        System.out.println(mg.findPopulars());

    }


    public static MatrixGraph readFile(String fileName){
            int numV, numRelation; // num of vertices and num of relations
            int [][] relations; // relations between edges

            try{
                Scanner sc = new Scanner(new File(fileName));

                numV = sc.nextInt(); // first int is numV
                numRelation = sc.nextInt(); //second int is num of relations

                int i=0; // index to fill relation array
                relations = new int[numRelation][2];

                // relation array is filled
                while(i<numRelation){
                    relations[i][0]=sc.nextInt();
                    relations[i][1]=sc.nextInt();
                    ++i;
                }

                // matrix graph is returned.
                return new MatrixGraph(numV, relations);
            }
            catch (IOException e){
                System.out.println("IO exception is occurred...");
                exit(1);
            }

            return null;
        }

}