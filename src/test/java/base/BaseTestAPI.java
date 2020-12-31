package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import utils.ApplicationConfiguration;

public class BaseTestAPI {

    private static final String BASE_ENDPOINT = ApplicationConfiguration.getHostBaseUrl();
    private static String AUTHORIZATION = "Authorization";
    private static String BEARER = "Bearer ";

    /*******************************************************
     * Create a static RequestSpecification for Whisk Application :
     * - Set the Request Base URL
     * - Set the Request contentType is JSON
     * - Set the Request AUTHORIZATION in the header
     ******************************************************/

    public static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_ENDPOINT)
                .setContentType(ContentType.JSON)
                .addHeader(AUTHORIZATION, BEARER + ApplicationConfiguration.getToken())
                .build();
    }

    /*******************************************************
     * Create a static ResponseSpecification that checks whether:
     * - the response has statusCode 200
     * - the response contentType is JSON
     ******************************************************/

    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                expectContentType(ContentType.JSON).
                build();
    }
}
