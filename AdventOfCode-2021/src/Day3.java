import java.util.*;
import java.io.*;
import java.text.*;

class Day3
{
    public static void main(String[] args) throws Exception
    {
        Day3 m = new Day3();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        List<Integer> rates = new ArrayList<Integer>();
        int numBits = 0;

        String line;
        while ((line = br.readLine()) != null)
        {
            rates.add(Integer.parseInt(line, 2));

            numBits = line.length();
        }
        // System.out.println(rates.toString());

        int[] numOnes = new int[numBits];
        int[] numZeroes = new int[numBits];

        int most = 0;
        int least = 0;

        // Part One
        for(int i=0; i<numBits; i++)
        {
            for(Integer rate : rates)
            {
                int bit = ((rate >> i) & 1);
                if (bit == 1)
                    numOnes[i]++;
                else
                    numZeroes[i]++;
            }

            if (numOnes[i] >= numZeroes[i])
            {
                most |= (1 << i);
                least |= (0 << i); // No-op, but better code readability.
            }
            else
            {
                most |= (0 << i);
                least |= (1 << i);
            }
        }

        System.out.println("Most: " + most);
        System.out.println("Least: " + least);
        System.out.println("Part One: " + (most * least));

        // Part Two
        List<Integer> copyMost = new ArrayList<Integer>(rates);
        List<Integer> copyLeast = new ArrayList<Integer>(rates);

        // Get Most
        for(int i=numBits-1; i>=0; i--)
        {
            int bitGoal = (numOnes[i] >= numZeroes[i]) ? 1 : 0;
            // System.out.println("i=" + i + ", bitGoal=" + bitGoal);
            // System.out.println("numOnes: " + numOnes[i] + ", numZeroes: " + numZeroes[i]);
            for(int k=0; k<copyMost.size(); k++)
            {
                if (((copyMost.get(k) >> i) & 1) != bitGoal)
                {
                    // System.out.println("Removing " + copyMost.get(k));
                    copyMost.remove(k);
                    k--;
                }
                if (copyMost.size() == 1)
                    break;
            }
            // System.out.println("bit i=" + i + ", copyMost=" + copyMost);
            if(copyMost.size() == 1)
                break;

            numOnes = this.countOnes(copyMost, numBits);
            numZeroes = this.countZeroes(copyMost, numBits);
        }
        System.out.println("Last copyMost: " + copyMost.get(0));

        // Get Least
        for(int i=numBits-1; i>=0; i--)
        {
            numOnes = this.countOnes(copyLeast, numBits);
            numZeroes = this.countZeroes(copyLeast, numBits);

            int bitGoal = (numOnes[i] >= numZeroes[i]) ? 0 : 1;
            // System.out.println("i=" + i + ", bitGoal=" + bitGoal);
            // System.out.println("numOnes: " + numOnes[i] + ", numZeroes: " + numZeroes[i]);
            for(int k=0; k<copyLeast.size(); k++)
            {
                if (((copyLeast.get(k) >> i) & 1) != bitGoal)
                {
                    // System.out.println("Removing " + copyLeast.get(k));
                    copyLeast.remove(k);
                    k--;
                }
                if (copyLeast.size() == 1)
                    break;
            }
            // System.out.println("bit i=" + i + ", copyLeast=" + copyLeast);
            if(copyLeast.size() == 1)
                break;
        }
        System.out.println("Last copyLeast: " + copyLeast.get(0));

        System.out.println("Part Two: " + copyMost.get(0) * copyLeast.get(0));
    }

    private int[] countOnes(List<Integer> list, int numBits)
    {
        int[] numOnes = new int[numBits];
        int[] numZeroes = new int[numBits];
        for(int i=0; i<numBits; i++)
        {
            for(Integer num : list)
            {
                int bit = ((num >> i) & 1);
                if (bit == 1)
                    numOnes[i]++;
                else
                    numZeroes[i]++;
            }
        }

        return numOnes;
    }

    private int[] countZeroes(List<Integer> list, int numBits)
    {
        int[] numOnes = new int[numBits];
        int[] numZeroes = new int[numBits];
        for(int i=0; i<numBits; i++)
        {
            for(Integer num : list)
            {
                int bit = ((num >> i) & 1);
                if (bit == 1)
                    numOnes[i]++;
                else
                    numZeroes[i]++;
            }
        }

        return numZeroes;
    }
}