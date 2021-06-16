package steps;

import com.microsoft.playwright.Page;
import core.cucumber.Context;

public class TestContext extends Context
 {
    public Page playWrightSession;
    public void sePlayWrightSession( Page session)
    {
       playWrightSession = session;
    }

    public Page getPlayWrightSession()
    {
       return playWrightSession;
    }


}
