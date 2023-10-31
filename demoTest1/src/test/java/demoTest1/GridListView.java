package demoTest1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class GridListView extends Driver{
	
	@Test (groups = "mainRun")
	public void changeView () throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	//Hover Women menu	
		Actions hoverWomen = new Actions(driver);
		WebElement women = driver.findElement(By.xpath("//a[@id=\"ui-id-4\"]/span[contains(text(),'Women')]"));
		hoverWomen.moveToElement(women).perform();

		WebDriverWait waitWomenItems = new WebDriverWait(driver, Duration.ofSeconds(1));
		waitWomenItems.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id=\"ui-id-9\"]//span[contains(text(),'Tops')]"))).click();
	
	//Verify product view is in grid
		WebDriverWait waitGridProducts = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitGridProducts.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"products wrapper grid products-grid\"]")));
		int itemsInProductList = driver.findElements(By.xpath("//div[@class=\"products wrapper list products-list\"]")).size();
		assertEquals(0,itemsInProductList,"Items not listed in products view");
		
		assertTrue(driver.findElement(By.id("modes-label")).isDisplayed(),"View options available");
		takePicture(driver, "verify-grid-view");
	
	
	//Click list view
		driver.findElement(By.id("mode-list")).click();
		
	//Verify product view is updated to list
		WebDriverWait waitListProducts = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitListProducts.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"products wrapper list products-list\"]")));
		int itemsInGridList = driver.findElements(By.xpath("//div[@class=\"products wrapper grid products-grid\"]")).size();
		assertEquals(0,itemsInGridList,"Items not listed in grid view");
		
		assertTrue(driver.findElement(By.id("modes-label")).isDisplayed(),"View options available");
		takePicture(driver, "verify-list-view");
	}
}
