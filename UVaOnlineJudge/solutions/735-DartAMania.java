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
			int x = s.nextInt();

			if(x <= 0)
				break;

			Set<String> combos = new HashSet<String>();
			//List<String> permutations = new ArrayList<String>();
			int perms = 0;

			Set<Integer> scores = new HashSet<Integer>();
			for(int i=0; i<=20; i++)
			{
				scores.add(i);
				scores.add(i*2);
				scores.add(i*3);
			}
			scores.add(50);

			for(Integer i : scores)
			{
				for(Integer j : scores)
				{
					for(Integer k : scores)
					{
						if(i+j+k == x)
						{
							int[] nums = new int[3];
							nums[0] = i;
							nums[1] = j;
							nums[2] = k;
							Arrays.sort(nums);

							String sorted = nums[0] + "," + nums[1] + "," + nums[2];

							combos.add(sorted);
							perms++;
						}
					}
				}
			}

			if(perms == 0)
			{
				System.out.println("THE SCORE OF " + x + " CANNOT BE MADE WITH THREE DARTS.");
			}
			else
			{
				System.out.println("NUMBER OF COMBINATIONS THAT SCORES " + x + " IS " + combos.size() + ".");
				System.out.println("NUMBER OF PERMUTATIONS THAT SCORES " + x + " IS " + perms + ".");
			}
			// NUMBER OF COMBINATIONS THAT SCORES x IS c.
			// NUMBER OF PERMUTATIONS THAT SCORES x IS p.
			// THE SCORE OF x CANNOT BE MADE WITH THREE DARTS.


			System.out.println("**********************************************************************");
		}

		System.out.println("END OF OUTPUT");

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