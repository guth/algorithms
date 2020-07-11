import java.util.*;
import java.io.*;
import java.math.*;

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
		Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		
		List<Integer> primes = new ArrayList<Integer>();
		int MAX = 1000000;
		boolean[] isPrime = new boolean[MAX+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		for(int k=2; k<=MAX; k++)
		{
			if(isPrime[k])
			{
				primes.add(k);
				for(int i=k+k; i<=MAX; i+=k)
				{
					isPrime[i] = false;
				}
			}
		}

		int caseNum = 0;
		while(true)
		{
			caseNum++;
			int a = s.nextInt();
			int b = s.nextInt();

			if(a == 0 && b == 0)
				break;

			Map<Integer, Integer> mapA = new HashMap<Integer, Integer>();
			Map<Integer, Integer> mapB = new HashMap<Integer, Integer>();

			// Build our prime factorization map
			for(int prime : primes)
			{
				while(a > 1 && a % prime == 0)
				{
					if(!mapA.containsKey(prime))
					{
						mapA.put(prime, 1);
					}
					else
					{
						mapA.put(prime, mapA.get(prime) + 1);
					}

					a /= prime;
				}

				while(b > 1 && b % prime == 0)
				{
					if(!mapB.containsKey(prime))
					{
						mapB.put(prime, 1);
					}
					else
					{
						mapB.put(prime, mapB.get(prime) + 1);
					}

					b /= prime;
				}
			}

			// X is the number of unique primes in the
			// prime factorizations of A and B
			int X = 0;
			X += mapA.keySet().size();
			for(int key : mapB.keySet())
			{
				if(!mapA.containsKey(key))
				{
					X++;
				}
			}

			// D is the distance between the two numbers.
			// This is the absolute value of the difference
			// in the values of the prime powers.
			// Consider 315 = 3^2 x 5^1 x 7^1
			//      and  75 = 3^1 x 5^2 x 7^0
			// The distance is 3.
			int D = 0;
			for(int prime : mapA.keySet())
			{
				if(mapB.containsKey(prime))
				{
					D += Math.abs(mapA.get(prime) - mapB.get(prime));
				}
				else
				{
					D += mapA.get(prime);
				}
			}

			for(int prime : mapB.keySet())
			{
				if(!mapA.containsKey(prime))
				{
					D += mapB.get(prime);
				}
			}
			
			System.out.println(caseNum + ". " + X + ":" + D);
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