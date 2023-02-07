package hopine2e;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class TestLogin extends TestBase {
	
	private static final String EMAIL_INVALID_FORMAT = "mika";
	private static final String EMAIL_INVALID= "invalid@gmail.com";
	private static final String PASSWORD_INVALID= "invalid";

	private static final String EMAIL_PASSENGER = "mika@gmail.com";
	private static final String EMAIL_DRIVER = "driver@gmail.com";
	private static final String EMAIL_ADMIN = "admin@gmail.com";
	private static final String PASSWORD = "123";
	
	@Test
	public void testLoginPasseneger() {
		LoginPage lp = new LoginPage(driver);
		assertTrue(lp.isLoginPageLoad());
		lp.inputEmail(EMAIL_PASSENGER);
		lp.inputPassword(PASSWORD);
		assertTrue(lp.isLoginEnabled());
		lp.pressLogin();
		
		PassengerHome ph = new PassengerHome(driver);
		ph.isPassengerHomeLoad();
		assertTrue(driver.getCurrentUrl().equals("http://localhost:4200/order-ride"));
	}
	
	@Test
	public void testLoginAdmin() {
		LoginPage lp = new LoginPage(driver);
		assertTrue(lp.isLoginPageLoad());
		lp.inputEmail(EMAIL_ADMIN);
		lp.inputPassword(PASSWORD);
		assertTrue(lp.isLoginEnabled());
		lp.pressLogin();
		
		AdminHome ah = new AdminHome(driver);
		ah.isAdminHomeLoad();
		assertTrue(driver.getCurrentUrl().equals("http://localhost:4200/admin-home"));
	}

	@Test
	public void testLoginDriver() {
		LoginPage lp = new LoginPage(driver);
		assertTrue(lp.isLoginPageLoad());
		lp.inputEmail(EMAIL_DRIVER);
		lp.inputPassword(PASSWORD);
		assertTrue(lp.isLoginEnabled());
		lp.pressLogin();
		
		DriverHome dh = new DriverHome(driver);
		assertTrue(dh.isDriverHomeLoad());
		assertTrue(driver.getCurrentUrl().equals("http://localhost:4200/home-driver"));
	}
	
	@Test
	public void shouldOpenErrorForEmptyEmailField() {
		LoginPage lp = new LoginPage(driver);
		assertTrue(lp.isLoginPageLoad());
		assertFalse(lp.existsElement("//mat-error[text() = 'entering email is required']"));
		lp.inputEmail("");
		lp.inputPassword("1");
		assertTrue(lp.isEmptyEmailErrorVisible());
	}
	
	@Test
	public void shouldOpenErrorForInvalidEmailFormat() {
		LoginPage lp = new LoginPage(driver);
		assertTrue(lp.isLoginPageLoad());
		assertFalse(lp.existsElement("//mat-error[text() = 'invalid email format']"));
		lp.inputEmail(EMAIL_INVALID_FORMAT);
		lp.inputPassword("");
		assertTrue(lp.isInvalidEmailErrorVisible());
	}
	
	@Test
	public void shouldOpenErrorForEmptyPassword() {
		LoginPage lp = new LoginPage(driver);
		assertTrue(lp.isLoginPageLoad());
		assertFalse(lp.existsElement("//mat-error[text() = 'entering password is required']"));
		lp.inputPassword("");
		lp.inputEmail("");
		assertTrue(lp.isEmptyPasswordErrorVisible());
	}
	
	@Test
	public void shouldOpenSnackBarForBadCredentials() {
		LoginPage lp = new LoginPage(driver);
		assertTrue(lp.isLoginPageLoad());
		assertTrue(lp.isSnackBarOpen());
		lp.inputEmail(EMAIL_INVALID);
		lp.inputPassword(PASSWORD_INVALID);
		assertTrue(lp.isLoginEnabled());
		lp.pressLogin();
		
		assertFalse(lp.isSnackBarOpen());
	}
	

}
