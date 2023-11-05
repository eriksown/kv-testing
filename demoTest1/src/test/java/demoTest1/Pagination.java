package demoTest1;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Pagination extends Driver{
	
	@Test (groups = "newTest")
	public void changePagination() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
	//Hover Men > Tops
		Actions hoverMen = new Actions(driver);
		WebElement men = driver.findElement(By.xpath("//span[contains(text(),'Men')]"));
		WebElement tops = driver.findElement(By.xpath("//a[@id=\"ui-id-17\"]/span[contains(text(),'Tops')]"));
		//WebElement jackets = driver.findElement(By.xpath("//a[@id=\"ui-id-19\"]/span[contains(text(),'Jackets')]"));
		//WebElement hoodies = driver.findElement(By.xpath("//a[@id=\"ui-id-20\"]/span[contains(text(),'Hoodies & Sweatshirts')]"));
		hoverMen.moveToElement(men).moveToElement(tops).click().build().perform();
		
	//Total number of items in grid list
		String gridItems = driver.findElement(By.id("toolbar-amount")).getText();
		String itemCount = gridItems.substring(14);
		int itemCountInt = Integer.valueOf(itemCount);
		//System.out.println("Items Count: "+(itemCountInt));
			
	//Verify number in the limiter drop down
		Select itemsPerPageSelector = new Select(driver.findElement(By.xpath("//div[@class=\"toolbar toolbar-products\"][2]//select[@id=\"limiter\"]")));
		WebElement itemsPerPageElement = itemsPerPageSelector.getFirstSelectedOption();
		String itemsPerPage = itemsPerPageElement.getText();
		int itemsPerPageInt = Integer.valueOf(itemsPerPage);
		//System.out.println("Items per Page: "+(itemsPerPageInt));
		assertEquals(12,itemsPerPageInt,"Default number of limiter");
		
	//Verify page count based on number of items
		int numberOfPagesActual = (driver.findElements(By.xpath("//div[@class=\"toolbar toolbar-products\"][2]//ul[@class=\"items pages-items\"]/li")).size())-1;  //-1 is for the 'next' arrow
		int numberOfPagesExpected = itemCountInt/itemsPerPageInt;
		//System.out.println("Expected: "+numberOfPagesExpected);		
		assertEquals(numberOfPagesActual, numberOfPagesExpected, "Number of pages available");
		
	//Verify number of items in grid list after updating limiter
		JavascriptExecutor scrollDown = (JavascriptExecutor)driver;
		scrollDown.executeScript("window.scrollBy(0, 1500)");
		Thread.sleep(2000);		
		itemsPerPageSelector.selectByValue("24");
		Thread.sleep(2000);
		
		String gridItemsUpdated = driver.findElement(By.id("toolbar-amount")).getText();
		String itemCountUpdated = gridItemsUpdated.substring(14);
		int itemCountIntUpdated = Integer.valueOf(itemCountUpdated);
	
		int numberOfPagesActualUpdated = (driver.findElements(By.xpath("//div[@class=\"toolbar toolbar-products\"][2]//ul[@class=\"items pages-items\"]/li")).size())-1;  //-1 is for the 'next' arrow
		int numberOfPagesExpectedUpdated = itemCountIntUpdated/24;
		
		assertEquals(numberOfPagesActualUpdated, numberOfPagesExpectedUpdated, "Number of pages available after update");
	}
}
