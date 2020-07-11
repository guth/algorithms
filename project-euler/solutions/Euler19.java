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
//		Date d = new Date(1901, )
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(1901, 1, 1);
		int count = 0;
		
		do
		{
//			System.out.println(c.get(Calendar.DATE) + " " + c.get(Calendar.MONTH) + " " + c.get(Calendar.YEAR));
			
			if(c.get(Calendar.DATE) == 1 && c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				count++;
			
			c.add(Calendar.DATE, 1);
			
			if(c.get(Calendar.YEAR) == 2000 && c.get(Calendar.MONTH) == Calendar.DECEMBER && c.get(Calendar.DATE) == 30)
				break;
		}
		while(true);
		
		System.out.println(count);
	}
}
