import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args) throws Exception
	{
		Main m = new Main();
		m.go();
	}
	
	private Map<Integer, Long> map = new HashMap<Integer, Long>();

	void go() throws Exception
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);

		while(true)
		{
			int B = s.nextInt();
			int N = s.nextInt();

			if(B == 0 && N == 0)
				break;

			int[] cost = new int[N];
			int[] fun = new int[N];

			for(int i=0; i<N; i++)
			{
				cost[i] = s.nextInt();
				fun[i] = s.nextInt();
			}

			int[] memo = new int[B+1];

			for(int i=0; i<N; i++)
			{
				for(int c=B; c >= cost[i]; c--)
				{
					memo[c] = Math.max(memo[c], memo[c-cost[i]] + fun[i]);
				}
			}
			int max = memo[B];
			int actualCost = B;
			while(memo[actualCost-1] == max)
			{
				actualCost--;
			}
			System.out.println(actualCost + " " + memo[B]);
		}
	}

	private long solve(int n)
	{
		if(n < 12)
		{
			return n;
		}
		else if(map.containsKey(n))
		{
			return map.get(n);
		}
		else
		{
			long answer = solve(n/2) + solve(n/3) + solve(n/4);
			map.put(n, answer);
			return answer;
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