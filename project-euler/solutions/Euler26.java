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
		int max = -1;
		int maxN = -1;
		for(int n=2; n<=1000; n++)
		{
			int length = cycleLength(n);
			if(max < length)
			{
				max = length;
				maxN = n;
			}
		}
		System.out.println("Max cycle for 1/n is n = " + maxN + " with a cycle length of " + max);
	}
	
	public int cycleLength(int n)
	{
//		System.out.println("Starting " + n);
		int t = n;
		while(t%2 == 0)
			t /= 2;
		while(t%5 == 0)
			t /= 5;

		if(t == 1)	// n is of the form (2^a)(5^b)
			return 0;
		else if(isPrime(n))	// n is prime. only numbers we have to look at.
		{
			int k = 1;
			while(true)
			{
				BigInteger tenPow = BigInteger.valueOf(10);
				tenPow = tenPow.pow(k);
				
				if(tenPow.mod(BigInteger.valueOf(n)).intValue() == 1)
					break;
				k++;
			}
			return k;
		}
		
		return -1;
	}
	
	public boolean isPrime(int n)
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
