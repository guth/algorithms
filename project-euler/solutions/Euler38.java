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
		long max = -1;
		for(long p=1; p<99999; p++)
		{
			String total = ""+p;
			for(long n=2; n<=9; n++)
			{
				total += (p*n);
				if(total.length() > 9)
					break;
				if(isPanDigital(total))
				{
					max = Math.max(max, Long.parseLong(total));
				}
			}
		}
		System.out.println("Max: " + max);
	}
	
	public boolean isPanDigital(String s)
	{
		if(s.length() != 9)
			return false;
		if(s.contains("0"))
			return false;
		return isPanDigital(Long.parseLong(s));
	}
	
	public boolean isPanDigital(long n)
	{
		Set<Long> set = new HashSet<Long>();
		if(n > 987654321 || n < 123456789)
			return false;
		while(n > 0)
		{
			set.add(n%10);
			n /= 10;
		}
		return (set.size()==9 && !set.contains(0));
	}
}
