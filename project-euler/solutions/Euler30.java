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
		
		for(int n=11; n<=999999; n++)
		{
			int t = n;
			int sumOfDigits = 0;
			int numNonZero = 0;
			while(t > 0)
			{
				int val = t%10;
				if(val != 0)
					numNonZero++;
				sumOfDigits += Math.pow(val, 5);
				t /= 10;
			}
			if(sumOfDigits == n && numNonZero > 1)
				sum += n;
		}
		System.out.println("Total sum: " + sum);
	}
}
