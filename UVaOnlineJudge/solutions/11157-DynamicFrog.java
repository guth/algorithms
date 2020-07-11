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
		// Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);
		//DecimalFormat df = new DecimalFormat("#.####");
		//System.out.printf("%s %.4f\n", name, ratio);
		
		int T = s.nextInt();
		int caseNum = 0;
		while(T-->0)
		{
			caseNum++;
			int N = s.nextInt();
			int D = s.nextInt();

			List<Integer> stones = new ArrayList<Integer>();

			stones.add(0);
			stones.add(0);
			for(int i=0; i<N; i++)
			{
				String line = s.next();
				String[] parts = line.split("-");

				char type = parts[0].charAt(0); // B (big) or S (small)
				int distance = Integer.parseInt(parts[1]);

				stones.add(distance);

				if(type == 'B')
				{
					stones.add(distance);
				}
			}
			stones.add(D);
			stones.add(D);

			int answer = Integer.MIN_VALUE;
			for(int i=2; i<stones.size(); i++)
			{
				answer = Math.max(answer, stones.get(i) - stones.get(i-2));
			}
			System.out.println("Case " + caseNum + ": " + answer);
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