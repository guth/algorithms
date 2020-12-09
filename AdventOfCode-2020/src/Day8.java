import java.util.*;
import java.io.*;
import java.text.*;

class Day8
{
    public static void main(String[] args) throws Exception
    {
        Day8 m = new Day8();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        List<Operation> ops = new ArrayList<>();

        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null)
        {
            String[] parts = line.split(" ");
            String name = parts[0];
            int value = Integer.parseInt(parts[1]);
            
            Operation op = new Operation(name, value);
            ops.add(op);
        }

        int partOne = this.partOne(ops);
        System.out.println("Part One: " + partOne);

        // Part Two
        int partTwo = this.partTwo(ops);
        System.out.println("Part Two: " + partTwo);
    }

    private int partTwo(List<Operation> ops)
    {
        for(int i=0; i < ops.size(); i++)
        {
            Operation newOp;
            if(ops.get(i).name.equals("nop"))
            {
                newOp = new Operation("jmp", ops.get(i).value);
            }
            else if(ops.get(i).name.equals("jmp"))
            {
                newOp = new Operation("nop", ops.get(i).value);
            }
            else
            {
                newOp = ops.get(i);
            }

            List<Operation> copy = new ArrayList<Operation>(ops);
            copy.set(i, newOp);

            int value = this.evaluate(copy);

            if(value > 0)
            {
                return value;
            }
        }

        // We should never get here with the right.
        return -1;
    }

    // Return -1 if there's a cycle, otherwise
    // return the accumulator value.
    private int evaluate(List<Operation> ops)
    {
        int accumulator = 0;
        Set<Integer> visited = new HashSet<Integer>();
        int i = 0;

        while(true)
        {
            if(visited.contains(i))
            {
                return -1;
            }

            if(i == ops.size())
            {
                return accumulator;
            }

            visited.add(i);

            Operation op = ops.get(i);
            if(op.name.equals("nop"))
            {
                i++;
            }
            else if(op.name.equals("acc"))
            {
                accumulator += op.value;
                i++;
            }
            else if(op.name.equals("jmp"))
            {
                i += op.value;
            }
        }
    }

    private int partOne(List<Operation> ops)
    {
        // Part One. Call this a bunch for Part Two?
        int accumulator = 0;
        Set<Integer> visited = new HashSet<Integer>();
        int i = 0;

        while(true)
        {
            if(visited.contains(i))
            {
                return accumulator;
            }

            visited.add(i);

            Operation op = ops.get(i);
            if(op.name.equals("nop"))
            {
                i++;
            }
            else if(op.name.equals("acc"))
            {
                accumulator += op.value;
                i++;
            }
            else if(op.name.equals("jmp"))
            {
                i += op.value;
            }
        }
    }

    public class Operation
    {
        public String name;
        public int value;

        public Operation(String name, int value)
        {
            this.name = name;
            this.value = value;
        }
    }
}