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

        Queue<Integer> xs = new LinkedList<Integer>();

        for(Operation op : ops)
        {
            if(op.type.equals("mask"))
            {
                xs = new LinkedList<Integer>();
                mask = op.mask;
                char[] c = mask.toCharArray();
                for(int i=0; i<c.length; i++)
                {
                    if(c[i] == 'X')
                    {
                        xs.add(i);
                        c[i] = '0';
                    }
                }
                mask = new String(c);
                // System.out.println("xs: " + xs);
            }
            else
            {
                long key = op.key;
                long value = op.value;
                key |= Long.parseLong(mask, 2);

                Queue<Integer> xsCopy = new LinkedList(xs);
                Set<Long> allKeys = this.getAllKeys(key, xsCopy);
                for(Long v : allKeys)
                {
                    map.put(v, value);
                    // System.out.println(Long.toBinaryString(v));

                    // String sv = Long.toBinaryString(v);
                    // while(sv.length() < 36)
                        // sv = "0" + sv;
                    // System.out.println(sv);
                }

                // System.out.println("allKeys: " + allKeys);

            }
        }
        // System.out.println(map);

        long partTwo = 0;
        for(long key : map.keySet())
        {
            partTwo += map.get(key);
        }

        System.out.println("Part Two: " + partTwo);
    }

    private Set<Long> getAllKeys(long key, Queue<Integer> xs)
    {
        Set<Long> keys = new HashSet<Long>();

        if(xs.isEmpty())
        {
            keys.add(key);
        }
        else
        {
            int i = xs.poll();
            Queue<Integer> copy1 = new LinkedList(xs);
            Queue<Integer> copy2 = new LinkedList(xs);

            // i'th bit set to 0
            long key1 = key & ~(1L << (35-i));

            // i'th bit set to 1
            long key2 = key | (1L << (35-i));

            keys.addAll(this.getAllKeys(key1, copy1));
            keys.addAll(this.getAllKeys(key2, copy2));
        }

        return keys;
    }
// i
// 0  1  2  3  4 5 6 7
// pow
// 12864 32 16 8 4 2 1

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