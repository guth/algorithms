import java.util.*;
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
		//Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);

// 		The first line of input consists of four integers: 1 ≤ N ≤ 200, the number of participants, 1 ≤ B ≤
// 500000, the budget, 1 ≤ H ≤ 18, the number of hotels to consider, and 1 ≤ W ≤ 13, the number
// of weeks you can choose between. Then follow two lines for each of the H hotels. The first gives
// 1 ≤ p ≤ 10000, the price for one person staying the weekend at the hotel. The second contains W
// integers, 0 ≤ a ≤ 1000, giving the number of available beds for each weekend at the hotel.

		while(s.hasNextInt())
		{
			int N = s.nextInt();
			int B = s.nextInt();
			int H = s.nextInt();
			int W = s.nextInt();

			int minCost = Integer.MAX_VALUE;

			while(H-->0)
			{
				int cost = s.nextInt();
				for(int i=0; i<W; i++)
				{
					int available = s.nextInt();

					if(available >= N && N*cost <= B)
					{
						minCost = Math.min(minCost, N*cost);
					}
				}
			}

			if(minCost != Integer.MAX_VALUE)
				System.out.println(minCost);
			else
				System.out.println("stay home");
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