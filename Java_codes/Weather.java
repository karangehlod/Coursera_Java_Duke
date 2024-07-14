import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class Weather {

    public static CSVRecord MaxTemperaturDay(CSVParser parser) {
        CSVRecord largestCsvRecord = null;
        for (CSVRecord curreCsvRecord : parser) {

            largestCsvRecord = LargestOfTwo(curreCsvRecord, largestCsvRecord);
        }
        return largestCsvRecord;
    }

    public static CSVRecord HottestInDays() {
        CSVRecord hottestCsvRecord = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);

            CSVRecord currentCsvRecord = MaxTemperaturDay(fr.getCSVParser());
            hottestCsvRecord = LargestOfTwo(currentCsvRecord, hottestCsvRecord);
        }
        return hottestCsvRecord;
    }

    public static CSVRecord LargestOfTwo(CSVRecord currentCsvRecord, CSVRecord hottestCsvRecord) {
        if (hottestCsvRecord == null) {
            hottestCsvRecord = currentCsvRecord;
        } else {
            Double currTemp = Double.parseDouble(currentCsvRecord.get("TemperatureF"));
            Double hotTemp = Double.parseDouble(hottestCsvRecord.get("TemperatureF"));
            if (hotTemp < currTemp) {
                hottestCsvRecord = currentCsvRecord;
            }
        }
        return hottestCsvRecord;
    }

    public static CSVRecord ColdestHourInFile(CSVParser parser) {
        CSVRecord coldCsvRecord = null;
        for (CSVRecord curreCsvRecord : parser) {
            coldCsvRecord = SmallestOfTwo(curreCsvRecord, coldCsvRecord, "TemperatureF");
        }
        return coldCsvRecord;
    }

    public static CSVRecord ColdestInDays() {
        CSVRecord coldestCsvRecord = null;
        String filename = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);

            CSVRecord currentCsvRecord = ColdestHourInFile(fr.getCSVParser());
            coldestCsvRecord = SmallestOfTwo(currentCsvRecord, coldestCsvRecord, "TemperatureF");
            if (coldestCsvRecord.equals(currentCsvRecord)) {
                filename = f.getName();
            }
        }
        System.out.println("Lowest Temperature is in file: " + filename);
        return coldestCsvRecord;
    }

    public static CSVRecord SmallestOfTwo(CSVRecord currentCsvRecord, CSVRecord coldCsvRecord, String headerCSV) {
        if (coldCsvRecord == null) {
            coldCsvRecord = currentCsvRecord;
        } else {
            Double currTemp = Double.parseDouble(currentCsvRecord.get(headerCSV));
            Double coldTemp = Double.parseDouble(coldCsvRecord.get(headerCSV));
            if (currTemp < coldTemp && currTemp > -100) {
                coldCsvRecord = currentCsvRecord;
            }
        }
        return coldCsvRecord;
    }

    public static CSVRecord LowestHumidityInFile(CSVParser parser) {
        CSVRecord lowHumidity = null;
        for (CSVRecord currentRecord : parser) {
            if (!currentRecord.get("Humidity").contains("N/A")) {
                lowHumidity = SmallestOfTwo(currentRecord, lowHumidity, "Humidity");
            }
        }
        return lowHumidity;
    }

    public static CSVRecord LowestHumidityInMany() {
        CSVRecord lowesthumidityCsvRecord = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRecord = LowestHumidityInFile(parser);
            lowesthumidityCsvRecord = SmallestOfTwo(currRecord, lowesthumidityCsvRecord, "Humidity");
        }
        return lowesthumidityCsvRecord;
    }

    public static Double AverageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        double avarage = 0;
        int count = 1;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum += currentTemp;
            avarage = sum / count;
            count++;
        }
        return avarage;
    }
    
    public static Double AverageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        Double sumTemperature = 0.0;
        int count = 0;
        for (CSVRecord curreCsvRecord : parser) {
            if (!curreCsvRecord.get("Humidity").contains("N/A")) {
                Double currHumidity = Double.parseDouble(curreCsvRecord.get("Humidity"));
                if (currHumidity >= value) {
                    sumTemperature += Double.parseDouble(curreCsvRecord.get("TemperatureF"));
                    count++;
                }
            }
        }
        return sumTemperature /count;
    }

    public static void main(String[] args) {
        // FileResource fr = new FileResource("Java_codes\\input\\nc_weather\\2013\\weather-2013-09-02.csv");
        // CSVParser parser = fr.getCSVParser();
        // CSVRecord res = MaxTemperaturDay(parser);
        // System.out.println(res.get("TemperatureF")+" at time "+ res.get("TimeEST"));
        // CSVRecord res = HottestInDays();
        // System.out.println(res.get("TemperatureF")+" at time "+ res.get("DateUTC"));
        // CSVRecord res = ColdestHourInFile(parser);
        // System.out.println(res.get("TemperatureF") + " at time " +
        // res.get("DateUTC"));
        CSVRecord colCsvRecord = ColdestInDays();
        System.out.println(colCsvRecord.get("TemperatureF")+ " at time " + colCsvRecord.get("DateUTC"));
        // CSVRecord res = LowestHumidityInFile(parser);
        // System.out.println("Lowest Humidity " + res.get("Humidity") + " at time " + res.get("DateUTC"));
        // CSVRecord res= LowestHumidityInMany();
        // System.out.println("Lowest Humidity " + res.get("Humidity") + " at time " +  res.get("DateUTC"));
        // System.out.println("Average Temperature is: " + AverageTemperatureInFile(parser));
        // System.out.println("Average Temperature when high Humidity is: " + AverageTemperatureWithHighHumidityInFile(parser, 80));
    }
}
