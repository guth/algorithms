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
		int sum = 0;
		for(int k=1; k<1000; k++)
		{
			if(k%3 == 0 || k%5 == 0)
				sum += k;
		}
		System.out.println(sum);
	}
}
