package demoTest1;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

public class CreateAccount extends Driver{
	WebDriver driver = Driver.driver;
	
	@Test
	public void accessWebsite() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		try {
			driver.get("https://magento.softwaretestingboard.com/");
		}
		catch (TimeoutException e) {
			System.out.println("The demo store website is not available");
		}
		
		//Verify Home page loads successfully
		String homeTitle = driver.getTitle();
		assertEquals(homeTitle, "Home Page");
		
		//Verify Create an Account link available and redirects user to create customer account
		driver.findElement(By.linkText("Create an Account")).click();		
		String newAccountTitle = driver.getTitle();
		assertEquals(newAccountTitle, "Create New Customer Account");
		
		WebDriverWait waitPersonalInfoFields = new WebDriverWait(driver,Duration.ofSeconds(3));
		waitPersonalInfoFields.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-validate")));
		driver.findElement(By.id("firstname")).sendKeys("Apo");
		driver.findElement(By.id("lastname")).sendKeys("Ambo");
		driver.findElement(By.name("email")).sendKeys("kierson.vigilla@uap.asia");
		
		//Verify Password strength indicator
		//driver.findElement(By.id("password")).sendKeys("1234abcd");
		WebElement weakPassword = driver.findElement(By.id("password"));
		new Actions(driver).sendKeys(weakPassword, "1234abcd").perform();		
		String passwordMeter = driver.findElement(By.id("password-strength-meter-container")).getAttribute("class");
		assertEquals(passwordMeter, "password-weak");
		weakPassword.clear();
		
		WebElement strongPassword = driver.findElement(By.id("password"));
		new Actions(driver).sendKeys(strongPassword, "Password1!").perform();
		passwordMeter = driver.findElement(By.id("password-strength-meter-container")).getAttribute("class");
		assertEquals(passwordMeter, "password-strong");
		strongPassword.clear();
		
		WebElement veryStrongPassword = driver.findElement(By.id("password"));
		new Actions(driver).sendKeys(veryStrongPassword, "Password123!!!").perform();
		passwordMeter = driver.findElement(By.id("password-strength-meter-container")).getAttribute("class");
		assertEquals(passwordMeter, "password-very-strong");
		
		driver.findElement(By.id("password-confirmation")).sendKeys("1234abcd");
		
		
		driver.quit();
	}
}
