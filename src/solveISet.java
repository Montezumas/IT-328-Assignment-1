import java.util.List;
import java.util.Set;

/**
 * Created by jonathanmitchell on 2/16/2016.
 */
public class solveISet {

    public static void main(String[] args){
        if(args == null || args.length == 0 || args[0] == null || args[0].isEmpty()){
            System.out.println("Error, please provide a valid file name!");
            return;
        }

        String filename = args[0];

        List<Graph> graphs = Parser.parseGraph(filename);

        if(graphs == null){
            System.out.println("There was an error parsing the file. Ensure you have correct file name");
            return;
        }

        System.out.println("* Max Independent Sets in graphs in graphs16.txt : (reduced to K-Clique) *\n" +
                "(|V|,|E|) Independent Set (size, ms used)");


        for(int i = 0; i < graphs.size(); i++){
            long start = System.nanoTime();
            Set<Integer> maxClique = graphs.get(i).getIndependentSet();
            long elapsed = System.nanoTime() - start;

            printCliqueSet(i+1,graphs.get(i),maxClique,elapsed/1000000);
        }
    }

    private static void printCliqueSet(int graphNum, Graph g, Set<Integer> set, long time) {
        System.out.print("G"+graphNum+" ("+g.getNodeCount()+", "+g.getEdgeCount()+")");
        System.out.print("{ ");
        boolean first = true;
        for(Integer i : set){
            if(first){
                first = false;
            } else {
                System.out.print(", ");
            }
            System.out.print(i);
        }

        System.out.print(" }" + " size="+set.size() + " " + time+"ms\n");

    }
}
