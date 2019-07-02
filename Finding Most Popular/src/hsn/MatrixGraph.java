package hsn;

public class MatrixGraph extends Graph {

    private boolean [][] matrix;

    public MatrixGraph(int numV, int [][] relations) {
        super(numV, true);

        // number of vertex starts from 1. To prevent confusion I allocate one row and column extra.
        matrix = new boolean[numV+1][numV+1];

        for (int[] relation : relations) {
            insert(new Edge(relation[0], relation[1]));
        }

    }

    public void printAdjMatrix(){
        for(boolean [] a :matrix){
            for(boolean b:a){
                if(b)
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }

    private void insert(Edge e){
        // ArrayIndexOutOfBounds exception is prevented.
        if(e.source > matrix.length || e.dest > matrix[0].length)
            return;

        matrix[e.source][e.dest] = true;
    }

    private boolean isEdge(int source, int dest){
        return (matrix[source][dest]);
    }

    /**
     * Find number of most popular people
     * @return number of most popular people which are considered popular by every other people.
     */
    int findPopulars(){
        // number of vertex starts from 1. To prevent confusion I allocate one row and column extra.
        boolean [][] visited = new boolean[getNumV()+1][getNumV()+1];

        // visited array is being filled with deepFirstSearch.
        for(int i=1; i<=getNumV(); ++i)
            depthFirstSrc(visited[i], i);

        // for every vertex visited[][] array is controlled and number of most popular people is determined.
        int countPopular =0;
        for(int i=1; i<=getNumV(); ++i){
            boolean popular = true;
            // to count as most popular everyone should think he/she is popular.
            for(int j=1; j<=getNumV(); ++j){
                if(!visited[j][i] && j!=i){
                    popular = false;
                }
            }
            if(popular){
                ++countPopular;
            }
        }
        return countPopular;
    }

    /**
     * Performs depth first search over matrix[][] data field, then mark the results to "visited" array.
     * @param visited keeps if the vertices visited or not
     * @param startV starting vertice
     */
    private void depthFirstSrc(boolean []visited, int startV){
        for(int i=1; i<=getNumV(); ++i){
            if(isEdge(startV,i) && (!visited[i])){
                visited[i]=true;
                if(i!=startV)
                    depthFirstSrc(visited, i);
            }
        }
    }

    class Edge{
        // data fields
        private int dest;
        private int source;

        // constructors
        Edge(int source, int dest) {
            this.dest = dest;
            this.source = source;
        }

        // getter and setters
        public int getDest() {
            return dest;
        }

        public void setDest(int dest) {
            this.dest = dest;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        @Override
        public boolean equals(Object obj) {

            if(this == obj)
                return true;

            if(!(obj instanceof Edge))
                return false;

            Edge e2 = (Edge)obj;
            return (e2.dest == dest && e2.source==source);
        }
    }


}
