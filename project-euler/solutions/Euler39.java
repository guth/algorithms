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
		int maxP = -1;
		for(int p=6; p<1000; p++)
		{
			int numSols = 0;
			for(int a=1; a<p; a++)
			{
				for(int b=a+1; b<p; b++)
				{
					for(int c=b+1; c<p; c++)
					{
						if(a+b+c > p)
							break;
						if(a+b+c == p && a*a+b*b==c*c)
							numSols++;
					}
				}
			}
			if(numSols > max)
			{
				max = numSols;
				maxP = p;
			}
		}
		
		
		
		System.out.println("Perimeter of " + maxP + " has " + max + " solutions.");
	}
}
