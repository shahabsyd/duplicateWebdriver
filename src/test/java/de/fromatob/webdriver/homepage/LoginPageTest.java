package de.fromatob.webdriver.homepage;

import de.fromatob.webdriver.TestCase;
import de.fromatob.webdriver.pageObjects.homepage.LoginPage;
import org.testng.annotations.Test;

public class LoginPageTest extends TestCase {

    @Test
    public void testLoginPage() {
        LoginPage lp = new LoginPage();
        
        lp.openLoginPage()
        .writeEmailText("cosmin.paun@fromatob.com")
        .writePasswordText("pass")
        .clickOnLogin();

    }
}
