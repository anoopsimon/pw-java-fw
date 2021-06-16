package steps;

import com.microsoft.playwright.Page;

import core.cucumber.ScenarioContext;

public class TestContext extends ScenarioContext
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
