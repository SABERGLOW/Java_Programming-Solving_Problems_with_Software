import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData
{
    public  String countryInfo  (CSVParser parser, String country)
    {
        for(CSVRecord record: parser)
        {
            /* ////////////////////////////////////////// This method works too :) //////////////////////////////////
            String countryCheck = record.get("Country");
            if(countryCheck.contains(country))
            {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(countryCheck + ": " + exports + ": " + value);
                break;
            }
            */ /////////////////////////////////////////////////////////////////////////////////////////////////////
            if(record.get("Country").equals(country))
            {
                String countryCheck = record.get("Country");
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(countryCheck + ": " + exports + ": " + value);
                break;
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for(CSVRecord record: parser)
        {
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                String countryCheck = record.get("Country");
                System.out.println(countryCheck);
            }
        }
    }

    public int numberOfExporters (CSVParser parser, String exportItem)
    {
        int numberOfExporters=0;
        for(CSVRecord record: parser)
        {
            String export = record.get("Exports");
            if(export.contains(exportItem))
            {
                numberOfExporters++;
            }
        }
        return numberOfExporters;
    }

    public void bigExporters (CSVParser parser, String amount)
    {
        for(CSVRecord record: parser)
        {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length())
            {
                String countryCheck = record.get("Country");
                System.out.println(countryCheck + " " + value);
            }
        }
    }




    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // parser = fr.getCSVParser(); //... Each time you want to use the parser with another method,
        ///////////////////////////// .. you will need to reset the parser by calling fr.getCSVParser() again to get a new parser.
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("Country Info for Nauru: ");
        countryInfo(parser, "Nauru");

        parser = fr.getCSVParser();
        System.out.println("Countries that export gold+diamonds: ");
        listExportersTwoProducts(parser, "gold", "diamonds");

        parser = fr.getCSVParser();
        System.out.println("Total countries that export sugar: "+ numberOfExporters(parser, "sugar"));

        parser = fr.getCSVParser();
        System.out.println("Big Exporters: ");
        bigExporters(parser, "$999,999,999,999");

    }


    public static void main (String[] args)
    {
        ParsingExportData CSV = new ParsingExportData();
        CSV.tester();
    }
}
