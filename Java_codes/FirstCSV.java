
import org.apache.commons.csv.*;

import edu.duke.FileResource;

public class FirstCSV {
    public static void ReadFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.print(record.get("Name")+ " ");
            System.out.println(record.get("Favorite Food"));
        }
    }

    public static void ListOfExporter(CSVParser parser, String foodOfInterest) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(foodOfInterest)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public static String CountryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String data = record.get("Country");
            if (data.contains(country)) {
                return data + ":" + record.get("Exports") + ":" + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }

    public static void ListExportersTwoProducts(CSVParser parser, String exportItem1, String exportitem2) {
        for (CSVRecord record : parser) {
            String data = record.get("Exports");
            if (data.contains(exportItem1) && data.contains(exportitem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public static int NumberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String data = record.get("Exports");
            if (data.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }

    public static void BigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String data = record.get("Value (dollars)");
            if (data.length() > amount.length()) {
                System.out.println(record.get("Country")+ " " + data);
            }
        }
    }

    public static void main(String[] args) {
        // ReadFood();
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // ListOfExporter(parser, "coffee");
        // System.out.println(CountryInfo(parser, "Germany"));
        // ListExportersTwoProducts(parser, "diamond", "gold");
        // System.out.println(NumberOfExporters(parser, "gold"));
        BigExporters(parser, "$999,999,999");
    }
}