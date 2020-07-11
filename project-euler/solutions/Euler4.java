import java.io.*;
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
		int max = -1;
		for(int a=100; a<=999; a++)
		{
			for(int b=100; b<=999; b++)
			{
				int p = a*b;
				if(isPalin(""+p) && p > max)
					max = p;
			}
		}
		System.out.println(max);
	}
	
	public boolean isPalin(String s)
	{
		return isPalin(s.toCharArray());
	}
	
	public boolean isPalin(char[] c)
	{
		int N = c.length;
		for(int k=0; k<N/2; k++)
		{
			if(c[k] != c[N-k-1])
				return false;
		}
		return true;
	}
}
