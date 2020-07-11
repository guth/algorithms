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
	
//	Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
//	 all integers greater than 28123 can be written as the sum of two abundant numbers
	
	public void go() throws Exception
	{
		List<Integer> abundant = new ArrayList<Integer>();
		
		for(int k=2; k<28123; k++)
		{
			int sum = d(k);
			if(sum > k)
				abundant.add(k);
		}
		Set<Integer> s = new HashSet<Integer>();
		for(int k=1; k<=28123; k++)
			s.add(k);
		
		for(int i=0; i<abundant.size(); i++)
		{
			for(int j=0; j<abundant.size(); j++)
			{
				int val = abundant.get(i) + abundant.get(j);
				s.remove(val);
			}
		}
		
		int ans = 0;
		for(Integer i : s)
		{
			ans += i;
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
