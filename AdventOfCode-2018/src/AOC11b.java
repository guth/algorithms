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

    void go() throws Exception
    {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Scanner s = new Scanner(System.in);

        // This took 30 seconds to run for part b because it's brute force.
        // I think there's a dynamic programming solution that's O(N^4) (N = square size, in this case 300).
        // Given the top left coordinates, a square whose top-left coordinate is (x,y) with size 1 is contained within
        // any larger square whose top-left coordinate is (x,y). So we can build off of previous answers.
        // Maybe int[][][] memo, where memo[x][y][1] is that 1-sized square,
        // and memo[x][y][2] = memo[x][y][1] + the values in the row & column that gets added.
        int serialNumber = 9306;
        int MAX = 300;
        int[][] grid = new int[MAX+3][MAX+3];

        for(int x=1; x<=MAX; x++)
        {
            for(int y=1; y<=MAX; y++)
            {
                int power = calculatePower(x, y, serialNumber);
                grid[x][y] = power;
            }
        }

        int max = Integer.MIN_VALUE;
        int mx = -1;
        int my = -1;
        int msize = -1;

        for(int x=1; x<=MAX-2; x++)
        {
            for(int y=1; y<=MAX-2; y++)
            {
                for(int size=1; size<=MAX; size++)
                {
                    if(x+size-1 > MAX || y+size-1>MAX)
                        break;

                    int value = 0;

                    for(int i=0; i<size; i++)
                    {
                        for(int j=0; j<size; j++)
                        {
                            value += grid[x+i][y+j];
                        }
                    }

                    if(value > max)
                    {
                        max = value;
                        mx = x;
                        my = y;
                        msize = size;
                    }
                }

            }
        }

        System.out.println(max + ": " + mx + "," + my + "," + msize);
    }

    private int calculatePower(int x, int y, int serialNumber)
    {
        int rackID = x + 10;
        int power = rackID * y;
        power += serialNumber;
        power *= rackID;
        power = (power % 1000) / 100;
        power -= 5;

        return power;
    }

}