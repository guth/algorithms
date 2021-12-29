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

        List<Display> displays = new ArrayList<Display>();

        String line;
        while((line = br.readLine()) != null)
        {
            // System.out.println("Line: " + line);
            String[] parts = line.split(" \\| ");
            // System.out.println("parts[0]: " + parts[0]);
            // System.out.println("parts[1]: " + parts[1]);
            Display display = new Display();
            display.inputs = parts[0].split(" ");
            display.outputs = parts[1].split(" ");
            displays.add(display);
        }

        // Part One
        int partOne = 0;
        for(Display display : displays)
        {
            for(int i=0; i<display.outputs.length; i++)
            {
                // System.out.println("Output[" + i + "]: " + display.outputs[i]);
                int length = display.outputs[i].length();
                if(length == 2 || length == 3 || length == 4 || length == 7)
                {
                    partOne++;
                }
            }
        }

        System.out.println("Part One: " + partOne);

        // Part Two

    }

    class Display
    {
        public String[] inputs;
        public String[] outputs;
    }

}