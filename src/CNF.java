import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a 3CNF
 * 
 * @author Robby
 *
 */
public class CNF {

	// 3CNF formula
	// Length of this give the number of clauses
	private ArrayList<Integer[]> formula;

	// number of variables in the formula
	private int numVars;

	/**
	 * This constructor will take in a line representing a CNF and parse it into
	 * the necessary data structure
	 * 
	 * @param line
	 */
	public CNF(String line) {
		// initialize the data
		this.formula = new ArrayList<Integer[]>();
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
				}
			} else {
				// need to construct whole literal
				literal += line.charAt(i);
			}
		}
	}
	
	/**
	 * Reduce this 3CNF to a graph
	 * This allows for a max clique to be searched in the graph
	 * which shows 3CNF <p k-clique
	 * 
	 * @return graph representation of 3CNF
	 */
	public Graph reduceToGraph() {
		/*
		 * This matrix will have size formula.size() * 3 so that every literal in
		 * each clause in formula has its own unique index. Some indexes will represent the 
		 * same literal.
		 * 
		 */
		List<String> matrix = new ArrayList<String>();
		int this3 = 0;
				
		for(int i = 0; i < formula.size(); i++) {
			Integer[] temp = formula.get(i);
			this3 = i*3;

			for(int j = 0; j < temp.length; j++) {
				String line = "";
				for(int k = 0; k < formula.size() * 3; k++) {
					if(k == (i*3) + j) {
						// same node
						line += "1";
					} else if(k == (this3) || k == (this3 + 1) || k == (this3 + 2)) {
						// other nodes in this 3clause
						line += "0";
					} else {
						// some other node
						int l = k / 3;
						int m = k % 3;
												
						if(formula.get(l)[m] != (temp[j] * -1)) {
							// this is not the negation of temp[j]
							line += "1";
						} else {
							// this is negation of temp[j]
							line += "0";
						}
					}
				}
				matrix.add(line);
			}
		}
		
		if(matrix.size() != (formula.size() * 3) || matrix.get(0).length() != matrix.size()) {
			System.out.println("Matrix size error");
		}
		
		Graph graph = new Graph(matrix, matrix.size());
		
		return graph;
	}
	
	public int getNumVars() {
		return numVars;
	}
	
	public Integer getLiteral(int index) {
		return new Integer(formula.get(index/3)[index%3]);
	}
	
	public int getFormulaSize() {
		return formula.size();
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
