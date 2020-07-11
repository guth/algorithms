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
		loop: for(int a=1; a<=997; a++)
		{
			for(int b=a; b<=997; b++)
			{
				for(int c=b+1; c<=997; c++)
				{
					int left = a*a + b*b;
					int right = c*c;
					if(left == right && a+b+c==1000)
					{
						System.out.println(a*b*c);
						break loop;
					}
				}
			}
		}
	}
}
