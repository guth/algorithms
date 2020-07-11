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

        Node root = createNode(s);
        System.out.println(root);

        int value = findValue(root);
        System.out.println(value);
    }

    public int findValue(Node node)
    {
        if(node.children.size() == 0)
        {
            int value = 0;
            for(Integer num : node.metadata)
            {
                value += num;
            }
            return value;
        }
        else
        {
            int value = 0;
            for(Integer index : node.metadata)
            {
                index--;
                if(index >= 0 && index <= node.children.size()-1)
                {
                    value += this.findValue(node.children.get(index));
                }
            }

            return value;
        }
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