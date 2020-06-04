package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");


        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncSuggestionScreen();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }
            MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesInOneList()
    {
        // Adding first article to the favourites list

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String first_article_search_line = "Java (programming language)";

        SearchPageObject.typeSearchLine(first_article_search_line);
        SearchPageObject.clickByArticleWithSubstring(first_article_search_line);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement(first_article_search_line);
        String first_article_title = ArticlePageObject.getArticleTitle(first_article_search_line);

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeSyncSuggestionScreen();
        }
        ArticlePageObject.closeArticle();

        // Adding second article to the favourites list

        SearchPageObject.initSearchInput();

        if(Platform.getInstance().isIOS()){
            SearchPageObject.clearSearchLine();
        }

        String second_article_search_line = "Python (programming language)";

        SearchPageObject.typeSearchLine(second_article_search_line);
        SearchPageObject.clickByArticleWithSubstring(second_article_search_line);

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        // Open previously saved list

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        // Delete first saved article

        MyListsPageObject.swipeByArticleToDelete(first_article_title);

      // Verify that second article is still in the list and click on it

        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(second_article_search_line);
        } else if(Platform.getInstance().isIOS()) {
            SearchPageObject.clickByArticleWithSubstring(second_article_search_line);
        }

        // Verify that article title is matched to the second search

        ArticlePageObject.waitForTitleElement(second_article_search_line);
        String second_article_title = ArticlePageObject.getArticleTitle(second_article_search_line);

        assertEquals("We see unexpected title", second_article_search_line, second_article_title);
    }
}
