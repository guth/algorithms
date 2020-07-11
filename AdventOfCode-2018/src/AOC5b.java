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

        int minSize = Integer.MAX_VALUE;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for(char letter : alphabet.toCharArray())
        {
            List<Character> copy = new ArrayList<Character>(list);
            for(int i=0; i<copy.size(); i++)
            {
                if(copy.get(i) == letter || copy.get(i) == (letter - 32))
                {
                    copy.remove(i);
                    i--;
                }
            }
            int size = this.getSizeAfterReacting(copy);
            System.out.println("Removed '" + letter + "' and got size " + size);
            if(size < minSize)
            {
                minSize = size;
            }
        }
        System.out.println("Min size: " + minSize);
    }

    private int getSizeAfterReacting(List<Character> list)
    {
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

        return list.size();
    }
}