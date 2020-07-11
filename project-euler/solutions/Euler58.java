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
		long total = 1;
		long numPrimes = 0;

		long times = 0;
		long plus = 2;
		long val = 1;
		while(true)
		{
			val += plus;

			if(isPrime(val))
			{
				numPrimes++;
			}
			total++;
			times++;
			if(times % 4 == 0)
				plus += 2;
			double ratio = ((double)numPrimes)/((double)total);
			if(ratio <= 0.1)
			{
				System.out.println(plus+1);
				System.out.println(ratio);
				break;
			}
		}
		
	}

	public boolean isPrime(long n)
	{
		if(n==2 || n==3)
			return true;
		if(n%2==0)
			return false;
		long L = (long)Math.sqrt(n);
		for(long k=3; k<=L; k+=2)
		{
			if(n%k==0)
			{
				return false;
			}
		}
		return true;
	}
}
