import java.util.*;
import java.util.regex.*;
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
        // Scanner s = new Scanner(System.in);
        String firstLine = br.readLine();
        br.readLine();

        String initialState = firstLine.substring(firstLine.lastIndexOf(' ') + 1, firstLine.length());
        System.out.println("Initial State: " + initialState);

        Map<String, String> map = new HashMap<String, String>();

        String line;
        while((line = br.readLine()) != null)
        {
            String[] parts = line.split(" => ");
            map.put(parts[0], parts[1]);
        }
        for(String key : map.keySet())
        {
            System.out.println(key + ": " + map.get(key));
        }

        String extra = "..................................";
        initialState = extra + initialState + extra;
        String currState = initialState;
        for(int i=1; i<=20; i++)
        {
            StringBuilder sb = new StringBuilder(currState.length());
            sb.append("..");
            for(int k=2; k<currState.length()-2; k++)
            {
                String state = currState.substring(k-2, k+3);
                String newValue = this.getNewValue(map, state);
                sb.append(newValue);
            }
            sb.append("..");
            currState = sb.toString();
            System.out.println(i + ": " + currState);
        }

        int sum = 0;
        for(int i=0; i<currState.length(); i++)
        {
            if(currState.charAt(i) == '#')
            {
                int value = (i - extra.length());
                sum += value;
            }
        }
        System.out.println(sum);
    }

    private String getNewValue(Map<String, String> map, String state)
    {
        if(map.containsKey(state))
        {
            return map.get(state);
        }
        return ".";
    }
}