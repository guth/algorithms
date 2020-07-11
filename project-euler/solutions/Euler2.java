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
		int f2 = 1;
		int f1 = 2;
		long sum = 2;
		while(true)
		{
			int fib = f2+f1;
			f2 = f1;
			f1 = fib;
			
			if(fib > 4000000)
				break;
			if(fib%2 == 0)
				sum += fib;
		}
		System.out.println(sum);
	}
}
