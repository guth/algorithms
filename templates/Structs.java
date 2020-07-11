import java.util.*;
import java.io.*;
import java.text.*;

class Structs
{
    public static void main(String[] args) throws Exception
    {
        Structs m = new Structs();
        m.go();
    }

    void go() throws Exception
    {
        // String.format("%.5g%n", 0.912300);
        // DecimalFormat df = new DecimalFormat("#.##");
        // System.out.println(df.format(maxSpread));

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Parser s = new Parser(System.in);
        Scanner s = new Scanner(System.in);
        
        Queue<Integer> queue = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
        Set<String> set = new HashSet<String>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();

        BitSet bitSet = new BitSet(10);
    }

    public class Team implements Comparable<Team>
    {
        public boolean[] Solved = new boolean[10];
        public int[] IncorrectSubmissions = new int[10];
        public int NumSolved = 0;
        public int PenaltyTime = 0;
        public int Id;

        public Team(int id)
        {
            this.Id = id;
        }

        public int compareTo(Team other)
        {
            int solveDiff = (this.NumSolved - other.NumSolved) * -1;

            if(solveDiff != 0)
                return solveDiff;
            
            int penaltyDiff = (this.PenaltyTime - other.PenaltyTime) * -1;

            if(penaltyDiff != 0)
                return penaltyDiff;

            return other.Id - this.Id;
        }
    }
    
    private void makeSieve()
    {
        int MAX = 1000000;

        boolean[] isPrime = new boolean[MAX+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        List<Integer> primes = new ArrayList<Integer>();
        for(int k=2; k<=MAX; k++)
        {
            if(isPrime[k])
            {
                primes.add(k);
                for(int i=k+k; i<=MAX; i+=k)
                {
                    isPrime[i] = false;
                }
            }
        }
    }

    private boolean isPrime(int x)
    {
        if(x == 2)
            return true;
        if(x%2 == 0)
            return false;
        for(int i=3; i<=(int)Math.sqrt(x); i+=2)
        {
            if(x%i == 0)
                return false;
        }

        return true;
    }
}