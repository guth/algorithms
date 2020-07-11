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
		int n = 1;
		while(true)
		{
			if(allSame(n))
			{
				System.out.println(n);
				break;
			}
			n++;
		}
	}

	public boolean allSame(int n)
	{
		return allSame(new int[]{n,2*n,3*n,4*n,5*n,6*n});
	}

	public boolean allSame(int[] a)
	{
		char[][] c = new char[a.length][];
		for(int k=0; k<a.length; k++)
		{
			c[k] = (""+a[k]).toCharArray();
			Arrays.sort(c[k]);
		}

		for(int r=0; r<c.length; r++)
		{
			for(int s=r+1; s<c.length; s++)
			{
				if(c[r].length != c[s].length)
					return false;
				for(int i=0; i<c[r].length; i++)
				{
					if(c[r][i] != c[s][i])
						return false;
				}
			}
		}
		return true;
	}
}
