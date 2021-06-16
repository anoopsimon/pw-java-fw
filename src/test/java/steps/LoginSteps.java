package steps;
import com.microsoft.playwright.Page;

import core.PlaywrightAdapater;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import core.ExcelUtil;
import io.cucumber.java.en.Given;
import pages.SearchPage;



public class LoginSteps{
    private TestContext testContext;
    public LoginSteps(TestContext scenarioContext)
    {
        this.testContext = scenarioContext;
    }

    @Given("I navigate to Playwright website")
    public void navigateToPlaywright() throws Exception {
        PlaywrightAdapater adapter= new PlaywrightAdapater();
        Page page = adapter.initialize();
        testContext.sePlayWrightSession(page);
       

      // Stream<Row> sheet = ExcelUtil.readSheet(System.getProperty("user.dir")+"/src/test/data/tData.xlsx","Sheet1");
       //System.out.print(sheet.findFirst().get().getCell(0).asString());
      

    }
}
