import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTest extends CoreTestCase {

    /*
    @Test
    public void testSearchWordInAllSearchResults()
    {
        String search_value = "JAVA";

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_value,
                "Cannot find search input",
                5
        );

        List<WebElement> elements = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement e : elements)
        {
            String search_value_lc = search_value.toLowerCase();
            assertTrue("Word '" + search_value + "' is not found in search result",e.getText().toLowerCase().contains(search_value_lc));
        }
    }
     */

//
//    @Test
//    public void testTextPresenceAtSearchField()
//    {
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find 'Search Wikipedia' input",
//                5
//        );
//
//        MainPageObject.waitForTextPresentInField(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Search…",
//                "Placeholder text is not 'Search...'",
//                5
//        );
//
//    }



//    @Test
//    public void testCheckArticleTitlePresence()
//    {
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find 'Search Wikipedia' input",
//                5
//        );
//
//        String article_search_line = "Game of Thrones";
//        String article_search_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article_search_line +"']";
//
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search…')]"),
//                article_search_line,
//                "Cannot find search input",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath(article_search_locator),
//                "No results found for " + article_search_line,
//                5
//        );
//        String article_title_locator_id = "org.wikipedia:id/view_page_title_text";
//
//        MainPageObject.assertElementPresent(
//                By.id(article_title_locator_id),
//                "Article title '" + article_search_line +" 'is not found"
//        );
//    }

}
