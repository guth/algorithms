import java.util.*;
import java.io.*;
import java.text.*;

class Day13
{
    public static void main(String[] args) throws Exception
    {
        Day13 m = new Day13();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        int start = Integer.parseInt(br.readLine());
        String[] parts = br.readLine().split(",");
        int N = parts.length;
        
        int[] bus = new int[N];
        for(int i=0; i<parts.length; i++)
        {
            if(parts[i].equals("x"))
                bus[i] = -1;
            else
                bus[i] = Integer.parseInt(parts[i]);
        }

        // Brute force solution for part one because it's fast
        // enough. Keep 'x' bus in the array because we'll need them
        // for part two.
        long time = start;
        int busID = -1;
        while(true)
        {
            boolean done = false;
            for(int i=0; i<N; i++)
            {
                if(bus[i] == -1)
                    continue;
                
                if(time % bus[i] == 0)
                {
                    done = true;
                    busID = bus[i];
                    break;
                }
            }

            if(done)
                break;
            System.out.println("None for time: " + time);

            time++;
        }
        long diff = time - start;
        System.out.println("Part One: " + (busID * diff));

        // Part Two
        // CRT and math stuff.
        // Makes sense since all bus IDs are (co)prime.
        // If they weren't I'd be sad.
        
        List<Bus> buses = new ArrayList<Bus>();
        for(int i=0; i<bus.length; i++)
        {
            if(bus[i] != -1)
            {
                buses.add(new Bus(bus[i], i));
            }
        }
        System.out.println("Buses: " + buses);

        time = 0;
        long step = 1;
        for(Bus b : buses)
        {
            while((time + b.offset) % b.id != 0)
            {
                time += step;
            }
            step *= b.id;
        }
        System.out.println("Part Two: " + time);
    }

    public class Bus
    {
        public int id;
        public int offset;

        public Bus(int id, int offset)
        {
            this.id = id;
            this.offset = offset;
        }

        @Override
        public String toString()
        {
            return "{id: " + id + ", offset: " + offset + "}";
        }
    }
}