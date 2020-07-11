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
		
		for(int n=1; n<1000000; n++)
		{
			BigInteger big = BigInteger.valueOf(n);
			if(isPalin(big.toString(10)) && isPalin(big.toString(2)))
				sum += n;
		}
		System.out.println("Total sum: " + sum);
	}
	
	public boolean isPalin(String s)
	{
		char[] c = s.toCharArray();
		int N = c.length;
		for(int k=0; k<N/2; k++)
		{
			if(c[k] != c[N-k-1])
				return false;
		}
		return true;
	}
}
