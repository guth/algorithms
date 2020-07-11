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

        //   0  1   2  3     4       5      6  7  8    9
        // Step C must be finished before step A can begin.
        // Step C must be finished before step F can begin.
        // Step A must be finished before step B can begin.
        // Step A must be finished before step D can begin.
        // Step B must be finished before step E can begin.
        // Step D must be finished before step E can begin.
        // Step F must be finished before step E can begin.

        Map<String, Set<String>> graph = new HashMap<String, Set<String>>();
        Map<String, Set<String>> requirements = new HashMap<String, Set<String>>();

        // Read input to build connection graph and requirements graph.
        String line;
        while((line = br.readLine()) != null)
        {
            String[] parts = line.split(" ");
            String a = parts[1];
            String b = parts[7];

            System.out.println(a + " --> " + b);

            if(!graph.containsKey(a))
                graph.put(a, new HashSet<String>());

            if(!requirements.containsKey(b))
                requirements.put(b, new HashSet<String>());

            graph.get(a).add(b);
            requirements.get(b).add(a);
        }

        // Print graph
        System.out.println("Graph:");
        for(String node : graph.keySet())
        {
            System.out.println(node + ": " + graph.get(node));
        }

        // Print requirements
        System.out.println("Requirements:");
        for(String node : requirements.keySet())
        {
            System.out.println(node + ": " + requirements.get(node));
        }

        // Construct initial queue
        PriorityQueue<String> queue = new PriorityQueue<String>();
        for(String node : graph.keySet())
        {
            Set<String> set = requirements.get(node);
            if(set == null || set.size() == 0)
            {
                queue.add(node);
            }
        }

        // Process!
        Set<String> visisted = new HashSet<String>();
        String result = "";

        while(!queue.isEmpty())
        {
            System.out.println("res: " + result);
            String curr = queue.poll();

            if(visisted.contains(curr))
                continue;

            result += curr;
            if(graph.containsKey(curr))
            {
                for(String node : graph.get(curr))
                {
                    requirements.get(node).remove(curr);
                    if(requirements.get(node).size() == 0)
                    {
                        queue.add(node);
                    }
                }
            }
        }

        System.out.println(result);
    }
}