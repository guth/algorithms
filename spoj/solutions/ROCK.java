import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args) throws Exception
	{
		Main m = new Main();
		m.go();
	}
	
	private int[][] memo;
	private char[] c;
	private int N;
	void go() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		int T = Integer.parseInt(br.readLine());

		while(T-->0)
		{
			N = Integer.parseInt(br.readLine());
			c = br.readLine().toCharArray();

			memo = new int[N+1][N+1];

			for(int i=0; i<memo.length; i++)
			{
				Arrays.fill(memo[i], -1);
			}

			int ans = solve(0, N-1);
			System.out.println(ans);
		}
	}

	// input.txt expected answers: 9, 13, 10, 7, 0, 6
	private int solve(int a, int b)
	{
		if(a==b)
		{
			if(c[a] == '1')
				return 1;
			else
				return 0;
		}
		
		if(memo[a][b] != -1)
			return memo[a][b];

		int sweet = 0;
		int sour = 0;

		for(int i=a; i<=b; i++)
		{
			if(c[i]=='1')
				sweet++;
			else
				sour++;
		}

		if(sweet > sour)
		{
			memo[a][b] = b-a+1;
			return b-a+1;
		}

		int ans = 0;
		for(int i=a+1; i<=b; i++)
		{
			ans = Math.max(ans, solve(a, i-1) + solve(i, b));
		}
		memo[a][b] = ans;
		return ans;
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