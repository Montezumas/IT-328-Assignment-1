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

		//testClique();
		testCNF();

	}

	private static void testClique(){
		List<Graph> graphs = Parser.parseGraph("graphs16.txt");
		System.out.println("* Max Cliques in graphs in graphsDense.txt\n" +
				"(|V|,|E|) Cliques (size, ms used)");
		for(int i = 0; i < graphs.size(); i++){
			long start = System.nanoTime();
			Set<Integer> maxClique = graphs.get(i).getMaxCliqueSet();
			long elapsed = System.nanoTime() - start;

			printCliqueSet(i+1,graphs.get(i),maxClique,elapsed/1000000);
		}

		System.out.println("\n");
		System.out.println("* Max Independent Sets in graphs in graphs16.txt : (reduced to K-Clique) *\n" +
				"(|V|,|E|) Independent Set (size, ms used)");


		for(int i = 0; i < graphs.size(); i++){
			long start = System.nanoTime();
			Set<Integer> maxClique = graphs.get(i).getIndependentSet();
			long elapsed = System.nanoTime() - start;

			printCliqueSet(i+1,graphs.get(i),maxClique,elapsed/1000000);
		}
	}

	private static void testCNF() {
		List<CNF> test = Parser.parseCNF("cnfs16.txt");

		for(int i = 0; i < test.size(); i++) {
			CNF cnf = test.get(i);

			Graph graph = cnf.reduceToGraph();

			long start = System.nanoTime();
			Set<Integer> clique = graph.getKClique(cnf.getFormulaSize());
			long elapsed = System.nanoTime() - start;
			elapsed /= 1000000;
			
			//System.out.println(clique.toString() + " " + clique.size());

			int k = clique.size();
			if(k < cnf.getFormulaSize()) {
				k = cnf.getFormulaSize();
			}

			System.out.print("3CNF No." + (i+1) + " [n=" + cnf.getNumVars() + " k=" + k + "] ");
			printCNFClique(cnf, clique);
			System.out.println(" (" + elapsed + " ms)");
		}

	}

	public static void printCNFClique(CNF cnf, Set<Integer> cnfClique) {
		if(cnfClique.size() < cnf.getFormulaSize()) {
			System.out.print("No " + cnf.getFormulaSize() + "-clique; no solution");
			return;
		}

		String line = "[";
		HashMap<Integer, Character> values = new HashMap<Integer, Character>();

		for(Integer i : cnfClique) {
			Integer literal = cnf.getLiteral(i);

			if(!values.containsKey(literal)) {
				if(literal < 0){
					values.put(literal*(-1), 'F');
				} else {
					values.put(literal, 'T');
				}
			}
		}

		int size = values.size()+1;
		
		for(int i = 1; i < size; i++) {
			if(!values.containsKey(i)) {
				size++;
			} else {
				line += "A"+ i + "=" + values.get(i) + " ";
			}
		}

		line += "]";


		System.out.print(line);
		
	}


	private static void printCliqueSet(int graphNum,Graph g,Set<Integer> set,long time) {
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
