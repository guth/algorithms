import java.io.*;
import java.math.BigInteger;
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
		int[][] a = {
			{75},
			{95,64},
			{17,47,82},
			{18,35,87,10},
			{20,4,82,47,65},
			{19,1,23,75,3,34},
			{88,2,77,73,7,63,67},
			{99,65,4,28,6,16,70,92},
			{41,41,26,56,83,40,80,70,33},
			{41,48,72,33,47,32,37,16,94,29},
			{53,71,44,65,25,43,91,52,97,51,14},
			{70,11,33,28,77,73,17,78,39,68,17,57},
			{91,71,52,38,17,14,91,43,58,50,27,29,48},
			{63,66,4,68,89,53,67,30,73,16,69,87,40,31},
			{4,62,98,27,23,9,70,98,73,93,38,53,60,4,23},
		};
		int[][] dp = new int [15][15];
		dp[0][0] = a[0][0];
		dp[1][0] = a[0][0] + a[1][0];
		dp[1][1] = a[0][0] + a[1][1];
		
		for(int r=2; r<15; r++)
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
		for(int r=0; r<15; r++)
		{
			for(int c=0; c<15; c++)
			{
				System.out.print(dp[r][c] + " ");
			}
			System.out.println();
		}
		
		int max = -1;
		for(int c=0; c<dp[14].length; c++)
		{
			max = Math.max(max, dp[14][c]);
		}
		System.out.println(max);
	}
}
