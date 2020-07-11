import java.io.*;
import java.math.BigInteger;
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
		long MAX = (long)Math.pow(2, 30);
		int count = 0;
		for(long n=1; n<=MAX; n++)
		{
			long val = X(n, 2*n, 3*n);
			if(val == 0)
				count++;
		}
		System.out.println(count);
	}

	public long X(long n1, long n2, long n3)
	{
		return n1 ^ n2 ^ n3;
	}
}