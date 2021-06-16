package steps;
import core.ExcelUtil;
import io.cucumber.java.en.Given;



public class LoginSteps {
    
    @Given("I navigate to Playwright website")
    public void navigateToPlaywright() throws Exception {
       ExcelUtil.read(System.getProperty("user.dir")+"/src/test/data/tData.xlsx");
    }
}
