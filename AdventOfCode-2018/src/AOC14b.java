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

        String input = br.readLine();

        // goal = 2018;
        System.out.println("Input: " + input);
        List<Integer> goal = new ArrayList<Integer>();
        for(char c : input.toCharArray())
        {
            goal.add((int)(c-'0'));
        }

        List<Integer> recipes = new ArrayList<Integer>();

        // Elf a starts at 0, elf b starts at 1.
        // Each round that move forward 1 + current recipe.
        int a = 0;
        int b = 1;
        recipes.add(3);
        recipes.add(7);

        boolean goalFound = false;

        while(!goalFound)
        {

            int value = recipes.get(a) + recipes.get(b);
            char[] chars = (value+"").toCharArray();
            for(char c : chars)
            {
                recipes.add((int)(c-'0'));

                // Check for goal at the end of recipes.
                if(recipes.size() > goal.size())
                {
                    goalFound = true;
                    for(int i=0; i<goal.size(); i++)
                    {
                        if(goal.get(i) != recipes.get(i + recipes.size() - goal.size()))
                        {
                            goalFound = false;
                            break;
                        }
                    }
                }

                if(goalFound)
                    break;

            }

            a = (a + 1 + recipes.get(a)) % recipes.size();
            b = (b + 1 + recipes.get(b)) % recipes.size();
        }

        System.out.println(recipes.size() - goal.size());
    }
}