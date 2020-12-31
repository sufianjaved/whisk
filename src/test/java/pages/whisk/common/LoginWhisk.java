package pages.whisk.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ApplicationConfiguration;
import utils.selenium.SeleniumFactory;

public class LoginWhisk extends SeleniumFactory {

    @FindBy(id="_input-1") public WebElement Email_textbox;
    @FindBy(id="_input-2") public WebElement Password_textbox;
    @FindBy(xpath="//div[text()='Continue']") public WebElement Continue_btn;
    @FindBy(xpath="//button[@data-testid='auth-login-button']") public WebElement Login_btn;

    public void loginWhisk() throws Exception{
        driver.get(ApplicationConfiguration.getWhiskApplicationURL());
        waitForElement(Email_textbox);
        clearField(Email_textbox);
        sendKeys(Email_textbox, ApplicationConfiguration.getWhiskUsername());
        click(Continue_btn);
        clearField(Password_textbox);
        sendKeys(Password_textbox, ApplicationConfiguration.getWhiskUserPassword());
        click(Login_btn);
        waitForElementToBeInvisible(By.xpath("//button[@data-testid='auth-login-button']"));
    }
}
