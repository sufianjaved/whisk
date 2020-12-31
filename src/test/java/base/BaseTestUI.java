package base;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import pages.whisk.common.LoginWhisk;
import utils.common.ScreenShotOnFailure;
import utils.selenium.SeleniumFactory;

public abstract class BaseTestUI extends SeleniumFactory {

    protected String packageName = this.getClass().getPackage().getName();
    protected abstract void initializePageElements();

    LoginWhisk login;

    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(packageName);

    @Before
    public void setUp() throws Exception
    {
        if(driver!=null)
            driver.close();

        initializeLocalBrowser();
        login = new LoginWhisk();

        initializePageElements();
        login.loginWhisk();
    }

    @AfterClass
    public static void cleanUp()
    {
        if(driver!=null)
            driver.quit();
        driver = null;
    }
}
