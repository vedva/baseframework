package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    @Test
    void elementsCheck(){
        LoginPage loginPage = new LoginPage(this);
        Assert.assertTrue(loginPage.navigateLoginPage(), "Can't navigate to login page");
    }
}
