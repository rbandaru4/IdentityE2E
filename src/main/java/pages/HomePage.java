package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "vrm-input")
    private WebElement vehicleRegistrationInput;

    @FindBy(id = "mileage-input")
    private WebElement mileageInput;

    @FindBy(class = "Button-module__label-SKEy")
    private WebElement confirmValue;
	
	@FindBy(class = "MotorwayValuationPage__offer-xgNB")
    private WebElement valuationResult;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterRegistration(String registration) {
        vehicleRegistrationInput.clear();
        vehicleRegistrationInput.sendKeys(registration);
        vehicleRegistrationInput.sendKeys(Keys.RETURN);
    }

    public void enterMileage(String mileage) {
        mileageInput.clear();
        mileageInput.sendKeys(mileage);
        mileageInput.sendKeys(Keys.RETURN);
    }
	
	public void submitValue() {
		confirmValue.click();
	}

    public String getValuation() {
        return valuationResult.getText();
    }
}