import java.util.*;
import java.io.*;
import java.text.*;

class Main
{
	public static void main(String[] args) throws Exception
	{
		Main m = new Main();
		m.go();
	}

	void go() throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//Parser s = new Parser(System.in);
		// Scanner s = new Scanner(System.in);
		int N = Integer.parseInt(br.readLine());

		while(N-->0)
		{
			// int[] cost = new int[3000];
			Map<Character, Integer> cost = new HashMap<Character, Integer>();

			int K = Integer.parseInt(br.readLine());
			for(int k=0; k<K; k++)
			{
				String[] parts = br.readLine().split(" ");
				//System.err.println("parts: " + parts[0] + " | " + parts[1]);
				
				char c = parts[0].charAt(0);
				int value = Integer.parseInt(parts[1]);

				//cost[(int)c] = value;
				cost.put(c, value);
			}
			int M = Integer.parseInt(br.readLine());
			int total = 0;

			for(int m=0; m<M; m++)
			{
				char[] chars = br.readLine().toCharArray();
				for(int i=0; i<chars.length; i++)
				{
					//total += cost[(int)chars[i]];
					if(cost.containsKey(chars[i]))
						total += cost.get(chars[i]);
				}
			}
			
			NumberFormat formatter = new DecimalFormat("#0.00");   
			System.out.println(formatter.format(total/100.0) + "$");
		}
	}
}