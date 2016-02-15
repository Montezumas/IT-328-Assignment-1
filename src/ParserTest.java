import java.util.List;
import java.util.Set;

public class ParserTest {

	public static void main(String[] args) {
		List<Graph> graphs = Parser.parseGraph("graphs16.txt");

		System.out.println("Graph Count: "+graphs.size());

		Set<Integer> max = graphs.get(59).getMaxClique();
		printSet(max);




	}

	private static void printSet(Set<Integer> set){
		System.out.print("{ ");
		for(Integer i : set){
			System.out.print(i+", ");
		}

		System.out.print(" }" + " SIZE -> "+set.size() + " Clique Graph");

	}
	
}
