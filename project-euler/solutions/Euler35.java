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
		Set<Integer> good = new HashSet<Integer>();
		for(int n=2; n<1000000; n++)
		{
			boolean success = true;
			if(n<10 && isPrime(n))
			{
				good.add(n);
				continue;
			}
			if(!isPrime(n))
				continue;
			
			int p = (""+n).length()-1;
			
			int t = n;
			while((t = t/10 + (t%10)*(int)Math.pow(10, p)) != n)
			{
				System.out.println(n + ": " + t);
				if(!isPrime(t))
				{
					success = false;
					break;
				}
			}
			if(success)
				good.add(n);
		}
		System.out.println(good.size());
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
