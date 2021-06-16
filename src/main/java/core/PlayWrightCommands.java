package core;

import java.util.List;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

public class PlayWrightCommands
{
    Page page;
    public PlayWrightCommands(Page page){
        this.page=page;
    }

    public Page getPage(){
        return page;
    }

    public void click(String selector)
    {
        getPage().click(selector);
    }
    public List<ElementHandle> getElements(String selector)
    {        
       return getPage().querySelectorAll(selector);
    }

    public void type(String selector , String value)
    {
        getPage().fill(selector,value);
    }

    public void goTo(String url)
    {
        getPage().navigate(url);
    }

    public void handleDialog(boolean accept)
    {
        if(accept)
            getPage().onDialog(dialog -> dialog.accept());
        else
            getPage().onDialog(dialog -> dialog.dismiss());
    }

    public void getPopupMessage()
    {
        getPage().onDialog(dialog -> dialog.message());        
    }

    public String getText(String selector)
    {
        String content = getPage().textContent(selector);

        return content;
    }

    public String getInnerText(String selector)
    {
        String innerText = getPage().innerText(selector);

        return innerText;
    }

    public boolean isChecked(String selector)
    {
        boolean isChecked = getPage().isChecked(selector);

        return isChecked;
    }

    public boolean isVisible(String selector)
    {
        boolean visible = getPage().isVisible(selector);

        return visible;
    }
    public boolean isEnabled(String selector)
    {
        boolean enabled = getPage().isEnabled(selector);

        return enabled;
    }

    

}