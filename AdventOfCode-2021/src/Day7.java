import java.util.*;
import java.io.*;
import java.text.*;

class Day7
{
    public static void main(String[] args) throws Exception
    {
        Day7 m = new Day7();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        String line = br.readLine();
        String[] parts = line.split(",");
        int C = parts.length;
        int[] crabs = new int[C];

        // Part One
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int c=0; c<C; c++)
        {
            crabs[c] = Integer.parseInt(parts[c]);
            min = Math.min(min, crabs[c]);
            max = Math.max(max, crabs[c]);
        }

        int partOne = Integer.MAX_VALUE;
        for(int x=min; x<=max; x++)
        {
            int diff = 0;
            for(int c=0; c<C; c++)
            {
                diff += Math.abs(crabs[c] - x);
            }
            partOne = Math.min(diff, partOne);
        }

        System.out.println("Part One: " + partOne);

        // Part Two
        int partTwo = Integer.MAX_VALUE;

        for(int x=min; x<=max; x++)
        {
            int diff = 0;
            for(int c=0; c<C; c++)
            {
                int num = Math.abs(crabs[c] - x);
                diff += ((num+1)*num)/2;
            }
            partTwo = Math.min(diff, partTwo);
        }
        System.out.println("Part Twp: " + partTwo);
    }

}