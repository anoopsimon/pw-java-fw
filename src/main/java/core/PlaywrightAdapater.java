package core;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.*;

public class PlaywrightAdapater {
    private Browser browser;

    public Page initialize()
    {        
        Browser browser = Playwright
        .create()
        .chromium()
        .launch(new BrowserType.LaunchOptions().withHeadless(false));
        //A single browser tab
        Page page = browser.newPage();

        return page;
    }
}
