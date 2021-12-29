import java.util.*;
import java.io.*;
import java.text.*;

class Day6
{
    public static void main(String[] args) throws Exception
    {
        Day6 m = new Day6();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        List<Integer> fish = new ArrayList<Integer>();
        Map<Integer, Long> map = new HashMap<Integer, Long>();

        for(int i=0; i<=8; i++)
        {
            map.put(i, 0L);
        }

        String line = br.readLine();
        String parts[] = line.split(",");
        for(int i=0; i<parts.length; i++)
        {
            int f = Integer.parseInt(parts[i]);
            fish.add(f);
            map.put(f, map.get(f) + 1);
        }

        // I doubt brute force simulation will work for Part Two, but for now let's do it.
        // Part One
        for(int T=1; T<=80; T++)
        {
            int fishCount = fish.size();
            for(int i=0; i<fishCount; i++)
            {
                if(fish.get(i) == 0)
                {
                    fish.set(i, 6);
                    fish.add(8);
                }
                else
                {
                    fish.set(i, fish.get(i) - 1);
                }
            }
        }

        System.out.println("Part One: " + fish.size());

        // Part Two
        // Ohhh, simulation but smarter?
        for(int T=1; T<=256; T++)
        {
            long numZero = map.get(0);
            for(int i=1; i<=8; i++)
            {
                map.put(i-1, map.get(i));
            }
            map.put(6, map.get(6) + numZero);
            map.put(8, numZero);
        }

        long partTwo = 0L;
        for(int i=0; i<=8; i++)
        {
            partTwo += map.get(i);
        }
        System.out.println("Part Two: " + partTwo);
    }

}