package demoTest1;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
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
		
		WebDriverWait waitPersonalInfoFields = new WebDriverWait(driver,Duration.ofSeconds(2));
		waitPersonalInfoFields.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-validate")));
		
	//Verify validation is NOT present upon first load of Create Account	
		WebDriverWait invRequiredFieldError = new WebDriverWait(driver,Duration.ofMillis(1));
		invRequiredFieldError.until(ExpectedConditions.invisibilityOfElementLocated(By.id("firstname-error")));
		System.out.println("First Name validation not yet available");
		invRequiredFieldError.until(ExpectedConditions.invisibilityOfElementLocated(By.id("lastname-error")));
		System.out.println("Last Name validation not yet available");
		invRequiredFieldError.until(ExpectedConditions.invisibilityOfElementLocated(By.id("email_address-error")));
		System.out.println("Email Address validation not yet available");
		invRequiredFieldError.until(ExpectedConditions.invisibilityOfElementLocated(By.id("password-error")));
		System.out.println("Password validation not yet available");
		invRequiredFieldError.until(ExpectedConditions.invisibilityOfElementLocated(By.id("password-confirmation-error")));
		System.out.println("Password Confirmation not yet available\n");
		
	//Verify validation for required fields
		driver.findElement(By.xpath("//div[@class=\"primary\"]/button")).click();
		WebDriverWait requiredFieldError = new WebDriverWait(driver,Duration.ofMillis(1));
		requiredFieldError.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname-error")));
		System.out.println("First Name validation available");
		requiredFieldError.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastname-error")));
		System.out.println("Last Name validation available");
		requiredFieldError.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_address-error")));
		System.out.println("Email Address validation available");
		requiredFieldError.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-error")));
		System.out.println("Password validation available");
		requiredFieldError.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-confirmation-error")));
		System.out.println("Password Confirmation available\n");
		
	//Fill up form
		driver.findElement(By.id("firstname")).sendKeys("Apo");
		driver.findElement(By.id("lastname")).sendKeys("Ambo");
		driver.findElement(By.name("email")).sendKeys("kierson.vigilla@uap.asia");
		
	//Scroll down
		JavascriptExecutor scrollDown = (JavascriptExecutor)driver;
		scrollDown.executeScript("window.scrollBy(0,600)");
		
	//Verify Password strength indicator
	//usual sendKeys will not trigger the password-meter [driver.findElement(By.id("password")).sendKeys("1234abcd")]
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
		
		
	//Verify Confirm Password
		driver.findElement(By.id("password-confirmation")).sendKeys("Password1");
		driver.findElement(By.xpath("//div[@class=\"primary\"]/button[@type=\"submit\"]")).click();
		String passwordConfirm = driver.findElement(By.id("password-confirmation-error")).getText();
		assertEquals(passwordConfirm, "Please enter the same value again.");
		
		
		driver.quit();
	}
}
