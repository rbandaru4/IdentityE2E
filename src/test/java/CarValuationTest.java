package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

import java.io.*;
import java.util.*;

public class CarValuationTest {

    private WebDriver driver;
    private HomePage homePage;
	private static final String inputFilePath = "src/resources/car_input.txt";
    private static final String outputFilePath = "src/resources/car_output.txt";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update with actual path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    public void testCarValuations() throws IOException {

        List<String> registrationNumbers = FileReaderUtil.extractRegistrationNumbers(inputFilePath);
        Map<String, String> expectedValues = FileReaderUtil.readExpectedOutput(outputFilePath);

        for (String reg : registrationNumbers) {
            driver.get("https://motorway.co.uk/");
            homePage.enterRegistration(reg);
			homePage.submitValue();
            homePage.enterMileage(String.valueOf(new Random().nextInt(100000) + 5000)); // Random mileage
			homePage.submitValue();
            String actualValuation = homePage.getValuation();

            String expectedValuation = expectedValues.get(reg);
            assert actualValuation.equals(expectedValuation) : 
                String.format("Mismatch for %s: Expected %s, Got %s", reg, expectedValuation, actualValuation);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
