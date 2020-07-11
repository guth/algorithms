import java.util.*;
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);
        
        Set<String> values = new HashSet<String>();

        String line;
        while((line = br.readLine()) != null)
        {
            values.add(line);
        }

        for(String a : values)
        {
            for(String b : values)
            {
                if(a.equals(b))
                    continue;
                if(a.length() != b.length())
                    continue;

                char[] c1 = a.toCharArray();
                char[] c2 = b.toCharArray();

                int numDiff = 0;
                int diffIndex = -1;
                for(int i=0; i < c1.length; i++)
                {
                    if(c1[i] != c2[i])
                    {
                        numDiff++;
                        diffIndex = i;
                    }
                }

                if(numDiff == 1)
                {
                    String ans1 = a.substring(0, diffIndex) + a.substring(diffIndex+1, a.length());
                    String ans2 = b.substring(0, diffIndex) + b.substring(diffIndex+1, b.length());

                    System.out.println(ans1);
                    System.out.println(ans2);
                }
            }
        }
    }
}