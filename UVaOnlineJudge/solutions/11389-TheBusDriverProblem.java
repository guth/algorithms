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
			int N = s.nextInt(); // number of drivers
			int D = s.nextInt(); // max route length before overtime
			int R = s.nextInt(); // rate per hour of overtime

			if(N+D+R == 0)
			{
				break;
			}

			int[] morningRoutes = new int[N];
			int[] afternoonRoutes = new int[N];

			for(int i=0; i<N; i++)
			{
				morningRoutes[i] = s.nextInt();
			}

			for(int i=0; i<N; i++)
			{
				afternoonRoutes[i] = s.nextInt();
			}

			Arrays.sort(morningRoutes);
			Arrays.sort(afternoonRoutes);

			int minOvertime = 0;

			for(int i=0; i<N; i++)
			{
				int sum = morningRoutes[i] + afternoonRoutes[N-i-1];
				int diff = sum - D;
				if(diff > 0)
				{
					minOvertime += diff;
				}
			}

			System.out.println(minOvertime*R);
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