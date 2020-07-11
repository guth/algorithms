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
		int maxPrimes = -1;
		int maxProd = -1;
		for(int a=-1000; a<=1000; a++)
		{
			for(int b=-1000; b<=1000; b++)
			{
				long n = 0;
				int numPrimes = 0;
				while(true)
				{
					long val = n*n + a*n + b;
					if(val > 0 && isPrime(val))
					{
						numPrimes++;
					}
					else
					{
						break;
					}
					n++;
				}
				if(numPrimes > maxPrimes)
				{
					maxPrimes = numPrimes;
					maxProd = a*b;
				}
			}
		}
		
		System.out.println("a*b = " + maxProd + " with " + maxPrimes + " consecutive primes.");
	}
	
	public boolean isPrime(long n)
	{
		if(n==2 || n==3)
			return true;
		if(n%2==0)
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
