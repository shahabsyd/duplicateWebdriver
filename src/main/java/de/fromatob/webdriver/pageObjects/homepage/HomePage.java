package de.fromatob.webdriver.pageObjects.homepage;

import com.codeborne.selenide.*;
import de.fromatob.webdriver.pageObjects.BasePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static de.fromatob.webdriver.configuration.ConfigurationEnvironment.getHomePage;

public class HomePage extends BasePage {
    private final SelenideElement departure = $(".input-stop__input--departure");
    private final SelenideElement arrival = $(".input-stop__input--arrival");
    private final SelenideElement startDate = $(".ssc-dates__start");
    private final SelenideElement endDate = $(".ssc-dates__end");
    private final ElementsCollection results = $$("li.autocomplete__item");
    private final SelenideElement nextMonth = $(".calendar__next");
    private final SelenideElement searchForTrip = $(".ssc-submit");
    private final SelenideElement compareWithDB = $(".icon__check");
    private final SelenideElement travellers = $(".ssc-travellers__button");

    /**
     * Open the Home page
     *
     * @return
     */
    public static HomePage openHomePage() {
        open(getHomePage());
        return new HomePage();
    }

    /**
     * Write the Departure location
     *
     * @param start String
     * @return
     */
    public HomePage setDeparture(String start) {
        departure.setValue(start);
        results.findBy(Condition.matchesText(start)).click();
        return this;
    }

    /**
     * Write Arrival destination
     * @param end
     * @return
     */
    public HomePage setArrival(String end) {
        arrival.setValue(end);
        results.findBy(Condition.matchesText(end)).click();
        return this;
    }

    /**
     * Date of the trip
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public HomePage startOnDate(int year, int month, int day){
        startDate.click();

        LocalDate date = LocalDate.of(year, month, day);

        searchForDateAndClick(date);
        return this;
    }

    /**
     * Return Date of the trip
     * @param year
     * @param month
     * @param day
     * @return
     */
    public HomePage arriveOnDate(int year, int month, int day){
        endDate.click();

        LocalDate date = LocalDate.of(year, month, day);

        searchForDateAndClick(date);
        return this;
    }


    public HomePage withoutDBResults() {
        if (compareWithDB.isSelected()) {
            compareWithDB.click();
            compareWithDB.shouldNotBe(Condition.checked);
        }
        return this;
    }
    public HomePage searchForTrips() {
        searchForTrip.click();
        return this;
    }

    /**
     * Search for a date inside the calendar
     * We use the same approach for both ways
     * In case the Month is not displayed, we click on "Next"
     * until the required date is being seen
     * @param date
     */
    private void searchForDateAndClick(LocalDate date){
        SelenideElement selectedDate = $(".day__btn-" + formatDate(date));
        do {
            nextMonth.click();
        } while (!selectedDate.isDisplayed());

        Selenide.sleep(500);
        selectedDate.click();
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-M-dd");
        return date.format(df);
    }
}
