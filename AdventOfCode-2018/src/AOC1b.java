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
        
        List<Integer> values = new ArrayList<Integer>();

        String line;
        while((line = br.readLine()) != null)
        {
            int value = Integer.parseInt(line);
            values.add(value);
        }

        int sum = 0;
        Set<Integer> seen = new HashSet<Integer>();

        boolean done = false;
        while(!done)
        {
            for(Integer value : values)
            {
                sum += value;

                if(seen.contains(sum))
                {
                    done = true;
                    System.out.println(sum);
                    break;
                }

                seen.add(sum);
            }
        }
    }
}