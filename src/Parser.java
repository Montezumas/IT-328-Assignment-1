import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

	public static List<Graph> parseGraph(String filename) {
        List<Graph> allGraphs = new ArrayList<>();

		BufferedReader bufferedReader;
		try {
			FileReader reader = new FileReader(new File(filename));
			bufferedReader = new BufferedReader(reader);

			String strStartNumber;

			while ((strStartNumber = bufferedReader.readLine()) != null) {

				int rowCount = Integer.parseInt(strStartNumber);
				
				if(rowCount == 0)
					break;

                List<String> lines = new ArrayList<>();
				for(int i = 0; i < rowCount; i++) {
					String line = bufferedReader.readLine().replaceAll("\\s+","");
                    lines.add(line);
				}

                allGraphs.add(new Graph(lines,rowCount));
			}

			reader.close();
			bufferedReader.close();

            //System.out.println(allGraphs.get(2).toString());

            return allGraphs;

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());

		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		}

        return null;
	}
	
	public static List<CNF> parseCNF(String filename) {
        List<CNF> allFormulas = new ArrayList<>();

		BufferedReader bufferedReader;
		try {
			FileReader reader = new FileReader(new File(filename));
			bufferedReader = new BufferedReader(reader);

			String lineString;

			while ((lineString = bufferedReader.readLine()) != null) {
				String literals = "";
				
				for(int i = 0; i < lineString.length(); i++) {
					if(lineString.charAt(i) == ' ') {
						lineString = lineString.substring(i);
						break;
					}
					
					literals += lineString.charAt(i);
				}
				
				lineString = lineString.replaceAll("\\s+", "");
				//System.out.println(lineString);
				
				if(lineString.equals("0"))
					break;
				
				CNF tempCNF = new CNF(lineString, Integer.parseInt(literals));

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
