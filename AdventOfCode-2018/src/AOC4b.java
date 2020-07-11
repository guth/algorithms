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

    private final int FALLS_ASLEEP = -1;
    private final int WAKES_UP = -2;

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        // [1518-11-01 00:00] Guard #10 begins shift
        // [1518-11-01 00:05] falls asleep
        // [1518-11-01 00:25] wakes up
        Pattern pattern = Pattern.compile("\\[([\\d+\\-: ]+)\\] (.*)");
        // Pattern pattern = Pattern.compile("\\[([^\\]]+)\\] (.*)");
        Pattern guardPattern = Pattern.compile("Guard #(\\d+) begins shift");

        // Read input and sort lines.
        List<String> lines = new ArrayList<String>();

        String inputLine;
        while((inputLine = br.readLine()) != null)
        {
            lines.add(inputLine);
        }
        Collections.sort(lines);

        // Create operations.
        List<Operation> operations = new ArrayList<Operation>();
        for(String line : lines)
        {
            // System.out.println(line);

            Matcher matcher = pattern.matcher(line);
            matcher.matches();
            String dateString = matcher.group(1);
            String right = matcher.group(2);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
            Date date = dateFormat.parse(dateString);

            int op;
            if(right.equals("falls asleep"))
            {
                op = FALLS_ASLEEP;
            }
            else if(right.equals("wakes up"))
            {
                op = WAKES_UP;
            }
            else
            {
                Matcher guardMatcher = guardPattern.matcher(right);
                guardMatcher.matches();
                int id = Integer.parseInt(guardMatcher.group(1));
                op = id;
            }
            Operation operation = new Operation(date, op);
            operations.add(operation);
        }

        Map<Integer, Integer> guardSleepCount = new HashMap<Integer, Integer>();
        Map<Integer, int[]> guardMinuteCount = new HashMap<Integer, int[]>();

        int i = 0;
        int minute = 0;
        int currentGuard = -1;
        int currentState = 0;
        while(true)
        {
            Operation operation = operations.get(i);
            if(minute == operation.date.getMinutes())
            {
                if(operation.op > 0) // Guard beginning shift
                {
                    currentGuard = operation.op;
                    currentState = WAKES_UP;
                }
                else
                {
                    currentState = operation.op;
                }

                i++;
                if(i == operations.size())
                    break;
            }
            else // Process current operation at current minute
            {
                if(currentState == FALLS_ASLEEP)
                {
                    if(!guardSleepCount.containsKey(currentGuard))
                    {
                        guardSleepCount.put(currentGuard, 0);
                        guardMinuteCount.put(currentGuard, new int[60]);
                    }

                    guardSleepCount.put(currentGuard, guardSleepCount.get(currentGuard) + 1);
                    guardMinuteCount.get(currentGuard)[minute]++;
                }
                minute = (minute + 1) % 60;

            }

        }

        // Process maps
        System.out.println("Done. Process stuff.");

        // Map<Integer, Integer> guardSleepCount = new HashMap<Integer, Integer>();
        // Map<Integer, int[]> guardMinuteCount = new HashMap<Integer, int[]>();
        
        int frequentGuard = -1;
        int timesAsleep = -1;
        int minuteAsleep = -1;
        for(int guard : guardSleepCount.keySet())
        {
            int[] minuteCount = guardMinuteCount.get(guard);
            for(int k=0; k<minuteCount.length; k++)
            {
                if(minuteCount[k] > timesAsleep)
                {
                    frequentGuard = guard;
                    timesAsleep = minuteCount[k];
                    minuteAsleep = k;
                }
            }
        }
        System.out.println("Guard: " + frequentGuard);
        System.out.println("Minute: " + minuteAsleep);
        System.out.println("Times asleep: " + timesAsleep);

        System.out.println("Answer: " + (frequentGuard * minuteAsleep));

        // for(int guard : guardSleepCount.keySet())
        // {
        //     System.out.println("Guard " + guard + " was asleep for " + guardSleepCount.get(guard) + " minutes.");
        
        //     int[] minuteCount = guardMinuteCount.get(guard);
        //     for(int k=0; k<59; k++)
        //     {
        //         if(minuteCount[k] != 0)
        //         {
        //             System.out.println("Slept at minute " + k + " this many times: " + minuteCount[k]);
        //         }
        //     }
        // }
    }

    public class Operation implements Comparable<Operation>
    {
        public Date date;
        public int op; // -1, -2, or GuardID

        public Operation(Date date, int op)
        {
            if(date.getHours() == 23)
            {
                date.setHours(0);
                date.setMinutes(0);
            }
            this.date = date;
            this.op = op;
        }

        public String toString()
        {
            return String.format("{%s, %d}", this.date, this.op);
        }

        public int compareTo(Operation other)
        {
            return this.date.compareTo(other.date);
        }
    }
}