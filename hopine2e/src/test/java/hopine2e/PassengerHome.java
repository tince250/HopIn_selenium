package hopine2e;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengerHome {
	
	@FindBy(id = "tell-us")
	WebElement title;
	
	private WebDriver driver;
	
	public PassengerHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPassengerHomeLoad() {
		return (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.textToBePresentInElement(title, "Tell us about the ride:"));
	}

}
