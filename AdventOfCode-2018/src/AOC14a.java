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
        // Scanner s = new Scanner(System.in);

        String line = br.readLine();
        int goal = Integer.parseInt(line);

        List<Integer> recipes = new ArrayList<Integer>();
        // for(char c : line.toCharArray())
        // {
        //     int value = (int)(c-'0');
        //     recipes.add(value);
        // }
        // System.out.println(recipes);

        // Elf a starts at 0, elf b starts at 1.
        // Each round that move forward 1 + current recipe.
        int a = 0;
        int b = 1;
        recipes.add(3);
        recipes.add(7);

        // StringBuilder sb = new StringBuilder(1000);
        // sb.append("37");


        while(true)
        {
            if(recipes.size() >= goal + 10)
                break;

            int value = recipes.get(a) + recipes.get(b);
            char[] chars = (value+"").toCharArray();
            for(char c : chars)
            {
                recipes.add((int)(c-'0'));
            }

            a = (a + 1 + recipes.get(a)) % recipes.size();
            b = (b + 1 + recipes.get(b)) % recipes.size();

            // System.out.println(recipes);
            // System.out.println(a + "," + b);
        }

        for(int i=goal; i<goal+10; i++)
        {
            System.out.print(recipes.get(i));
        }
        System.out.println();
    }
}