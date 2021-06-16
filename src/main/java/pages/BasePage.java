package pages;

import com.microsoft.playwright.Page;

import core.PlayWrightCommands;

public class BasePage{
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

    
}