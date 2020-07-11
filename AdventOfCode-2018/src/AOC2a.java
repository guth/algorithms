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

        int numTwos = 0;
        int numThrees = 0;

        String line;
        while((line = br.readLine()) != null)
        {
            Map<Character, Integer> letters = new HashMap<Character, Integer>();

            char[] c = line.toCharArray();
            for(int i=0; i<c.length; i++)
            {
                if(letters.containsKey(c[i]))
                {
                    letters.put(c[i], letters.get(c[i]) + 1);
                }
                else
                {
                    letters.put(c[i], 1);
                }
            }

            boolean twoFound = false;
            boolean threeFound = false;
            for(char letter : letters.keySet())
            {
                if(!twoFound && letters.get(letter) == 2)
                {
                    twoFound = true;
                    numTwos++;
                }
                if(!threeFound && letters.get(letter) == 3)
                {
                    threeFound = true;
                    numThrees++;
                }
            }
        }

        System.out.println(numTwos * numThrees);
    }
}