package demoTest1;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;

import org.testng.annotations.Test;

public class EmptyCartDialog extends Driver{
	
	@Test (groups = "mainRun")
	public void emptyCart() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	
	//Verify cart is empty
		driver.findElement(By.xpath("//div[@class=\"minicart-wrapper\"]")).click();
		
		String emptyCartMsg = driver.findElement(By.xpath("//div[@id=\"minicart-content-wrapper\"]//div[@class=\"block-content\"]//strong")).getText();
		assertEquals(emptyCartMsg,"You have no items in your shopping cart.");
		takePicture(driver, "verify-empty-cart");
		
		driver.findElement(By.id("btn-minicart-close")).click();
		//driver.quit();
	}
	
	
}
