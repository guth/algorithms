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
		long num = 600851475143L;
		int k = 3;
		int max = -1;
		while(k <= num)
		{
			if(num % k == 0)
			{
				num /= k;
				if(isPrime(k) && k > max)
					max = k;
			}
			k++;
		}
		System.out.println(max);
	}
	
	public boolean isPrime(int n)
	{
		if(n==2 || n==3)
			return true;
		if(n%2 == 0)
			return false;
		int L = (int)Math.sqrt(n);
		for(int k=3; k<=L; k+=2)
		{
			if(n%k == 0)
				return false;
		}
		return true;
	}
}
