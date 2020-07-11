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
		//DecimalFormat df = new DecimalFormat("#.####");
		//System.out.printf("%s %.4f\n", name, ratio);

		int T = s.nextInt();

		while(T-->0)
		{
			int D = s.nextInt();

			String[] name = new String[D];
			int[] low = new int[D];
			int[] high = new int[D];

			for(int i=0; i<D; i++)
			{
				name[i] = s.next();
				low[i] = s.nextInt();
				high[i] = s.nextInt();
			}

			int Q = s.nextInt();

			while(Q-->0)
			{
				int P = s.nextInt();
				//System.err.println("P: " + P);

				String foundName = null;
				boolean multiple = false;
				for(int i=0; i<D; i++)
				{
					//System.err.println("Comparing P to " + low[i] + " and " + high[i]);
					if(low[i] <= P && P <= high[i])
					{
						//System.err.println("Within range.");
						if(foundName == null)
						{
							//System.err.println("Setting name to " + name[i]);
							foundName = name[i];
						}
						else
						{
							//System.err.println("Setting multiple to true");
							multiple = true;
						}
					}
				}

				if(foundName == null || multiple)
					System.out.println("UNDETERMINED");
				else
					System.out.println(foundName);
			}
			if(T >= 1)
				System.out.println();
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