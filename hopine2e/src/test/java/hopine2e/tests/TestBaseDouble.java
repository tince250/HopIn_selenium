package hopine2e.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBaseDouble {
	public static WebDriver driver_driver;
	public static WebDriver driver_passenger;
	
	@BeforeSuite
	public void initializeWebDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		driver_driver = new ChromeDriver(options);
		driver_passenger = new ChromeDriver(options);
		
		driver_driver.manage().window().maximize();
		driver_driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver_passenger.manage().window().maximize();
		driver_passenger.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void tearDown() {
		driver_driver.quit();
		driver_passenger.quit();
	}
}
