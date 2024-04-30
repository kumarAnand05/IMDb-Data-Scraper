import java.io.FileWriter;
import java.io.IOException;


public class ExcelUtility {
    /**
     * Creates the csv file from a StringBuilder object
     * @param tableData collected table data as StringBuilder format
     */
    public static void writeCSV(StringBuilder tableData){
        try (FileWriter writer = new FileWriter("IMDbData.csv")) {
            writer.write(tableData.toString());
            System.out.println("CSV file has been created successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred while writing CSV file: " + e.getMessage());
        }
    }
}
