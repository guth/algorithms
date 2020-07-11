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
		
		while(true)
		{
			int N = s.nextInt();
			if(N == 0)
				break;
			int E = s.nextInt();
			//System.err.println("N: " + N + ", E: " + E);

			Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();

			boolean[] visited = new boolean[N+1];
			double[] cost = new double[N+1];
			int[][] prob = new int[N+1][N+1];

			for(int i=1; i<=N; i++)
			{
				List<Integer> adj = new ArrayList<Integer>();
				graph.put(i, adj);
			}

			for(int k=0; k<E; k++)
			{
				int a = s.nextInt();
				int b = s.nextInt();
				int p = s.nextInt();

				//System.err.println("a: " + a + ", b: " + b + ", p: " + p);
				prob[a][b] = p;
				prob[b][a] = p;

				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			Arrays.fill(cost, -1.0);
			cost[1] = 1.0;

			while(true)
			{
				// Find the next node to expand.
				double maxP = -1.0;
				int curr = -1;
				for(int i=1; i<=N; i++)
				{
					if(maxP < cost[i] && !visited[i])
					{
						maxP = cost[i];
						curr = i;
					}
				}
				visited[curr] = true;

				// If we're at node N, then we're done.
				if(curr == N)
				{
					System.out.printf("%.6f percent\n", maxP * 100.0);
					break;
				}

				// Visit all neighbors of curr and update
				// their cost.
				for(int b : graph.get(curr))
				{
					double newProb = cost[curr] * (double)prob[curr][b] / 100.0;
					cost[b] = Math.max(cost[b], newProb);
				}
			}
		}
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