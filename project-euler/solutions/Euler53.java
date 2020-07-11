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
		int count = 0;
		for(int n=1; n<=100; n++)
		{
			for(int k=1; k<=n; k++)
			{
				BigInteger b = choose(n, k);
				if(b.toString().length() >= 7)
					count++;
			}
		}
		System.out.println(count);
	}

	public BigInteger choose(int n, int k)
	{
		// n! / ((n-k)! * k!)
		BigInteger num = fact(n);
		BigInteger d1 = fact(n-k);
		BigInteger d2 = fact(k);
		d1 = d1.multiply(d2);
		return num.divide(d1);
	}

	public BigInteger fact(int n)
	{
		BigInteger b = BigInteger.ONE;
		for(int k=2; k<=n; k++)
		{
			b = b.multiply(BigInteger.valueOf(k));
		}
		return b;
	}
}
