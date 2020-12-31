package testcases.api.shopping;

import base.BaseTestAPI;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static constants.ShoppingControllerConstants.CREATE_SHOPPING_LIST;
import static constants.ShoppingControllerConstants.GET_SHOPPING_LIST;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_OK;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShoppingListTest extends BaseTestAPI {

    public static boolean isPreviousTestPass;
    public static String shoppingListID = null;

    /*******************************************************
     * Create a new Shopping List
     * Get Shopping List by id: GET /list/v2/{id}
     * Verify that response contains the necessary id
     * Verify that Shopping list is empty (content object is empty)
     ******************************************************/
    @Test
    public void testA_CreateEmptyShoppingList() {
        Faker faker = new Faker();
        String shoppingListName = "Automation Shopping List "+  faker.random().nextInt(1);
        isPreviousTestPass = false;

        shoppingListID = given().
                spec(requestSpec).
                and().
                body("{\"name\": \""+shoppingListName+"\",\"primary\": false}").
                when().
                post(CREATE_SHOPPING_LIST).getBody().jsonPath().get("list.id");

        Response response = given().
                spec(requestSpec).
                when().
                get(GET_SHOPPING_LIST + shoppingListID)
                ;
        if (response.getStatusCode() == SC_OK)
            isPreviousTestPass = true;
        response.
                then().
                spec(responseSpec).
                /*******************************************************
                 * Using a Hamcrest anEmptyMap Method to verify that
                 * Shopping list content object is empty
                 ******************************************************/
                        body("content", Matchers.anEmptyMap());

    }

    /*******************************************************
     * Delete Shopping list by id
     * Get Shopping List by id
     * Verify that code response = 200
     * Verify that the response message is 'shoppingList.notFound'
     ******************************************************/
    @Test
    public void testB_DeleteExistingEmptyShoppingList() {
        Assume.assumeTrue(isPreviousTestPass);
        isPreviousTestPass = false;
        given().
                spec(requestSpec).
                when().
                delete(GET_SHOPPING_LIST + shoppingListID).
                then().
                spec(responseSpec);

        String responseMessage = given().
                spec(requestSpec).
                when().
                get(GET_SHOPPING_LIST + shoppingListID).
                then().
                assertThat().
                statusCode(SC_BAD_REQUEST).
                extract().
                path("code");
        Assert.assertTrue("Invalid Response: ", responseMessage.equals("shoppingList.notFound"));
    }
}
