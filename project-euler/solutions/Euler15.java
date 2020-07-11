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
		int N = 21;
		long[][] dp = new long[N][N];
		dp[0][0] = 1;
		for(int r=0; r<N; r++)
		{
			for(int c=0; c<N; c++)
			{
				if(r > 0)
					dp[r][c] += dp[r-1][c];
				if(c > 0)
					dp[r][c] += dp[r][c-1];
			}
		}
		System.out.println(dp[N-1][N-1]);
	}
}
