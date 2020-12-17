import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.text.*;

class Day15
{
    public static void main(String[] args) throws Exception
    {
        Day15 m = new Day15();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        List<Integer> nums = new ArrayList<Integer>();

        String line = br.readLine();
        String[] parts = line.split(",");
        for(String part : parts)
        {
            int num = Integer.parseInt(part);
            nums.add(num);
        }

        this.playGame(nums, 2020);
        this.playGame(nums, 30_000_000);
    }

    private void playGame(List<Integer> nums, int turns)
    {
        Map<Integer, List<Integer>> spoken = new HashMap<Integer, List<Integer>>();

        int turn = 0;
        int value = -1;

        for(int i=0; i<nums.size(); i++)
        {
            turn++;
            value = nums.get(i);
            
            List<Integer> list = new ArrayList<Integer>();
            list.add(turn);

            spoken.put(value, list);
        }

        while(turn < turns)
        {
            turn++;

            // We just said 'value'. Was it the first time?
            if(spoken.get(value).size() == 1)
            {
                // Yes. Now we say 0.
                value = 0;
                spoken.get(value).add(turn);
            }
            else
            {
                // We've been $value before. Find the diff.
                // spoken map will contain at least 2 elements.
                List<Integer> turnsSeen = spoken.get(value);
                int diff = turnsSeen.get(turnsSeen.size()-1) - turnsSeen.get(turnsSeen.size()-1-1);

                // Speak $diff.
                value = diff;

                if(!spoken.containsKey(diff))
                {
                    spoken.put(diff, new ArrayList<Integer>());
                }
                spoken.get(diff).add(turn);

                // Small performance gain. I shouln't be using a list, but w/e.
                if(spoken.get(diff).size() > 2)
                {
                    spoken.get(diff).remove(0);
                }
            }

            // if(turn % 500000 == 0)
            // System.out.println("turn: " + turn + ", value: " + value);
        }

        System.out.println("answer on turn " + turn + " = " + value);
    }
}