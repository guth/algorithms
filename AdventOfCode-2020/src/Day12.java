import java.util.*;
import java.io.*;
import java.text.*;

class Day12
{
    public static void main(String[] args) throws Exception
    {
        Day12 m = new Day12();
        m.go();
    }
    
    // dx[degrees/90]    0, 90, 180, 270
    int[] dx = new int[]{0,  1,   0,  -1};
    int[] dy = new int[]{1,  0,   -1,  0};

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        // Read input
        List<Action> actions = new ArrayList<Action>();
        String line;
        while((line = br.readLine()) != null)
        {
            char op = line.charAt(0);
            int value = Integer.parseInt(line.substring(1, line.length()));

            Action action = new Action(op, value);
            actions.add(action);
        }

        int x = 0;
        int y = 0;
        int degrees = 90; // start facing east
        for(Action action : actions)
        {
            if(action.op == 'N') 
            {
                y += action.value;
            }
            else if(action.op == 'E')
            {
                x += action.value;
            }
            else if(action.op == 'S')
            {
                y -= action.value;
            }
            else if(action.op == 'W')
            {
                x -= action.value;
            }
            else if(action.op == 'R')
            {
                degrees += action.value;
                degrees = degrees % 360;
            }
            else if(action.op == 'L')
            {
                degrees -= action.value;
                while(degrees < 0)
                {
                    degrees += 360;
                }
                degrees = degrees % 360;
            }
            else if(action.op == 'F')
            {
                x += dx[degrees/90] * action.value;
                y += dy[degrees/90] * action.value;
            }
        }
        System.out.println("x: " + x + ", y: " + y);

        int partOne = Math.abs(x) + Math.abs(y);
        System.out.println("Part One: " + partOne);

        // Part Two
        x = 0;
        y = 0;
        
        int wx = 10;
        int wy = 1;
        
        for(Action action : actions)
        {
            if(action.op == 'N') 
            {
                wy += action.value;
            }
            else if(action.op == 'E')
            {
                wx += action.value;
            }
            else if(action.op == 'S')
            {
                wy -= action.value;
            }
            else if(action.op == 'W')
            {
                wx -= action.value;
            }
            else if(action.op == 'R')
            {
                for(int i=0; i<action.value/90; i++)
                {
                    int tmp = wx;
                    wx = wy;
                    wy = -1 * tmp;
                }
            }
            else if(action.op == 'L')
            {
                for(int i=0; i<action.value/90; i++)
                {
                    int tmp = wx;
                    wx = -1 * wy;
                    wy = tmp;
                }
            }
            else if(action.op == 'F')
            {
                x += wx * action.value;
                x += wy * action.value;
            }
        }
        System.out.println("x: " + x + ", y: " + y);

        int partTwo = Math.abs(x) + Math.abs(y);
        System.out.println("Part Two: " + partTwo);
    }

    public class Action
    {
        public char op;
        public int value;

        public Action(char op, int value)
        {
            this.op = op;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return "{op : " + op +", value: " + value + "}";
        }
    }
}