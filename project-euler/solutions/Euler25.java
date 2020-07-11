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
		BigInteger f2 = BigInteger.valueOf(1);
		BigInteger f1 = BigInteger.valueOf(2);
		int count = 3;
		while(true)
		{
			BigInteger fib = f1.add(f2);
			count++;
			if(fib.toString().length() >= 1000)
			{
				System.out.println(count);
				break;
			}
			
			f2 = f1;
			f1 = fib;
		}
	}
}
