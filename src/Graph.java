import java.util.List;

public class Graph implements Comparable<Graph>{


    private boolean edgeList[][];
    private int nodeCount;

    public Graph(List<String> lines, int nodeCount){


        this.nodeCount = nodeCount;
        edgeList = new boolean[lines.size()][lines.get(0).length()];

        for(int row = 0; row < lines.size(); row++){
            for(int col = 0; col < lines.get(row).length(); col++){
                edgeList[row][col] = lines.get(row).charAt(col) == '1';
            }
        }
    }

    public Graph getMaxClique(){

        boolean firstCall = true;
        Graph largestCompleteGraph = null;

        largestCompleteGraph = getMaxClique(0);

        for(int i = 1; i < nodeCount; i++){
            Graph newMax = getMaxClique(i);
            if(largestCompleteGraph.compareTo(newMax) == 1){
                largestCompleteGraph = newMax;
            }
        }

        return largestCompleteGraph;
    }

    private Graph getMaxClique(int nodeNumber){

        return null;
    }


    @Override
    public int compareTo(Graph o) {
        return 0;
    }

    @Override
    public String toString() {

        String returnString = "";

        for(int i = 0; i < edgeList.length; i++){
            for(int j = 0; j < edgeList[i].length;j++){
                returnString += edgeList[i][j] ? "1" : "0";
            }
            returnString += "\n";
        }

        return returnString;
    }
}
