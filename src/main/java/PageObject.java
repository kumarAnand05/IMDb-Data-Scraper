import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class PageObject extends Driver{

    /**
     * Constructor to initialize the page object elements
     */
    public PageObject(){
        PageFactory.initElements(driver, this);
    }

    /**
     * List of all the keyword elements present on the keyword selection page
     */
    @FindBy(xpath = "//div[@class='table-cell primary']//a")
    List<WebElement> keywords;

    /**
     * List of all the close box of the selected filters keywords
     */
    @FindBy(xpath = "//span[@class='facets-active']//span[contains(@class, 'remove')]")
    List<WebElement> removeFilter;

    /**
     * Next page element
     */
    @FindBy(xpath = "//a[contains(@class,'next-page')]")
    List<WebElement> nextPage;

    /**
     * List of the media
     */
    @FindBy(xpath = "//div[@class='lister-list']")
    WebElement dataTable;

    /**
     * Selects a random keyword from the available list of the keywords from the keyword selection page
     */
    public void selectAKeyword(){
        int keywordSize = keywords.size();
        Random random = new Random();
        int randomKeyword = random.nextInt(keywordSize);
        keywords.get(randomKeyword).click();
    }


    /**
     * Clear all the selected keyword filters
     */
    public void clearAllFilters(){
        try {
            while (!removeFilter.isEmpty()){
                removeFilter.get(0).click();
            }
        }
        catch (NoSuchElementException ignored){
        }
    }


    /**
     * Clicks on the next page button
     */
    public void openNextPage(){
        nextPage.get(1).click();
    }

    /**
     * Collects all the media info as String from the data table
     */
    public String getMediaInfo(){
        return dataTable.getText();
    }

    /**
     * Collects all the data and store the data as csv
     */
    public void storeAllData(){
        StringBuilder collectedData= new StringBuilder();
        while(true){
            try{
                collectedData.append(DataExtractor.extractData(getMediaInfo()));
                collectedData.append("\n");
                openNextPage();
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                Thread.sleep(8000);
            }
            catch (Exception e){
                break;
            }
        }
        ExcelUtility.writeCSV(collectedData);
    }
}
