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
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        List<Point> points = new ArrayList<Point>();

        String line;
        while((line = br.readLine()) != null)
        {
            String[] parts = line.split(", ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            
            Point point = new Point(x, y);
            points.add(point);
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for(Point point : points)
        {
            minX = Math.min(minX, point.x);
            maxX = Math.max(maxX, point.x);
            minY = Math.min(minY, point.y);
            maxY = Math.max(maxY, point.y);
        }

        // Set<Point> ignorePoints = new HashSet<Point>();
        // for(Point point : points)
        // {
        //     if(point.x == minX || point.x == maxX || point.y == minY || point.y == maxY)
        //     {
        //         ignorePoints.add(point);
        //     }
        // }

        int count = 0;
        for(int x = minX - 1000; x <= maxX + 1000; x++)
        {
            for(int y = minY - 1000; y <= maxY + 1000; y++)
            {
                int sum = 0;
                for(Point p : points)
                {
                    int dist = Math.abs(p.x - x) + Math.abs(p.y - y);
                    sum += dist;
                }

                if(sum < 10000)
                {
                    count++;
                }

            }
        }

        System.out.println(count);
    }

    public class Point
    {
        public int x;
        public int y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o)
        {
            if(o == null)
                return false;
            if(this == o)
                return true;

            if(o instanceof Point)
            {
                Point p = (Point)o;
                return (this.x == p.x && this.y == p.y);
            }
            else
            {
                return false;
            }
        }

        @Override
        public int hashCode()
        {
            return (x + "_" + y).hashCode();
        }

        public String toString()
        {
            return "{" + this.x + "," + this.y + "}";
        }
    }
}