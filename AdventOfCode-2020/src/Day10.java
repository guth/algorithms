import java.util.*;
import java.io.*;
import java.text.*;

class Day10
{
    public static void main(String[] args) throws Exception
    {
        Day10 m = new Day10();
        m.go();
    }

    void go() throws Exception
    {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        Scanner s = new Scanner(System.in);

        // Read input
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        int max = 0;

        while(s.hasNextInt())
        {
            int val = s.nextInt();
            list.add(val);
            max = Math.max(val, max);
        }
        list.add(max + 3);

        int N = list.size();

        System.out.println("N: " + N);
        
        // Change it to an array
        int[] a = new int[N];
        for(int i=0; i<N; i++)
        {
            a[i] = list.get(i);
        }

        // System.out.println(list);

        // Part One
        Arrays.sort(a);

        int ones = 0;
        int threes = 0;

        for(int i=0; i<N; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        for(int i=0; i<N-1; i++)
        {
            int diff = a[i+1] - a[i];

            if(diff == 1)
                ones++;
            else if(diff == 3)
                threes++;
        }
        System.out.println("Ones: " + ones);
        System.out.println("Threes: " + threes);

        System.out.println("Part One: " + (ones*threes));

        long[] ways = new long[N];
        ways[0] = 1;

        for(int i=1; i<N; i++)
        {
            if(i-1 >= 0 && a[i]-a[i-1] <= 3) {
                ways[i] += ways[i-1];
            }
            if(i-2 >= 0 && a[i]-a[i-2] <= 3) {
                ways[i] += ways[i-2];
            }
            if(i-3 >= 0 && a[i]-a[i-3] <= 3) {
                ways[i] += ways[i-3];
            }
        }

        System.out.println("Part Two: " + ways[N-1]);

    }
}