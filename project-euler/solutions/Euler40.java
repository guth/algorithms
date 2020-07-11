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
		StringBuilder sb = new StringBuilder(1000500);
		for(int k=0; sb.length()<1000001; k++)
		{
			sb.append(k);
		}
		char[] c = sb.toString().toCharArray();
		int ans = 1;
		ans *= (c[1]-'0') * (c[10]-'0') * (c[100]-'0') * (c[1000]-'0') * (c[10000]-'0') * (c[100000]-'0') * (c[1000000]-'0');
		System.out.println(ans);
	}
}
