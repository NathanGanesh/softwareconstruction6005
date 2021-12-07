package warmup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        solution(new char[][]{{'.', '.', '.', '1', '4', '.', '.', '2', '.'}
                , {'.', '.', '6', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, {
                '.', '.', '1', '.', '.', '.', '.', '.', '.'
        }
                , {
                '.', '6', '7', '.', '.', '.', '.', '.', '9'
        }, {
                '.', '.', '.', '.', '.', '.', '8', '1', '.'
        }, {'.', '3', '.', '.', '.', '.', '.', '.', '6'}, {'.', '.', '.', '.', '.', '7', '.', '.', '.'}, {'.', '.', '.', '5', '.', '.', '.', '7', '.'}});

//        solution(new char[][]{{'.', '.', '.', '.', '2', '.', '.', '9', '.'}, {'.', '.', '.', '.', '6', '.', '.', '.', '.'} ,{'7', '1', '.', '.', '7', '5', '.', '.', '.'},{'.', '7', '.', '.', '.', '.', '.', '.', '.'}, {
//                '.', '.', '.', '.', '8', '3', '.', '.', '.'
//        }, {'.', '.', '8', '.', '.', '7', '.', '6', '.'}, {'.', '.', '.', '.', '.', '2', '.', '.', '.'}, {
//                '.', '1', '.', '2', '.', '.', '.', '.', '.'
//        }, {'.', '2', '.', '.', '3', '.', '.', '.', '.'}});
    }

    static boolean solution(char[][] grid) {
        boolean valid = false;
        System.out.println(Arrays.deepToString(grid));
        Set<Character> columns;
        Set<Character> row;
        //Row Index dan Column index
        HashMap<HashMap<Integer, Integer>, Set<Character>> gridHashMap = new HashMap<>();
        HashMap<Integer, Integer> currentRowAndColumn;
        for (int i = 0; i < grid.length; i++) {
            columns = new HashSet<>();
            row = new HashSet<>();
            for (int i1 = 0; i1 < grid.length; i1++) {
                if (!columns.contains(grid[i][i1])) {
                    columns.add(grid[i][i1]);
                }else{
                    valid = true;
                    break;
                }
                if (!row.contains(grid[i1][i])){
                    row.add(grid[i1][i]);
                }else {
                    valid = true;
                    break;
                }

//                int rowIndex = i / 3;
//                int columnIndex = i1/3;
//                currentRowAndColumn= new HashMap<Integer, Integer>(rowIndex, columnIndex);
//                currentRowAndColumn.put(rowIndex, columnIndex);
//                if (gridHashMap.get(currentRowAndColumn).contains()){
//
//                }

            }
        }
        return valid;

    }
}
