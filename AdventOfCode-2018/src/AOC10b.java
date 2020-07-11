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

        List<Star> stars = new ArrayList<Star>();

        String line;
        while((line = br.readLine()) != null)
        {
            line = line.replaceAll("position=<", " ");
            line = line.replaceAll("velocity=<", " ");
            line = line.replaceAll(">", " ");
            line = line.replaceAll(",", " ");

            Scanner s = new Scanner(line);

            int x = s.nextInt();
            int y = s.nextInt();
            int dx = s.nextInt();
            int dy = s.nextInt();

            Star star = new Star(x, y, dx, dy);
            stars.add(star);
            System.out.println(star);
        }

        int seconds = 1;
        while(true)
        {
            // Process
            for(Star star : stars)
            {
                star.x += star.dx;
                star.y += star.dy;
            }

            // Check bounds. If within 100x100, print & sleep.
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            for(Star star : stars)
            {
                minX = Math.min(minX, star.x);
                minY = Math.min(minY, star.y);
                maxX = Math.max(maxX, star.x);
                maxY = Math.max(maxY, star.y);
            }

            if(Math.abs(maxX - minX) <= 100 && Math.abs(maxY - minY) <= 100)
            {
                this.printGrid(stars, minX, minY, maxX, maxY);
                System.out.println("Seconds: " + seconds);
                Thread.sleep(1000);
            }
            seconds++;
        }
    }

    private void printGrid(List<Star> stars, int minX, int minY, int maxX, int maxY)
    {
        for(int y=minY; y<=maxY; y++)
        {
            for(int x=minX; x<=maxX; x++)
            {
                Star star = new Star(x, y, 0, 0);
                if(stars.contains(star))
                    System.out.print("#");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
            System.out.println("------------");
    }

    public class Star
    {
        public int x;
        public int y;
        public int dx;
        public int dy;

        public Star(int x, int y, int dx, int dy)
        {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }

        public String toString()
        {
            return String.format("{%d,%d,%d,%d}", this.x, this.y, this.dx, this.dy);
        }

        @Override
        public boolean equals(Object o)
        {
            if(o == null)
                return false;
            if(!(o instanceof Star))
                return false;

            Star other = (Star)o;

            return (this.x == other.x && this.y == other.y);
        }

        @Override
        public int hashCode()
        {
            return (this.x + "_" + this.y).hashCode();
        }
    }

}