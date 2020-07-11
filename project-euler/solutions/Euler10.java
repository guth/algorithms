import java.io.*;
import java.util.*;

public class Euler
{
	public static void main(String[] args)
	{
		Euler e = new Euler();
		e.go();
	}
	
	public void go()
	{
		long sum = 0;
		for(int k=2; k<2000000; k++)
		{
			if(isPrime(k))
				sum += k;
		}
		System.out.println(sum);
	}
	
	public boolean isPrime(int n)
	{
		if(n==2 || n==3)
			return true;
		else if(n%2 == 0)
			return false;
		
		int L = (int)Math.sqrt(n)+3;
		for(int k=3; k<L; k+=2)
		{
			if(n%k == 0)
				return false;
		}
		return true;
	}
}
