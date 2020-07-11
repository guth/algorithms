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
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);

		int C = s.nextInt();
		
		Queue<Car> leftQueue = new LinkedList<Car>();
		Queue<Car> rightQueue = new LinkedList<Car>();

		while(C-->0)
		{
			int L = s.nextInt() * 100;

			int M = s.nextInt();

			while(M-->0)
			{
				Car car = new Car(s.nextInt(), s.next());
				if(car.direction.equals(LEFT))
					leftQueue.offer(car);
				else
					rightQueue.offer(car);
			}

			int crosses = 0;
			while(true)
			{
				// Start on left side. Check for completion.
				if(leftQueue.isEmpty() && rightQueue.isEmpty())
					break;

				// Take as many from left as we can
				int size = 0;
				while(!leftQueue.isEmpty() && leftQueue.peek().length+size <= L)
				{
					Car car = leftQueue.poll();
					//System.err.println("Taking " + car.length + " from left");
					size += car.length;
				}

				// Cross to the right and unload
				crosses++;
				size = 0;

				// Start on right side, check for completion.
				if(leftQueue.isEmpty() && rightQueue.isEmpty())
					break;

				// Take as many from right as we can.
				while(!rightQueue.isEmpty() && rightQueue.peek().length+size <= L)
				{
					Car car = rightQueue.poll();
					//System.err.println("Taking " + car.length + " from right");
					size += car.length;
				}

				// Cross to the left and unload.
				crosses++;
				size = 0;
			}

			System.out.println(crosses);
		}
	}

	public class Car
	{
		public int length;
		public String direction;

		public Car(int length, String direction)
		{
			this.length = length;
			this.direction = direction;
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