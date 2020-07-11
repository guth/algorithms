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
		//Parser p = new Parser(System.in);
		// Scanner s = new Scanner(System.in);
		// int T = p.nextInt();
		int T = Integer.parseInt(br.readLine());
		//BufferedWriter o = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder(50000);

		while(T-->0)
		{
			// int N = p.nextInt();
			int N = Integer.parseInt(br.readLine());

			Activity[] act = new Activity[N];

			for(int k=0; k<N; k++)
			{
				//act[k] = new Activity(p.nextInt(), p.nextInt());
				String[] parts = br.readLine().split(" ");
				act[k] = new Activity(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

			}

			Arrays.sort(act);

			//System.err.println(Arrays.toString(act));

			int max = 1;
			int last = 0;
			
			for(int i = 1; i < N; i++)
			{
				if(act[last].End <= act[i].Start)
				{
					//System.err.println("Updating last to " + i);
					last = i;
					max++;
				}
			}

			//System.out.println(max);
			sb.append(max + "\n");
		}
		System.out.print(sb.toString());
	}

	public class Activity implements Comparable<Activity>
	{
		public int Start;
		public int End;

		public Activity(int start, int end)
		{
			this.Start = start;
			this.End = end;
		}

		public int compareTo(Activity other)
		{
			return this.End - other.End;
		}

		@Override
		public String toString()
		{
			return "{" + this.Start + ", " + this.End + "}";
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