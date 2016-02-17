import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {


    private boolean edgeList[][];
    private int nodeCount;
    private int edgeCount;
    private boolean stopClique = false;

    public Graph(List<String> lines, int nodeCount){

        this.nodeCount = nodeCount;
        this.edgeCount = 0;

        edgeList = new boolean[lines.size()][lines.get(0).length()];

        for(int row = 0; row < lines.size(); row++){
            for(int col = 0; col < lines.get(row).length(); col++){
                edgeList[row][col] = lines.get(row).charAt(col) == '1';
            }
        }

        //count edges
        for(int i = 0; i < edgeList.length; i++){
            for(int j = i+1; j < edgeList[i].length; j++){
                if(edgeList[i][j]) {
                    this.edgeCount++;
                }
            }
        }

        //System.out.println(edgeCount);

    }

    public int getNodeCount(){
        return this.nodeCount;
    }

    public int getEdgeCount(){
        return this.edgeCount;
    }
    //Copy contructor
    public Graph(Graph g){
        this.edgeList = new boolean[g.edgeList.length][g.edgeList[0].length];

        for(int i = 0; i < edgeList.length; i++){
            for(int j = 0; j < edgeList[i].length; j++){
                this.edgeList[i][j] = g.edgeList[i][j];
            }
        }

        this.edgeCount = g.edgeCount;
        this.nodeCount = g.nodeCount;
    }

    public Set<Integer> getMaxCliqueSet(){

        HashSet<Integer> q = new HashSet<>();

        for(int i = 0; i < nodeCount; i++){
            q.add(i);
        }

        List<HashSet<Integer>> cliques = new ArrayList<>();
        getMaxClique(cliques,new HashSet<Integer>(),q,new HashSet<Integer>());


        Set<Integer> clique = new HashSet<>();

        for(int i = 0; i < cliques.size(); i++){
            if(i==0){
                clique = cliques.get(i);
            } else {
                if(cliques.get(i).size() > clique.size()){
                    clique = cliques.get(i);
                }
//                This just ensures there is more than 1 clique of size 12 to match Li's example
//                if(cliques.get(i).size() == 12){
//                    System.out.println("Got a clique of size 12!");
//                }
            }
        }


        return clique;
    }

    public Set<Integer> getKCliqueSet(int k){
        HashSet<Integer> q = new HashSet<>();

        for(int i = 0; i < nodeCount; i++){
            q.add(i);
        }


        stopClique = false;
        List<HashSet<Integer>> cliques = new ArrayList<>();
        getKClique(k,cliques,new HashSet<Integer>(),q,new HashSet<Integer>());



//        for(int i = 0; i < cliques.size(); i++){
//            if(i==0){
//                clique = cliques.get(i);
//            } else {
//                if(cliques.get(i).size() > clique.size()){
//                    clique = cliques.get(i);
//                }
////                This just ensures there is more than 1 clique of size 12 to match Li's example
////                if(cliques.get(i).size() == 12){
////                    System.out.println("Got a clique of size 12!");
////                }
//            }
//        }

        Set<Integer> hi = new HashSet<>(0);

        if(cliques.size() == 0 || cliques.get(0).size() == 0){
            return hi;
        } else {
            return cliques.get(0);
        }

        //return cliques.get(0) == null ? new HashSet<>() : cliques.get(0);

    }



    public Graph getIndepedentSetGraph(){
        return inverse();
    }
    public Set<Integer> getIndependentSet(){
        Graph inversed = inverse();

        return inversed.getMaxCliqueSet();

    }

    private Graph inverse(){
        Graph g = new Graph(this);


        for(int i = 0; i < g.edgeList.length; i++ ){
            for(int j = 0; j < g.edgeList[i].length;j++){
                if(i != j){
                    g.edgeList[i][j] = !g.edgeList[i][j];
                }
            }
        }

        return g;
    }

    private void getMaxClique(List<HashSet<Integer>> cliqueCatcher,HashSet<Integer> r, HashSet<Integer> p, HashSet<Integer> x){

        if(p.size() == 0 && x.size() == 0){
            cliqueCatcher.add(new HashSet<Integer>(r));
            return;
        }

        HashSet<Integer> newP = new HashSet<>(p);


        for(Integer i : p){

            r.add(i);
            HashSet<Integer> neighborSet = getNeighborSet(i);
            getMaxClique(cliqueCatcher,r,intersect(newP,neighborSet),intersect(x,neighborSet));

            r.remove(i);
            newP.remove(i);
            x.add(i);
        }
    }

    private void getKClique(int k){

        for(int i = 0; i < nodeCount; i++){
            HashSet<Integer> temp = new HashSet<Integer>();
            temp.add(i);
            getKClique(k, temp);
        }

    }

    private void getKClique(int k, Set<Integer> set){



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
