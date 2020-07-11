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
//		1p, 2p, 5p, 10p, 20p, 50p, 100p, 200p
		int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
		int MAX = 200;
		int[] dp = new int[MAX+1];
		dp[0] = 1;
		for(int c=0; c<coins.length; c++)
		{
			for(int k=0; k<dp.length; k++)
			{
				if(k + coins[c] < dp.length)
				{
					dp[k+coins[c]] += dp[k];
				}
			}
		}
		System.out.println(dp[MAX]);
	}
}
