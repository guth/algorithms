import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Euler
{
	public static void main(String[] args) throws Exception
	{
		Euler e = new Euler();
		e.go();
	}

	public void go() throws Exception
	{
		List<Integer> primes = new ArrayList<Integer>();
		Set<Integer> primeSet = new HashSet<Integer>();
		for(int n=2; n<1000000; n++)
		{
			if(isPrime(n))
			{
				primes.add(n);
				primeSet.add(n);
			}
		}
		System.out.println("# of primes below one million: " + primes.size()); // 78,498
		System.out.println("Largest prime: " + primes.get(primes.size()-1)); // 999,983

		int ans = -1;
		int consecPrimes = -1;
		loop: for(int n=1000; n>=0; n--)
		{
			// System.out.println("Trying " + n + " consecutive primes");
			for(int i=0; i<primes.size(); i++)
			{
				// n consecutive primes starting at primes[i]
				if(i+n >= primes.size())
					break;
				long sum = 0;
				for(int k=0; k<n; k++)
				{
					sum += primes.get(i+k);
				}

				if(sum > 999983)
					break;

				if(primeSet.contains((int)sum))
				{
					ans = (int)sum;
					consecPrimes = n;
					break loop;
				}
			}
		}
		System.out.println(ans + " can be written as " + consecPrimes + " consecutive primes.");
		
	}

	public boolean isPrime(int n)
	{
		if(n==2 || n==3)
			return true;
		else if(n%2==0)
			return false;

		int L = (int)Math.sqrt(n);
		for(int k=3; k<=L; k+=2)
		{
			if(n%k==0)
				return false;
		}
		return true;
	}
}
