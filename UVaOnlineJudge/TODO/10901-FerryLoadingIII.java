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
			int N = s.nextInt();
			int T = s.nextInt();
			int M = s.nextInt();

			Car[] car = new Car[M];
			for(int i=0; i<M; i++)
			{
				car[i] = new Car(s.nextInt(), s.next());

				if(car.direction.equals(LEFT))
					leftQueue.offer(car);
				else
					rightQueue.offer(car);
			}

			int currTime = 0;
			while(true)
			{
				// At left bank, check for completion.
				if(leftQueue.isEmpty() && rightQueue.isEmpty())
					break;

				int numCarsOnBoard = 0;

				// Check for cars to take
				while(!leftQueue.isEmpty() && leftQueue.peek().time <= currTime && numCarsOnBoard < N)
				{
					Car car = queue.poll();
					numCarsOnBoard++;
				}

				// None on the left to take, see if left is coming before the right
				if(numCarsOnBoard == 0 && !leftQueue.isEmpty() &&
					(rightQueue.isEmpty() || rightQueue.peek().time <= leftQueue.peek().time))
				{
					currTime = leftQueue.poll().time;
					numCarsOnBoard++;
				}

				// Cross and unload cargo
				currTime += T;
				for(int i=0; i<numCarsOnBoard; i++)
					System.out.println(currTime);

				numCarsOnBoard = 0;

				// At right bank, check for completion.
				if(leftQueue.isEmpty() && rightQueue.isEmpty())
					break;
			}
		}
	}

	public class Car
	{
		public int time;
		public String direction;

		public Car(int time, String direction)
		{
			this.time = time;
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