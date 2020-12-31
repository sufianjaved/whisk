package testcases.ui.shopping;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.whisk.common.Header;
import pages.whisk.shopping.ShoppingListPage;
import base.BaseTestUI;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShoppingListCreateTest extends BaseTestUI {
    Header header;
    ShoppingListPage shoppingListPage;

    @Override
    protected void initializePageElements() {

        shoppingListPage = new ShoppingListPage();
        header = new Header();
    }

    @Test
    public void testA_CreateShoppingListForFiveItems() throws Exception {
        int itemsToBeAdded = 5;
        header.navigateToShoppingTab();
        shoppingListPage.createShoppingList();
        assertTrue(shoppingListPage.addItems(itemsToBeAdded));
    }

    @Test
    public void testB_DeleteShoppingListForFiveItems() throws Exception {
        testA_CreateShoppingListForFiveItems();
        assertTrue(shoppingListPage.deleteItems());
    }
}
