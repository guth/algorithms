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
		boolean first = true;

		while(s.hasNextInt())
		{
			int N = s.nextInt();
			//System.err.println("N: " + N);
			if(!first && N != 0)
			{
				System.out.println();
			}
			first = false;

			if(N == 0)
				break;

			while(true)
			{
				int[] values = new int[N];

				values[0] = s.nextInt();
				//System.err.println("values[0]: " + values[0]);

				if(values[0] == 0)
				{
					//System.err.println();
					break;
				}

				for(int k=1; k<N; k++)
				{
					values[k] = s.nextInt();
					//System.err.println("values["+k+"]: " + values[k]);
				}

				Stack<Integer> stack = new Stack<Integer>();
				int curr = 0;
				for(int train=1; train<=N; train++)
				{
					if(values[curr] == train)
					{
						curr++;
						if(curr >= N)
							break;
						continue;
					}
					else
					{
						while(!stack.empty() && stack.peek() == values[curr] && curr < N)
						{
							curr++;
							stack.pop();
						}

						stack.push(train);
					}
				}

				while(!stack.empty() && curr < N)
				{
					if(stack.peek() == values[curr])
					{
						stack.pop();
						curr++;
					}
					else
					{
						break;
					}
				}

				if(curr >= N && stack.empty())
					System.out.println("Yes");
				else
					System.out.println("No");
			}
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