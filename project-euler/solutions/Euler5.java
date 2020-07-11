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
		int num=1;
		while(!isSpecial(++num));
		System.out.println(num);
	}
	
	public boolean isSpecial(int n)
	{
		for(int k=1; k<=20; k++)
		{
			if(n%k != 0)
				return false;
		}
		return true;
	}
}
