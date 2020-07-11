import java.util.*;
import java.io.*;

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

		// int T = p.nextInt();
		int T = Integer.parseInt(br.readLine());
		int caseNum = 1;
		// System.out.println("T: " + T);
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());
			// System.out.println("N: " + N);

			Map<String, String> map = new HashMap<String, String>();
			Set<String> locations = new HashSet<String>();
			Set<String> endLocations = new HashSet<String>();

			for(int i=1; i<=N-1; i++)
			{
				String line = br.readLine();
				String[] parts = line.split(" ");

				map.put(parts[0], parts[1]);
				locations.add(parts[0]);
				locations.add(parts[1]);
				endLocations.add(parts[1]);
			}

			String start = null;
			for(String location : locations)
			{
				if(!endLocations.contains(location))
				{
					start = location;
				}
			}
			
			System.out.println("Scenario #" + caseNum + ":");

			String curr = start;
			while(map.containsKey(curr))
			{
				System.out.println(curr);

				curr = map.get(curr);
			}
			System.out.println(curr);

			caseNum++;
			System.out.println();
			// System.out.println("---------------");
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