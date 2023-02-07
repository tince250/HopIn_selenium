package hopine2e.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hopine2e.pages.CurrentRidePage;
import hopine2e.pages.DriverHome;
import hopine2e.pages.LoginPage;
import hopine2e.pages.PassengerHome;

public class TestOrderRide extends TestBaseDouble{
	
	private TestLogin testLogin;
	private static final String departure = "Beograd";
	private static final String destination = "Novi Sad";
	private static final String rejection_reason = "Tired, need break.";
	
	@BeforeClass
	public void setup() {
		testLogin = new TestLogin();
	}
	
	@Test
	public void testOrderRide_HappyPath() {
		if (testLogin == null)
			testLogin = new TestLogin();
		this.testLogin.testLoginPasseneger();
		this.testLogin.testLoginDriver();
		
		PassengerHome passengerHome = new PassengerHome(driver_passenger);
		passengerHome.enterPickupLocation(departure);
		passengerHome.chooseFirstAutocomplete();
		passengerHome.enterDestinationLocation(destination);
		passengerHome.chooseFirstAutocomplete();
		passengerHome.clickOnNext();
		
		assertTrue(passengerHome.isVehiclePreferencesFormLoaded());
		passengerHome.clickBabyPreference();
		passengerHome.clickPetPreference();
		passengerHome.clickOnOrder();
		
		passengerHome.clickOnLoadingDialog();
		assertTrue(passengerHome.isLoadingDialogOpened());

		DriverHome driverHome = new DriverHome(driver_driver);
		assertTrue(driverHome.isOrderRequestDialogOpened());
		assertTrue(driverHome.isOrderRequestDialogLoaded());
		driverHome.clickOnAcceptOrderRequest();
		
		assertTrue(passengerHome.isRideConfirmed());
		assertTrue(passengerHome.isRideForNow());
		
		CurrentRidePage currentRidePagePassenger = new CurrentRidePage(driver_passenger);
		assertTrue(currentRidePagePassenger.isPageLoaded());
		assertTrue(driver_passenger.getCurrentUrl().equals("http://localhost:4200/current-ride"));
		
		CurrentRidePage currentRidePageDriver = new CurrentRidePage(driver_driver);
		assertTrue(currentRidePageDriver.isPageLoaded());
		assertTrue(driver_driver.getCurrentUrl().equals("http://localhost:4200/current-ride"));
		
		assertTrue(currentRidePagePassenger.isRightDriver(TestLogin.EMAIL_DRIVER));
		assertTrue(currentRidePageDriver.isRightPassenger(TestLogin.EMAIL_PASSENGER));
	}
	
	@Test
	public void testOrderRide_Scheduled_HappyPath() {
		this.testLogin.testLoginPasseneger();
		this.testLogin.testLoginDriver();
		
		PassengerHome passengerHome = new PassengerHome(driver_passenger);
		passengerHome.enterPickupLocation(departure);
		passengerHome.chooseFirstAutocomplete();
		passengerHome.enterDestinationLocation(destination);
		passengerHome.chooseFirstAutocomplete();
		
		LocalDateTime datetime = LocalDateTime.now();
		datetime = datetime.plusHours(1);
		datetime = datetime.minusMinutes(datetime.getMinute());
		int hoursToChoose = datetime.getHour();
		String expected = this.formatScheduledTime(datetime);
		
		System.out.println(expected);
		
		passengerHome.openTimePicker();
		assertTrue(passengerHome.isTimePickerOpened());
		passengerHome.chooseScheduledTime(hoursToChoose);
		passengerHome.clickOnTimePickerOk();
		
		passengerHome.clickOnNext();
		
		assertTrue(passengerHome.isVehiclePreferencesFormLoaded());
		passengerHome.clickBabyPreference();
		passengerHome.clickPetPreference();
		passengerHome.clickOnOrder();
		
		passengerHome.clickOnLoadingDialog();
		assertTrue(passengerHome.isLoadingDialogOpened());

		DriverHome driverHome = new DriverHome(driver_driver);
		assertTrue(driverHome.isOrderRequestDialogOpened());
		assertTrue(driverHome.isOrderRequestDialogLoaded());
		assertTrue(driverHome.isCorrectScheduledTime(expected));
		driverHome.clickOnAcceptOrderRequest();
		
		assertTrue(passengerHome.isRideConfirmed());
		assertTrue(passengerHome.isRideScheduled(expected));
	}
	
	private String formatScheduledTime(LocalDateTime datetime) {
		String minutes = datetime.getMinute() + "";
	    if (minutes.length() == 1) {
	      minutes = "0" + minutes;
	    }
	    return datetime.getHour() + ":" + minutes + ", " + datetime.getDayOfMonth() + "." + (datetime.getMonthValue()) + "." + datetime.getYear();
	}

	@Test
	public void testOrderRide_DriverDeclined() {
		this.testLogin.testLoginPasseneger();
		this.testLogin.testLoginDriver();
		
		PassengerHome passengerHome = new PassengerHome(driver_passenger);
		passengerHome.enterPickupLocation(departure);
		passengerHome.chooseFirstAutocomplete();
		passengerHome.enterDestinationLocation(destination);
		passengerHome.chooseFirstAutocomplete();
		passengerHome.clickOnNext();
		
		assertTrue(passengerHome.isVehiclePreferencesFormLoaded());
		passengerHome.clickBabyPreference();
		passengerHome.clickPetPreference();
		passengerHome.clickOnOrder();
		
		DriverHome driverHome = new DriverHome(driver_driver);
		assertTrue(driverHome.isOrderRequestDialogOpened());
		assertTrue(driverHome.isOrderRequestDialogLoaded());
		driverHome.clickOnDeclineOrderRequest();
		
		passengerHome.clickOnLoadingDialog();
		assertTrue(passengerHome.isLoadingDialogOpened());
		
		assertTrue(driverHome.isRejectionDialogLoaded());
		driverHome.setRejectionReason(rejection_reason);
		driverHome.clickOnReject();
		
		assertTrue(passengerHome.isNoRideDialogOpened());

	}
	
	@Test
	public void testOrderRide_NoActiveDriver() {
		this.testLogin.testLoginPasseneger();
		
		PassengerHome home = new PassengerHome(driver_passenger);
		home.enterPickupLocation(departure);
		driver_passenger.findElements(By.cssSelector(".pac-item")).get(0).click();
		home.enterDestinationLocation(destination);
		home.chooseFirstAutocomplete();
		home.clickOnNext();
		
		assertTrue(home.isVehiclePreferencesFormLoaded());
		home.clickOnOrder();
		
		home.clickOnLoadingDialog();
		assertTrue(home.isLoadingDialogOpened());
		assertTrue(home.isNoRideDialogOpened());
	}
	
	@Test
	public void testOrderRide_NoSuitableDriver() {
		this.testLogin.testLoginPasseneger();
		this.testLogin.testLoginDriver();
		
		PassengerHome home = new PassengerHome(driver_passenger);
		home.enterPickupLocation(departure);
		driver_passenger.findElements(By.cssSelector(".pac-item")).get(0).click();
		home.enterDestinationLocation(destination);
		home.chooseFirstAutocomplete();
		home.clickOnNext();
		
		assertTrue(home.isVehiclePreferencesFormLoaded());
		home.chooseLuxuryVehicleType();
		home.clickOnOrder();
		
		home.clickOnLoadingDialog();
		assertTrue(home.isLoadingDialogOpened());
		assertTrue(home.isNoRideDialogOpened());
	}

}
