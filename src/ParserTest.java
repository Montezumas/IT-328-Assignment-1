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

		testCNF();

	}
	
	private static void testCNF() {
		List<CNF> test = Parser.parseCNF("cnfs16.txt");
		
		printCNFClique(test.get(1), test.get(1).reduceToGraph().getMaxClique());
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
	}

	private static void printSet(Set<Integer> set) {
		System.out.print("{ ");
		for(Integer i : set){
			System.out.print(i+", ");
		}

		System.out.print(" }" + " SIZE -> "+set.size() + " Clique Graph");

	}
	
}
