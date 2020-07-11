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
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Parser s = new Parser(System.in);
		// Scanner s = new Scanner(System.in);

		while(true)
		{
			int S = s.nextInt();
			int B = s.nextInt();

			if(S == 0 && B == 0)
				break;

			Node[] soldiers = new Node[S+2];

			for(int i=1; i<=S; i++)
			{
				soldiers[i] = new Node(i);
			}

			for(int i=1; i<=S; i++)
			{
				soldiers[i].prev = soldiers[i-1];
				soldiers[i].next = soldiers[i+1];
			}

			for(int b=0; b<B; b++)
			{
				int L = s.nextInt();
				int R = s.nextInt();

				Node prevAlive = soldiers[L].prev;
				Node nextAlive = soldiers[R].next;

				String left = "*";
				String right = "*";

				if(prevAlive != null)
				{
					prevAlive.next = nextAlive;
					left = "" + prevAlive.id;
				}

				if(nextAlive != null)
				{
					nextAlive.prev = prevAlive;
					right = "" + nextAlive.id;
				}

				System.out.println(left + " " + right);
			}

			System.out.println("-");
		}
	}

	public class Node
	{
		public int id;
		public Node next;
		public Node prev;

		public Node(int id)
		{
			this.id = id;
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