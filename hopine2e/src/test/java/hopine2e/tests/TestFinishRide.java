package hopine2e.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import hopine2e.pages.CurrentRidePage;
import hopine2e.pages.DriverHome;
import hopine2e.pages.PassengerHome;

public class TestFinishRide extends TestBaseDouble{

	private TestOrderRide testOrderRide;
	
	@BeforeClass
	public void setup() {
		testOrderRide = new TestOrderRide();
	}
	
	@Test
	public void testFinishRide_HappyPath() {
		this.testOrderRide.testOrderRide_HappyPath();
		
		CurrentRidePage currentRidePagePassenger = new CurrentRidePage(driver_passenger);
	
		CurrentRidePage currentRidePageDriver = new CurrentRidePage(driver_driver);
		
		currentRidePageDriver.clickStart();
		assertTrue(currentRidePagePassenger.isRideStarted());
		assertTrue(currentRidePageDriver.isRideStarted());
		
		currentRidePageDriver.clickFinish();
		assertTrue(currentRidePageDriver.provera());
		assertTrue(currentRidePagePassenger.isRideSuccessfullyFinishedForPassenger());
	}
	
	
	
}
