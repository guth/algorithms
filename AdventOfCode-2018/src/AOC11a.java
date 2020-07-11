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



        // For example, to find the power level of the fuel cell at 3,5 in a grid with serial number 8:

        //     The rack ID is 3 + 10 = 13.
        //     The power level starts at 13 * 5 = 65.
        //     Adding the serial number produces 65 + 8 = 73.
        //     Multiplying by the rack ID produces 73 * 13 = 949.
        //     The hundreds digit of 949 is 9.
        //     Subtracting 5 produces 9 - 5 = 4.

        // So, the power level of this fuel cell is 4.
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

        for(int x=1; x<=MAX-2; x++)
        {
            for(int y=1; y<=MAX-2; y++)
            {
                int value = grid[x][y] + grid[x+1][y] + grid[x][y] + 
                            grid[x][y+1] + grid[x+1][y+1] + grid[x+2][y+1] + 
                            grid[x][y+2] + grid[x+1][y+2] + grid[x+2][y+2];

                if(value > max)
                {
                    max = value;
                    mx = x;
                    my = y;
                }
            }
        }

        System.out.println(max + ": " + mx + "," + my);
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