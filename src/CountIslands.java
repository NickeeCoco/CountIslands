import java.util.Arrays;

public class CountIslands {
    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1, 0},
                        {1, 1, 0, 1, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0}}; // 1

        int[][] grid2 = {{1, 1, 0, 0, 0},
                         {1, 1, 0, 0, 0},
                         {0, 0, 1, 0, 0},
                         {0, 0, 0, 1, 1}}; // 3

        int[][] grid3 = {{0, 1, 0, 1, 1},
                         {1, 1, 0, 1, 1},
                         {0, 0, 1, 0, 0},
                         {0, 0, 0, 1, 1}}; // 4

        int[][] grid4 = {{0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0},
                         {0, 0, 0, 0, 0}}; // 0

        int[][] grid5 = {{0, 1, 1, 1, 0, 1, 1},
                         {1, 1, 0, 0, 0, 0, 1},
                         {0, 1, 0, 0, 1, 0, 1},
                         {0, 1, 0, 0, 0, 0, 1},
                         {0, 1, 1, 1, 1, 1, 1}};

        System.out.println("grid 1: " + countIslands(grid)); // 1
        System.out.println("grid 2: " + countIslands(grid2)); // 3
        System.out.println("grid 3: " + countIslands(grid3)); // 4
        System.out.println("grid 4: " + countIslands(grid4)); // 0
        System.out.println("grid 5: " + countIslands(grid5)); // 2
    }

    public static int countIslands(int[][] grid) {
        int numberOfIslands = 0;
        int[][] map = new int[grid.length][grid[0].length];

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {

                if(grid[row][col] == 1) { // if the current square is land

                    if(numberOfIslands == 0)
                    { // if it's the first square of land we find
                        numberOfIslands++; // add an island
                        map[row][col] = numberOfIslands; // start the new island with the new number of islands
                    }
                    else
                    { // if it's not the first square of land we find
                        int topValue = checkTop(row, col, map);
                        int leftValue = checkLeft(row, col, map);

                        if(topValue > 0 && leftValue > 0)
                        { // if there's land to the top and to the left
                            if(topValue != leftValue) {
                                int minValue = topValue < leftValue ? topValue : leftValue;
                                int maxValue = topValue > leftValue ? topValue : leftValue;

                                map[row][col] = minValue;

                                correctMap(row, col, map, maxValue, minValue);
                                numberOfIslands--;
                            }
                            else {
                                map[row][col] = topValue; //
                            }
                        }
                        else if (topValue > 0)
                        { // if there's land at the top
                            map[row][col] = topValue; // assign the same value as the top plot of land to the current map position
                        }
                        else if (leftValue > 0) {
                            map[row][col] = leftValue;
                        }
                        else
                        {
                            numberOfIslands++;
                            map[row][col] = numberOfIslands;
                        }
                    }
                }

            }
        }

        System.out.println(Arrays.deepToString(map));

        return numberOfIslands;
    }

    public static int checkLeft(int row, int col, int[][] map) {
        if(col == 0) {
            return 0;
        }
        return map[row][col - 1];
    }

    public static int checkTop(int row, int col, int[][] map) {
        if(row == 0) {
            return 0;
        }
        return map[row - 1][col];
    }

    public static void correctMap(int row, int col, int[][] map, int valueToCorrect, int correctedValue) {
        for(int currentRow = 0; currentRow < row; currentRow++) {
            for(int currentCol = 0; currentCol < map[0].length; currentCol++) {
                correctValue(currentRow, currentCol, map, valueToCorrect, correctedValue);
            }
        }

        for(int currentCol = 0; currentCol < col; currentCol++) {
            correctValue(row, currentCol, map, valueToCorrect, correctedValue);
        }
    }

    public static void correctValue(int row, int col, int[][] map, int valueToCorrect, int correctedValue) {
        if(map[row][col] == valueToCorrect) {
            map[row][col] = correctedValue;
        }
        if(map[row][col] > valueToCorrect) {
            map[row][col]--;
        }
    }
}
