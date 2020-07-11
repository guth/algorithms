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
		int[][] a = new int[100][];
		int[][] dp = new int [100][100];
		
		// Reads the 100-layer triangle from standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int k=0; k<100; k++)
		{
			String line = br.readLine();
			String[] parts = line.split(" ");
			
			a[k] = new int[parts.length];
			for(int i=0; i<parts.length; i++)
			{
				a[k][i] = Integer.parseInt(parts[i]);
			}
		}
		dp[0][0] = a[0][0];
		dp[1][0] = a[0][0] + a[1][0];
		dp[1][1] = a[0][0] + a[1][1];
		
		for(int r=2; r<100; r++)
		{
			for(int c=0; c<a[r].length; c++)
			{
				if(c==0)	// Edge case: far left
				{
					dp[r][c] = a[r][c] + dp[r-1][c]; 
				}
				else if(c==a[r].length-1)	// Edge case: far right
				{
					dp[r][c] = a[r][c] + dp[r-1][c-1];
				}
				else	// General case in the middle
				{
					dp[r][c] = a[r][c] + Math.max(dp[r-1][c-1], dp[r-1][c]);
				}
			}
		}
		for(int r=0; r<100; r++)
		{
			for(int c=0; c<100; c++)
			{
				System.out.print(dp[r][c] + " ");
			}
			System.out.println();
		}
		
		int max = -1;
		for(int c=0; c<dp[99].length; c++)
		{
			max = Math.max(max, dp[99][c]);
		}
		System.out.println(max);
	}
}
