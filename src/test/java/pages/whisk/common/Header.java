package pages.whisk.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.selenium.SeleniumFactory;

public class Header extends SeleniumFactory {


    @FindBy(xpath="//a[contains(text(), 'Shopping')]") public WebElement Shopping_tab;

    public Header(){
        super();
    }

    public void navigateToShoppingTab() throws Exception{
        waitForElement(Shopping_tab);
        click(Shopping_tab);
    }
}
