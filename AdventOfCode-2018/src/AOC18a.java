import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.text.*;

class Main
{
    public static void main(String[] args) throws Exception
    {
        Main m = new Main();
        m.go();
    }

    int R = 50;
    int C = 50;
    // int R = 10;
    // int C = 10;

    char OPEN_GROUND = '.';
    char TREE = '|';
    char LUMBERYARD = '#';

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Scanner s = new Scanner(System.in);
        
        char[][] grid = new char[R][C];

        for(int r=0; r<R; r++)
        {
            grid[r] = br.readLine().toCharArray();
        }

        this.printGrid(grid);

    // Open ground: .
    // Trees: |
    // Lumberyard: #

        for(int min = 1; min <= 10; min++)
        {
            grid = this.getNextState(grid);
            // this.printGrid(grid);
        }

        int trees = 0;
        int lumberyards = 0;
        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                if(grid[r][c] == TREE)
                    trees++;
                else if(grid[r][c] == LUMBERYARD)
                    lumberyards++;
            }
        }

        System.out.println(trees + " * " + lumberyards + " = " + (trees*lumberyards));


    }

    private char[][] getNextState(char[][] grid)
    {
        // An open acre will become filled with trees if three or
        // more adjacent acres contained trees. Otherwise, nothing happens.

        // An acre filled with trees will become a lumberyard if three or
        // more adjacent acres were lumberyards. Otherwise, nothing happens.

        // An acre containing a lumberyard will remain a lumberyard if it
        // was adjacent to at least one other lumberyard and at least one acre containing trees.
        // Otherwise, it becomes open.
        char[][] newGrid = new char[R][C];
        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                if(grid[r][c] == OPEN_GROUND)
                {
                    int num = this.getNumAdjacent(grid, r, c, TREE);
                    if(num >= 3)
                        newGrid[r][c] = TREE;
                    else
                        newGrid[r][c] = grid[r][c];
                }
                else if(grid[r][c] == TREE)
                {
                    int num = this.getNumAdjacent(grid, r, c, LUMBERYARD);
                    if(num >= 3)
                        newGrid[r][c] = LUMBERYARD;
                    else
                        newGrid[r][c] = grid[r][c];
                }
                else // grid [r][c] == LUMBERYARD
                {
                    int numLumberyards = this.getNumAdjacent(grid, r, c, LUMBERYARD);
                    int numTrees = this.getNumAdjacent(grid, r, c, TREE);
                    if(numLumberyards >= 1 && numTrees >= 1)
                        newGrid[r][c] = LUMBERYARD;
                    else
                        newGrid[r][c] = OPEN_GROUND;
                }
            }
        }
        return newGrid;
    }

    private int getNumAdjacent(char[][] grid, int r, int c, char val)
    {
        int num = 0;
        if(r > 0 && grid[r-1][c] == val)
            num++;
        if(c > 0 && grid[r][c-1] == val)
            num++;
        if(r < R-1 && grid[r+1][c] == val)
            num++;
        if(c < C-1 && grid[r][c+1] == val)
            num++;
        if(r > 0 && c > 0 && grid[r-1][c-1] == val)
            num++;
        if(r > 0 && c < C-1 && grid[r-1][c+1] == val)
            num++;
        if(r < R-1 && c > 0 && grid[r+1][c-1] == val)
            num++;
        if(r < R-1 && c < C-1 && grid[r+1][c+1] == val)
            num++;
        return num;
    }

    private void printGrid(char[][] grid)
    {
        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
        System.out.println();
    }
}