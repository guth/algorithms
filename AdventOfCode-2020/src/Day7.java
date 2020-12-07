import java.util.*;
import java.io.*;
import java.text.*;

class Day7
{
    public static void main(String[] args) throws Exception
    {
        Day7 m = new Day7();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        // Read input. Store it for now, I have a feeling Part Two
        // will require reversing the already reversed graph.
        List<String> lines = new ArrayList<String>();
        String input;
        while ((input = br.readLine()) != null)
        {
            // Remove the trialing '.'
            lines.add(input.substring(0, input.length()-1));
        }



        // light red bags contain 1 bright white bag, 2 muted yellow bags
        // dark orange bags contain 3 bright white bags, 4 muted yellow bags
        // bright white bags contain 1 shiny gold bag
        // muted yellow bags contain 2 shiny gold bags, 9 faded blue bags
        // shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags
        // dark olive bags contain 3 faded blue bags, 4 dotted black bags
        // vibrant plum bags contain 5 faded blue bags, 6 dotted black bags
        // faded blue bags contain no other bags
        // dotted black bags contain no other bags
        // clear indigo bags contain 4 dark magenta bags, 2 shiny brown bags
        // posh salmon bags contain 
        // 4 dull tomato bags, 3 drab black bags, 5 mirrored chartreuse bags, 4 shiny blue bags
        // mirrored coral bags contain 4 faded aqua bags, 2 plaid plum bags, 5 striped tan bags, 5 wavy tomato bags

        Map<String, List<Edge>> graph = new HashMap<String, List<Edge>>();

        // Build the graph!
        for(String line : lines)
        {
            String[] parts = line.split(" bags contain ");
            String color = parts[0];

            List<Edge> edges = new ArrayList<Edge>();

            if(parts[1].equals("no other bags"))
            {
                // No-op
            }
            else
            {
                String[] connections = parts[1].split(", ");
                for(String connection : connections)
                {
                    String conn = connection.replace(" bags", "");
                    conn = conn.replace(" bag", "");
                    
                    int size = Integer.parseInt(conn.substring(0, 1));
                    String colorB = conn.substring(2, conn.length());

                    Edge e = new Edge(colorB, size);
                    edges.add(e);
                }
            }

            graph.put(color, edges);
        }

        System.out.println(graph + "\n\n");

        // Hopefully graph is used in Part Two. Let's build the reversed graph!
        Map<String, Set<String>> reversedGraph = new HashMap<String, Set<String>>();

        for(String line : lines)
        {
            String[] parts = line.split(" bags contain ");
            String color = parts[0];

            if(parts[1].equals("no other bags"))
            {
                // No-op
            }
            else
            {
                String[] connections = parts[1].split(", ");
                for(String connection : connections)
                {
                    String conn = connection.replace(" bags", "");
                    conn = conn.replace(" bag", "");
                    
                    int size = Integer.parseInt(conn.substring(0, 1));
                    String colorB = conn.substring(2, conn.length());

                    if(!reversedGraph.containsKey(colorB))
                    {
                        reversedGraph.put(colorB, new HashSet<String>());
                    }

                    reversedGraph.get(colorB).add(color);
                }
            }
        }

        System.out.println(reversedGraph + "\n\n");

        // Okay okay, compute Part One using reversedGraphs.
        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.add("shiny gold");
        String curr;
        int partOne = 0;
        do
        {
            curr = queue.poll();

            Set<String> nodes = reversedGraph.get(curr);
            if(nodes == null || nodes.size() == 0)
                continue;

            for(String node : nodes)
            {
                if(!visited.contains(node))
                {
                    partOne++;
                    visited.add(node);
                    queue.add(node);

                    // System.out.println("Adding to queue: " + node);
                }
            }
        }
        while(!queue.isEmpty());

        System.out.println("Part One: " + partOne);

        System.out.println("\n\n --------------------- \n\n");

        // Nailed it. Part Two requires the original graph.
        // Map<String, List<Edge>> graph = new HashMap<String, List<Edge>>();

        /**
        SG -> 1 do, 2 vb
        1 do -> 3 fb, 4 db
        2 fb -> 3 fb, 4 db
        - Therefore, don't keep a list of nodes visited.
        - Graph is acyclic, but will have repeat visited nodes.
        */

        visited = new HashSet<String>();
        Queue<State> queue2 = new LinkedList<State>();

        queue2.add(new State("shiny gold", 1));

        int partTwo = 0;
        while(!queue2.isEmpty())
        {
            State current = queue2.poll();

            // System.out.println("Color: " + current.color);

            List<Edge> edges = graph.get(current.color);

            for(Edge edge : edges)
            {
                partTwo += current.factor * edge.size;

                State next = new State(edge.color, current.factor * edge.size);
                queue2.add(next);
                // System.out.println("Adding next: " + next + ". Part Two: " + partTwo);
            }
        }

        System.out.println("Part Two: " + partTwo);
    }

    public class State
    {
        public String color;
        public int factor;

        public State(String color, int factor)
        {
            this.color = color;
            this.factor = factor;
        }

        @Override
        public String toString()
        {
            return "{" + this.color + "," + this.factor + "}";
        }
    }

    public class Edge
    {
        public String color;
        public int size;

        public Edge(String color, int size)
        {
            this.color = color;
            this.size = size;
        }

        @Override
        public String toString()
        {
            return "{" + this.color + "," + this.size + "}";
        }
    }
}