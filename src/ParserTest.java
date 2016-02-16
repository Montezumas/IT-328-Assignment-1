import java.util.List;
import java.util.Set;

public class ParserTest {

	public static void main(String[] args) {
		List<Graph> graphs = Parser.parseGraph("graphs16.txt");

		System.out.println("Graph Count: "+graphs.size());

		//for(int i = 0; i < graphs.size(); i++){
			//Set<Integer> max = graphs.get(59).getIndependentSet();
			//printSet(max);
		//}

		Graph isg = graphs.get(2).getIndepedentSetGraph();
		Set<Integer> max = isg.getMaxCliqueSet();
		printSet(max);
		System.out.println(isg.getEdgeCount());



		//testCNF();

	}

	private static void testCNF() {
		List<CNF> test = Parser.parseCNF("cnfs16.txt");

		printSet(test.get(1).reduceToGraph().getMaxCliqueSet());
	}

	private static void printSet(Set<Integer> set){
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

		System.out.print(" }" + " SIZE -> "+set.size() + " Clique Graph\n");

	}
	
}
