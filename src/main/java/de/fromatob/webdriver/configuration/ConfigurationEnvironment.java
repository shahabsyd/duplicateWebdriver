package de.fromatob.webdriver.configuration;


import com.codeborne.selenide.Configuration;
import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import static de.fromatob.webdriver.configuration.ConfigurationConstant.*;

@Getter
public class ConfigurationEnvironment {
    protected static final Logger LOGGER = Logger.getLogger(ConfigurationEnvironment.class);

    protected Properties properties;
    protected boolean useLocalDriver;


    public static String homePage;
    protected String browser;

    public ConfigurationEnvironment() { }

    @BeforeMethod(alwaysRun = true)
    public synchronized void setInitialConfiguration() {
        LOGGER.info("Set initial Configuration");

        loadProperties();
        addEnvironmentProperties();
    }

    protected void loadProperties() {
        try {
            File props;
            properties = new Properties();
            URL file = this.getClass().getClassLoader().getResource(TESTING_PROPERTIES);

            if (file != null) {
                props = new File(file.getFile());
                properties.load(new FileInputStream(props));
            }
        } catch (Exception e) {
            LOGGER.error("Exception to load File testing.properties:", e);
            throw new RuntimeException(e);
        }
        System.setProperty(USE_LOCAL_DRIVER, properties.getProperty(USE_LOCAL_DRIVER));
        System.setProperty(HOMEPAGE, properties.getProperty(HOMEPAGE));
        System.setProperty(BROWSER, properties.getProperty(BROWSER));
    }

    protected void addEnvironmentProperties() {
        useLocalDriver = System.getProperty(USE_LOCAL_DRIVER) != null ? Boolean.valueOf(System.getProperty(USE_LOCAL_DRIVER)) : Boolean.valueOf(properties.getProperty(USE_LOCAL_DRIVER));
        homePage = System.getProperty(HOMEPAGE) != null ? System.getProperty(HOMEPAGE) : properties.getProperty(HOMEPAGE);
        browser = System.getProperty(BROWSER) != null ? System.getProperty(BROWSER) : properties.getProperty(BROWSER);
    }

    public static String getHomePage() {
        return homePage;
    }
}
