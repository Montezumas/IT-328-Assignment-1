import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParserTest {

	public static void main(String[] args) {
//		List<Graph> graphs = Parser.parseGraph("graphs16.txt");
//
//		System.out.println("Graph Count: "+graphs.size());
//
//		Set<Integer> max = graphs.get(3).getMaxClique();
//		printSet(max);

		testClique();
		//testCNF();

	}

	private static void testClique(){
		List<Graph> graphs = Parser.parseGraph("graphs16.txt");
		System.out.println("* Max Cliques in graphs in graphsDense.txt\n" +
				"(|V|,|E|) Cliques (size, ms used)\n");
		for(int i = 0; i < graphs.size(); i++){
			long start = System.nanoTime();
			Set<Integer> maxClique = graphs.get(i).getMaxCliqueSet();
			long elapsed = System.nanoTime() - start;

			printCliqueSet(i+1,graphs.get(i),maxClique,elapsed/1000000);

		}
	}

	private static void testCNF() {
		List<CNF> test = Parser.parseCNF("cnfs16.txt");
		
		printCNFClique(test.get(1), test.get(1).reduceToGraph().getMaxCliqueSet());
	}
	
	private static void printCNFClique(CNF cnf, Set<Integer> cnfClique) {
		int variables = cnf.getNumVars();
		Map<Integer, Boolean> allLiteralValues = new HashMap<Integer, Boolean>();
		
		for(Integer i : cnfClique) {
			Integer literal = cnf.getLiteral(i);
			System.out.println(literal);

		}
		
		if(allLiteralValues.size() != variables) {
			System.out.println("Error with number of variables");
		}
		
		for(int i = -5; i < allLiteralValues.size(); i++) {
			System.out.println(i + " = " + allLiteralValues.get(i));
		}

		//printSet(test.get(1).reduceToGraph().getMaxClique());
	}

	private static void printCliqueSet(int graphNum,Graph g,Set<Integer> set,long time) {
		System.out.print("\nG"+graphNum+" ("+g.getNodeCount()+", "+g.getEdgeCount()+")");
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

		System.out.print(" }" + " size="+set.size() + " " + time+"ms");

	}
	
}
