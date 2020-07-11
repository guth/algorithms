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
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);
        
        int sum = 0;

        String line;
        while((line = br.readLine()) != null)
        {
            int value = Integer.parseInt(line);
            sum += value;
        }

        System.out.println(sum);
    }
}