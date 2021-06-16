package pages;

import com.microsoft.playwright.Page;

import java.util.List;
import java.util.stream.Collectors;

import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.ATTACHED;
import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.DETACHED;

public class SearchPage extends BasePage {

    private Page page;



    public SearchPage(Page page) 
     {
         super(page);
        this.page = page;
    }

    public void search(String query) {
        clearSearchBar();
        commands.type(appSelectors("locator_searchBar"), query);

        var expectedState = new Page.WaitForSelectorOptions().withState(ATTACHED);
        page.waitForSelector(appSelectors("locator_hiddenBooks"), expectedState);
    }

    public void navigate(String url){
        commands.goTo(url);
    }
    public void clearSearchBar() {
        commands.clear(appSelectors("locator_searchBar"));
        var expectedState = new Page.WaitForSelectorOptions().withState(DETACHED);
        page.waitForSelector(appSelectors("locator_hiddenBooks"), expectedState);
    }

    public int getNumberOfVisibleBooks() {
       // return page.querySelectorAll(locator_visibleBooks).size();
       return commands.getElements(appSelectors("locator_visibleBooks")).size();
    }

    public List<String> getVisibleBooks() {
        return page.querySelectorAll(appSelectors("visibleBookTitles"))
                .stream()
                .map(e -> e.innerText())
                .collect(Collectors.toList());
    }
}