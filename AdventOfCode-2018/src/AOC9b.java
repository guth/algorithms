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
        // Scanner s = new Scanner(System.in);

        // this took 40 minutes to run for part B and had integer overflows, but it worked!
        // Answer is the output + 2^31 + 2^31.
        // I should use a linked list with a reference to the current marble node
        // for O(1) removal and additions, but letting this run while making
        // food was easier.
        int NUM_PLAYERS = 413;
        int NUM_MARBLES = 7108200;

        int[] score = new int[NUM_PLAYERS];

        // Player 4 just went, and this is the current state:
        // [4]  0 (4) 2  1  3
        List<Integer> marbles = new ArrayList<Integer>();
        marbles.add(0);
        marbles.add(4);
        marbles.add(2);
        marbles.add(1);
        marbles.add(3);

        int currMarble = 1;
        int currPlayer = 5;
        for(int marble = 5; marble <= NUM_MARBLES; marble++)
        {
            // Player makes their move.
            if(marble % 23 == 0)
            {
                score[currPlayer] += marble;

                int toRemove = currMarble - 7;
                if(toRemove < 0)
                {
                    toRemove += marbles.size();
                }

                score[currPlayer] += marbles.get(toRemove);
                marbles.remove(toRemove);
                currMarble = toRemove;
            }
            else
            {
                currMarble = (currMarble + 2);
                if(currMarble > marbles.size())
                {
                    currMarble = currMarble % marbles.size();
                }

                marbles.add(currMarble, marble);
            }

            // Next player goes.
            currPlayer = (currPlayer + 1) % NUM_PLAYERS;

            // System.out.println(marbles);
            if(marble % 100000 == 0)
                System.out.println(marble);
        }
        int max = Integer.MIN_VALUE;
        for(int i=0; i<score.length; i++)
        {
            max = Math.max(max, score[i]);
        }
        System.out.println(max);
    }

}