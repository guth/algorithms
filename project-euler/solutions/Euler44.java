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
		long D = Long.MAX_VALUE;

		int N = 10000;
		long[] p = new long[N];
		Set<Long> pents = new HashSet<Long>();
		for(int n=1; n<=N; n++)
		{
			p[n-1] = (n*(3*n-1))/2;
			pents.add(p[n-1]);
		}

		for(int i=0; i<N; i++)
		{
			for(int j=i+1; j<N; j++)
			{
				long sum = p[i]+p[j];
				long diff = Math.abs(p[i]-p[j]);
				if(pents.contains(sum) && pents.contains(diff))
				{
					D = Math.min(D, diff);
				}
			}
		}
		System.out.println("Minimum D:" + D);
	}
}
