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
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        Scanner s = new Scanner(System.in);
        
        List<Rectangle> rectangles = new ArrayList<Rectangle>();
        int xMax = 0;
        int yMax = 0;
        do
        {
            // #1 @ 335,861: 14x10
            s.findInLine("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
            MatchResult match = s.match();
            int id = Integer.parseInt(match.group(1));
            int x = Integer.parseInt(match.group(2));
            int y = Integer.parseInt(match.group(3));
            int dx = Integer.parseInt(match.group(4));
            int dy = Integer.parseInt(match.group(5));

            Rectangle rectangle = new Rectangle(id, x, y, (x+dx-1), (y+dy-1));
            xMax = Math.max(xMax, rectangle.bx);
            yMax = Math.max(yMax, rectangle.by);
            
            rectangles.add(rectangle);

            System.out.println(rectangle);
        }
        while(s.hasNextLine() && (s.nextLine() != null));

        int[][] counts = new int[xMax+1][yMax+1];

        for(Rectangle rec : rectangles)
        {
            for(int x = rec.x; x <= rec.bx; x++)
            {
                for(int y = rec.y; y <= rec.by; y++)
                {
                    counts[x][y]++;
                    // System.out.println("Incrementing ("+x+","+y+")");
                }
            }
        }
        //  01234567
        // 0........
        // 1...2222.
        // 2...2222.
        // 3.11XX22.
        // 4.11XX22.
        // 5.111133.
        // 6.111133.
        // 7........
        // for(int x=0; x<counts.length; x++)
        // {
        //     for(int y=0; y<counts[x].length; y++)
        //     {
        //         System.out.print(counts[x][y]);
        //     }
        //     System.out.println();
        // }
        for(Rectangle rec : rectangles)
        {
            boolean winner = true;
            for(int x = rec.x; x <= rec.bx; x++)
            {
                for(int y = rec.y; y <= rec.by; y++)
                {
                    if(winner && counts[x][y] > 1)
                    {
                        winner = false;
                        break;
                    }
                }
            }

            if(winner)
            {
                System.out.println(rec.id);
            }
        }
    }

    public class Rectangle
    {
        public int id;
        public int x;
        public int y;
        public int bx;
        public int by;

        public Rectangle(int id, int x, int y, int bx, int by)
        {
            this.id = id;
            this.x = x;
            this.y = y;
            this.bx = bx;
            this.by = by;
        }

        public String toString()
        {
            return String.format("{id: %d,%d,%d,%d,%d}", this.id, this.x, this.y, this.bx, this.by);
        }
    }
}