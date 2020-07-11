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
		int max = -1;
		for(int a=1; a<100; a++)
		{
			for(int b=1; b<100; b++)
			{
				BigInteger biga = BigInteger.valueOf(a);
				// BigInteger bigb = BigInteger.valueOf(b);
				BigInteger result = biga.pow(b);
				char[] c = result.toString().toCharArray();
				int sum = 0;
				for(int k=0; k<c.length; k++)
				{
					sum += (c[k]-'0');
				}
				max = Math.max(max, sum);
			}
		}
		System.out.println(max);
	}
}
