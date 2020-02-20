package de.fromatob.webdriver.pageObjects.homepage;

import com.codeborne.selenide.SelenideElement;
import de.fromatob.webdriver.pageObjects.BasePage;

import static com.codeborne.selenide.Selenide.*;
import static de.fromatob.webdriver.configuration.ConfigurationConstant.LOGIN_PAGE;

public class LoginPage extends BasePage {
    private final SelenideElement email = $x("//*[@id=\"field-user-reception-email\"]/div[1]/input");
    private final SelenideElement password = $x("//*[@id=\"field-user-reception-password\"]/div[1]/input");
    private final SelenideElement login = $(".modal-buttons__button--sign");

    public LoginPage openLoginPage() {
        open(LOGIN_PAGE);
        return new LoginPage();
    }

    public LoginPage writeEmailText(String input) {
        email.setValue(input);
        return this;
    }

    public LoginPage writePasswordText(String input) {
        password.setValue(input);
        return this;
    }

    public LoginPage clickOnLogin() {
        login.click();
        return this;
    }
}
