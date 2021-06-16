package steps;
import com.microsoft.playwright.Page;

import core.TestConfiguration;
import io.cucumber.java.en.Given;
import pages.SearchPage;

public class HomeSteps {
    private TestContext testContext;

    public HomeSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @Given("I verify search")
    public void navigateToPlaywright() throws Exception 
    {
        Page page1 = testContext.getPlayWrightSession();
        SearchPage searchPage = new SearchPage(page1);
        searchPage.navigate(TestConfiguration.appUrl());
        searchPage.search("Test");
    }
}
