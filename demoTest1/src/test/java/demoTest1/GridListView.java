package demoTest1;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class GridListView extends Driver{
	
	@Test (groups = "mainRun")
	public void changeView () {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	//Hover Women menu	
		Actions hoverWomen = new Actions(driver);
		WebElement women = driver.findElement(By.xpath("//a[@id=\"ui-id-4\"]/span[contains(text(),'Women')]"));
		hoverWomen.moveToElement(women).perform();

		WebDriverWait waitWomenItems = new WebDriverWait(driver, Duration.ofSeconds(1));
		waitWomenItems.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id=\"ui-id-9\"]//span[contains(text(),'Tops')]"))).click();
		
		WebDriverWait waitProducts = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitProducts.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"products wrapper grid products-grid\"]")));
		
		assertTrue(driver.findElement(By.id("modes-label")).isDisplayed(),"View options available");
		
		driver.quit();
	
	//Verify product view is in grid
	//Click list view
	//Verify product view is updated to list
	}
}
