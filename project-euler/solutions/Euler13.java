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
		Scanner s = new Scanner(System.in);

		BigInteger sum = BigInteger.ZERO;
		int N = 100;
//		while(s.hasNextLine())
		while(N-->0)
		{
			String si = s.nextLine();
			BigInteger x = new BigInteger(si);

			sum = sum.add(x);
		}
		System.out.println(sum);
	}
}
