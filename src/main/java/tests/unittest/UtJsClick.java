package tests.unittest;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UtJsClick extends BaseTest{

    @Test
    void jsClick(){
        String signInButtonXpath = "//input[@id='recaptchaSubmit']";
        BaseTest b = new BaseTest();
        getDriver().get("https://www.vitaminshoppe.com/s/myAccount/login.jsp");
        getDriver().manage().window().maximize();
        Assert.assertTrue(jsClick(signInButtonXpath), "jsClick error");
    }



}
