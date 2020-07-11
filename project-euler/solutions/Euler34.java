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
		long sum = 0;
		for(int n=3; n<2550000; n++)
		{
			int t = n;
			int sumFacts = 0;
			while(t > 0)
			{
				sumFacts += fact(t%10);
				t /= 10;
			}
			if(sumFacts == n)
			{
				System.out.println("Match wtih " + n);
				sum += n;
			}
		}
		System.out.println("Total sum: " + sum);
	}
	
	public int fact(int n)
	{
		int prod = 1;
		while(n > 1)
		{
			prod *= n;
			n--;
		}
		return prod;
	}
}
