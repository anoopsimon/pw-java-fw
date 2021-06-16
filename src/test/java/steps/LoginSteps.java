package steps;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;

import core.ExcelUtil;
import io.cucumber.java.en.Given;



public class LoginSteps {
    
    @Given("I navigate to Playwright website")
    public void navigateToPlaywright() throws Exception {
       Stream<Row> sheet = ExcelUtil.readSheet(System.getProperty("user.dir")+"/src/test/data/tData.xlsx","Sheet1");
       System.out.print(sheet.findFirst().get().getCell(0).asString());
    }
}
