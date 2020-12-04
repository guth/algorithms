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


        // Part A
        for(int i=0; i<nums.size(); i++)
        {
            for(int k=i+1; k<nums.size(); k++)
            {
                if((nums.get(i) + nums.get(k)) == 2020)
                {
                    System.out.println("nums[i]: " + nums.get(i));
                    System.out.println("nums[k]: " + nums.get(k));

                    long value = (long)(nums.get(i)) * nums.get(k);
                    System.out.println("Part A: " + value);
                }
            }
        }

        // Part B
        for(int i=0; i<nums.size(); i++)
        {
            for(int k=i+1; k<nums.size(); k++)
            {
                for(int j=k+1; j<nums.size(); j++)
                {
                    if((nums.get(i) + nums.get(k) + nums.get(j)) == 2020)
                    {
                        System.out.println("nums[i]: " + nums.get(i));
                        System.out.println("nums[k]: " + nums.get(k));
                        System.out.println("nums[j]: " + nums.get(j));

                        long value = (long)(nums.get(i)) * nums.get(k) * nums.get(j);
                        System.out.println("Part B: " + value);
                    }
                }
            }
        }
    }
}