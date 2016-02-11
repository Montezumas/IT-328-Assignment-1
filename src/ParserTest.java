
public class ParserTest {

	public static void main(String[] args) {
		//List<Graph> graphs = Parser.parseGraph("graphs16.txt");

		Parser.parseCNF("cnfs16.txt").get(0).toString();
	}
	
}
