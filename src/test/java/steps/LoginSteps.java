package steps;
import java.util.stream.Stream;

import com.microsoft.playwright.Page;

import core.PlaywrightAdapater;
import core.cucumber.CoreSteps;
import core.cucumber.ScenarioContext;

import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import core.ExcelUtil;
import io.cucumber.java.en.Given;
import pages.SearchPage;



public class LoginSteps{
    private ScenarioContext scenarioContext;
    public LoginSteps(ScenarioContext scenarioContext)
    {
        this.scenarioContext = scenarioContext;
    }

    @Given("I navigate to Playwright website")
    public void navigateToPlaywright() throws Exception {
        PlaywrightAdapater adapter= new PlaywrightAdapater();
        Page page = adapter.initialize();
        scenarioContext.set("session",page);
       ;
        SearchPage searchPage = new SearchPage(page);
        searchPage.navigate("https://automationbookstore.dev/");
        searchPage.search("Test");

      // Stream<Row> sheet = ExcelUtil.readSheet(System.getProperty("user.dir")+"/src/test/data/tData.xlsx","Sheet1");
       //System.out.print(sheet.findFirst().get().getCell(0).asString());
      

    }
}
