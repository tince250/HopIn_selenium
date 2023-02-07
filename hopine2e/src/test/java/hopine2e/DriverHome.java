package hopine2e;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverHome {
	
	@FindBy( css = "mat-slide-toggle > label > div")
	WebElement toggleBtn;
	
	private WebDriver driver;
	
	public DriverHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isDriverHomeLoad() {
		boolean isOpen = (new WebDriverWait(driver, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.tagName("mat-slide-toggle"))) != null;
		return isOpen;
	}

}
