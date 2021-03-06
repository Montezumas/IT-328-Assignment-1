import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

	public static List<Graph> parseGraph(String filename) {
        List<Graph> allGraphs = new ArrayList<Graph>();

		BufferedReader bufferedReader;
		try {
			FileReader reader = new FileReader(new File(filename));
			bufferedReader = new BufferedReader(reader);

			String strStartNumber;

			while ((strStartNumber = bufferedReader.readLine()) != null) {

				int rowCount = Integer.parseInt(strStartNumber);
				
				if(rowCount == 0)
					break;

                List<String> lines = new ArrayList<String>();
				for(int i = 0; i < rowCount; i++) {
					String line = bufferedReader.readLine().replaceAll("\\s+","");
                    lines.add(line);
				}

                allGraphs.add(new Graph(lines,rowCount));
			}

			reader.close();
			bufferedReader.close();

            return allGraphs;

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());

		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		}

        return null;
	}

	public static List<CNF> parseCNF(String filename) {
        List<CNF> allFormulas = new ArrayList<CNF>();

		BufferedReader bufferedReader;
		try {
			FileReader reader = new FileReader(new File(filename));
			bufferedReader = new BufferedReader(reader);

			String CNFLine;

			while ((CNFLine = bufferedReader.readLine()) != null) {				
				if(CNFLine.equals("0"))
					break;
				
				CNF tempCNF = new CNF(CNFLine);

				allFormulas.add(tempCNF);
			}

			reader.close();
			bufferedReader.close();

            return allFormulas;
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());

		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		}
		
		
		System.out.println("Error parsing CNF");
		return null;
	}

}
