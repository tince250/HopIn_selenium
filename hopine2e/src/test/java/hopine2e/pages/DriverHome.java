package hopine2e.pages;

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
	
	@FindBy(css = "#invite-dialog>h1")
	WebElement orderRequestTitle;
	
	@FindBy(id = "accept-btn")
	WebElement acceptBtn;
	
	@FindBy(id = "decline-btn")
	WebElement declineBtn;
	
	@FindBy(css = "#rejection-dialog>h1")
	WebElement rejectionTitle;
	
	@FindBy(name = "reason")
	WebElement rejectionReason;
	
	@FindBy(css = "#rejection-dialog button")
	WebElement rejectBtn;
	
	@FindBy(id = "scheduled-time")
	WebElement scheduledTime;
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public DriverHome(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
		
		PageFactory.initElements(driver, this);
	}
	
	public boolean isDriverHomeLoad() {
		boolean isOpen = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("mat-slide-toggle"))) != null;
		return isOpen;
	}
	
	public boolean isOrderRequestDialogOpened() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("invite-dialog"))) != null;
	}
	
	public boolean isOrderRequestDialogLoaded() {
		return wait.until(ExpectedConditions.textToBePresentInElement(orderRequestTitle, "Wanna go for a ride?"));
	}
	
	public void clickOnAcceptOrderRequest() {
		this.acceptBtn.click();
	}
	
	public void clickOnDeclineOrderRequest() {
		this.declineBtn.click();
	}
	
	public boolean isRejectionDialogLoaded() {
		return wait.until(ExpectedConditions.textToBePresentInElement(rejectionTitle, "Ride rejection reason"));
	}
	
	public void setRejectionReason(String reason) {
		rejectionReason.clear();
		rejectionReason.sendKeys(reason);
	}
	
	public void clickOnReject() {
		this.rejectBtn.click();
	}
	
	public boolean isCorrectScheduledTime(String expected) {
		System.out.println(scheduledTime.getText());
		return wait.until(ExpectedConditions.textToBePresentInElement(scheduledTime, expected));
	}
}
