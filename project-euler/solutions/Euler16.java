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
		BigInteger b = BigInteger.valueOf(1);
		int N = 1000;
		while(N-->0)
		{
			b = b.multiply(BigInteger.valueOf(2));
		}
		System.out.println("Value: " + b.toString());
		System.out.println("# digits: " + b.toString().length());
		char[] c = b.toString().toCharArray();
		long sumDigits = 0;
		for(int k=0; k<c.length; k++)
		{
			sumDigits += (c[k]-'0');
		}
		System.out.println("Sum of digits: " + sumDigits);
	}
}
