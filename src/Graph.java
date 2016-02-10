import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class Graph {


    private boolean edgeList[][];

    public Graph(List<String> lines){
        edgeList = new boolean[lines.size()][lines.get(0).length()];

        for(int row = 0; row < lines.size(); row++){
            for(int col = 0; col < lines.get(row).length(); col++){
                edgeList[row][col] = lines.get(row).charAt(col) == '1';
            }
        }
    }


    @Override
    public String toString() {

        String returnString = "";

        for(int i = 0; i < edgeList.length; i++){
            for(int j = 0; j < edgeList[i].length;j++){
                returnString += edgeList[i][j] ? "1" : "0";
            }
            returnString += "\n";
        }

        return returnString;
    }
}
