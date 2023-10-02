package demoTest1;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;

public class EmptyCartDialog extends Driver{

	WebDriver driver = Driver.driver;
	
	@Test (groups = "mainRun")
	public void emptyCart() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	
	//Verify cart is empty
		driver.findElement(By.xpath("//div[@class=\"minicart-wrapper\"]")).click();
		
		String emptyCartMsg = driver.findElement(By.xpath("//div[@id=\"minicart-content-wrapper\"]//div[@class=\"block-content\"]//strong")).getText();
		assertEquals(emptyCartMsg,"You have no items in your shopping cart.");
		
		//driver.quit();
	}
	
	
}
