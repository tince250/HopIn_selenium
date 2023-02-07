package hopine2e.pages;

import java.util.ArrayList;
import java.util.List;

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
	
	@FindBy(id="startBtn")
	WebElement buttonStart;
	
	@FindBy(id = "endBtn")
	WebElement buttonFinish;
	
	@FindBy(id = "ride-review-container")
	WebElement reviewContainer;
	
	@FindBy(css = "a[href='/ride-history']")
	WebElement historyTag;
	
	@FindBy(css = "a[href='#']")
	WebElement logo;
	
	@FindBy(css = "button#closeBtn")
	WebElement buttonCloseDialog;
	
	@FindBy(xpath = "//div[contains(@class, 'current-ride-timer') and contains(.//h1, 'Current ride')]")
	WebElement currentRideHeader;
	
	@FindBy(xpath = "//div[@class='row']//h2[contains(text(), 'Ride history')]")
	WebElement historyTitle;
	
	@FindBy(xpath = "(//div[@id='historyCard'])[1]//h4")
	WebElement titleOfRide;

	private WebDriver driver;
	private WebDriverWait wait;
	
	public CurrentRidePage(WebDriver driver) {
		this.driver = driver;		
		this.wait = new WebDriverWait(driver, 20);

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
	
	public void clickStart() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonStart)).click();
	}
	
	public boolean isRideStarted() {
		return (wait.until(ExpectedConditions.visibilityOf(currentRideHeader)) != null);
	}
	
	public void clickFinish() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonFinish)).click();
	}
	
	public boolean isRideSuccessfullyFinishedForDriver() {
		List<WebElement> snack = new ArrayList<WebElement>();
		snack = driver.findElements(By.xpath("//div[contains(@class, 'cdk-overlay-container')]//following-sibling::*"));
		return snack.size() != 0;
	}
	
	public boolean isRideSuccessfullyFinishedForPassenger() {
		return (wait.until(ExpectedConditions.visibilityOf(reviewContainer)) != null);
	}
	
	public void clickHistory() {
		wait.until(ExpectedConditions.elementToBeClickable(historyTag)).click();
	}
	
	public boolean isHomePageOpened() {
		return (wait.until(ExpectedConditions.elementToBeClickable(logo)) != null);
	}
	
	public void closeReviewDialog() {
		wait.until(ExpectedConditions.elementToBeClickable(buttonCloseDialog)).click();
	}
	
	public boolean isHistoryPageOpened() {
		return (wait.until(ExpectedConditions.elementToBeClickable(historyTitle)) != null);
	}
	public boolean checkIfRideIsInHistory(String departure, String destination) {
		return wait.until(ExpectedConditions.textToBePresentInElement(titleOfRide, departure + " -> " + destination));
	}
}
