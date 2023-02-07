package hopine2e.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hopine2e.pages.DriverHome;
import hopine2e.pages.PassengerHome;

public class TestOrderRide extends TestBaseDouble{
	
	private TestLogin testLogin;
	
	@BeforeClass
	public void setup() {
		testLogin = new TestLogin();
	}
	
	@Test
	public void testOrderRide_HappyPath() {
		this.testLogin.testLoginPasseneger();
		this.testLogin.testLoginDriver();
		
		PassengerHome passengerHome = new PassengerHome(driver_passenger);
		passengerHome.enterPickupLocation("Beograd");
		passengerHome.chooseFirstAutocomplete();
		passengerHome.enterDestinationLocation("Novi Sad");
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

	}
	
	@Test
	public void testOrderRide_DriverDeclined() {
		this.testLogin.testLoginPasseneger();
		this.testLogin.testLoginDriver();
		
		PassengerHome passengerHome = new PassengerHome(driver_passenger);
		passengerHome.enterPickupLocation("Beograd");
		passengerHome.chooseFirstAutocomplete();
		passengerHome.enterDestinationLocation("Novi Sad");
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
		driverHome.setRejectionReason("Tired, need break.");
		driverHome.clickOnReject();
		
		assertTrue(passengerHome.isNoRideDialogOpened());

	}
	
	@Test
	public void testOrderRide_NoActiveDriver() {
		this.testLogin.testLoginPasseneger();
		
		PassengerHome home = new PassengerHome(driver_passenger);
		home.enterPickupLocation("Beograd");
		driver_passenger.findElements(By.cssSelector(".pac-item")).get(0).click();
		home.enterDestinationLocation("Novi Sad");
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
		home.enterPickupLocation("Beograd");
		driver_passenger.findElements(By.cssSelector(".pac-item")).get(0).click();
		home.enterDestinationLocation("Novi Sad");
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
