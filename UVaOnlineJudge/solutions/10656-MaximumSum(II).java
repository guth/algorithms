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
		Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		//DecimalFormat df = new DecimalFormat("#.####");
		//System.out.printf("%s %.4f\n", name, ratio);
		
		while(true)
		{
			int N = s.nextInt();
			if(N == 0)
			{
				break;
			}

			List<Integer> list = new ArrayList<Integer>();

			for(int i=0; i<N; i++)
			{
				int value = s.nextInt();

				if(value > 0)
				{
					list.add(value);
				}
			}

			if(list.size() == 0)
			{
				System.out.println("0");
			}
			else
			{
				for(int i=0; i<list.size(); i++)
				{
					System.out.print(list.get(i));
					if(i != list.size() - 1)
					{
						System.out.print(" ");
					}
				}
				System.out.println();
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