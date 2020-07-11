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

	long MOD = 10000000000L;
	public void go() throws Exception
	{
		long val = 0;
		for(int n=1; n<=1000; n++)
		{
			val += lastTen(n, n);
			val %= MOD;
		}
		System.out.println(val);
	}

	// Returns last ten digits of a^b
	public long lastTen(int a, int b)
	{
		long val = 1;
		for(int i=1; i<=b; i++)
		{
			val *= a;
			val %= MOD;
		}
		return val;
	}
}
