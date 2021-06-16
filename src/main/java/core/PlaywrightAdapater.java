package core;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.*;

public class PlaywrightAdapater {
    public Page initialize()
    {        
        Browser browser = Playwright
        .create()
        .chromium()
        .launch(new BrowserType.LaunchOptions().withHeadless(TestConfiguration.headless()));
        Page page = browser.newPage();

        return page;
    }
}
