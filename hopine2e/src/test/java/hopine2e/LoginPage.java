package hopine2e;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriver driver;
	
	@FindBy(css = ".title")
	WebElement title;
	
	@FindBy(css = "input[name='email']")
	WebElement emailInputField;
	
	@FindBy(css = "input[name='password']")
	WebElement passwordInputField;
	
	@FindBy(css = "#login-container button")
	WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get("http://localhost:4200/login");
		PageFactory.initElements(driver, this);
	}
	
	
	public void inputEmail(String email) {
		this.emailInputField.clear();
		this.emailInputField.sendKeys(email);
	}
	
	public void inputPassword(String password) {
		this.passwordInputField.clear();
		this.passwordInputField.sendKeys(password);
	}
	
	public void proceedToPasswordField() {
		this.emailInputField.click();
	}
	
	public void pressLogin() {
		this.loginBtn.click();
	}
	
	public boolean isLoginEnabled() {
		boolean isEnabled = (new WebDriverWait(driver, 10)).
				until(ExpectedConditions.elementToBeClickable(loginBtn)) != null;
		return isEnabled;
	}
	
	public boolean isEmptyEmailErrorVisible() {
		boolean isEnabled = (new WebDriverWait(driver, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-error[text() = 'entering email is required']"))) != null;
		return isEnabled;
	}
	
	public boolean isInvalidEmailErrorVisible() {
		boolean isEnabled = (new WebDriverWait(driver, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-error[text() = 'invalid email format']"))) != null;
		return isEnabled;
	}
	
	public boolean isEmptyPasswordErrorVisible() {
		boolean isEnabled = (new WebDriverWait(driver, 10)).
				until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-error[text() = 'entering password is required']"))) != null;
		return isEnabled;
	}
	
	public boolean isSnackBarOpen() {
		List<WebElement> snack = new ArrayList<WebElement>();
		snack = driver.findElements(By.xpath("//div[contains(@class, 'cdk-overlay-container')]//following-sibling::*"));
		return snack.size() == 0;
	}
	
	public boolean existsElement(String id) {
	    try {
	        driver.findElement(By.xpath(id));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
	
	public boolean isLoginPageLoad() {
		return (new WebDriverWait(driver, 10))
        .until(ExpectedConditions.textToBePresentInElement(title, "Welcome back"));
	}
	
	
}
