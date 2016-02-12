import java.util.List;

public class ParserTest {

	public static void main(String[] args) {
		List<Graph> graphs = Parser.parseGraph("graphs16.txt");

		System.out.println("Graph Count: "+graphs.size());

		graphs.get(3).getMaxClique();




	}
	
}
