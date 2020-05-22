package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesInOneList()
    {
        // Adding first article to the favourites list

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String first_article_search_line = "Game of Thrones";

        SearchPageObject.typeSearchLine(first_article_search_line);
        SearchPageObject.clickByArticleWithSubstring(first_article_search_line);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String first_article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "My TV-shows";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        // Adding second article to the favourites list

        SearchPageObject.initSearchInput();
        String second_article_search_line = "Friends";

        SearchPageObject.typeSearchLine(second_article_search_line);
        SearchPageObject.clickByArticleWithSubstring(second_article_search_line);

        ArticlePageObject.addSecondArticleToExistingList(name_of_folder);
        ArticlePageObject.closeArticle();

        // Open previously saved list

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);

        // Delete first saved article

        MyListsPageObject.swipeByArticleToDelete(first_article_title);

      // Verify that second article is still in the list and click on it

        MyListsPageObject.openFolderByName(second_article_search_line);

        // Verify that article title is matched to the second search

        ArticlePageObject.waitForTitleElement();
        String second_article_title = ArticlePageObject.getArticleTitle();

        assertEquals("We see unexpected title", second_article_search_line, second_article_title);
    }
}
