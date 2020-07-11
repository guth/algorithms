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
		// Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);
		//DecimalFormat df = new DecimalFormat("#.####");
		//System.out.printf("%s %.4f\n", name, ratio);
		
		while(s.hasNextInt())
		{
			int N = s.nextInt();
			int L = s.nextInt();
			int U = s.nextInt();

			// int highBitU = getHighestBit(U);
			//System.err.println("Highest bit: " + highBitU);

			// N = N & ((1 << (highBitU+1)) - 1);

			int highBitN = getHighestBit(N);
			int mask = 0;

			for(int i = highBitN; i >= 0; i--)
			{
				if((N & (1 << i)) > 0) // i'th bit of N is on
				{
					// Keep it off in the mask if we can
					int remainingMask = (1 << i) - 1;
					if((mask | remainingMask) >= L)
					{
						continue;
					}
					else
					{
						// Need to turn it on to be greater than L
						mask = mask | (1 << i);
					}
				}
				else // i'th bit of N is off
				{
					// If we can turn this bit on, do it.
					if((mask | (1 << i)) <= U)
					{
						mask = mask | (1 << i);
					}
				}
			}

			System.out.print(mask);

			if(s.hasNextInt())
			{
				System.out.println();
			}
		}
	}

	private int getHighestBit(int n)
	{
		for(int i = 31; i >= 0; i--)
		{
			if((n & (1 << i)) > 0)
			{
				return i;
			}
		}

		return -1;
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