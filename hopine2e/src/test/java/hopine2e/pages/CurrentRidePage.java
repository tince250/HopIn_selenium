package hopine2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrentRidePage {
	
	@FindBy(id = "map")
	WebElement mapDiv;
	
	@FindBy(id = "passenger-email")
	WebElement passengerEmail;
	
	@FindBy(id = "driver-email")
	WebElement driverEmail;

	private WebDriver driver;
	private WebDriverWait wait;
	
	public CurrentRidePage(WebDriver driver) {
		this.driver = driver;		
		this.wait = new WebDriverWait(driver, 10);

		PageFactory.initElements(driver, this);
	}
	
	public boolean isRightPassenger(String email) {
		return wait.until(ExpectedConditions.textToBePresentInElement(passengerEmail, email));
	}
	
	public boolean isRightDriver(String email) {
		return wait.until(ExpectedConditions.textToBePresentInElement(driverEmail, email));
	}
	
	public boolean isPageLoaded() {
		return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("map"))) != null);
	}
}
