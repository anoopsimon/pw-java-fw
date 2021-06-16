package steps;
import java.util.HashMap;
import java.util.stream.Stream;

import com.microsoft.playwright.Page;

import core.PlaywrightAdapter;
import org.dhatim.fastexcel.reader.Row;
import core.ExcelUtil;
import core.JsonUtil;
import io.cucumber.java.en.Given;
import com.google.common.flogger.FluentLogger;


public class LoginSteps{
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private TestContext testContext;
    public LoginSteps(TestContext scenarioContext)
    {
        this.testContext = scenarioContext;
    }

    @Given("I navigate to Playwright website")
    public void navigateToPlaywright() throws Exception{
        

        PlaywrightAdapter adapter= new PlaywrightAdapter();
        Page page = adapter.initialize();
        testContext.sePlayWrightSession(page);       

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

    @Given("I read test data from excel")
    public void readTestDataFromExcel() 
    {
        Stream<Row> sheet = ExcelUtil.readSheet(System.getProperty("user.dir") + "/src/test/data/tData.xlsx","Sheet1");
        System.out.print(sheet.findFirst().get().getCell(0).asString());
    }
}
