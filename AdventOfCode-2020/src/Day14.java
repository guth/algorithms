import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.text.*;

class Day14
{
    public static void main(String[] args) throws Exception
    {
        Day14 m = new Day14();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);
// 01234567
// mask = 01101X001X111X010X0000X1001X010XX0X0
// mem[4841] = 3942
// mem[9168] = 414370178

        List<Operation> ops = new ArrayList<Operation>();
        String line;
        while((line = br.readLine()) != null)
        {
            Pattern pattern = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");

            if(line.startsWith("mask"))
            {
                String mask = line.substring(7, line.length());
                ops.add(new Operation("mask", mask, 0, 0));
            }
            else // "mem"
            {
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                long key = Long.parseLong(matcher.group(1));
                long value = Long.parseLong(matcher.group(2));

                ops.add(new Operation("mem", null, key, value));
            }
        }
        // System.out.println(ops);
        String mask = "";

        // Part One
        Map<Long, Long> map = new HashMap<Long, Long>();

        for(Operation op : ops)
        {
            if(op.type.equals("mask"))
            {
                mask = op.mask;
            }
            else
            {
                long key = op.key;
                long value = op.value;

                char[] c = mask.toCharArray();
                for(int i=0; i<c.length; i++)
                {
                    if(c[i] == 'X')
                        continue;
                    long twos = (long)Math.pow(2, 35-i);

                    if(c[i] == '1')
                    {
                        value |= twos;
                    }
                    else
                    {
                        value &= ~twos;
                    }
                }

                map.put(key, value);
            }
        }
        long partOne = 0;
        for(Long key : map.keySet())
        {
            partOne += map.get(key);
        }

        System.out.println("Part One: " + partOne);

        // Part Two. Hm.
        mask = "";
        map = new HashMap<Long, Long>();

        Set<String> maskValues = new HashSet<String>();

        for(Operation op : ops)
        {
            if(op.type.equals("mask"))
            {
                mask = op.mask;
            }
            else
            {
                long key = op.key;
                long value = op.value;

                String binaryKey = Long.toBinaryString(key);
                while(binaryKey.length() < 36)
                    binaryKey = "0" + binaryKey;
                System.out.println("binary: " + binaryKey);

                char[] c = binaryKey.toCharArray();
                for(int i=0; i<c.length; i++)
                {
                    if(mask.charAt(i) == '1')
                        c[i] = '1';
                    else if(mask.charAt(i) == 'X')
                        c[i] = 'X';
                }
                System.out.println("c: " + new String(c));

                Set<String> allValues = this.getAllValues(c);
                for(String v : allValues)
                {
                    long computedKey = Long.parseLong(v, 2);
                    map.put(computedKey, value);
                }
            }
        }

        long partTwo = 0;
        for(long key : map.keySet())
        {
            partTwo += map.get(key);
        }

        System.out.println("Part Two: " + partTwo);
    }

    private Set<String> getAllValues(char[] c)
    {
        boolean done = true;
        for(int i=0; i<c.length; i++)
        {
            if(c[i] == 'X')
            {
                done = false;
                break;
            }
        }

        Set<String> values = new HashSet<String>();
        
        if(done)
        {
            values.add(new String(c));
        }
        else
        {
            for(int i=0; i<c.length; i++)
            {
                if(c[i] == 'X')
                {
                    char[] copy1 = this.copyArray(c);
                    char[] copy2 = this.copyArray(c);
                    copy1[i] = '0';
                    copy2[i] = '1';

                    values.addAll(this.getAllValues(copy1));
                    values.addAll(this.getAllValues(copy2));
                }
            }
        }

        return values;
    }

    private char[] copyArray(char[] c)
    {
        char[] copy = new char[c.length];
        for(int i=0; i<c.length; i++)
        {
            copy[i] = c[i];
        }
        return copy;
    }

    public class Operation
    {
        public String type;
        public String mask;
        public long key;
        public long value;

        public Operation(String type, String mask, long key, long value)
        {
            this.type = type;
            this.mask = mask;
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return "(" + type + "," + mask + "," + key + "," + value + ")";
        }
    }
}