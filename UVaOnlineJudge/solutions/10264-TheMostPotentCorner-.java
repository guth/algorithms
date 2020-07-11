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
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);
		
		while(s.hasNextInt())
		{
			int N = s.nextInt();
			//System.err.println("N: " + N);
			int MAX = 1 << N;
			//System.err.println("MAX: " + MAX);
			int[] weight = new int[MAX];

			for(int k=0; k<MAX; k++)
			{
				weight[k] = s.nextInt();
			}

			int[] potency = new int[MAX];

			for(int corner=0; corner<MAX; corner++)
			{
				//System.err.println("Corner: " + corner);
				int total = 0;
				for(int i=0; i<N; i++)
				{
					//System.err.println("i: " + i);
					
					int neighbor = corner ^ (1 << i);
					//System.err.println("Neighbor index: " + neighbor);
					total += weight[neighbor];
				}
				//System.err.println("Potency of corner " + corner + " is " + total);
				potency[corner] = total;
			}

			int max = Integer.MIN_VALUE;

			for(int corner=0; corner<MAX; corner++)
			{
				for(int i=0; i<N; i++)
				{
					// TODO: Confirm XOR operator
					int neighbor = corner ^ (1 << i);
					int pairTotal = potency[corner] + potency[neighbor];

					if(pairTotal > max)
						max = pairTotal;
				}
			}

			//System.err.println("ANSWER: " + max);
			System.out.println(max);
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