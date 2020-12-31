package pages.whisk.shopping;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.selenium.SeleniumFactory;

import java.util.Arrays;
import java.util.List;

public class ShoppingListPage extends SeleniumFactory {


    @FindBy(xpath = "//div[contains(text(), 'Create new list')]")
    public WebElement CreateNewList_link;
    @FindBy(xpath = "//div[text()='Create']")
    public WebElement AddItem_textbox;
    @FindBy(xpath = "//input[@data-testid='desktop-add-item-autocomplete']")
    public WebElement SearchItem_textbox;
    @FindBy(xpath = "//div[@class='sc-1hjjpl0 hxpNZm']")
    public WebElement AddItem_icon;
    @FindBy(xpath = "//button[@data-testid='vertical-dots-shopping-list-button']")
    public WebElement ListOptions_dots;
    @FindBy(xpath = "//button[@data-testid='shopping-list-delete-menu-button']")
    public WebElement DeleteList_menuItem;
    @FindBy(xpath = "//button[@data-testid='confirm-delete-button']")
    public WebElement ConfirmDelete_btn;
    @FindBy(xpath = "//div[@data-testid='shopping-lists-list-name']")
    public WebElement ShoppingList_link;
    @FindBy(xpath = "//button[@data-testid='shopping-list-is-primary-menu-button']")
    public WebElement DefaultList_menuItem;

    public ShoppingListPage() {
        super();
    }

    public void createShoppingList() throws Exception {
        click(CreateNewList_link);
        click(AddItem_textbox);
    }

    public boolean addItems(int totalItems) throws Exception {
        Faker faker = new Faker();
        String[] itemsList = new String[totalItems];
        Arrays.setAll(itemsList, p -> faker.food().ingredient());
        List<String> items = Arrays.asList(itemsList);
        int i;
        for (i = 0; i < items.size(); i++) {
            sendKeys(SearchItem_textbox, items.get(i));
            click(AddItem_icon);
            waitForElementToBeInvisible(By.xpath("//div[@class='sc-1hjjpl0 hxpNZm']"));
            waitForElement(By.xpath("//span[@data-testid='shopping-list-item-name']"));
            Thread.sleep(1000);
            Assert.assertTrue("Item Present", driver.getPageSource().contains(items.get(i)));
        }
        if (i == 5)
            return true;
        else
            return false;
    }

    public boolean deleteItems() throws Exception {
        click(ListOptions_dots);
        click(DeleteList_menuItem);
        click(ConfirmDelete_btn);
        click(ShoppingList_link);
        click(ListOptions_dots);

        if(waitForTextToBePresentInElement(DefaultList_menuItem, "Default list") &&
                waitForElementToBeInvisible(By.xpath("//button[@data-testid='shopping-list-delete-menu-button']")))
            return true;
        return false;
    }
}
