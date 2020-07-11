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
		int MAX = 10000;
		int ans = 0;
		for(int a=2; a<=MAX; a++)
		{
			if(a == d(d(a)) && a != d(a))
				ans += a;
		}
		System.out.println(ans);
	}
	
	public int d(int n)
	{
		int sum = 0;
		for(int k=1; k<n; k++)
		{
			if(n%k==0)
			{
				sum += k;
			}
		}
		return sum;
	}
}
