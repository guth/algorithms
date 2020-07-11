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

			int[] car = new int[N];
			int[] change = new int[N];

			int[] start = new int[N];
			Arrays.fill(start, -1);

			for(int i=0; i<N; i++)
			{
				car[i] = s.nextInt();
				change[i] = s.nextInt();
			}
			
			boolean valid = true;

			for(int i=0; i<N; i++)
			{
				int newPos = i + change[i];

				if(newPos < 0 || newPos >= N || start[newPos] != -1)
				{
					valid = false;
					break;
				}

				start[newPos] = car[i];
			}

			if(valid)
				this.printArray(start);
			else
				System.out.println(-1);
		}
	}

	void printArray(int[] a)
	{
		for(int i=0; i<a.length; i++)
		{
			System.out.print(a[i]);
			if(i < a.length-1)
				System.out.print(" ");
		}
		System.out.println();
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