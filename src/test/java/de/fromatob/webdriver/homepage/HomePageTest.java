package de.fromatob.webdriver.homepage;

import de.fromatob.webdriver.TestCase;
import de.fromatob.webdriver.pageObjects.homepage.HomePage;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class HomePageTest extends TestCase {
    protected static final Logger LOGGER = Logger.getLogger(HomePageTest.class);

    @BeforeTest
    public void makeTheTrip() {

    }
    @Test
    public void firstTest() {

        HomePage hp = new HomePage();
        hp.openHomePage()
                .setDeparture("Berlin")
                .setArrival("Frankfurt")
                .startOnDate(2020, 4, 1)
                .arriveOnDate(2020, 6, 8)
                .withoutDBResults()
                .searchForTrips();

        LOGGER.info("Finish");

    }
}
