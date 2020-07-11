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

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Set<Integer> repeats = new HashSet<Integer>();

		while(T-->0)
		{
			String line = br.readLine();
			String[] parts = line.split(" ");
			
			String op = parts[0];
			int n = Integer.parseInt(parts[1]);

			if(op.equals("insert"))
			{
				if(!map.containsKey(n))
				{
					map.put(n, 1);
				}
				else
				{
					map.put(n, map.get(n)+1);
					repeats.add(n);
				}
			}
			else if(op.equals("delete"))
			{
				if(map.containsKey(n))
				{
					map.put(n, map.get(n) - 1);	

					if(map.get(n) == 0)
					{
						map.remove(n);
					}
					else if(map.get(n) == 1)
					{
						repeats.remove(n);
					}
				}
			}
			String state = this.CurrentState(map, repeats);
			System.out.println(state);

			// System.err.println("----------------");
		}
	}

	String CurrentState(Map<Integer, Integer> map, Set<Integer> repeats)
	{
		if(map.keySet().size() == 0)
		{
			return "neither";
		}

		boolean homo = false;
		boolean hetero = false;
		
		if(map.keySet().size() >= 2)
			hetero = true;

		if(repeats.size() > 0)
			homo = true;

		if(homo && hetero)
			return "both";
		else if(!homo && !hetero)
			return "neither";

		return homo ? "homo" : "hetero";

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