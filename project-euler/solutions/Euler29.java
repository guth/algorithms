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
		Set<BigInteger> set = new HashSet<BigInteger>();
		for(int a=2; a<=100; a++)
		{
			for(int b=2; b<=100; b++)
			{
				BigInteger bigA = BigInteger.valueOf(a);
				
				BigInteger val = bigA.pow(b);
				set.add(val);
			}
		}
		System.out.println("# of distinct numbers: " + set.size());
	}
}
