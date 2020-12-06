import java.util.*;
import java.io.*;
import java.text.*;

class Day5
{
    public static void main(String[] args) throws Exception
    {
        Day5 m = new Day5();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);
        
        List<Integer> rows = new ArrayList<Integer>();
        List<Integer> columns = new ArrayList<Integer>();

        // Read input
        String line;
        while((line = br.readLine()) != null)
        {
            String rowString = line.substring(0, 7);
            String rowBinary = rowString.replace("F", "0").replace("B", "1");

            String columnString = line.substring(7, 10);
            String columnBinary = columnString.replace("L", "0").replace("R", "1");

            // System.out.println(rowBinary + " " + columnBinary);

            int row = Integer.parseInt(rowBinary, 2);
            int column = Integer.parseInt(columnBinary, 2);

            rows.add(row);
            columns.add(column);
        }

        // Part One
        int[] seats = new int[rows.size()];

        int max = 0;
        for(int i=0; i < rows.size(); i++)
        {
            int seatID = (rows.get(i) * 8) + columns.get(i);
            max = Math.max(max, seatID);

            seats[i] = seatID;
        }
        System.out.println("Part One: " + max);


        // Part Two
        Arrays.sort(seats);

        for(int i=0; i < seats.length - 1; i++)
        {
            if(seats[i] != seats[i+1] - 1)
            {
                System.out.println(seats[i] + 1);
            }
        }
    }
}