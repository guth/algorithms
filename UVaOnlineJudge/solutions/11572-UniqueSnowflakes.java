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

	private String LEFT = "left";
	private String RIGHT = "right";

	void go() throws Exception
	{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		//DecimalFormat df = new DecimalFormat("#.####");
		//System.out.printf("%s %.4f\n", name, ratio);
		
		int T = s.nextInt();

		while(T-->0)
		{
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();

			int N = s.nextInt();

			int size = 0;
			int max = Integer.MIN_VALUE;

			int[] flakes = new int[N+1];

			for(int i=1; i<=N; i++)
			{
				flakes[i] = s.nextInt();
			}

			for(int i=1; i<=N; i++)
			{
				int flake = flakes[i];
				
				if(!map.containsKey(flake))
				{
					size++;
					map.put(flake, i);

					// System.err.println("First time seeing flake " + flake + " at index " + i);
					// System.err.println("Size is now " + size);
				}
				else
				{
					// System.err.println("Seeing duplicate flake " + flake + " at index " + i);

					i = map.get(flake);
					size = 0;
					map = new HashMap<Integer, Integer>();

					// System.err.println("Resetting i to " + i);

					// int oldIndex = map.get(flake);
					// map.put(flake, i);

					// size = i - oldIndex;

					// System.err.println("Flake " + flake + ", old index " + oldIndex + ", new i " + i);
					// System.err.println("Size is now " + size);
				}
				max = Math.max(max, size);
				// System.err.println("Max is now " + max);
			}

// 1 2 3 4 5 2 6
// 1 2 3 4 5 6 7

// size = 4
			System.out.println(max);
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