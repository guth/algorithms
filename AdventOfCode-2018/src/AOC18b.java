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

        // Set<State> states = new HashSet<State>();
        Map<String, Integer> states = new HashMap<String, Integer>();
        states.put(this.getString(grid), 0);


    // Open ground: .
    // Trees: |
    // Lumberyard: #

        // State startingState = new State(this.getString(grid), 0);
        int numRepeats = -1;
        int firstRepeat = -1;
        int firstRepeatFound = -1;
        for(int min = 1; min <= 1000000000; min++)
        {
            grid = this.getNextState(grid);

            String gridString = this.getString(grid);
            // State state = new State(gridString, min);

            if(states.containsKey(gridString))
            {
                int old = states.get(gridString);
                System.out.println("Repeats at " + old + " and " + min);
                numRepeats = (min - old);
                firstRepeat = old;
                firstRepeatFound = min;
                break;
            }
            else
            {
                states.put(gridString, min);
            }
            // this.printGrid(grid);
        }

        int offset = (1000000000 - firstRepeat) % numRepeats;
        int keyToFind = firstRepeat + offset;
        String answerState = null;
        for(String key : states.keySet())
        {
            if(states.get(key) == keyToFind)
            {
                answerState = key;
                // answerState = states.get(key);
            }
        }

        int numTrees = 0;
        int numLumberyards = 0;
        for(char c : answerState.toCharArray())
        {
            if(c == TREE)
                numTrees++;
            if(c == LUMBERYARD)
                numLumberyards++;
        }
        System.out.println(numTrees + " * " + numLumberyards + " = " + (numTrees * numLumberyards));

    }

    public class State
    {
        public String value;
        public int minute;

        public State(String value, int minute)
        {
            this.value = value;
            this.minute = minute;
        }

        @Override
        public int hashCode()
        {
            return this.value.hashCode();
        }

        @Override
        public boolean equals(Object o)
        {
            if(o == null)
                return false;
            if(!(o instanceof State))
                return false;

            State other =(State)o;
            if(other.value == null && this.value != null)
                return false;
            if(other.value != null && this.value == null)
                return false;
            if(other.value == null && this.value == null)
                return true;
            return (other.value.equals(this.value));
        }
    }

    private String getString(char[][] grid)
    {
        StringBuilder sb = new StringBuilder(R*C);
        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                sb.append(grid[r][c]);
            }
        }
        return sb.toString();
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