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

		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, -1, 0, 1};
		int[] stepInc = {0, 1, 0, 1};

		while(true)
		{
			int N = s.nextInt();
			int P = s.nextInt();

			if(N == 0 && P == 0)
				break;
			
			//int[][] grid = new int[N+5][N+5];

			int r = 0;
			int c = 0;

			if(P == 1)
			{
				c = N/2 + 1;
				r = N/2 + 1;
			}
			else
			{
				long min = -1;
				long max = -1;

				// Find the odd square P falls between
				for(long i = 1; i < N; i += 2)
				{
					long oddSquare = i*i;
					long nextOddSquare = (i+2)*(i+2);

					if(oddSquare < P && P <= nextOddSquare)
					{
						min = oddSquare;
						max = nextOddSquare;
					}
				}

				System.err.println("min: " + min + ", max: " + max);
			}


			System.out.println("Line = " + c + ", column = " + r + ".");
		}
	}

	void printArray(char[][] grid)
	{
		for(int r=0; r<grid.length; r++)
		{
			for(int c=0; c<grid[r].length; c++)
			{
				System.err.print(grid[r][c]);
			}
			System.err.println();
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