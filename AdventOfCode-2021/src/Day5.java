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

    private int X = 1000;
    private int Y = 1000;

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        List<Line> lines = new ArrayList<Line>();

        String inputLine;
        while ((inputLine = br.readLine()) != null)
        {
            String[] parts = inputLine.split(" -> ");
            String[] p1Parts = parts[0].split(",");
            String[] p2Parts = parts[1].split(",");

            Point p1 = new Point(Integer.parseInt(p1Parts[0]), Integer.parseInt(p1Parts[1]));
            Point p2 = new Point(Integer.parseInt(p2Parts[0]), Integer.parseInt(p2Parts[1]));
            Line line = new Line(p1, p2);
            lines.add(line);
            // System.out.println("Line: " + line.toString());
        }

        int[][] count = new int[X][Y];
        int[][] count2 = new int[X][Y];
        for(int x=0; x<X; x++)
        {
            for(int y=0; y<Y; y++)
            {
                for(Line line : lines)
                {
                    if(line.isHorizontal())
                    {
                        if(y == line.p1.y
                            && x >= Math.min(line.p1.x, line.p2.x)
                            && x <= Math.max(line.p1.x, line.p2.x))
                        {
                            count[x][y]++;
                            count2[x][y]++;
                        }
                    }
                    else if(line.isVertical())
                    {
                        if(x == line.p1.x
                            && y >= Math.min(line.p1.y, line.p2.y)
                            && y <= Math.max(line.p1.y, line.p2.y))
                        {
                            count[x][y]++;
                            count2[x][y]++;
                        }
                    }
                    else
                    {
                        if(x >= Math.min(line.p1.x, line.p2.x)
                            && x <= Math.max(line.p1.x, line.p2.x)
                            && y >= Math.min(line.p1.y, line.p2.y)
                            && y <= Math.max(line.p1.y, line.p2.y))
                        {
                            // Relearned geometry with MS Paint!
                            double slope = (line.p1.y - line.p2.y) / (line.p1.x - line.p2.x);
                            double b = line.p1.y - slope * line.p1.x;
                            double yhat = slope * x + b;
                            if(Math.abs(y-yhat) <= 0.00001)
                            {
                                count2[x][y]++;
                            }
                        }
                    }
                }
            }
        }

        int partOne = 0;
        int partTwo = 0;
        for(int x=0; x<X; x++)
        {
            for(int y=0; y<Y; y++)
            {
                if(count[x][y] >= 2)
                    partOne++;

                if(count2[x][y] >= 2)
                    partTwo++;
            }
        }

        for(int x=0; x<10; x++)
        {
            System.out.println("-------");
            for(int y=0; y<10; y++)
            {
                System.out.print(count2[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("-------");

        System.out.println("Part One: " + partOne);
        System.out.println("Part Two: " + partTwo);
    }

    class Line
    {
        public Point p1;
        public Point p2;

        public Line(Point p1, Point p2)
        {
            this.p1 = p1;
            this.p2 = p2;
        }

        public boolean isVertical()
        {
            return (p1.x == p2.x);
        }

        public boolean isHorizontal()
        {
            return (p1.y == p2.y);
        }

        @Override
        public String toString()
        {
            return this.p1.toString() + "->" + this.p2.toString();
        }
    }

    class Point
    {
        public int x;
        public int y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString()
        {
            return "(" + this.x + "," + this.y + ")";
        }
    }

}