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
		int T = s.nextInt();
		while(T-->0)
		{
			int N = s.nextInt();
			int[][] a = new int[2*N][N*2];

			for(int r=0; r<N; r++)
			{
				for(int c=0; c<N; c++)
				{
					a[r][c] = s.nextInt();
					a[r][c+N] = a[r][c];
					a[r+N][c] = a[r][c];
					a[r+N][c+N] = a[r][c];
				}
			}

			for(int r=0; r<2*N; r++)
			{
				for(int c=0; c<2*N; c++)
				{
					if(r > 0) a[r][c] += a[r-1][c];
					if(c > 0) a[r][c] += a[r][c-1];
					if(r > 0 && c > 0) a[r][c] -= a[r-1][c-1];
				}
			}

			printArray(a);

			int max = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) // start row
			{
				for(int j=0; j<N; j++) // start col
				{
					for(int k=i; k<i+N; k++) // end row
					{
						for(int l=j; l<j+N; l++) // end col
						{
							int subRect = a[k][l];
							if(i > 0) subRect -= a[i-1][l];
							if(j > 0) subRect -= a[k][j-1];
							if(i > 0 && j > 0) subRect += a[i-1][j-1];

							max = Math.max(max, subRect);
						}
					}
				}
			}
			//System.err.println("Printing an answer");
			System.out.println(max);
		}
	}

	void printArray(int[][] a)
	{
		//System.err.println("Printing an array");
		for(int r=0; r<a.length; r++)
		{
			for(int c=0; c<a[r].length; c++)
			{
				//System.err.print(a[r][c] + " ");
			}
			//System.err.println();
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