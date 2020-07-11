import java.util.*;
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
		//Parser s = new Parser(System.in);
		//Scanner s = new Scanner(System.in);
		
		int N = Integer.parseInt(br.readLine());
		br.readLine();

		while(N-->0)
		{
			Team[] teams = new Team[101];

			String line;
			while((line = br.readLine()) != null && line.length() > 0)
			{
				String[] parts = line.split(" ");

				// contestant problem time L
				int id = Integer.parseInt(parts[0]);
				int problemNum = Integer.parseInt(parts[1]);
				int time = Integer.parseInt(parts[2]);
				char L = parts[3].charAt(0);

				if(teams[id] == null)
				{
					//System.err.println("Creating team " + id);
					teams[id] = new Team(id);
				}

				if(L == 'I')
				{
					teams[id].IncorrectSubmissions[problemNum]++;
				}
				else if (L == 'C')
				{
					if(teams[id].Solved[problemNum])
						continue;

					teams[id].Solved[problemNum] = true;
					teams[id].NumSolved++;

					teams[id].PenaltyTime += time + 20 * teams[id].IncorrectSubmissions[problemNum];
				}
			}

			List<Team> actualTeams = new ArrayList<Team>();
			for(int i=0; i<teams.length; i++)
			{
				if(teams[i] != null)
				{
					actualTeams.add(teams[i]);
				}
			}

			Collections.sort(actualTeams);

			for(int i=0; i<actualTeams.size(); i++)
			{
				Team team = actualTeams.get(i);
				System.out.println(team.Id + " " + team.NumSolved + " " + team.PenaltyTime);
			}

			if(N > 0)
			{
				System.out.println();
			}
		}
	}

	public class Team implements Comparable<Team>
	{
		public boolean[] Solved = new boolean[10];
		public int[] IncorrectSubmissions = new int[10];
		public int NumSolved = 0;
		public int PenaltyTime = 0;
		public int Id;

		public Team(int id)
		{
			this.Id = id;
		}

		public int compareTo(Team other)
		{
			int solveDiff = (this.NumSolved - other.NumSolved) * -1;

			if(solveDiff != 0)
				return solveDiff;
			
			int penaltyDiff = (this.PenaltyTime - other.PenaltyTime) * -1;

			if(penaltyDiff != 0)
				return penaltyDiff;

			return this.Id - other.Id;
		}
	}
}