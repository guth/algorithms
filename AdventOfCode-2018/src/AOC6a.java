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

        Set<Point> ignorePoints = new HashSet<Point>();
        for(Point point : points)
        {
            if(point.x == minX || point.x == maxX || point.y == minY || point.y == maxY)
            {
                ignorePoints.add(point);
            }
        }

        Map<Point, Integer> count = new HashMap<Point, Integer>();
        for(int x = minX; x <= maxX; x++)
        {
            for(int y = minY; y <= maxY; y++)
            {
                Point closest = null;
                int minDist = Integer.MAX_VALUE;
                Map<Integer, Integer> distCount = new HashMap<Integer, Integer>();

                for(Point p : points)
                {
                    int dist = Math.abs(p.x - x) + Math.abs(p.y - y);
                    if(dist < minDist)
                    {
                        minDist = dist;
                        closest = p;
                    }

                    if(!distCount.containsKey(dist))
                        distCount.put(dist, 0);
                    distCount.put(dist, distCount.get(dist) + 1);
                }

                if(distCount.get(minDist) == 1)
                {
                    if(!count.containsKey(closest))
                        count.put(closest, 0);
                    count.put(closest, count.get(closest) + 1);
                }
            }
        }

        for(Point p : count.keySet())
        {
            System.out.println(p + " = " + count.get(p));
        }

        int max = Integer.MIN_VALUE;
        for(Point p : count.keySet())
        {
            if(!ignorePoints.contains(p))
            {
                max = Math.max(max, count.get(p));
            }
        }
        System.out.println("max: " + max);
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