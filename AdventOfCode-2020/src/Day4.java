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
        
        // Read input
        List<String> lines = new ArrayList<String>();
        String line;
        String currLine = "";
        while((line = br.readLine()) != null)
        {
            if(line.length() != 0)
            {
                if(currLine.length() != 0)
                    currLine += " ";
                currLine += line;
                continue;
            }

            // System.out.println(currLine);

            lines.add(currLine);
            currLine = "";
        }
        lines.add(currLine);

        // Turn passwords into maps
        List<Map<String, String>> passports = new ArrayList<Map<String,String>>();
        for(String inputLine : lines)
        {
            Map<String, String> passport = new HashMap<String, String>();
            String[] parts = inputLine.split(" ");
            for(String part : parts)
            {
                String[] entry = part.split(":");
                passport.put(entry[0], entry[1]);
            }

            passports.add(passport);
        }

        System.out.println("Num passports: " + passports.size());

    // byr (Birth Year)
    // iyr (Issue Year)
    // eyr (Expiration Year)
    // hgt (Height)
    // hcl (Hair Color)
    // ecl (Eye Color)
    // pid (Passport ID)
    // cid (Country ID)

        // Count the valid ones!
        int count = 0;
        for(Map<String, String> passport : passports)
        {
            if(passport.keySet().size() == 8
                || (passport.keySet().size() == 7 && !passport.containsKey("cid")))
            {
                count++;
            }
        }
        
        System.out.println("Part A: " + count);

        // Part B!
        int partB = 0;
        for(Map<String, String> passport : passports)
        {
            // cid (Country ID) - ignored, missing or not.
            passport.remove("cid");
            if(passport.keySet().size() != 7)
            {
                continue;
            }

            try
            {
                System.out.println("passport: " + passport);

                // byr (Birth Year) - four digits; at least 1920 and at most 2002.
                int byr = Integer.parseInt(passport.get("byr"));
                if(byr < 1920 || byr > 2002)
                {
                    System.out.println("Failed byr: " + passport.get("byr"));
                    continue;
                }

                // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
                int iyr = Integer.parseInt(passport.get("iyr"));
                if(iyr < 2010 || iyr > 2020)
                {
                    System.out.println("Failed iyr: " + passport.get("iyr"));
                    continue;
                }

                // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
                int eyr = Integer.parseInt(passport.get("eyr"));
                if(eyr < 2010 || eyr > 2030)
                {
                    System.out.println("Failed eyr: " + passport.get("eyr"));
                    continue;
                }

                // hgt (Height) - a number followed by either cm or in:
                //     If cm, the number must be at least 150 and at most 193.
                //     If in, the number must be at least 59 and at most 76.
                String hgtRegex = "(1[5-8][0-9]|19[0-3])cm|(59|6[0-9]|7[0-6])in";
                if(!passport.get("hgt").matches(hgtRegex))
                {
                    System.out.println("Failed hgt: " + passport.get("hgt"));
                    continue;
                }

                // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
                String hclRegex = "#[0-9a-f]{6}";
                if(!passport.get("hcl").matches(hclRegex))
                {
                    System.out.println("Failed hcl: " + passport.get("hcl"));
                    continue;
                }

                // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
                String eclRegex = "amb|blu|brn|gry|grn|hzl|oth";
                if(!passport.get("ecl").matches(eclRegex))
                {
                    System.out.println("Failed ecl: " + passport.get("ecl"));
                    continue;
                }

                // pid (Passport ID) - a nine-digit number, including leading zeroes.
                String pidRegex = "[0-9]{9}";
                if(!passport.get("pid").matches(pidRegex))
                {
                    System.out.println("Failed pid: " + passport.get("pid"));
                    continue;
                }

                partB++;
            }
            catch(Exception e)
            {
                // Parsing failed somewhere. Don't count the passport!
            }
        }
        System.out.println("Part A: " + count);
        System.out.println("Part B: " + partB);
    }
}