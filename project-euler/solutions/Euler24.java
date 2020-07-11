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
		int[] a = {0,1,2,3,4,5,6,7,8,9};
		int N = 1000000-1;
		while(N-->0)
		{
			nextPermutation(a);
		}
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]);
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
