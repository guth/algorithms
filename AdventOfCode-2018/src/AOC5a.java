import java.util.*;
import java.util.regex.*;
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

        char[] line = br.readLine().toCharArray();
        List<Character> list = new ArrayList<Character>(line.length);

        for(int i=0; i<line.length; i++)
        {
            list.add(line[i]);
        }

        System.out.println("Size: " + list.size());

        boolean found;
        do
        {
            found = false;

            for(int i=0; i<list.size() - 1; i++)
            {
                char c1 = list.get(i);
                char c2 = list.get(i+1);

                if(Math.abs(c2-c1) == 32)
                {
                    found = true;
                    list.remove(i);
                    list.remove(i);
                }
            }
        }
        while(found);

        System.out.println(list.toString());
        System.out.println(list.size());
    }
}