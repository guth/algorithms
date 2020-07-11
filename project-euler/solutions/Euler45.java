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
		// Triangle	 	Tn=n(n+1)/2	.
		// Pentagonal	 Pn=n(3n-1)/2
		// Hexagonal	 Hn=n(2n-1)
		Set<Long> pen = new HashSet<Long>();
		Set<Long> hex = new HashSet<Long>();
		for(long n=143; n <= 1000000; n++)
		{
			long p = (n*(3*n-1))/2;
			pen.add(p);

			long h = n*(2*n-1);
			hex.add(h);
		}

		long val = -1;
		long nth = -1;
		for(long n=286; ; n++)
		{
			long t = (n*(n+1))/2;
			if(pen.contains(t) && hex.contains(t))
			{
				val = t;
				nth = n;
				break;
			}
		}
		System.out.println(val + " is the " + nth + "th triangle number and it is pentagonal and hexagonal.");

	}
}
