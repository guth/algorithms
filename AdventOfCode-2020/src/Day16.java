import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.text.*;

class Day16
{
    public static void main(String[] args) throws Exception
    {
        Day16 m = new Day16();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        // START: Parse input. Whew.
        List<String> rangeLines = new ArrayList<String>();
        String myTicketLine = "";
        List<String> nearbyTicketsLines = new ArrayList<String>();

        while(true)
        {
            String line = br.readLine();

            if(line == null || line.length() <= 0)
                break;

            rangeLines.add(line);
        }
        while(true)
        {
            String line = br.readLine();

            if(line == null || line.length() <= 0)
                break;

            myTicketLine = line;
        }
        while(true)
        {
            String line = br.readLine();

            if(line == null || line.length() <= 0)
                break;

            nearbyTicketsLines.add(line);
        }
        nearbyTicketsLines.remove(0);

        System.out.println("--- Input lines ---");
        System.out.println(rangeLines);
        System.out.println(myTicketLine);
        System.out.println(nearbyTicketsLines);

        List<Range> ranges = new ArrayList<Range>();

        for(String rangeLine : rangeLines)
        {
            // Pattern pattern = Pattern.compile("mem\\[(\\d+)\\] = (\\d+)");
// class: 1-3 or 5-7
// row: 6-11 or 33-44
// seat: 13-40 or 45-50
// convert to--->"6-11 or 33-44"
            rangeLine = rangeLine.substring(rangeLine.indexOf(":") + 2, rangeLine.length());

            Pattern pattern = Pattern.compile("(\\d+)-(\\d+) or (\\d+)-(\\d+)");
            Matcher matcher = pattern.matcher(rangeLine);
            matcher.find();

            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            int c = Integer.parseInt(matcher.group(3));
            int d = Integer.parseInt(matcher.group(4));

            ranges.add(new Range(a, b, c, d));
        }

        List<Integer> myTicket = new ArrayList<Integer>();
        String[] parts = myTicketLine.split(",");
        for(String part : parts)
        {
            myTicket.add(Integer.parseInt(part));
        }

        List<List<Integer>> nearbyTickets = new ArrayList<List<Integer>>();
        for(String nearbyTicketLine : nearbyTicketsLines)
        {
            List<Integer> nearbyTicket = new ArrayList<Integer>();
            parts = nearbyTicketLine.split(",");
            for(String part : parts)
            {
                nearbyTicket.add(Integer.parseInt(part));
            }
            nearbyTickets.add(nearbyTicket);
        }

        System.out.println("--- Parsed input ---");
        System.out.println(ranges);
        System.out.println(myTicket);
        System.out.println(nearbyTickets);

        // DONE Reading Input.
        int errorRate = 0;

        for(List<Integer> nearbyTicket : nearbyTickets)
        {
            for(int value : nearbyTicket)
            {
                boolean found = false;

                for(Range r : ranges)
                {
                    if((r.a <= value && r.b >= value) || (r.c <= value && r.d >= value))
                    {
                        found = true;
                        break;
                    }
                }

                if(!found)
                {
                    errorRate += value;
                }

            }
        }

        System.out.println("Part One: " + errorRate);
    }

    public class Range
    {
        // [a, b] or [c, d]
        public int a;
        public int b;
        public int c;
        public int d;

        public Range(int a, int b, int c, int d)
        {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString()
        {
            return "[" + a + "-" + b +"] or [" + c + "-" + d + "]";
        }
    }
}