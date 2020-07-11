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

		boolean first = true;
		while(true)
		{
			int K = s.nextInt();

			if(K == 0)
				break;
			if(!first)
				System.out.println();
			first = false;

			int[] a = new int[K];
			for(int i=0; i<K; i++)
			{
				a[i] = s.nextInt();
			}

			Arrays.sort(a);

			for(int i=0; i<K; i++)
			{
				for(int j=i+1; j<K; j++)
				{
					for(int k=j+1; k<K; k++)
					{
						for(int l=k+1; l<K; l++)
						{
							for(int m=l+1; m<K; m++)
							{
								for(int n=m+1; n<K; n++)
								{
									if(fits(a[i]) && fits(a[j]) && fits(a[k]) && fits(a[l]) && fits(a[m]) && fits(a[n]))
									{
										System.out.println(a[i]+" "+a[j]+" "+a[k]+" "+a[l]+" "+a[m]+" "+a[n]);
									}
								}
							}
						}
					}
				}
			}
		}

	}

	public boolean fits(int k)
	{
		return (k >= 1) && (k <= 49);
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