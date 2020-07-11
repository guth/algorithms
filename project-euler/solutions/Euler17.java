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
		String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		String[] tens_hardcoded = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
		String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
		
		int count = "onethousand".length();
		
		for(int k=1; k<ones.length; k++)
		{
			count += ones[k].length();
			System.out.println(ones[k]);
		}
		
		for(int k=0; k<tens_hardcoded.length; k++)
		{
			count += tens_hardcoded[k].length();
			System.out.println(tens_hardcoded[k]);
		}
		
		for(int k=20; k<=999; k++)
		{
			if(k < 100)
			{
				count += tens[k/10].length() + ones[k%10].length();
				System.out.println(tens[k/10] + " " + ones[k%10]);
			}
			else
			{
				count += ones[k/100].length() + "hundred".length();
				System.out.print(ones[k/100] + " hundred");
				if(k%100 > 0)
				{
					if((k%100) >= 1 && (k%100) <= 9)
					{
						count += "and".length();
						count += ones[k%100].length();
						System.out.print(" and ");
						System.out.print(ones[k%100]);
					}
					else if((k%100) >= 10 && (k%100) <= 19)
					{
						count += "and".length();
						count += tens_hardcoded[(k%100)-10].length();
						System.out.print(" and ");
						System.out.print(tens_hardcoded[(k%100)-10]);
					}
					else
					{
						count += "and".length();
						count += tens[(k%100)/10].length() + ones[k%10].length();
						System.out.print(" and ");
						System.out.print(tens[(k%100)/10] + " " + ones[k%10]);
					}
				}
				System.out.println();
			}
		}
		System.out.println(count);
	}
}
