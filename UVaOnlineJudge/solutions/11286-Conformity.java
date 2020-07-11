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
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		//DecimalFormat df = new DecimalFormat("#.####");
		//System.out.printf("%s %.4f\n", name, ratio);
		
		while(true)
		{
			int N = s.nextInt();

			if(N == 0)
				break;

			Map<String, Integer> map = new HashMap<String, Integer>();

			int[] courses = new int[5];
			int max = 1;

			for(int k=0; k<N; k++)
			{
				for(int i=0; i<5; i++)
				{
					courses[i] = s.nextInt();
				}

				Arrays.sort(courses);
				String key = courses[0] + "" + courses[1] + "" + courses[2] + "" + courses[3] + "" + courses[4];
				//System.err.println("key: " + key);

				if(map.containsKey(key))
				{
					int newValue = map.get(key) + 1;
					map.put(key, newValue);
					max = Math.max(max, newValue);
				}
				else
				{
					map.put(key, 1);
				}
			}

			int[] count = new int[10002];
			for(String key : map.keySet())
			{
				int value = map.get(key);
				count[value]++;
				max = Math.max(max, count[value]);
			}
			System.out.println(max);
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