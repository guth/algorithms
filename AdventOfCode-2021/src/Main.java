import java.util.*;
import java.io.*;
import java.text.*;

class Main
{
    public static void main(String[] args) throws Exception
    {
        Main m = new Main();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        String numbersCalled = br.readLine();



        String line;
        while ((line = br.readLine()) != null)
        {
            // System.out.println(line.length() + ": " + line);
            if (line.length() == 0)
                continue;
        }
    }

    class Board
    {
        public int[][] grid;
        public boolean[][] seen;

        public Board()
        {
            this.grid = new int[5][5];
            this.seen = new boolean[5][5];
        }

        public boolean isWinner()
        {
            return false;
        }

        public int score()
        {
            int score = 0;
            for(int r=0; r<5; r++)
            {
                for(int c=0; c<5; c++)
                {
                    if(!this.seen[r][c])
                    {
                        score += this.grid[r][c];
                    }
                }
            }

            return score;
        }
    }
}