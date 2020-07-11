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
	int MAX = 1000000;
	boolean[] notLongest = new boolean[MAX+1];
	
	public void go()
	{
		int max = -1;
		int maxK = -1;
		for(int k=1; k<=MAX; k++)
		{
			if(notLongest[k])
				continue;
			int len = chainLength(k);
			if(len > max)
			{
				max = len;
				maxK = k;
			}
		}
		System.out.println(maxK + " with a chain length of " + max);
	}
	
	public int chainLength(long n)
	{
		int terms = 1;
		while(n != 1)
		{
			if(n%2==0)
				n /= 2;
			else
				n = 3*n+1;
			if(n < MAX)
				notLongest[(int)n] = true;
			terms++;
		}
		return terms;
	}
}
