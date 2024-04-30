import org.openqa.selenium.WebDriver;

public class Main {

    public static void main(String[] args) {
        
        //Target Website
        WebDriver driver = Driver.createDriver();
        PageObject mediaPage = new PageObject();
        driver.get("https://www.imdb.com/search/keyword/?sort=moviemeter,asc&mode=advanced&page=1");

        // Navigate to media list page
        mediaPage.selectAKeyword();
        mediaPage.clearAllFilters();

        // Stores all the data in csv format
        mediaPage.storeAllData();

        System.out.println("Data Collected successfully");
    }
}
