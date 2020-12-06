import java.util.*;
import java.io.*;
import java.text.*;

class Day6
{
    public static void main(String[] args) throws Exception
    {
        Day6 m = new Day6();
        m.go();
    }

    void go() throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Parser s = new Parser(System.in);
        // Scanner s = new Scanner(System.in);

        List<List<String>> groups = new ArrayList<List<String>>();

        List<String> inputGroup = new ArrayList<String>();

        // Read input
        String line;
        while ((line = br.readLine()) != null)
        {
            if (line.length() == 0)
            {
                // We've reached the end of a group.
                groups.add(inputGroup);
                inputGroup = new ArrayList<String>();
                continue;
            }

            inputGroup.add(line);
        }

        // Idk what part two will need, so let's do a list of sets for now.
        List<Set<Character>> groupSets = new ArrayList<Set<Character>>();
        for(List<String> answers : groups)
        {
            Set<Character> groupAnswers = new HashSet<Character>();
            for(String answer : answers)
            {
                for(Character c : answer.toCharArray())
                {
                    groupAnswers.add(c);
                }
            }

            groupSets.add(groupAnswers);
        }

        // Calculate Part One
        int partOne = 0;
        for(Set<Character> groupAnswers : groupSets)
        {
            partOne += groupAnswers.size();
        }

        System.out.println("Part One: " + partOne);

        int partTwo = 0;
        for(List<String> group : groups)
        {
            // For each group, count how many people answered each question
            Map<Character, Integer> answerCount = new HashMap<Character, Integer>();
            for(String answers : group)
            {
                for(Character c : answers.toCharArray())
                {
                    if(answerCount.containsKey(c))
                        answerCount.put(c, answerCount.get(c) + 1);
                    else
                        answerCount.put(c, 1);
                }
            }
            
            for(Character c : answerCount.keySet())
            {
                if(answerCount.get(c) == group.size())
                {
                    partTwo++;
                }
            }
        }

        System.out.println("Part Two: " + partTwo);
    }
}