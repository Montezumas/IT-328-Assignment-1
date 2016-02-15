import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class representing a 3CNF
 * 
 * @author Robby
 *
 */
public class CNF {

	// 3CNF formula
	private ArrayList<Integer[]> formula;

	// number of variables in the formula
	private int numVars;

	// number of clauses in formula
	private int clauseCount;

	/**
	 * This constructor will take in a line representing a CNF and parse it into
	 * the necessary data structure
	 * 
	 * @param line
	 * @param nv
	 */
	public CNF(String line) {
		// initialize the data
		this.formula = new ArrayList<Integer[]>();
		this.clauseCount = 0;
		String numVarsString = "";
		for (int i = 0; i < line.length(); i++) {
			// getting entire number representing numVars
			if (line.charAt(i) == ' ') {
				line = line.substring(i + 1);
				break;
			}

			numVarsString += line.charAt(i);
		}
		numVars = Integer.parseInt(numVarsString);

		// temporary data used to add items to the ArrayList
		Integer[] tempClause = new Integer[3];
		int tempClauseSize = 0;

		// a single literal that will be built
		String literal = "";

		// getting 3CNF formula
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == ' ' && line.charAt(i - 1) != ' ') {
				// literal ready to be added
				tempClause[tempClauseSize] = Integer.parseInt(literal);
				tempClauseSize++;
				literal = "";

				if (tempClauseSize == 3) {
					formula.add(tempClause);
					tempClause = new Integer[3];
					tempClauseSize = 0;
					clauseCount++;
				}
			} else {
				// need to construct whole literal
				literal += line.charAt(i);
			}
		}
	}
	
	public Graph reduceToGraph() {
		List<String> matrix = new ArrayList<String>();
		int this3 = 0;
		
		String emptyLine = "";
		
		for(int i = 0; i < formula.size(); i++) {
			Integer[] temp = formula.get(i);
			this3 = i*3;

			for(int j = 0; j < temp.length; j++) {
				String line = emptyLine;
				for(int k = 0; k < formula.size() * 3; k++) {
					if(k == (i*3) + j) {
						// same node
						line += "1";
					} else if(k == (this3) || k == (this3 + 1) || k == (this3 + 2)) {
						// other nodes in this clause
						line += "0";
					} else {
						// some other node
						int l = k / 3;
						int m = k % 3;
						
						//System.out.println(l+" "+m);
						
						if(formula.get(l)[m] != (temp[j] * -1)) {
							line += "1";
						} else {
							line += "0";
						}
					}
				}
				matrix.add(line);
			}
		}
		
		
		Graph graph = new Graph(matrix, 0, '1');
		
		return graph;
	}

	@Override
	public String toString() {
		String str = "{ ";

		for (int i = 0; i < formula.size(); i++) {
			Integer[] temp = formula.get(i);
			str += "[";

			for (int j = 0; j < temp.length; j++) {
				str += temp[j];
				if (j != temp.length - 1) {
					str += ",";
				}
			}

			str += "]";
			if (i != formula.size() - 1) {
				str += ", ";
			}

		}

		str += " }";

		return str;
	}

}
