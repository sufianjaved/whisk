package utils.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFactory extends EnvironmentSetup {

        Logger logger = LogManager.getLogger("common");

        public SeleniumFactory(){
            super();
        }

        public WebElement waitForElement(By selector){
            return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        }

        public WebElement waitForElement(WebElement element){
            return wait.until(ExpectedConditions.visibilityOf(element));
        }

        public Boolean waitForElementToBeInvisible(By selector){
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
        }

        public Boolean waitForTextToBePresentInElement(WebElement element, String text){
            return wait.until(ExpectedConditions.textToBePresentInElement(element,text));
    }

        public void click(WebElement element){
            waitForElement(element);
            element.click();
        }

        public void sendKeys(WebElement element, CharSequence... keysToSend) throws Exception {
            waitForElement(element);
            try {
                element.sendKeys(keysToSend);
            } catch (Exception e) {
                logger.error(String.format("Error in sending [%s] to the following element: [%s]", keysToSend, element.toString()));
            }
        }

        public void clearField(WebElement element) {
            try {
                waitForElement(element);
                element.clear();
            } catch (Exception e) {
                logger.error(String.format("The following element could not be cleared: [%s]", element.getText()));
            }
        }
}
