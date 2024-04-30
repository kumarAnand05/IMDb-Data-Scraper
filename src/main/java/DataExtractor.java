import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataExtractor {
    /**
     * Separates the data from the raw string data collected form the media table per page
     * @param rawTableData String data of the entire data table collected per page
     * @return String comma separated media data of each media in the raw table data
     */
    public static String extractData(String rawTableData){
        String[] splitVal = rawTableData.split("\n");

        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d+\\.\\s.*\\((\\d{4})");
        for(String dataLine : splitVal){
            Matcher matcher = pattern.matcher(dataLine);
            if(matcher.find()) {
                sb.append("\n");
            }
            sb.append(dataLine);
            sb.append(",");
        }
        return sb.toString().trim();
    }
}
