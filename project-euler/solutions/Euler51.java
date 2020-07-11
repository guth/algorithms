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
		for(int n=100001; n<1000000; n+=2)
		{
			if(!isPrime(n))
				continue;
			
			int MAX_STARS = (int)Math.pow(2, (""+n).length());
			for(int s=2; s<MAX_STARS; s++)
			{
				if((s&1) == 1)
					continue;

				int mask = s;
				char[] c = (""+n).toCharArray();
				int i = 0;
				
				while(mask > 0)
				{
					if((mask & 1) == 1)
					{
						c[c.length-1-i] = '*';
					}
					i++;
					mask = mask >> 1;
				}
				String pattern = new String(c);

				int numPrimes = 0;
				for(int k=0; k<=9; k++)
				{
					if(pattern.charAt(0)=='*' && k==0)
						k++;
					String temp = pattern.replace('*', (char)('0'+k));
					int val = Integer.parseInt(temp);
					if(isPrime(val))
						numPrimes++;
				}
				if(numPrimes == 8)
				{
					System.out.println(pattern);
					return;
				}
			}
		}
	}

	boolean isPrime(int n)
	{
		if(n==2||n==3)
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
