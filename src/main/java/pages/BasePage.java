package pages;

import java.util.HashMap;

import com.google.common.flogger.FluentLogger;
import com.microsoft.playwright.Page;

import core.PlayWrightCommands;
import core.util.JsonUtil;

public class BasePage{
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    Page page;
    PlayWrightCommands commands;
    public BasePage(Page page){
        this.page=page;
        commands = new PlayWrightCommands(page);
    }

    protected PlayWrightCommands commands()
    {
        return commands;
    }

    protected String appSelectors(String locatorName)
    {
        logger.atInfo().log("Get value of locator => %s ",locatorName);
        HashMap map= JsonUtil.fromJsonFile(System.getProperty("user.dir")+"/src/main/java/selectors/appSelectors.json", HashMap.class);
        return map.get(locatorName).toString();
    }
    
}