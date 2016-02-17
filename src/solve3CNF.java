import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class solve3CNF {

	public static void main(String args[]) {
		List<CNF> temp = Parser.parseCNF(args[0]);
		
		for(int i = 0; i < temp.size(); i++) {
			CNF cnf = temp.get(i);

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
	
}
