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
		int add = 8;
		int n = 28;
		while(true)
		{
			int num = numDivisors(n);
			System.out.println(n + ": " + num);
			if(num > 500)
			{
				System.out.println(n);
				break;
			}
			n += add;
			add++;
		}
	}
	
	public int numDivisors(int n)
	{
		int L = (int)Math.sqrt(n);
		int divs = 0;
		for(int k=1; k<=L; k++)
		{
			if(n%k==0)
			{
				divs += k == L ? 1 : 2;
			}
		}
		return divs;
	}
}
