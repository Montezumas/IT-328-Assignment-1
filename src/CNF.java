import java.util.ArrayList;

public class CNF {
	
	private ArrayList<Integer[]> formula;
	
	private int literals;
	private int clauseCount;
	
	public CNF(String line, int literals) {
		// initialize the data
		this.formula = new ArrayList<Integer[]>();
		this.literals = literals;
		this.clauseCount = 0;

		boolean isNot = false;
		Integer[] tempClause = new Integer[literals];
		int tempClauseSize = 0;
		
		for(int i = 0; i < line.length(); i++) {
			if(isNot) {
				tempClause[tempClauseSize] = Integer.parseInt("-"+line.charAt(i));
				tempClauseSize++;
				isNot = false;
			} else if(line.charAt(i) == '-') {
				isNot = true;
			} else {
				tempClause[tempClauseSize] = Integer.parseInt(""+line.charAt(i));
				tempClauseSize++;
			}
			
			if(tempClauseSize == literals) {
				formula.add(tempClause);
				tempClause = new Integer[literals];
				tempClauseSize = 0;
				clauseCount++;
			}
			
		}
		
		System.out.println(literals + " literals each, " + clauseCount + " clauses total\n" + this.toString());
	}

	@Override
	public String toString() {
		String str = "[ ";
		
		for(int i = 0; i < formula.size(); i++) {
			Integer[] temp = formula.get(i);
			str += "{";
			
			for(int j = 0; j < temp.length; j++) {
				str += temp[j];
				if(j != temp.length - 1) {
					str += ",";
				}
			}
						
			str += "}";
			if(i != formula.size() - 1) {
				str += ",";
			}
			
		}
		
		str += "]";
		
		return str;
	}

}
