import java.util.*;
import java.io.*;
import java.text.*;

class Day11
{
    public static void main(String[] args) throws Exception
    {
        Day11 m = new Day11();
        m.go();
    }

    private int R;
    private int C;

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        // Read input
        List<String> lines = new ArrayList<String>();
        String tmp;
        while((tmp = br.readLine()) != null)
        {
            lines.add(tmp);
        }

        R = lines.size();
        C = lines.get(0).length();

        char[][] grid = new char[R][C];
        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                grid[r][c] = lines.get(r).charAt(c);
            }
        }
        char[][] originalGrid = this.copyGrid(grid);

        int iteration = 0;

        // Part One
        while(true)
        {
            iteration++;

            char[][] next = this.process1(grid);

            if(this.areEqual(next, grid))
            {
                System.out.println("Equal on iteration: " + iteration);
                int occupied = 0;
                for(int r=0; r<R; r++)
                {
                    for(int c=0; c<C; c++)
                    {
                        if(grid[r][c] == '#')
                            occupied++;
                    }
                }
                System.out.println("Part One: " + occupied);
                break;
            }
            else
            {
                grid = next;
            }
        }

        // Part Two
        grid = this.copyGrid(originalGrid);
        iteration = 0;
        while(true)
        {
            iteration++;

            char[][] next = this.process2(grid);

            // this.printGrid(next);

            if(this.areEqual(next, grid))
            {
                System.out.println("Equal on iteration: " + iteration);
                int occupied = 0;
                for(int r=0; r<R; r++)
                {
                    for(int c=0; c<C; c++)
                    {
                        if(grid[r][c] == '#')
                            occupied++;
                    }
                }
                System.out.println("Part Two: " + occupied);
                break;
            }
            else
            {
                grid = next;
            }
        }
    }

    private char[][] process2(char[][] grid)
    {
        char[][] copy = this.copyGrid(grid);
        int[] drs = {-1, 1, 0};
        int[] dcs = new int[]{-1, 1, 0};

        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                if(grid[r][c] == '.')
                    continue;

                int numOccupiedInSight = 0;
                int numEmptyInSight = 0;

                for(int dr : drs)
                {
                    for(int dc: dcs)
                    {
                        if(dr == 0 && dc == 0)
                            continue;

                        char first = this.firstChairInSight(grid, r, c, dr, dc);
                        if(first == 'L')
                            numEmptyInSight++;
                        else if(first == '#')
                            numOccupiedInSight++;
                    }
                }

                if(grid[r][c] == 'L' && numOccupiedInSight == 0) // empty seat
                {
                    copy[r][c] = '#';
                }
                else if(grid[r][c] == '#' && numOccupiedInSight >= 5)
                {
                    copy[r][c] = 'L';
                }
            }
        }

        return copy;
    }

    // If a seat is empty (L) and there are no occupied seats adjacent to it,
    // the seat becomes occupied.
    // If a seat is occupied (#) and four or more seats adjacent to it are also occupied,
    // the seat becomes empty.
    // Otherwise, the seat's state does not change.
    private char[][] process1(char[][] grid)
    {
        char[][] copy = this.copyGrid(grid);

        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                if(grid[r][c] == 'L') // empty seat
                {
                    int adjOccupied = this.numAdjacent(grid, r, c, '#');
                    if(adjOccupied == 0)
                    {
                        copy[r][c] = '#';
                    }
                }
                else if(grid[r][c] == '#') // occupied seat
                {
                    int adjOccupied = this.numAdjacent(grid, r, c, '#');
                    if(adjOccupied >= 4)
                    {
                        copy[r][c] = 'L';
                    }
                }
                else // '.' floor
                {
                    continue;
                }
            }
        }

        return copy;
    }

    private char firstChairInSight(char[][] grid, int r, int c, int dr, int dc)
    {
        while(true)
        {
            r += dr;
            c += dc;
            if(r < 0 || r >= R || c < 0 || c >= C)
                break;

            if(grid[r][c] == 'L')
                return 'L';
            else if(grid[r][c] == '#')
                return '#';
        }

        return '.';
    }

    private int numAdjacent(char[][] grid, int r, int c, char obj)
    {
        int count = 0;
        int[] drs = {-1, 1, 0};
        int[] dcs = new int[]{-1, 1, 0};

        for(int dr : drs)
        {
            for(int dc : dcs)
            {
                if(dr == 0 && dc == 0)
                    continue;

                if(r+dr >= 0 && r+dr < R && c+dc >= 0 && c+dc < C)
                {
                    if(grid[r+dr][c+dc] == obj)
                        count++;
                }
            }
        }

        return count;
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
            System.out.println("--------------");
    }

    private char[][] copyGrid(char[][] grid)
    {
        char[][] copy = new char[R][C];

        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                copy[r][c] = grid[r][c];
            }
        }

        return copy;
    }

    private boolean areEqual(char[][] left, char[][] right)
    {
        for(int r=0; r<R; r++)
        {
            for(int c=0; c<C; c++)
            {
                if(left[r][c] != right[r][c])
                    return false;
            }
        }
        return true;
    }
}