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
		int n = 11;
		int count = 0;
		long sum = 0;
		while(count < 11)
		{
			if(isPrime(n))
			{
				boolean success = true;
				int t = n;
				// Right to left
				while(t > 0)
				{
					t /= 10;
					if(t==0)
						break;
//					System.out.println("RTL: " + t);
					if(!isPrime(t))
					{
						success = false;
						break;
					}
				}
				
				t = n;
				// Left to right
				while(t > 0)
				{
					int p = (""+t).length()-1;
					int ten = (int)Math.pow(10, p);
					t = t%ten;
					if(t==0)
						break;
//					System.out.println("LTR: " + t);
					if(!isPrime(t))
					{
						success = false;
						break;
					}
				}
				
				if(success)
				{
					System.out.println("Success with " + n);
					count++;
					sum += n;
				}
			}
			
//			System.out.println("Done checking " + n + "    " + count);
			n+=2;
		}
		System.out.println(count + " truncatable primes with sum of " + sum);
	}
	
	public boolean isPrime(int n)
	{
		if(n==1)
			return false;
		else if(n==2 || n==3)
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
