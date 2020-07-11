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
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner s = new Scanner(System.in);

        // List<Integer> list = new ArrayList<Integer>();
        // while(s.hasNext())
        // {
        //     int value = s.nextInt();
        //     list.add(value);
        // }

        Node root = createNode(s);
        System.out.println(root);

        int sum = countSum(root, 0);
        System.out.println(sum);
    }

    public int countSum(Node node, int sum)
    {
        for(Integer value : node.metadata)
        {
            sum += value;
        }

        for(Node child : node.children)
        {
            int childSum = countSum(child, 0);
            sum += childSum;
        }

        return sum;
    }

    public Node createNode(Scanner s)
    {
        int numChildren = s.nextInt();
        int numMetadata = s.nextInt();
        Node curr = new Node(numChildren, numMetadata);

        if(numChildren == 0) // Base case
        {
            for(int i=0; i<numMetadata; i++)
            {
                curr.metadata.add(s.nextInt());
            }
        }
        else // Recursive case
        {
            for(int i=0; i<numChildren; i++)
            {
                Node child = createNode(s);
                curr.children.add(child);
            }

            for(int i=0; i<numMetadata; i++)
            {
                curr.metadata.add(s.nextInt());
            }
        }
        return curr;

    }

    // public Node createNode(List<Integer> list, int i)
    // {
    //     int numChildren = list.get(i);
    //     int numMetadata = list.get(i+1);
    //     Node curr = new Node(numChildren, numMetadata);

    //     if(numChildren == 0) // Base case
    //     {

    //     }
    //     else // Recursive case
    //     {

    //     }
    //     return null;

    // }

    public class Node
    {
        public int numChildren;
        public int numMetadata;
        public List<Node> children;
        public List<Integer> metadata;

        public Node(int numChildren, int numMetadata)
        {
            this.numChildren = numChildren;
            this.numMetadata = numMetadata;

            this.children = new ArrayList<Node>();
            this.metadata = new ArrayList<Integer>();
        }

        public String toString()
        {
            return "{" + numChildren + "," + numMetadata + "," + metadata.toString() + "}";
        }
    }
}