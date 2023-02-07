package hopine2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengerHome {
	
	@FindBy(id = "tell-us")
	WebElement title;
	
	@FindBy(name = "pickup")
	WebElement pickupLocation;
	
	@FindBy(name = "destination")
	WebElement destinationLocation;
	
	@FindBy(name = "time")
	WebElement timeInput;
	
	@FindBy(css = "div.clock-face")
	WebElement timePickerClockFace;
	
	@FindBy(xpath = "//button[@class='timepicker-button']//span[text()='Ok']")
	WebElement timePickerOK;
	
	@FindBy(id = "next-step-btn")
	WebElement nextBtn;
	
	@FindBy(id = "order-ride-btn")
	WebElement orderBtn;
	
	@FindBy(id = "mat-dialog-title-1")
	WebElement dialogTitle;
	
	@FindBy(id = "preferences-title")
	WebElement prefTitle;
	
	@FindBy(id = "lux-car-type")
	WebElement luxCar;
	
	@FindBy(css = ".fas.fa-baby")
	WebElement babyPreference;
	
	@FindBy(css = ".fas.fa-dog")
	WebElement petPreference;
	
	@FindBy(css = ".mat-spinner")
	WebElement spinnerLoadingDialog;
	
	@FindBy(id = "laoding-dialog")
	WebElement loadingDialog;
	
	@FindBy(css = "#loading-dialog h1")
	WebElement loadingDialogTitle;
	
	@FindBy(css = "#loading-dialog h3.subtitle")
	WebElement loadingDialogSubtitle;
	
	WebElement firstInAutocomplete;
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public PassengerHome(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
		
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPassengerHomeLoad() {
		return (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.textToBePresentInElement(title, "Tell us about the ride:"));
	}
	
	public void enterPickupLocation(String location) {
		this.pickupLocation.clear();
		this.pickupLocation.sendKeys(location);
	}
	
	public void enterDestinationLocation(String location) {
		this.destinationLocation.clear();
		this.destinationLocation.sendKeys(location);
	}
	
	public void chooseFirstAutocomplete() {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.pac-item-query>span.pac-matched"))).click();
	}
	
	public void openTimePicker() {
		wait.until(ExpectedConditions.elementToBeClickable(timeInput)).click();
	}
	
	public boolean isTimePickerOpened() {
		return (wait.until(ExpectedConditions.visibilityOf(timePickerClockFace)) != null); 
	}
	
	public void chooseScheduledTime(int hoursToChoose) {
		driver.findElement(By.xpath("//div[@class='clock-face']//span[text()=' " + hoursToChoose + "']")).click();;
	}
	
	public void clickOnTimePickerOk() {
		this.timePickerOK.click();
	}
	
	public void clickOnNext() {
		wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
	}

	public void clickOnOrder() {
		this.orderBtn.click();
	}
	
	public void clickBabyPreference() {
		this.babyPreference.click();
	}
	
	public void clickPetPreference() {
		this.petPreference.click();
	}
	
	public boolean isVehiclePreferencesFormLoaded() {
		return wait.until(ExpectedConditions.textToBePresentInElement(prefTitle, "What’s your vehicle type?"));
	}
	
	public boolean isNoRideDialogOpened() {
		return wait.until(ExpectedConditions.textToBePresentInElement(dialogTitle, "No rides found"));
	}
	
	public boolean isLoadingDialogOpened() {
		return (wait.until(ExpectedConditions.visibilityOf(loadingDialogTitle)) != null);
		
	}
	
	public boolean isRideConfirmed() {
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#loading-dialog h1"), "Hooraay!"));
	}
	
	public boolean isRideForNow() {
		return wait.until(ExpectedConditions.textToBePresentInElement(loadingDialogSubtitle, "You'll be redirected to current ride shortly."));
	}
	
	public boolean isRideScheduled(String scheduledTime) {
		return wait.until(ExpectedConditions.textToBePresentInElement(loadingDialogSubtitle, scheduledTime));
	}
	
	public void clickOnLoadingDialog() {
//		wait.until(ExpectedConditions.elementToBeClickable(loadingDialog)).click();
		Actions actions = new Actions(driver);
		actions.moveByOffset(5, 5).click().build().perform();
	}
	
	public void chooseLuxuryVehicleType() {
		this.luxCar.click();
	}
}
