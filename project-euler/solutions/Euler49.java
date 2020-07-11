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
		Set<Integer> primes = new HashSet<Integer>();
		for(int n=1000; n<=9999; n++)
		{
			if(isPrime(n))
			{
				primes.add(n);
			}
		}
		System.out.println("# of four digit primes: " + primes.size());

		for(int p : primes)
		{
			for(int q : primes)
			{
				if(p >= q)
					continue;

				int next = (q-p)+q;
				if(primes.contains(next))
				{
					char[] a = (""+p).toCharArray();
					char[] b = (""+q).toCharArray();
					char[] c = (""+next).toCharArray();

					Arrays.sort(a);
					Arrays.sort(b);
					Arrays.sort(c);

					if(arrayEqual(a,b) && arrayEqual(b,c))
					{
						System.out.println("Possible solution: " + p + ", " + q + ", " + next);
					}
				}
			}
		}
	}

	public boolean arrayEqual(char[] a, char[] b)
	{
		for(int i=0; i<a.length && i<b.length; i++)
		{
			if(a[i] != b[i])
				return false;
		}
		return true;
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
