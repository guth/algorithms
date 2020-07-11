import java.util.*;
import java.io.*;
import java.math.*;

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
		Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		
		int T = s.nextInt();

		List<Integer> primes = new ArrayList<Integer>();

		for(int i=1001; i<=9997; i+=2)
		{
			if(isPrime(i))
				primes.add(i);
		}

		Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
		for(int prime : primes)
		{
			graph.put(prime, new ArrayList<Integer>());
		}

		// Construct our graph.
		for(int i=0; i<primes.size(); i++)
		{
			for(int k=i+1; k<primes.size(); k++)
			{
				int a = primes.get(i);
				int b = primes.get(k);
				if(isOneOff(a, b))
				{
					graph.get(a).add(b);
					graph.get(b).add(a);
				}
			}
		}

		while(T-->0)
		{
			int a = s.nextInt();
			int b = s.nextInt();

			// Perform a bfs from a to b
			boolean[] visited = new boolean[10000+1];

			Queue<State> queue = new LinkedList<State>();
			queue.offer(new State(a, 0));

			boolean possible = false;

			while(!queue.isEmpty())
			{
				State curr = queue.poll();

				if(visited[curr.id])
					continue;

				visited[curr.id] = true;

				if(curr.id == b)
				{
					possible = true;
					System.out.println(curr.cost);
					break;
				}

				for(int prime : graph.get(curr.id))
				{
					if(!visited[prime])
					{
						State next = new State(prime, curr.cost + 1);
						queue.offer(next);
					}
				}
			}

			if(!possible)
				System.out.println("Impossible");
		}
	}

	public class State
	{
		public int id;
		public int cost;

		public State(int id, int cost)
		{
			this.id = id;
			this.cost = cost;
		}
	}

	boolean isOneOff(int a, int b)
	{
		char[] c1 = (a + "").toCharArray();
		char[] c2 = (b + "").toCharArray();

		int numOff = 0;
		for(int i=0; i<c1.length; i++)
		{
			if(c1[i] != c2[i])
			{
				numOff++;
			}
		}

		return (numOff == 1);
	}

	// Sieve of Eratosthenes would be faster but this is
	// sufficient for this problem
	boolean isPrime(int n)
	{
		if(n==2 || n==3)
			return true;
		if(n%2==0 || n==1)
			return false;

		for(int i=3; i<=(int)Math.sqrt(n); i+=2)
		{
			if(n % i == 0)
				return false;
		}

		return true;
	}

	public class Parser
	{
	   final private int BUFFER_SIZE = 1 << 16;
 
	   private DataInputStream din;
	   private byte[] buffer;
	   private int bufferPointer, bytesRead;
 
	   public Parser(InputStream in)
	   {
	      din = new DataInputStream(in);
	      buffer = new byte[BUFFER_SIZE];
	      bufferPointer = bytesRead = 0;
	   }
 
	   public int nextInt() throws Exception
	   {
	      int ret = 0;
	      byte c = read();
	      while (c <= ' ') c = read();
	      
	      boolean neg = c == '-';
	      if (neg) c = read();
	      
	      do
	      {
	         ret = ret * 10 + c - '0';
	         c = read();
	      } while (c > ' ');
	      
	      if (neg) return -ret;
	      
	      return ret;
	   }
	   
	   public long nextLong() throws Exception
	   {
		      long ret = 0;
		      byte c = read();
		      while (c <= ' ') c = read();
		      
		      boolean neg = c == '-';
		      if (neg) c = read();
		      
		      do
		      {
		         ret = ret * 10 + c - '0';
		         c = read();
		      } while (c > ' ');
		      
		      if (neg) return -ret;
		      
		      return ret;
	   }
 
	   private void fillBuffer() throws Exception
	   {
	      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
	      if (bytesRead == -1) buffer[0] = -1;
	   }
 
	   private byte read() throws Exception
	   {
	      if (bufferPointer == bytesRead) fillBuffer();
	      return buffer[bufferPointer++];
	   }
	}
}