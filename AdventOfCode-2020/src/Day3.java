import java.util.*;
import java.io.*;
import java.text.*;

class Day3
{
    public static void main(String[] args) throws Exception
    {
        Day3 m = new Day3();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);
        
        // Read input
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null)
        {
            lines.add(line);
        }

        // Convert to char array for convenience
        int R = lines.size();
        int C = lines.get(0).length();
        
        System.out.println("R: " + R + ", C: " + C);

        char[][] graph = new char[R][C];
        for (int r=0; r<R; r++)
        {
            for (int c=0; c<C; c++)
            {
                graph[r][c] = lines.get(r).charAt(c);
            }
        }

        // Count! Jumping 3 to the right, 1 down.
        int count = 0;
        int currC = 0;
        int currR = 0;

        while (true)
        {
            currC = (currC + 3) % C;
            currR += 1;

            if (currR >= R)
                break;

            if (graph[currR][currC] == '#')
            {
                count++;
            }
        }

        System.out.println("Part One: " + count);

        // Part Two: Check multiple slopes
        int[] dc = new int[] {1, 3, 5, 7, 1};
        int[] dr = new int[] {1, 1, 1, 1, 2};
        long[] trees = new long[dc.length];

        for(int i=0; i<dc.length; i++)
        {
            currC = 0;
            currR = 0;

            while(true)
            {
                currC = (currC + dc[i]) % C;
                currR = currR + dr[i];

                if(currR >= R)
                    break;

                if(graph[currR][currC] == '#')
                {
                    trees[i]++;
                }
            }
        }

        long partTwo = 1;
        for(long tree : trees)
        {
            partTwo *= tree;
        }
        System.out.println("Part Two: " + partTwo);
    }
}