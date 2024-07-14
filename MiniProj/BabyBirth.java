import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

/**
 * @author : Karan Gehlod
 */

public class BabyBirth {
    public static void PrintNames() {
        FileResource fr = new FileResource();
        for (CSVRecord record : fr.getCSVParser(false)) {
            System.out.println("Name :" + record.get(0) + " gender :" + record.get(1) + " Num Born :" + record.get(2));
        }
    }

    public static void TotalBirth(FileResource fr) {
        int totalBirth = 0;
        int mTotalCount = 0;
        int fTotalCount = 0;
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numberBorn = Integer.parseInt(record.get(2));
            totalBirth += numberBorn;
            if (record.get(1).equals("M")) {
                mTotalCount += numberBorn;
            } else {
                fTotalCount += numberBorn;
            }
        }
        System.out.println("Total Births: " + totalBirth + ",Female :" + fTotalCount + ", Male :" + mTotalCount);
    }

    /**
     * This method returns the rank of the name in the file for the given gender,
     * where rank 1 is the name with the largest number of births. If the name is
     * not in the file, then -1 is returned.
     * 
     * @param year
     * @param name
     * @param gender
     * @return rank as int
     */
    public static int GetRank(int year, String name, String gender) {
        FileResource fr = new FileResource(
                "Java_codes\\input\\names\\us_babynames_by_year/yob" + String.valueOf(year) + ".csv");
        int pivot = 0;
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {
                pivot++;
                if (rec.get(0).equals(name))
                    return pivot;
            }
        }
        return -1;
    }

    /**
     * This method returns the name of the person in the file at this rank, for the
     * given gender, where rank 1 is the name with the largest number of births. If
     * the rank does not exist in the file, then “NO NAME” is returned.
     * 
     * @param year
     * @param rank
     * @param gender
     * @return Name at rank
     */
    public static String GetName(int year, int rank, String gender) {

        FileResource fr = new FileResource(
                "Java_codes\\input\\names\\us_babynames_by_year/yob" + String.valueOf(year) + ".csv");
        int pivot = 0;
        CSVParser parser = fr.getCSVParser(false);
        // get the CSV file
        for (CSVRecord rec : parser) {
            if (rec.get(1).equals(gender)) {

                pivot++;
                if (pivot == rank)
                    return rec.get(0);
            }
        }

        System.out.println("The rank: " + rank + "... The last one rank " + pivot + ".");
        return "NO NAME";

    }

    /**
     * determines what name would have been named if they were born in a different
     * year, based on the same popularity. That is, you should determine the rank of
     * name in the year they were born, and then print the name born in newYear that
     * is at the same rank and same gender
     * 
     * @param name
     * @param year
     * @param newYear
     * @param gender
     */
    public static void WhatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = GetRank(year, name, gender);
        // System.out.println(name + " ranks " + rank + " at year " + year1);
        String equalName = GetName(newYear, rank, gender);

        if (gender == "F") {
            System.out.println(name + " born in " + year + " would be " + equalName + " if she was born in " + newYear);
        } else if (gender == "M") {
            // Isabella born in 2012 would be Sophia if she was born in 2014.
            System.out.println(name + " born in " + year + " would be " + equalName + " if he was born in " + newYear);
        }
    }

    /**
     * selects a range of files to process and returns an integer, the year with the
     * highest rank for the name and gender. If the name and gender are not in any
     * of the selected files, it should return -1.
     * 
     * @param name
     * @param gender
     * @return
     */
    public static int YearOfHighestRank(String name, String gender) {
        // initial year and rank;
        int rank = 1000000;
        int yearHigh = 0;

        // get the directory:
        DirectoryResource dr = new DirectoryResource();

        // get the files
        for (File fi : dr.selectedFiles()) {

            // get the name of the file, which contains the year
            String fileName = fi.getName();

            // get the year integer from the name of the file
            int year = Integer.parseInt(fileName.replaceAll("[\\D]", ""));

            // get the FileResource
            FileResource fr = new FileResource(fi);
            int currRank = -1;
            int pivot = 0;
            for (CSVRecord record : fr.getCSVParser(false)) {

                if (record.get(1).equals(gender)) {

                    pivot++;

                    if (record.get(0).equals(name)) {
                        currRank = pivot;
                        break;
                    }

                }

            }

            if (currRank != -1 && currRank < rank) {
                rank = currRank;
                yearHigh = year;
            }

        }

        return yearHigh;
    }

    /**
     * selects a range of files to process and returns a double representing the
     * average rank of the name and gender over the selected files. It should return
     * -1.0 if the name is not ranked in any of the selected files.
     * 
     * @param name
     * @param gender
     * @return
     */
    public static double GetAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int fileNum = 0;
        int totalRank = 0;

        for (File fi : dr.selectedFiles()) {
            fileNum++;

            // get the file resource
            FileResource fr = new FileResource(fi);

            int pivot = 0;
            int currRank = 0;
            for (CSVRecord record : fr.getCSVParser(false)) {
                if (record.get(1).equals(gender)) {
                    pivot++;
                    if (record.get(0).equals(name)) {
                        currRank = pivot;
                        break;
                    }
                }
            }
            totalRank += currRank;
        }
        if (totalRank == 0)
            return -1;
        else
            return (double) (totalRank) / fileNum;
    }


    /**
     * returns an integer, the total number of births of those names with the same
     * gender and same year who are ranked higher than name.
     * 
     * @param year
     * @param name
     * @param gender
     * @return
     */
    public static int GetTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource(
                "Java_codes\\input\\names\\us_babynames_by_year\\yob" + String.valueOf(year) + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int sum = 0;
        for (CSVRecord record : parser) {

            if (record.get(1).equals(gender)) {

                if (record.get(0).equals(name))
                    return sum;

                sum += Integer.parseInt(record.get(2));

            }

        }

        return sum;
    }

    public static void main(String[] args) {
        // PrintNames();
        FileResource fr = new FileResource("Java_codes\\input\\names\\us_babynames_by_year\\yob1900.csv");
        TotalBirth(fr);
        // System.out.println(GetRank(1960, "Emily", "F"));
        // System.out.println(GetRank(1971, "Frank", "M"));
        // System.out.println(GetName(1980, 350, "F"));
        // System.out.println(GetName(1982, 450, "M"));
        // WhatIsNameInYear("Susan", 1972,2014, "F");
        // WhatIsNameInYear("Owen", 1974,2014, "M");
        // System.out.println(YearOfHighestRank("Genevieve","F"));
        // System.out.println(YearOfHighestRank("Mich", "M"));
        // System.out.println(GetAverageRank("Susan", "F"));
        // System.out.println(GetAverageRank("Robert", "M"));
        // System.out.println(GetTotalBirthsRankedHigher(1990,"Emily","F"));
        // System.out.println(GetTotalBirthsRankedHigher(1990,"Drew","M"));

    }
}
