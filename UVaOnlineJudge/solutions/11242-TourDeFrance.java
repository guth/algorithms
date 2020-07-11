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

		while(true)
		{
			int F = s.nextInt();

			if(F == 0)
				break;

			int R = s.nextInt();

			int[] f = new int[F];
			int[] r = new int[R];

			for(int i=0; i<F; i++)
			{
				f[i] = s.nextInt();
			}
			for(int i=0; i<R; i++)
			{
				r[i] = s.nextInt();
			}

			double[] ratios = new double[F * R];
			//System.err.println("Ratios length: " + ratios.length);
			for(int i=0; i<F; i++)
			{
				for(int j=0; j<R; j++)
				{
					double ratio = r[j] / (double)f[i];

					//System.err.println("Inserting ratio number " + (j + i*R));
					ratios[j + i*R] = ratio;
				}
			}

			Arrays.sort(ratios);

			double maxSpread = -1;

			for(int i=0; i<ratios.length-1; i++)
			{
				double spread = ratios[i+1] / ratios[i];
				maxSpread = Math.max(maxSpread, spread);
			}
			// String.format("%.5g%n", 0.912300);
			// DecimalFormat df = new DecimalFormat("#.##");

			// System.out.println(df.format(maxSpread));
			System.out.println(String.format("%.3g", maxSpread));
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