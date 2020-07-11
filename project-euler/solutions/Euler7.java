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
		int n = 2;
		int count = 0;
		while(true)
		{
			if(isPrime(n))
			{
				count++;
				if(count == 10001)
				{
					System.out.println(n);
					break;
				}
			}
			n++;
		}
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
