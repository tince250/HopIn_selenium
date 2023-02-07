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
	
	@FindBy(css = "#loading-dialog>h1")
	WebElement loadingDialogTitle;
	
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
		firstInAutocomplete = driver.findElement(By.cssSelector("span.pac-item-query>span.pac-matched"));
		wait.until(ExpectedConditions.elementToBeClickable(firstInAutocomplete)).click();
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
		return wait.until(ExpectedConditions.textToBePresentInElement(loadingDialogTitle, "Hooraay!"));
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
