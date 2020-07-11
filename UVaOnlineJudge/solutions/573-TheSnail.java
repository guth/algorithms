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
		//Parser s = new Parser(System.in);
		Scanner s = new Scanner(System.in);

// 		The input file contains one or more test cases, each on a line by itself. Each line contains four integers
// H, U, D, and F, separated by a single space. If H = 0 it signals the end of the input; otherwise,
// all four numbers will be between 1 and 100, inclusive. H is the height of the well in feet, U is the
// distance in feet that the snail can climb during the day, D is the distance in feet that the snail slides
// down during the night, and F is the fatigue factor expressed as a percentage. The snail never climbs
// a negative distance. If the fatigue factor drops the snail’s climbing distance below zero, the snail does
// not climb at all that day. Regardless of how far the snail climbed, it always slides D feet at night.

		while(true)
		{
			double H = s.nextDouble();
			double climbPerDay = s.nextDouble();
			double slidePerNight = s.nextDouble();
			double fatigue = s.nextDouble();
			//System.err.println(H + " " + U + " " + D + " " + F);

			if(H == 0.0)
				break;

			double lossPerDay = climbPerDay * (fatigue/100.0);

			boolean success = true;
			int day = 1;
			double currHeight = 0.0;

			while(true)
			{
				// Day climb
				currHeight += climbPerDay;
				if(currHeight > H)
				{
					success = true;
					break;
				}

				// Night fall (lol)
				currHeight -= slidePerNight;
				if(currHeight < 0)
				{
					success = false;
					break;
				}

				// End updates
				climbPerDay -= lossPerDay;
				if(climbPerDay < 0)
					climbPerDay = 0; // Or it's failure at this point?

				day++;
			}

			String message = success ? "success" : "failure";
			System.out.println(message +" on day " + day);
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