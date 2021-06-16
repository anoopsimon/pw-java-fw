package core;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.google.common.flogger.FluentLogger;
import com.microsoft.playwright.*;

public class PlaywrightAdapter {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public Page initialize()
    {        
        logger.atInfo().log("Initializing a browser session using Playwright");
        Browser browser = Playwright
        .create()
        .chromium()
        .launch(new BrowserType.LaunchOptions().withHeadless(TestConfiguration.headless()));

        Page page = browser.newPage();
        logger.atInfo().log("Successfully created a new session using Playwright");

        return page;
    }
}
