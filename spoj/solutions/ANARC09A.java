import java.util.*;
import java.io.*;
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		int caseNum = 0;
		while(true)
		{
			caseNum++;
			char[] c = br.readLine().toCharArray();

			if(c[0] == '-')
				break;

			int N = c.length;
			int count = 0;
			int numOpen = 0;

			for(int i=0; i<N; i++)
			{
				if(c[i] == '{')
				{
					numOpen++;
				}
				else // c[i] == '}'
				{
					if(numOpen > 0)
					{
						numOpen--;
					}
					else
					{
						count++;
						numOpen++;
					}
				}
			}

			count += (numOpen / 2);

			System.out.println(caseNum + ". " + count);
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