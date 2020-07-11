import java.util.*;
import java.io.*;

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
		Parser p = new Parser(System.in);
		// Scanner s = new Scanner(System.in);

		// int T = p.nextInt();
		// int T = Integer.parseInt(br.readLine());
		while(true)
		{
			int N = p.nextInt();

			if(N == 0)
				break;

			int[] trucks = new int[N];
			for(int i=0; i<N; i++)
			{
				trucks[i] = p.nextInt();
			}

			boolean success = true;

			Stack<Integer> stack = new Stack<Integer>();
			int next = 1;
			for(int i=0; i<trucks.length; i++)
			{
				if(trucks[i] == next)
				{
					next++;
					continue;
				}
				
				while(!stack.isEmpty() && stack.peek() == next)
				{
					stack.pop();
					next++;
				}

				stack.push(trucks[i]);
			}

			while(!stack.isEmpty())
			{
				int check = stack.pop();
				if(next == check)
				{
					next++;
					continue;
				}
				else
				{
					success = false;
					break;
				}
			}

			System.out.println(success ? "yes" : "no");
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