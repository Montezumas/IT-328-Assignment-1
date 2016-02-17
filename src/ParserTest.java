import java.util.HashMap;
import java.util.HashSet;
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

		testCNF();

	}

	private static void testCNF() {
		List<CNF> test = Parser.parseCNF("cnfs16.txt");
				
		for(int i = 0; i < test.size(); i++) {
			CNF cnf = test.get(i);
			
			Graph graph = cnf.reduceToGraph();
			
			long start = System.nanoTime();
			Set<Integer> clique = graph.getMaxCliqueSet();
			long elapsed = System.nanoTime() - start;
			elapsed /= 1000000;
			
			int k = clique.size();
			if(k < cnf.getFormulaSize()) {
				k = cnf.getFormulaSize();
			}
			
			System.out.print("3CNF No." + (i+1) + " [n=" + cnf.getNumVars() + " k=" + k + "] ");
			printCNFClique(cnf, clique);
			System.out.println(" (" + elapsed + " ms)");
		}
		
	}
	
	private static void printCNFClique(CNF cnf, Set<Integer> cnfClique) {
		if(cnfClique.size() < cnf.getFormulaSize()) {
			System.out.print("No " + cnf.getFormulaSize() + "-clique; no solution");
			return;
		}
		
		int variables = cnf.getNumVars();
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
		
		for(int i = 1; i < values.size()+1; i++) {
			line += "A"+ i + "=" + values.get(i) + " ";
		}
		
		line += "]";
		

		System.out.print(line);
	}

	private static void printSet(Set<Integer> set) {
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

		System.out.print(" }" + " SIZE -> "+set.size() + " Clique Graph");

	}
	
}
