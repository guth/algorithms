import java.util.*;
import java.io.*;
import java.text.*;

class Day4
{
    public static void main(String[] args) throws Exception
    {
        Day4 m = new Day4();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        String numbersCalledString = br.readLine();
        String[] numberParts = numbersCalledString.split(",");

        List<Integer> numbers = new ArrayList<Integer>();
        for(String numberString : numberParts)
        {
            numbers.add(Integer.parseInt(numberString));
        }

        List<Board> boards = new ArrayList<Board>();
        boolean setupDone = false;

        String line;
        while ((line = br.readLine()) != null)
        {
            // System.out.println(line.length() + ": " + line);
            if (line.length() == 0)
            {
                Board board = new Board();
                // Next 5 lines are the board.
                for(int r=0; r<5; r++)
                {
                    String rowString = br.readLine();
                    if(rowString == null || rowString.length() == 0)
                    {
                        setupDone = true;
                        break;
                    }
                    // System.out.println("rowString: " + rowString);
                    String[] rowParts = rowString.split(" ");
                    int c = 0;
                    for(int i=0; i<rowParts.length; i++)
                    {
                        if(rowParts[i].length() == 0)
                            continue;

                        board.grid[r][c] = Integer.parseInt(rowParts[i]);
                        c++;
                    }
                }
                if(setupDone)
                    break;
                boards.add(board);
            }
        }

        // Part One
        Board winner = null;
        int lastCalled = 0;
        for(Integer number : numbers)
        {
            lastCalled = number;
            for(Board board : boards)
            {
                board.markNumber(number);

                if(board.isWinner())
                {
                    winner = board;
                    break;
                }
            }

            if(winner != null)
            {
                break;
            }
        }

        System.out.println("Winner:");
        winner.printGrid();
        System.out.println("Last called: " + lastCalled);
        System.out.println("Winner score: " + winner.calculateScore());

        System.out.println("Part One: " + lastCalled * winner.calculateScore());

        // Part Two
        for(Board board : boards)
        {
            board.resetGrid();
        }
        int numBoards = boards.size();
        Board lastWinner = null;

        List<Board> winningOrder = new ArrayList<Board>();
        List<Integer> winningNumber = new ArrayList<Integer>();
        List<Integer> scoreWhenWon = new ArrayList<Integer>();
        for(Integer number : numbers)
        {
            for(Board board : boards)
            {
                boolean alreadyWon = board.isWinner();

                board.markNumber(number);

                if(!alreadyWon && board.isWinner())
                {
                    winningOrder.add(board);
                    winningNumber.add(number);
                    scoreWhenWon.add(board.calculateScore());
                }
            }
        }
        System.out.println("winningNumbers: " + winningNumber);
        int answer = scoreWhenWon.get(numBoards - 1) * winningNumber.get(numBoards - 1);
        System.out.println("Part Two: " + answer);
    }

    private int countWinnters(List<Board> boards)
    {
        int winnerCount = 0;
        for(Board board : boards)
        {
            if(board.isWinner())
            {
                winnerCount++;
            }
        }
        return winnerCount;
    }

    class Board
    {
        public int[][] grid;
        public boolean[][] seen;

        public Board()
        {
            this.grid = new int[5][5];
            this.seen = new boolean[5][5];
        }

        public void markNumber(int num)
        {
            for(int r=0; r<5; r++)
            {
                for(int c=0; c<5; c++)
                {
                    if(this.grid[r][c] == num)
                    {
                        this.seen[r][c] = true;
                    }
                }
            }
        }

        public boolean isWinner()
        {
            for(int r=0; r<5; r++)
            {
                if(seen[r][0] && seen[r][1] && seen[r][2] && seen[r][3] && seen[r][4])
                    return true;
            }

            for(int c=0; c<5; c++)
            {
                if(seen[0][c] && seen[1][c] && seen[2][c] && seen[3][c] && seen[4][c])
                    return true;
            }

            boolean diagonal = true;
            for(int i=0; i<5; i++)
            {
                if(!seen[i][i])
                    diagonal = false;
            }
            if(diagonal)
                return diagonal;
            for(int i=0; i<5; i++)
            {
                if(!seen[0+i][4-i])
                    diagonal = false;
            }
            return diagonal;
        }

        public int calculateScore()
        {
            int score = 0;
            for(int r=0; r<5; r++)
            {
                for(int c=0; c<5; c++)
                {
                    if(!this.seen[r][c])
                    {
                        score += this.grid[r][c];
                    }
                }
            }

            return score;
        }

        public void resetGrid()
        {
            for(int r=0; r<5; r++)
            {
                for(int c=0; c<5; c++)
                {
                    this.seen[r][c] = false;
                }
            }
        }

        public void printGrid()
        {
            System.out.println("----------------");
            for(int r=0; r<5; r++)
            {
                for(int c=0; c<5; c++)
                {
                    if(this.seen[r][c])
                        System.out.print("*");
                    System.out.print(this.grid[r][c]);
                    if(this.seen[r][c])
                        System.out.print("*");
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println("----------------");

        }
    }
}