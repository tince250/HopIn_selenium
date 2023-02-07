package hopine2e.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import hopine2e.pages.AdminHome;
import hopine2e.pages.DriverHome;
import hopine2e.pages.LoginPage;
import hopine2e.pages.PassengerHome;

public class TestLogin extends TestBaseDouble {
	
	private static final String EMAIL_INVALID_FORMAT = "mika";
	private static final String EMAIL_INVALID= "invalid@gmail.com";
	private static final String PASSWORD_INVALID= "invalid";

	public static final String EMAIL_PASSENGER = "mika@gmail.com";
	public static final String EMAIL_DRIVER = "driver@gmail.com";
	private static final String EMAIL_ADMIN = "admin@gmail.com";
	private static final String PASSWORD = "123";
	
	@Test
	public void testLoginPasseneger() {
		LoginPage lp = new LoginPage(driver_passenger);
		assertTrue(lp.isLoginPageLoad());
		lp.inputEmail(EMAIL_PASSENGER);
		lp.inputPassword(PASSWORD);
		assertTrue(lp.isLoginEnabled());
		lp.pressLogin();
		
		PassengerHome ph = new PassengerHome(driver_passenger);
		ph.isPassengerHomeLoad();
		assertTrue(driver_passenger.getCurrentUrl().equals("http://localhost:4200/order-ride"));
	}
	
	@Test
	public void testLoginAdmin() {
		LoginPage lp = new LoginPage(driver_passenger);
		assertTrue(lp.isLoginPageLoad());
		lp.inputEmail(EMAIL_ADMIN);
		lp.inputPassword(PASSWORD);
		assertTrue(lp.isLoginEnabled());
		lp.pressLogin();
		
		AdminHome ah = new AdminHome(driver_passenger);
		ah.isAdminHomeLoad();
		assertTrue(driver_passenger.getCurrentUrl().equals("http://localhost:4200/admin-home"));
	}

	@Test
	public void testLoginDriver() {
		LoginPage lp = new LoginPage(driver_driver);
		assertTrue(lp.isLoginPageLoad());
		lp.inputEmail(EMAIL_DRIVER);
		lp.inputPassword(PASSWORD);
		assertTrue(lp.isLoginEnabled());
		lp.pressLogin();
		
		DriverHome dh = new DriverHome(driver_driver);
		assertTrue(dh.isDriverHomeLoad());
		assertTrue(driver_driver.getCurrentUrl().equals("http://localhost:4200/home-driver"));
	}
	
	@Test
	public void shouldOpenErrorForEmptyEmailField() {
		LoginPage lp = new LoginPage(driver_passenger);
		assertTrue(lp.isLoginPageLoad());
		assertFalse(lp.existsElement("//mat-error[text() = 'entering email is required']"));
		lp.inputEmail("");
		lp.inputPassword("1");
		assertTrue(lp.isEmptyEmailErrorVisible());
	}
	
	@Test
	public void shouldOpenErrorForInvalidEmailFormat() {
		LoginPage lp = new LoginPage(driver_passenger);
		assertTrue(lp.isLoginPageLoad());
		assertFalse(lp.existsElement("//mat-error[text() = 'invalid email format']"));
		lp.inputEmail(EMAIL_INVALID_FORMAT);
		lp.inputPassword("");
		assertTrue(lp.isInvalidEmailErrorVisible());
	}
	
	@Test
	public void shouldOpenErrorForEmptyPassword() {
		LoginPage lp = new LoginPage(driver_passenger);
		assertTrue(lp.isLoginPageLoad());
		assertFalse(lp.existsElement("//mat-error[text() = 'entering password is required']"));
		lp.inputPassword("");
		lp.inputEmail("");
		assertTrue(lp.isEmptyPasswordErrorVisible());
	}
	
	@Test
	public void shouldOpenSnackBarForBadCredentials() {
		LoginPage lp = new LoginPage(driver_passenger);
		assertTrue(lp.isLoginPageLoad());
		assertTrue(lp.isSnackBarOpen());
		lp.inputEmail(EMAIL_INVALID);
		lp.inputPassword(PASSWORD_INVALID);
		assertTrue(lp.isLoginEnabled());
		lp.pressLogin();
		
		assertFalse(lp.isSnackBarOpen());
	}
	

}
