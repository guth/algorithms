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
		BigInteger b = BigInteger.ONE;
		for(int k=2; k<=100; k++)
		{
			b = b.multiply(BigInteger.valueOf(k));
		}
		
		System.out.println("Value: " + b.toString());
		
		char[] c = b.toString().toCharArray();
		int sum = 0;
		for(int k=0; k<c.length; k++)
		{
			sum += (c[k]-'0');
		}
		System.out.println("Sum of digits: " + sum);
	}
}
