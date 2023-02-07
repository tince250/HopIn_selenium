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
	private static final String departure = "Belgrade, Serbia";
	private static final String destination = "Novi Sad, Serbia";
	
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
		assertTrue(currentRidePageDriver.isRideSuccessfullyFinishedForDriver());
		
		assertTrue(currentRidePagePassenger.isRideSuccessfullyFinishedForPassenger());
		currentRidePagePassenger.closeReviewDialog();
		
		assertTrue(currentRidePageDriver.isHomePageOpened());
		currentRidePageDriver.clickHistory();

		assertTrue(currentRidePagePassenger.isHomePageOpened());
		currentRidePagePassenger.clickHistory();
		
		assertTrue(currentRidePageDriver.isHistoryPageOpened());
		assertTrue(currentRidePagePassenger.isHistoryPageOpened());
		
		assertTrue(currentRidePageDriver.checkIfRideIsInHistory(departure, destination));
		assertTrue(currentRidePagePassenger.checkIfRideIsInHistory(departure, destination));
	}
	
	
	
}
