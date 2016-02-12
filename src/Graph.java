import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public ArrayList<Integer> getMaxClique(){

        /*List<Integer> largestCompleteGraph = getMaxClique(0);

        for(int i = 1; i < nodeCount; i++){
            List<Integer> newMax = getMaxClique(i);
            /*if(largestCompleteGraph.compareTo(newMax) == 1){
                largestCompleteGraph = newMax;

        }*/

        HashSet<Integer> q = new HashSet<>();

        for(int i = 0; i < nodeCount; i++){
            q.add(i);
        }

        HashSet<Integer> r = new HashSet<>();
        getMaxClique(r,q,new HashSet<>());

        System.out.println("SIZE: "+r.size());

        for(Integer i : r){
            System.out.println(i);
        }


        ArrayList<Integer> clique = new ArrayList<>();


        return clique;
        //return new ArrayList<Integer>(maxClique.toArray(Integer[] a));

        //return new List<>(getMaxClique(r,new HashSet<Integer>(),new HashSet<>());
    }

    private void getMaxClique(HashSet<Integer> r, HashSet<Integer> p, HashSet<Integer> x){

        if(p.size() == 0 && x.size() == 0){
            /*if(r.size() > 14) {
                System.out.println(r.size());
            }*/
            System.out.print("{ ");
            for(Integer i : r){
                System.out.print(i + ",");
            }
            System.out.print(" }\n");
            return;
        }

        HashSet<Integer> newP = new HashSet<>(p);


        for(Integer i : p){


            r.add(i);

            //HashSet<Integer> newR = new HashSet<>(r);
            //newR.add(i);
            HashSet<Integer> neighborSet = getNeighborSet(i);

            getMaxClique(r,intersect(newP,neighborSet),intersect(x,neighborSet));


            r.remove(i);
            newP.remove(i);
            x.add(i);
        }

    }

    private static HashSet<Integer> intersect(HashSet<Integer> a, HashSet<Integer> b){
        HashSet<Integer> temp = new HashSet<>(b);
        temp.retainAll(a);
        return temp;
    }

    private HashSet<Integer> getNeighborSet(int index){

        HashSet<Integer> returnMe = new HashSet<>();


        boolean[] table = edgeList[index];

        for(int i = 0; i < table.length; i++){
            if(table[i] && i != index){
                returnMe.add(i);
                //System.out.println("FOUND NEIGHBOR "+ i +" FOR: "+index);
            }
        }

        //System.out.println("/////////////////////");



        return returnMe;
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
