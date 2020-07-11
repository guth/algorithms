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
	
	private int MILLION = 1000000;

	void go() throws Exception
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);

		while(s.hasNextInt())
		{
			int N = s.nextInt();
			int M = s.nextInt();

			if(N == 0 && M == 0)
				break;

			boolean[] done = new boolean[MILLION + 1];
			boolean conflict = false;

			while(N-->0)
			{
				int start = s.nextInt();
				int end = s.nextInt();

				conflict = conflict || this.mark(done, start, end);
			}

			while(M-->0)
			{
				int start = s.nextInt();
				int end = s.nextInt();
				int interval = s.nextInt();

				while(start <= MILLION)
				{
					conflict = conflict || this.mark(done, start, end);

					start += interval;
					end += interval;
					if(end > MILLION)
						end = MILLION;
				}
			}


			if(conflict)
				System.out.println("CONFLICT");
			else
				System.out.println("NO CONFLICT");
		}
	}

	private boolean mark(boolean[] done, int start, int end)
	{
		boolean conflict = false;

		// if(done[start] || done[end])
		// 	conflict = true;

		for(int k=start; k<end && k<=MILLION; k++)
		{
			if(done[k])
			{
				conflict = true;
			}
			done[k] = true;
		}

		return conflict;
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