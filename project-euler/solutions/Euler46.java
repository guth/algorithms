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
		int NP = 100000;
		Set<Long> primes = new HashSet<Long>();

		for(long n=3; primes.size()<NP; n+=2)
		{
			if(isPrime(n))
			{
				primes.add(n);
			}
		}

		long n = 35;
		while(true)
		{
			boolean success = true;
			if(isPrime(n))
			{
				n += 2;
				continue;
			}
			for(long s=1; 2*s*s<n; s++)
			{
				long diff = n-2*s*s;

				if(primes.contains(diff))
				{
					success = false;
					break;
				}
			}
			if(success)
				break;
			n+=2;
		}
		System.out.println(n + " cannot be written as the sum of a prime and 2x a square.");

	}

	public boolean isPrime(long n)
	{
		if(n==2 || n==3)
			return true;
		else if(n%2==0)
			return false;

		long L = (long)Math.sqrt(n);
		for(int k=3; k<=L; k+=2)
			if(n%k==0)
				return false;
		return true;
	}
}
