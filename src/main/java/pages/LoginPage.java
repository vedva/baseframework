package pages;

import base.BasePageObject;
import base.BaseTest;

public class LoginPage extends BasePageObject {
    public static String pageURL = "myAccount/login.jsp";

    public LoginPage(BaseTest myBaseTest) {
        super(myBaseTest);
    }

    public boolean navigateLoginPage(){
        return t.getToUrl(t.getPageURL("hp") + pageURL);
    }
}
