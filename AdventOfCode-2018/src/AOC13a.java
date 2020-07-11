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

    private final int UP = 0b0001;   // 1
    private final int DOWN = 0b0010; // 2
    private final int LEFT = 0b0100; // 4
    private final int RIGHT = 0b1000;// 8
    private int ALL_DIRS = UP | DOWN | LEFT | RIGHT;
    // /->-\        
    // |   |  /----\
    // | /-+--+-\  |
    // | | |  | v  |
    // \-+-/  \-+--/
    //   \------/   

    private int maxX;
    private int maxY;

    void go() throws Exception
    {
        // System.out.println(UP + " " + DOWN + " " + LEFT + " " + RIGHT);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Scanner s = new Scanner(System.in);
        
        List<String> inputString = new ArrayList<String>();

        String line;
        while((line = br.readLine()) != null)
        {
            inputString.add(line);
        }

        this.maxX = inputString.get(0).length();
        this.maxY = inputString.size();
        char[][] input = new char[maxX][maxY];
        for(int x=0; x<maxX; x++)
        {
            for(int y=0; y<maxY; y++)
            {
                char c = inputString.get(y).charAt(x);
                input[x][y] = c;
            }
        }
        this.printGrid(input);


        int[][] graph = new int[maxX][maxY];
        this.buildGraph(graph, input);
        this.printGrid(graph);

        Cart[] carts = this.buildCarts(input);


        for(int x=0; x<maxX; x++)
        {
            for(int y=0; y<maxY; y++)
            {
                if(input[x][y] == 'v' || input[x][y] == '^')
                    input[x][y] = '|';
                if(input[x][y] == '<' || input[x][y] == '>')
                    input[x][y] = '-';
            }
        }

        System.out.println(carts[0] + ", " + carts[1]);

        boolean keepGoing = true;
        while(keepGoing)
        {
            Arrays.sort(carts);
            for(int i=0; i<carts.length; i++)
            {
                // Process cart
                int x = carts[i].x;
                int y = carts[i].y;

                if(graph[x][y] == ALL_DIRS)
                {
                    int turn = carts[i].turns[carts[i].currTurn];

                    int newDir = -1;
                    if(turn == ALL_DIRS) // Straight
                    {
                        // No-op.
                        newDir = carts[i].dir;
                    }
                    else if(turn == LEFT)
                    {
                        newDir = this.getLeftTurn(carts[i].dir);
                    }
                    else if(turn == RIGHT)
                    {
                        newDir = this.getRightTurn(carts[i].dir);
                    }

                    int[] dxdy = this.getDxDy(newDir);
                    carts[i].dir = newDir;
                    carts[i].x += dxdy[0];
                    carts[i].y += dxdy[1];

                    carts[i].currTurn = (carts[i].currTurn + 1) % 3;
                }
                else if((graph[x][y] == (UP | DOWN)) || (graph[x][y] == (LEFT | RIGHT)))
                {
                    int[] dxdy = this.getDxDy(carts[i].dir);
                    carts[i].x += dxdy[0];
                    carts[i].y += dxdy[1];
                }
                else // Angle, 2 possible directions.
                {
                    // UP,LEFT    UP,RIGHT     DOWN,LEFT      DOWN,RIGHT
                    if(carts[i].dir == RIGHT || carts[i].dir == LEFT)
                    {
                        carts[i].dir = (graph[x][y] & UP) | (graph[x][y] & DOWN);
                    }
                    else if(carts[i].dir == UP || carts[i].dir == DOWN)
                    {
                        carts[i].dir = (graph[x][y] & LEFT) | (graph[x][y] & RIGHT);
                    }

                    int[] dxdy = this.getDxDy(carts[i].dir);
                    carts[i].x += dxdy[0];
                    carts[i].y += dxdy[1];
                }

                // Check collision
                boolean collision = this.multipleCartsCollided(carts, carts[i].x, carts[i].y);
                if(collision)
                {
                    System.out.println("Collision at " + carts[i].x + "," + carts[i].y);
                    keepGoing = false;
                    break;
                }
            }

            // this.printUpdate(input, carts);
            // System.out.println(carts[0] + ", " + carts[1]);
            // Thread.sleep(1000);
        }

    }

    private int getLeftTurn(int dir)
    {
        if(dir == UP)
        {
            return LEFT;
        }
        else if(dir == DOWN)
        {
            return RIGHT;
        }
        else if(dir == LEFT)
        {
            return DOWN;
        }
        else if(dir == RIGHT)
        {
            return UP;
        }
        return -1;
    }

    private int getRightTurn(int dir)
    {
        if(dir == UP)
        {
            return RIGHT;
        }
        else if(dir == DOWN)
        {
            return LEFT;
        }
        else if(dir == LEFT)
        {
            return UP;
        }
        else if(dir == RIGHT)
        {
            return DOWN;
        }
        return -1;
    }

    private int[] getDxDy(int dir)
    {
        int[] dxdy = new int[2];
        if(dir == DOWN)
        {
            dxdy[0] = 0;
            dxdy[1] = 1;
        }
        else if(dir == UP)
        {
            dxdy[0] = 0;
            dxdy[1] = -1;
        }
        else if(dir == LEFT)
        {
            dxdy[0] = -1;
            dxdy[1] = 0;
        }
        else if(dir == RIGHT)
        {
            dxdy[0] = 1;
            dxdy[1] = 0;
        }
        return dxdy;
    }

    private boolean multipleCartsCollided(Cart[] carts, int x, int y)
    {
        int num = 0;
        for(int i=0; i<carts.length; i++)
        {
            if(carts[i].x == x && carts[i].y == y)
                num++;
        }

        return (num > 1);
    }

    public class Cart implements Comparable<Cart>
    {
        public int x;
        public int y;
        public int dir;

        public int currTurn;
        public int[] turns = new int[]{LEFT, ALL_DIRS, RIGHT};

        public Cart(int x, int y, int dir)
        {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.currTurn = 0;
        }

        public int compareTo(Cart other)
        {
            int xDiff = this.x - other.x;

            if(xDiff != 0)
                return xDiff;

            int yDiff = this.y - other.y;
            return yDiff;
        }

        public String toString()
        {
            return "{" + x + "," + y + "," + dir + "}";
        }
    }

    private void buildGraph(int[][] graph, char[][] input)
    {
        for(int x=0; x<maxX; x++)
        {
            for(int y=0; y<maxY; y++)
            {
                char c = input[x][y];

                if(c == '-' || c == '>' || c == '<')
                {
                    graph[x][y] = LEFT | RIGHT;
                }
                else if(c == '|' || c == 'v' || c == '^')
                {
                    graph[x][y] = UP | DOWN;
                }
                else if(c == '+')
                {
                    graph[x][y] = ALL_DIRS;
                }
                else if(c == '/')
                {
                    int dir = 0;
                    if(x == 0 || y == 0)
                        dir = (DOWN | RIGHT);
                    else if(x == maxX - 1 || y == maxY - 1)
                        dir = (LEFT | UP);
                    else
                    {
                        if(input[x+1][y] != ' ' && input[x][y+1] != ' ')
                            dir = (DOWN | RIGHT);
                        else
                            dir = (LEFT | UP);
                    }
                    graph[x][y] = dir;
                }
                else if(c == '\\')
                {
                    int dir = 0;
                    if(x == 0 || y == maxY - 1)
                        dir = (UP | RIGHT);
                    else if(x == maxX - 1 || y == 0)
                        dir = (DOWN | LEFT);
                    else
                    {
                        if(input[x-1][y] != ' ' && input[x][y+1] != ' ')
                            dir = (LEFT | DOWN);
                        else
                            dir = (UP | RIGHT);
                    }

                    graph[x][y] = dir;
                }
                // else if(c == '/' || c == '\\')
                // {
                //     int dir = 0;
                //     if(x > 0 && input[x-1][y] != ' ')
                //     {
                //         dir |= LEFT;
                //     }
                //     if(x < maxX - 1 && input[x+1][y] != ' ')
                //     {
                //         dir |= RIGHT;
                //     }
                //     if(y > 0 && input[x][y-1] != ' ')
                //     {
                //         dir |= UP;
                //     }
                //     if(y < maxY - 1 && input[x][y+1] != ' ')
                //     {
                //         dir |= DOWN;
                //     }
                //     graph[x][y] = dir;
                // }
            }
        }
    }

    private Cart[] buildCarts(char[][] input)
    {
        List<Cart> carts = new ArrayList<Cart>();
        for(int x=0; x<maxX; x++)
        {
            for(int y=0; y<maxY; y++)
            {
                if(input[x][y] == 'v')
                {
                    Cart c = new Cart(x, y, DOWN);
                    carts.add(c);
                }
                else if(input[x][y] == '<')
                {
                    Cart c = new Cart(x, y, LEFT);
                    carts.add(c);
                }
                else if(input[x][y] == '^')
                {
                    Cart c = new Cart(x, y, UP);
                    carts.add(c);
                }
                else if(input[x][y] == '>')
                {
                    Cart c = new Cart(x, y, RIGHT);
                    carts.add(c);
                }
            }
        }
        Cart[] result = new Cart[carts.size()];
        for(int i=0; i<carts.size(); i++)
        {
            result[i] = carts.get(i);
        }
        return result;
    }

    private void printGrid(int[][] graph)
    {
        for(int y=0; y<maxY; y++)
        {
            for(int x=0; x<maxX; x++)
            {
                if(graph[x][y] < 10)
                    System.out.print(" ");
                System.out.print(graph[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");
    }

    private void printGrid(char[][] graph)
    {
        for(int y=0; y<maxY; y++)
        {
            for(int x=0; x<maxX; x++)
            {
                System.out.print(graph[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");
    }

    private void printUpdate(char[][] input, Cart[] carts)
    {
        for(int y=0; y<maxY; y++)
        {
            for(int x=0; x<maxX; x++)
            {
                char c = input[x][y];

                for(Cart cart : carts)
                {
                    if(cart.x == x && cart.y == y)
                    {
                        if(cart.dir == RIGHT)
                            c = '>';
                        if(cart.dir == LEFT)
                            c = '<';
                        if(cart.dir == UP)
                            c = '^';
                        if(cart.dir == DOWN)
                            c = 'v';
                    }
                }

                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("----------------");
    }
}