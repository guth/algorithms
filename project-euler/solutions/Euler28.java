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
		int R = 1001;
		int C = 1001;
		int[][] a = new int[R][C];
		int MAX = R*C;
		int[] dr = {0, 1, 0, -1};
		int[] dc = {-1, 0, 1, 0};
		int d = 0;
		int NUM_DIRS = 4;
		
		int r = 0;
		int c = 1000;
		int numFill = 0;
		int curr = MAX;
		while(numFill < MAX)
		{
			a[r][c] = curr;
			
			if(d % NUM_DIRS == 0) // left
			{
				if(c == 0 || a[r][c-1] != 0)
					d++;
			}
			else if(d % NUM_DIRS == 1) // down
			{
				if(r == R-1 || a[r+1][c] != 0)
					d++;
			}
			else if(d % NUM_DIRS == 2) // right
			{
				if(c == C-1 || a[r][c+1] != 0)
					d++;
			}
			else if(d % NUM_DIRS == 3) // up
			{
				if(r == 0 || a[r-1][c] != 0)
					d++;
			}
			r += dr[d % NUM_DIRS];
			c += dc[d % NUM_DIRS];
			
			numFill++;
			curr--;
		}
		int mr = 1001/2;
		int mc = 1001/2;
		
		long sum = 1;
		
		dr = new int[]{1, 1, -1, -1};
		dc = new int[]{-1, 1, 1, -1};
		for(d=0; d<NUM_DIRS; d++)
		{
			r = mr+dr[d];
			c = mc+dc[d];
			while(r >= 0 && r < R && c >= 0 && c < C)
			{
				sum += a[r][c];
				r += dr[d];
				c += dc[d];
			}
		}
		System.out.println("Sum : " + sum);
	}
}
