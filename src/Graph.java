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

    public List<String> getMaxClique(){

        List<Integer> largestCompleteGraph = getMaxClique(0);

        for(int i = 1; i < nodeCount; i++){
            List<Integer> newMax = getMaxClique(i);
            /*if(largestCompleteGraph.compareTo(newMax) == 1){
                largestCompleteGraph = newMax;
            }*/
        }

        return null;
    }

    private List<Integer> getMaxClique(int nodeNumber){





        return null;
    }

    private List<Integer> getMaxClique(int nodeNumber, List<Integer> currentGraph){
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
