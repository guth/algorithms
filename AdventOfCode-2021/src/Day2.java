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
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        Scanner s = new Scanner(System.in);

        List<Command> commands = new ArrayList<Command>();

        while (s.hasNext())
        {
            Command c = new Command();
            c.move = s.next();
            c.value = s.nextInt();
            commands.add(c);
            // System.out.println(c.move + "_" + c.value);
        }
        // System.out.println(commands.size());

        // Part One
        int position = 0;
        int depth = 0;
        for (Command c : commands)
        {
            if (c.move.equals("forward"))
            {
                position += c.value;
            }
            else if (c.move.equals("down"))
            {
                depth += c.value;
            }
            else // c.move == "up"
            {
                depth -= c.value;
            }
        }
        System.out.println("Position: " + position);
        System.out.println("Depth: " + depth);
        System.out.println("Part One: " + position * depth);

        // Part Two
        int aim = 0;
        position = 0;
        depth = 0;
        for (Command c : commands)
        {
            if (c.move.equals("forward"))
            {
                position += c.value;
                depth += (aim * c.value);
            }
            else if (c.move.equals("down"))
            {
                aim += c.value;
            }
            else // c.move == "up"
            {
                aim -= c.value;
            }
        }
        System.out.println("Position: " + position);
        System.out.println("Depth: " + depth);
        System.out.println("Part Two: " + position * depth);
    }

    public class Command
    {
        public String move;
        public int value;
    }
}