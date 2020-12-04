import java.util.*;
import java.io.*;
import java.text.*;

class Day2
{
    public static void main(String[] args) throws Exception
    {
        Day2 m = new Day2();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);
        
        // 2-3 f: ffffvjff
        // 3-13 c: pmchdcwhdwdnchs
        // 7-12 s: sskdsssssssss
        // 1-4 x: kwqtxnhhxp
        // 13-14 n: nnnnnhnnnnnnnl
        // 11-15 f: nrffwclmlgxgdblj
        // 3-15 w: wvwwllsksktbbbdxmgmh
        // 6-7 g: gggggtgg

        int y =0;
        List<String> lines = new ArrayList<String>();
        String line;
        while((line = br.readLine()) != null)
        {
            lines.add(line);
        }

        // Get input before processing. Because why not?
        int N = lines.size();
        System.out.println("N: " + N);

        int[] low = new int[N];
        int[] high = new int[N];
        char[] chars = new char[N];
        String[] password = new String[N];
        for(int i=0; i<N; i++)
        {
            String[] parts = lines.get(i).split(" ");

            // Get low, then get high
            String[] lowHigh = parts[0].split("-");
            low[i] = Integer.parseInt(lowHigh[0]);
            high[i] = Integer.parseInt(lowHigh[1]);

            // Get the character
            chars[i] = parts[1].charAt(0);

            // Get the password
            password[i] = parts[2];
        }
        
        // Get the number of valid inputs
        int numValid = 0;

        for(int i=0; i<N; i++)
        {
            int count = 0;
            for(char c : password[i].toCharArray())
            {
                if(c == chars[i])
                {
                    count++;
                }
            }

            if(count >= low[i] && count <= high[i])
            {
                numValid++;
            }
        }

        int partB = 0;
        for(int i=0; i<N; i++)
        {
            if(
                (password[i].charAt(low[i]-1) != password[i].charAt(high[i]-1))
                &&
                (password[i].charAt(low[i]-1) == chars[i] || password[i].charAt(high[i]-1) == chars[i])
            )
            {
                partB++;
            }
        }

        System.out.println("Part A: " + numValid);
        System.out.println("Part B: " + partB);
    }
}