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
		int[] a = {0,1,2,3,4,5,6,7,8,9};
		int N = a.length;

		int[] primes = {2,3,5,7,11,13,17};
		long sum = 0;

		do
		{
			if(a[0]==0)
				continue;
			long pVal = permVal(a);
			boolean success = true;
			for(int i=1;i<8;i++)
			{
				int val = a[i]*100 + a[i+1]*10 + a[i+2];

				if(val % primes[i-1] != 0)
				{
					success = false;
					break;
				}
			}

			if(success)
			{
				long val = permVal(a);
				System.out.println("Adding val: " + val + " for perm " + permString(a));
				sum += val;
			}
			
		}while(nextPermutation(a));
		System.out.println(sum);
	}

	String permString(int[] a)
	{
		String s = "";
		for(int i=0; i<a.length; i++)
			s += a[i];
		return s;
	}

	long permVal(int[] a)
	{
		return Long.parseLong(permString(a));
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
}
