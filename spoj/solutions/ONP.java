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

		while(T-->0)
		{
			String line = br.readLine();
			char[] chars = line.toCharArray();

			// Queue<Character> variableQueue = new LinkedList<Character>();
			Stack<Character> operators = new Stack<Character>();

			for(int k=0; k<chars.length; k++)
			{
				char c = chars[k];

				if(c == ')')
				{
					char nextOp = operators.pop();
					System.out.print(nextOp);
				}
				else if(c == '(')
				{
					// No-op
				}
				else if(c == '^')
				{
					// Operator precedence doesn't matter because expressions are
					// fully enclosed in matching paranthesis
					operators.push(c);
				}
				else if(c == '*' || c == '/')
				{
					operators.push(c);
				}
				else if(c == '+' || c == '-')
				{
					operators.push(c);
				}
				else // variable
				{
					System.out.print(c);
				}
			}
			System.out.println();
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