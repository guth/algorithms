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
		int s1 = 0;
		int s2 = 0;
		for(int k=1; k<=100; k++)
		{
			s1 += k;
			s2 += k*k;
		}
		s1 = s1 * s1;
		System.out.println(s1-s2);
	}
}
