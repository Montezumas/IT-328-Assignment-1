import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Parser {

	public Graph[] parseGraph(String filename) {
		Graph[] arr = new Graph[60];

		BufferedReader bufferedReader;
		try {
			FileReader reader = new FileReader(new File(filename));
			bufferedReader = new BufferedReader(reader);

			String strStartNumber = null;
			int intStartNum = 0;

			Graph graph = new Graph();

			while ((strStartNumber = bufferedReader.readLine()) != null) {
				intStartNum = Integer.parseInt(strStartNumber);
				
				if(intStartNum == 0)
					break;
				
				for(int i = 0; i < intStartNum; i++) {
					graph.matrix.put(i+"", new HashMap<String, Boolean>());
					
					String adjLine = bufferedReader.readLine();
					System.out.println(adjLine);
					
					System.out.println(graph.matrix.containsKey(i+""));
					
					for(int j = 0; j < adjLine.length(); i++) {
						if(adjLine.charAt(j) == '1') {
							graph.matrix.get(i+"").put(j/2+"", new Boolean(true));
						} else {
							graph.matrix.get(i+"").put(j/2+"", new Boolean(false));
							//System.out.println(temp);
						}
					}
					
				}
				
				System.out.println("while");
			}

			reader.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
			return null;
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		}

		return arr;
	}

}
