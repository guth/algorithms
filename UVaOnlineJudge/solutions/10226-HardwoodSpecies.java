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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		//DecimalFormat df = new DecimalFormat("#.####");
		
		int T = Integer.parseInt(br.readLine());
		br.readLine();

		while(T-->0)
		{
			List<String> names = new ArrayList<String>();
			Map<String, Integer> map = new HashMap<String, Integer>();
			int total = 0;
			String line;
			while((line = br.readLine()) != null && !line.equals(""))
			{
				total++;
				if(map.containsKey(line))
				{
					map.put(line, map.get(line) + 1);
				}
				else
				{
					names.add(line);
					map.put(line, 1);
				}
			}

			Collections.sort(names);

			for(int k=0; k<names.size(); k++)
			{
				String name = names.get(k);
				int count = map.get(name);
				double ratio = (count*100) / (double)total;
				//System.out.println(name + " " + df.format(ratio));
				System.out.printf("%s %.4f\n", name, ratio);
			}
			if(T > 0)
			{
				System.out.println();
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