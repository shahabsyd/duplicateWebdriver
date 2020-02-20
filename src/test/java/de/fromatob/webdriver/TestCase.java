package de.fromatob.webdriver;

import com.codeborne.selenide.Configuration;
import de.fromatob.webdriver.configuration.ConfigurationEnvironment;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;


@Getter
public class TestCase extends ConfigurationEnvironment {
    protected static final Logger LOGGER = Logger.getLogger(TestCase.class);

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        if (!useLocalDriver) {
            Configuration.remote = "http://localhost:4444/wd/hub";
        }
        Configuration.browser = browser;
        Configuration.baseUrl = homePage;
        Configuration.startMaximized = true;
    }

    @AfterTest
    public void teardown() {

    }
}
