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
		List<Integer> nums = new ArrayList<Integer>();
		List<Integer> denoms = new ArrayList<Integer>();
		for(int n=10; n<=99; n++)
		{
			for(int d=n+1; d<=99; d++)
			{
				if(n%10 == 0 && d%10 == 0)
					continue;
				if(n%10 == d/10 && areEquiv(n, d, n/10, d%10))
				{
					nums.add(n);
					denoms.add(d);
					System.out.println("Adding " + n + "/" + d);
				}
			}
		}
		
		int num = 1;
		int denom = 1;
		for(int i=0; i<nums.size(); i++)
		{
			num *= nums.get(i);
			denom *= denoms.get(i);
		}
		
		for(int k=2; k<=denom; k++)
		{
			while(num % k == 0 && denom % k == 0)
			{
				num /= k;
				denom /= k;
			}
		}
		
		System.out.println("Final fraction: " + num + "/" + denom);
	}
	
	boolean areEquiv(int n1, int d1, int n2, int d2)
	{
		for(int k=2; k<=d1; k++)
		{
			while(n1%k==0 && d1%k==0)
			{
				n1 /= k;
				d1 /= k;
			}
		}
		
		for(int k=2; k<=d2; k++)
		{
			while(n2%k==0 && d2%k==0)
			{
				n2 /= k;
				d2 /= k;
			}
		}
		
		return (n1 == n2) && (d1 == d2);
	}
}
