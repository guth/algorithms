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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Parser p = new Parser(System.in);
		// Scanner s = new Scanner(System.in);

		while(true)
		{
			String[] parts = br.readLine().split(" ");
			int N = Integer.parseInt(parts[0]);
			int M = Integer.parseInt(parts[1]);

			if(N == 0 && M == 0)
				break;

			char[][] big = new char[N][N];
			char[][] small = new char[M][M];

			for(int r=0; r<N; r++)
			{
				big[r] = br.readLine().toCharArray();
			}
			// System.err.println("Big array");
			// printArray(big);

			for(int r=0; r<M; r++)
			{
				small[r] = br.readLine().toCharArray();
			}
			// System.err.println("Small array");
			// printArray(small);

			int times = 4;
			while(times-->0)
			{
				int count = countTimes(big, small);
				System.out.print(count);
				if(times != 0)
					System.out.print(" ");
				small = rotateArray(small);
			}
			System.out.println();
		}
	}

	int countTimes(char[][] big, char[][] small)
	{
		int N = big.length;
		int M = small.length;

		int count = 0;

		for(int R=0; R<N; R++)
		{
			for(int C=0; C<N; C++)
			{
				if(R+M-1 >= N || C+M-1 >= N)
					continue;
				
				boolean found = true;

				for(int r=0; r<M; r++)
				{
					if(!found)
						break;

					for(int c=0; c<M; c++)
					{
						if(small[r][c] != big[R+r][C+c])
						{
							found = false;
							break;
						}
					}
				}
				if(found)
					count++;
			}
		}


		return count;
	}

	char[][] rotateArray(char[][] grid)
	{
		int N = grid.length;
		char[][] newGrid = new char[N][N];

		for(int r=0; r<N; r++)
		{
			for(int c=0; c<N; c++)
			{
				//newGrid[r][c] = grid[c][N-r-1];
				newGrid[r][c] = grid[N-1-c][r];
			}
		}

		return newGrid;
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