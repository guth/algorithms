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
		int count = 0;
		for(int n=10; n<=10000; n++)
		{
			BigInteger b = BigInteger.valueOf(n);
			boolean lychrel = true;
			for(int i=1; i<=49; i++)
			{
				b = revAdd(b);
				if(isPalindrome(b.toString()))
				{
					lychrel = false;
					break;
				}
			}
			if(lychrel)
			{
				count++;
			}
		}
		System.out.printf("There are %d lychrel numbers below 10,000\n", count);
	}

	BigInteger revAdd(BigInteger n)
	{
		BigInteger rev = reverse(n.toString());
		return rev.add(n);
	}

	BigInteger reverse(String s)
	{
		StringBuilder sb = new StringBuilder(s.length());
		for(int k=s.length()-1; k>=0; k--)
		{
			sb.append(s.charAt(k));
		}
		return new BigInteger(sb.toString());
	}

	boolean isPalindrome(int n)
	{
		return isPalindrome((""+n).toCharArray());
	}

	boolean isPalindrome(String s)
	{
		return isPalindrome(s.toCharArray());
	}

	boolean isPalindrome(char[] c)
	{
		for(int k=0; k<c.length/2; k++)
		{
			if(c[k] != c[c.length-k-1])
				return false;
		}
		return true;
	}
}
