package demoTest1;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;

public class CreateAccount extends Driver{
	WebDriver driver = Driver.driver;
	
	@Test
	public void accessWebsite() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://magento.softwaretestingboard.com/");
		driver.quit();
	}
}
