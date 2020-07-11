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
		BigInteger prev = BigInteger.valueOf(3);
		BigInteger a = BigInteger.valueOf(7);	// Starting at the 2nd expansion, 7/5
		BigInteger b = BigInteger.valueOf(5);

		int count = 0;
		for(int k=1; k<=998; k++)
		{
			if(a.toString().length() > b.toString().length())
			{
				// System.out.println(a.toString() + "/" + b.toString());
				count++;
			}

			b = a.add(b);
			BigInteger temp = a;
			a = (a.multiply(BigInteger.valueOf(2))).add(prev);
			prev = temp;
		}

		System.out.println("Count: " + count);
	}
}
