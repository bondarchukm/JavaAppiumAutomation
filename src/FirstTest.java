import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTest extends CoreTestCase {

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

    @Test
    public void testCancelAfterSearch()
    {
        String search_value = "Java";

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

        assertTrue( "Less than 3 articles found",driver.findElementsById("org.wikipedia:id/page_list_item_container").size()>2);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Search results are still present on the page",
                5
        );
    }

    @Test
    public void testTextPresenceAtSearchField()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForTextPresentInField(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Placeholder text is not 'Search...'",
                5
        );

    }

    @Test
    public void testSaveTwoArticlesInOneList()
    {
        // Adding first article to the favourits list

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String first_article_search_line = "Game of Thrones";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                first_article_search_line,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + first_article_search_line +"']"),
                "No results found for " + first_article_search_line,
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='" + first_article_search_line +"']"),
                "Cannot find article title",
                10
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                10
        );
        MainPageObject. waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got It' tip overlay",
                5
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "My TV-shows";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5
        );

        // Adding second article to the favourits list

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String second_article_search_line = "Friends";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                second_article_search_line,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + second_article_search_line +"']"),
                "No results found for " + second_article_search_line,
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='" + second_article_search_line +"']"),
                "Cannot find article title",
                10
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                10
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView'][@text='" + name_of_folder + "']"),
                "Cannot find previously saved list " + name_of_folder,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5
        );

        // Open previously saved list

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5
        );

        // Delete first saved article

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='" + first_article_search_line + "']"),
                "Cannot find saved article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='" + first_article_search_line + "']"),
                "Cannot delete saved article",
                5
        );

        // Verify that second article is still in the list and click on it

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='" + second_article_search_line +"']"),
                "Second added article " + second_article_search_line + " not found",
                5
        );

        // Verify that article title is matched to the second search

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text'][@text='" + second_article_search_line +"']"),
                "Article title is not matched to " + second_article_search_line,
                10
        );
    }

    @Test
    public void testCheckArticleTitlePresence()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String article_search_line = "Game of Thrones";
        String article_search_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + article_search_line +"']";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                article_search_line,
                "Cannot find search input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath(article_search_locator),
                "No results found for " + article_search_line,
                5
        );
        String article_title_locator_id = "org.wikipedia:id/view_page_title_text";

        MainPageObject.assertElementPresent(
                By.id(article_title_locator_id),
                "Article title '" + article_search_line +" 'is not found"
        );
    }

}
