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

        int NUM_PLAYERS = 413;
        int NUM_MARBLES = 71082;

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
        }
        int max = Integer.MIN_VALUE;
        for(int i=0; i<score.length; i++)
        {
            max = Math.max(max, score[i]);
        }
        System.out.println(max);
    }

}