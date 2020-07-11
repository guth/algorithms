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
		//Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);
		//DecimalFormat df = new DecimalFormat("#.####");
		//System.out.printf("%s %.4f\n", name, ratio);

		while(s.hasNextInt())
		{
			int N = s.nextInt();

			boolean isQueue = true;
			boolean isStack = true;
			boolean isPriorityQueue = true;
			Stack<Integer> stack = new Stack<Integer>();
			Queue<Integer> queue = new LinkedList<Integer>();
			PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(N, new LargestIntegerComparator());

			while(N-->0)
			{
				int op = s.nextInt();
				int value = s.nextInt();

				if(op == 1)
				{
					stack.push(value);
					queue.offer(value);
					priorityQueue.add(value);
				}
				else // op == 2
				{
					if(isQueue)
					{
						if(queue.isEmpty())
						{
							isQueue = false;
						}
						else
						{
							int test = queue.poll();
							if(test != value)
								isQueue = false;
						}
					}
					if(isStack)
					{
						if(stack.isEmpty())
						{
							isStack = false;
						}
						else
						{
							int test = stack.pop();
							if(test != value)
								isStack = false;
						}
					}
					if(isPriorityQueue)
					{
						if(priorityQueue.isEmpty())
						{
							isPriorityQueue = false;
						}
						else
						{
							int test = priorityQueue.poll();
							if(test != value)
								isPriorityQueue = false;
						}
					}
				}
			}

			int count = 0;
			if(isStack)
				count++;
			if(isQueue)
				count++;
			if(isPriorityQueue)
				count++;

			if(count == 0)
				System.out.println("impossible");
			else if(count > 1)
				System.out.println("not sure");
			else
			{
				if(isStack)
					System.out.println("stack");
				else if(isQueue)
					System.out.println("queue");
				else if(isPriorityQueue)
					System.out.println("priority queue");
			}
		}
	}

	public class LargestIntegerComparator implements Comparator<Integer>
	{
	    @Override
	    public int compare(Integer left, Integer right)
	    {
	    	return right - left;
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