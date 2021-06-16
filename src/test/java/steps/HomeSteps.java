package steps;

import java.util.stream.Stream;

import com.microsoft.playwright.Page;

import core.PlaywrightAdapater;
import core.cucumber.CoreSteps;
import core.cucumber.Context;

import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import core.ExcelUtil;
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
        searchPage.navigate("https://automationbookstore.dev/");
        searchPage.search("Test");

        // Stream<Row> sheet =
        // ExcelUtil.readSheet(System.getProperty("user.dir")+"/src/test/data/tData.xlsx","Sheet1");
        // System.out.print(sheet.findFirst().get().getCell(0).asString());

    }
}
