import java.util.List;
import java.util.Set;

public class ParserTest {

	public static void main(String[] args) {
		List<Graph> graphs = Parser.parseGraph("graphs16.txt");

		System.out.println("Graph Count: "+graphs.size());

		Set<Integer> max = graphs.get(0).getMaxClique();
		printSet(max);




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

		System.out.print(" }" + " SIZE -> "+set.size() + " Clique Graph");

	}
	
}
