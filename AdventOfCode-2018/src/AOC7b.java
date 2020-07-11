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
        for(String id : graph.keySet())
        {
            System.out.println(id + ": " + graph.get(id));
        }

        // Print requirements
        System.out.println("Requirements:");
        for(String id : requirements.keySet())
        {
            System.out.println(id + ": " + requirements.get(id));
        }

        // Construct initial queue
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        for(String id : graph.keySet())
        {
            Set<String> set = requirements.get(id);
            if(set == null || set.size() == 0)
            {
                queue.add(new Node(id));
            }
        }

        List<Worker> workers = new ArrayList<Worker>();
        workers.add(new Worker(null, -1));
        workers.add(new Worker(null, -1));
        workers.add(new Worker(null, -1));
        workers.add(new Worker(null, -1));
        workers.add(new Worker(null, -1));
        workers.add(new Worker(null, -1));

        //String result = "";
        int time = 0;
        Set<String> visited = new HashSet<String>();

        // Process!
        while(true) // Each loop is one second.
        {
            // Process current work
            for(Worker worker : workers)
            {
                if(worker.id != null)
                    worker.timeLeft--;
            }

            // Remove jobs that are finished and update graph
            for(Worker worker : workers)
            {
                if(worker.id != null && worker.timeLeft == 0)
                {
                    System.out.println("Finished " + worker.id);
                    if(graph.containsKey(worker.id))
                    {
                        for(String node : graph.get(worker.id))
                        {
                            requirements.get(node).remove(worker.id);
                            if(requirements.get(node).size() == 0)
                            {
                                System.out.println("Adding " + node + " to the queue.");
                                queue.add(new Node(node));
                            }
                        }
                    }

                    worker.id = null;
                    worker.timeLeft = -1;
                }
            }

            // Add work to workers.
            for(Worker worker : workers)
            {
                if(!queue.isEmpty() && worker.id == null)
                {
                    Node node = queue.poll();
                    worker.id = node.id;
                    worker.timeLeft = node.cost;

                    System.out.println("Giving " + worker.id + " to a worker with cost: " + worker.timeLeft);
                }
            }

            // If all workers are done, exit.
            if(this.allWorkersDone(workers))
                break;

            time++;
            // System.out.println("Current time: " + time);
        }

        System.out.println(time);

        // Process!
        // Set<String> visisted = new HashSet<String>();
        // String result = "";

        // while(!queue.isEmpty())
        // {
        //     System.out.println("res: " + result);
        //     String curr = queue.poll();

        //     if(visisted.contains(curr))
        //         continue;

        //     result += curr;
        //     if(graph.containsKey(curr))
        //     {
        //         for(String node : graph.get(curr))
        //         {
        //             requirements.get(node).remove(curr);
        //             if(requirements.get(node).size() == 0)
        //             {
        //                 queue.add(node);
        //             }
        //         }
        //     }
        // }

        // System.out.println(result);
    }

    private boolean allWorkersDone(List<Worker> workers)
    {
        for(Worker worker : workers)
        {
            if(worker.timeLeft != -1)
                return false;
        }
        return true;
    }

    public class Worker
    {
        public String id;
        public int timeLeft;

        public Worker(String id, int timeLeft)
        {
            this.id = id;
            this.timeLeft = timeLeft;
        }
    }

    public class Node implements Comparable<Node>
    {
        public String id;
        public int cost;

        public Node(String id)
        {
            this.id = id;
            this.cost = id.toCharArray()[0] - 'A' + 61;
        }

        public String toString()
        {
            return "{" + this.id + "," + this.cost + "}";
        }

        public int compareTo(Node other)
        {
            return this.id.compareTo(other.id);
        }
    }
}