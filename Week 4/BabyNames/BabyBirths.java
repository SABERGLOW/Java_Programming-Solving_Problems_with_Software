import edu.duke.*;
import org.apache.commons.csv.*;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.File;

public class BabyBirths
{

    public void printNames()
    {
        FileResource fr = new FileResource();
        for(CSVRecord rec: fr.getCSVParser(false)) //... this CSV file doesn't have a header row
        {
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100)
            {
                System.out.println("Name: " + rec.get(0) + " Gender: " + rec.get(1) + " Number Born: " + rec.get(2));
            }
        }
    }

    public void totalBirths(FileResource fr)
    {
        int totalBirths = 0;
        int totalBoys = 0;
        int boyCount = 0;
        int girlCount = 0;
        int totalGirls = 0;
        int totalNames = 0;
        for(CSVRecord rec: fr.getCSVParser(false)) //... this CSV file doesn't have a header row
        {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames++;

            if(rec.get(1).equals("M"))
            {
                totalBoys += numBorn;
                boyCount++;
            }
            else
            {
                totalGirls += numBorn;
                girlCount++;
            }
        }
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Total Boys Names: " + totalBoys);
        System.out.println("Total Girls Names: " + totalGirls);
        System.out.println("Total Names: " + totalNames);
        System.out.println("Total Count of Boys: " + boyCount);
        System.out.println("Total Count of Girls: " + girlCount);
    }

    public int getRank(int year, String name, String gender)
    {
        int getRank = 0;
        String fileName = "C:\\Users\\wali6\\Desktop\\BabyNames\\us_babynames\\us_babynames_by_year\\" + "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        for(CSVRecord rec: fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                getRank++;
                if(rec.get(0).equals(name))
                {
                    return getRank;
                }
            }
        }
        return -1;
    }

    public String getNames(int year, int rank, String gender)
    {
        String fileName = "C:\\Users\\wali6\\Desktop\\BabyNames\\us_babynames\\us_babynames_by_year\\" + "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        for(CSVRecord rec: fr.getCSVParser(false)) //... this CSV file doesn't have a header row
        {
            if(getRank(year, rec.get(0),gender) == rank)
            {
                return rec.get(0);
            }
        }
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int getRank = getRank(year, name, gender);
        System.out.println(name + "born in "+ year + " would be " + getNames(newYear, getRank, gender) + " if born in " + newYear);
    }

    public int yearOfHighestRank (String name, String gender)
    {
        int yearOfHighestRank = -1;
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles())
        {
            int currentYear = Integer.parseInt(f.getName().substring(3,7)); // filename looks like "yob1880"
            int currentRank = getRank(currentYear, name, gender);
            if(yearOfHighestRank == -1 && currentRank != -1)
            {
                yearOfHighestRank = currentYear;
                rank = currentRank;
            }
            else if( (currentRank != -1 && currentRank < rank))
            {
                yearOfHighestRank = currentYear;
                rank = currentRank;
            }
        }
        return yearOfHighestRank;
    }

    public double getAverageRank(String name, String gender)
    {
        double totalRank = 0.0;
        int countFiles = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles())
        {
            int currentYear = Integer.parseInt(f.getName().substring(3,7)); // filename looks like "yob1880"
            int currentRank = getRank(currentYear, name, gender);
            if(currentRank != -1)
            {
                countFiles++;
                totalRank += currentRank;
            }
        }
        if(totalRank == 0.0)
        {
            return -1.0;
        }
        return (double)(totalRank/countFiles);
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        int TotalBirthsRankedHigher = 0;
        int tempRank = 0;
        String fileName = "C:\\Users\\wali6\\Desktop\\BabyNames\\us_babynames\\us_babynames_by_year\\" + "yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int currentRank = getRank(year, name, gender);
        for(CSVRecord rec: fr.getCSVParser(false)) //... this CSV file doesn't have a header row
        {
            if(rec.get(1).equals(gender))
            {
                tempRank++;
                if(tempRank < currentRank)
                {
                    TotalBirthsRankedHigher += Integer.parseInt(rec.get(2));
                }

            }
        }
        return TotalBirthsRankedHigher;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    public void testtotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public void testgetRank()
    {
        int rank = getRank(1971,"Frank", "M");
        if(rank == -1)
        {
            System.out.println("...Rank not found... -1 returned...");
        }
        else
        {
            System.out.println("Rank for Frank in 1971: " + rank);
        }
    }

    public void testgetNames()
    {
        //System.out.println("Name at Rank 350: " + getNames(1980,350, "F"));
        System.out.println("Name at Rank 450: " + getNames(1982,450, "M"));
    }

    public void testwhatIsNameInYear()
    {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }

    public void testyearOfHighestRank()
    {
        System.out.println("Year of Highest Rank for Mich: " + yearOfHighestRank("Mich" , "M"));
    }

    public void testgetAverageRank()
    {
        System.out.println("Avg Rank for Susan: " + getAverageRank("Susan" , "F"));
        System.out.println("Avg Rank for Robert: " + getAverageRank("Robert" , "M"));
    }

    public void testgetTotalBirthsRankedHigher()
    {
        System.out.println("Births Higher than Emily: " + getTotalBirthsRankedHigher(1990, "Emily" , "F"));
        System.out.println("Births Higher than Drew: " + getTotalBirthsRankedHigher(1990, "Drew" , "M"));
    }


//////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        BabyBirths CSV = new BabyBirths();
        //CSV.printNames();
        //CSV.testtotalBirths();
        //CSV.testgetRank();
        //CSV.testgetNames();
        //CSV.testwhatIsNameInYear();
        //CSV.testyearOfHighestRank();
        //CSV.testgetAverageRank();
        CSV.testgetTotalBirthsRankedHigher();
    }
}
