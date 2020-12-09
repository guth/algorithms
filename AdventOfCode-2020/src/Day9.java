import java.util.*;
import java.io.*;
import java.text.*;

class Day9
{
    public static void main(String[] args) throws Exception
    {
        Day9 m = new Day9();
        m.go();
    }

    void go() throws Exception
    {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        Scanner s = new Scanner(System.in);

        List<Long> input = new ArrayList<Long>();
        while(s.hasNextLong())
        {
            long value = s.nextLong();
            input.add(value);
        }

        int N = 25;
        List<Long> list = new ArrayList<Long>();

        for(int i=0; i<N; i++)
        {
            long value = input.get(i);
            list.add(value);
        }

        long partOne = -1;

        // Part One: Let's do a brute force solution because thinking is hard.
        for(int a=N; a<input.size(); a++)
        {
            long value = input.get(a);
            boolean found = false;

            for(int i=0; i<list.size(); i++)
            {
                for(int k=0; k<list.size(); k++)
                {
                    if(i != k && list.get(i) + list.get(k) == value)
                    {
                        found = true;
                        break;
                    }
                }
                if(found)
                    break;
            }

            if(found)
            {
                list.remove(0);
                list.add(value);
            }
            else
            {
                partOne = value;
                break;
            }
        }

        System.out.println("Part One: " + partOne);

        // Part Two: I feel like there's a faster way to this, but we're
        // brute force programmers around these parts (at least right now, I'm tired).
        // And the input size is N=1000, so O(N^2) is fine.



        for(int i=0; i<input.size(); i++)
        {
            long sum = input.get(i);
            for(int k=i+1; k<input.size(); k++)
            {
                sum += input.get(k);

                if(sum == partOne)
                {
                    long min = Long.MAX_VALUE;
                    long max = Long.MIN_VALUE;
                    for(int a=i; a<=k; a++)
                    {
                        min = Math.min(min, input.get(a));
                        max = Math.max(max, input.get(a));
                    }
                    System.out.println("i : " + i + ", k: " + k);
                    System.out.println("Part Two: " + (min+max));
                }

                if(sum >= partOne)
                {
                    break;
                }
            }
        }

        // Okay okay fine, let's use a rolling window. It's a bit quicker.
        long target = partOne;
        int i = 0;
        long sum = input.get(i);

        for(int k=i+1; k<input.size(); k++)
        {
            sum += input.get(k);

            while(sum > target && i < k)
            {
                sum -= input.get(i);
                i++;
            }

            if(sum == target)
            {
                long min = Long.MAX_VALUE;
                long max = Long.MIN_VALUE;
                for(int a=i; a<=k; a++)
                {
                    min = Math.min(min, input.get(a));
                    max = Math.max(max, input.get(a));
                }
                System.out.println("Part 3  : " + (min+max));
                break;
            }
        }
        System.out.println("Finished.");
    }
}