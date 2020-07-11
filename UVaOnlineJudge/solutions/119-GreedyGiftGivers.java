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
		

		while(s.hasNextInt())
		{
			Map<String, Integer> map = new HashMap<String, Integer>();
			
			int N = s.nextInt();
			String[] names = new String[N];

			for(int k=0; k<N; k++)
			{
				names[k] = s.next();
				map.put(names[k], 0);
			}

			for(int k=0; k<N; k++)
			{
				String giver = s.next();
				int amountGiven = s.nextInt();
				int numPeople = s.nextInt();

				if(numPeople == 0)
					continue;

				int amountKept = amountGiven % numPeople;
				map.put(giver, -1*amountGiven + amountKept + map.get(giver));

				int amountGivenPerPerson = amountGiven / numPeople;
				for(int i=0; i<numPeople; i++)
				{
					String person = s.next();
					map.put(person, amountGivenPerPerson + map.get(person));
				}
			}

			for(int k=0; k<N; k++)
			{
				System.out.println(names[k] + " " + map.get(names[k]));
			}

			if(s.hasNextInt())
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