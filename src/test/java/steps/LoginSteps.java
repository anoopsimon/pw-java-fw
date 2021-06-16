package steps;
import java.util.HashMap;

import com.microsoft.playwright.Page;

import core.PlaywrightAdapater;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import core.ExcelUtil;
import core.JsonUtil;
import io.cucumber.java.en.Given;


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

    @Given("I read test data from json")
    public void readTestData() 
    {
        String file=System.getProperty("user.dir")+"/src/test/data/tData.json";
        HashMap map = JsonUtil.fromJsonFile(file, HashMap.class);
        for (Object iterable_element : map.keySet())
         {
            System.out.println(map.get(iterable_element));

        }
    }
}
