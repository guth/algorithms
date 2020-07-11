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

		int N = s.nextInt();
		while(N-->0)
		{
			//System.err.println("N: " + N);
			int[][] grid = new int[5][5];
			for(int r=1; r<=3; r++)
			{
				int value = s.nextInt();
				grid[r][1] = value/100;
				grid[r][2] = (value % 100) / 10;
				grid[r][3] = value % 10;
			}

			int[][] zeroes = new int[5][5];

			if(areEqual(zeroes, grid))
			{
				System.out.println(-1);
			}
			else
			{
				int count = 0;
				while(true)
				{
					grid = f(grid);
					if(areEqual(zeroes, grid))
					{
						System.out.println(count);
						break;
					}
					count++;
				}
			}
		}
	}

	private int[][] f(int[][] grid)
	{
		int[][] copy = new int[5][5];

		for(int i=1; i<=3; i++)
		{
			for(int k=1; k<=3; k++)
			{
				copy[i][k] = grid[i][k+1] + grid[i][k-1] + grid[i+1][k] + grid[i-1][k];
				copy[i][k] = copy[i][k] % 2;
			}
		}

		return copy;
	}

	private boolean areEqual(int[][] a, int[][] b)
	{
		for(int i=1; i<=3; i++)
		{
			for(int k=1; k<=3; k++)
			{
				if(a[i][k] != b[i][k])
					return false;
			}
		}
		return true;
	}

	private void printGrid(int[][] grid)
	{
		for(int i=0; i<5; i++)
		{
			for(int k=0; k<5; k++)
			{
				System.out.print(grid[i][k]);
			}
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