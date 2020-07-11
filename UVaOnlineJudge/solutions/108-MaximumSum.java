import java.util.*;
import java.io.*;
import java.text.*;
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
		// Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);
		//DecimalFormat df = new DecimalFormat("#.####");
		//System.out.printf("%s %.4f\n", name, ratio);
		
		while(s.hasNextInt())
		{
			int N = s.nextInt();

			int[][] a = new int[N][N];

			for(int r=0; r<N; r++)
			{
				for(int c=0; c<N; c++)
				{
					a[r][c] = s.nextInt();
				}
			}
			//printArray(a, N);
			int[][] subA = new int[N][N];

			for(int r=0; r<N; r++)
			{
				for(int c=0; c<N; c++)
				{
					subA[r][c] = a[r][c];
					if(r > 0)
						subA[r][c] += subA[r-1][c];
					if(c > 0)
						subA[r][c] += subA[r][c-1];
					if(r > 0 && c > 0)
						subA[r][c] -= subA[r-1][c-1];
				}
			}
			//printArray(subA, N);

			int max = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) // start row
			{
				for(int j=0; j<N; j++) // start col
				{
					for(int k=i; k<N; k++) // end row
					{
						for(int l=j; l<N; l++) // end col
						{
							int subRect = subA[k][l];
							if(i > 0) subRect -= subA[i-1][l];
							if(j > 0) subRect -= subA[k][j-1];
							if(i > 0 && j > 0) subRect += subA[i-1][j-1];

							max = Math.max(subRect, max);
						}
					}
				}
			}

			System.out.println(max);
		}
	}

	void printArray(int[][] a, int N)
	{
		System.out.println("Printing an array.");
		for(int r=0; r<N; r++)
		{
			for(int c=0; c<N; c++)
			{
				System.out.print(a[r][c] + " ");
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