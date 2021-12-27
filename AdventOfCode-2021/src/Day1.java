import java.util.*;
import java.io.*;
import java.text.*;

class Day1
{
    public static void main(String[] args) throws Exception
    {
        Day1 m = new Day1();
        m.go();
    }

    void go() throws Exception
    {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        Scanner s = new Scanner(System.in);
        
        List<Integer> nums = new ArrayList<Integer>();

        while(s.hasNextInt())
        {
            int x = s.nextInt();
            nums.add(x);
        }
        System.out.println("List size: " + nums.size());


        // Part One
        int count = 0;
        for (int i=1; i<nums.size(); i++)
        {
            int diff = nums.get(i) - nums.get(i-1);
            if (diff > 0)
            {
                count++;
            }
        }
        System.out.println("Part One: " + count);

        // Part Two
        int windowCount = 0;
        for (int i=3; i<nums.size(); i++)
        {
            int b = nums.get(i) + nums.get(i-1) + nums.get(i-2);
            int a = nums.get(i-1) + nums.get(i-2) +nums.get(i-3);
            if (b > a)
            {
                windowCount++;
            }
        }

        System.out.println("Part Two: " + windowCount);

    }
}