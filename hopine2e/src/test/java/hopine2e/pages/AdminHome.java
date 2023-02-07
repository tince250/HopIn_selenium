package hopine2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminHome {
	
	@FindBy(css = "#requests > h4")
	WebElement requestBtnText;
	
	private WebDriver driver;
	
	public AdminHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isAdminHomeLoad() {
		return (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.textToBePresentInElement(requestBtnText, "Driver account update requests"));
	}

}
