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
		int max = -1;
		int[] a = {1,2,3,4,5,6,7}; // Result was -1 when 8 and/or 9 were included
		do
		{
			int val = 0;
			for(int i=0; i<a.length; i++)
			{
				val += a[i]*Math.pow(10,i);
			}
			
			if(isPrime(val))
			{
				max = Math.max(max, val);
			}
			
		}while(nextPermutation(a));
		System.out.println(max);
	}
	
	boolean nextPermutation(int[] arr)
	{
		int n = arr.length;
		int i = n - 2;
		while(i >= 0 && arr[i] >= arr[i + 1])
			--i;
		if(i < 0)
			return false;
		int j = n - 1;
		while(arr[i] >= arr[j])
			--j;
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		Arrays.sort(arr , i + 1, n);

		return true;
	}
	
	public boolean isPanDigital(int n)
	{
		int i = (""+n).length();
		Set<Integer> set = new HashSet<Integer>();
		while(n>0)
		{
			set.add(n%10);
			n/=10;
		}
		if(set.contains(0))
			return false;
		for(int k=1;k<=i;k++)
		{
			if(!set.contains(k))
				return false;
		}
		return (set.size()==i);
	}
	
	public boolean isPrime(int n)
	{
		if(n==2 || n==3)
			return true;
		else if(n%2==0)
			return false;
		
		int L = (int)Math.sqrt(n);
		for(int k=3;k<=L;k+=2)
			if(n%k==0)
				return false;
		return true;
	}
}
