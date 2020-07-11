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
		int ans = -1;
		for(int n=600; ;n++)
		{
			if(isPrime(n))
				continue;

			if(hasFourDistinctPrimeFactors(n)
					&& hasFourDistinctPrimeFactors(n+1)
					&& hasFourDistinctPrimeFactors(n+2)
					&& hasFourDistinctPrimeFactors(n+3))
			{
				ans = n;
				break;
			}
		}
		System.out.println(ans);
	}

	public boolean hasFourDistinctPrimeFactors(int n)
	{
		Set<Integer> facts = new HashSet<Integer>();
		for(int k=2; k<=n; k++)
		{
			while(n%k==0)
			{
				facts.add(k);
				n /= k;
				if(!isPrime(k))
					return false;
			}
		}

		return (facts.size() == 4);
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
