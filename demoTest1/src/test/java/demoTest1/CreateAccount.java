package demoTest1;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

public class CreateAccount extends Driver{
	WebDriver driver = Driver.driver;
	
	static String fName = "Apo";
	static String lName = "Ambo";
	static String email = "kierson.vig.illa@uap.asia";
	static String passkey = "Password123!!!";
	
	@Test (groups = "newAccount")
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
		
		
	//Verify that required field validations are NOT available upon initial load
		WebDriverWait checkErrorFields = new WebDriverWait(driver,Duration.ofMillis(1));
		checkErrorFields.until(ExpectedConditions.invisibilityOfElementLocated(By.id("firstname-error")));
		System.out.println("Validation message for First Name not available");
		checkErrorFields.until(ExpectedConditions.invisibilityOfElementLocated(By.id("lastname-error")));
		System.out.println("Validation message for Last Name not available");
		checkErrorFields.until(ExpectedConditions.invisibilityOfElementLocated(By.id("email_address-error")));
		System.out.println("Validation message for Email Address not available");
		checkErrorFields.until(ExpectedConditions.invisibilityOfElementLocated(By.id("password-error")));
		System.out.println("Validation message for Password not available");
		checkErrorFields.until(ExpectedConditions.invisibilityOfElementLocated(By.id("password-confirmation-error")));
		System.out.println("Validation message for Confirm Password not available\n");
		
		
	//Verify that validations are available for required fields
		driver.findElement(By.xpath("//div[@class=\"primary\"]/button[@class=\"action submit primary\"]")).click();
		checkErrorFields.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname-error")));
		System.out.println("Validation message for First Name available");
		checkErrorFields.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastname-error")));
		System.out.println("Validation message for Last Name available");
		checkErrorFields.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_address-error")));
		System.out.println("Validation message for Email Address available");
		checkErrorFields.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-error")));
		System.out.println("Validation message for Password available");
		checkErrorFields.until(ExpectedConditions.visibilityOfElementLocated(By.id("password-confirmation-error")));
		System.out.println("Validation message for Confirm Password available\n");
		
		
	//Fill up fields
		WebDriverWait waitPersonalInfoFields = new WebDriverWait(driver,Duration.ofSeconds(3));
		waitPersonalInfoFields.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-validate")));
		driver.findElement(By.id("firstname")).sendKeys(fName);
		driver.findElement(By.id("lastname")).sendKeys(lName);
		driver.findElement(By.name("email")).sendKeys(email);
		
		
	//Scroll down
		JavascriptExecutor scrollDown = (JavascriptExecutor)driver;
		scrollDown.executeScript("window.scrollBy(0,600)");
		
		
	//Verify Password strength indicator
	//Using the usual sendKeys() will not trigger the password-meter [driver.findElement(By.id("")).sendKeys("")]
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
		new Actions(driver).sendKeys(veryStrongPassword, passkey).perform();
		passwordMeter = driver.findElement(By.id("password-strength-meter-container")).getAttribute("class");
		assertEquals(passwordMeter, "password-very-strong");
		
		
	//Verify Confirm Password
		driver.findElement(By.id("password-confirmation")).sendKeys("Password1");
		driver.findElement(By.xpath("//div[@class=\"primary\"]/button[@type=\"submit\"]")).click();
		String passwordConfirm = driver.findElement(By.id("password-confirmation-error")).getText();
		assertEquals(passwordConfirm, "Please enter the same value again.");
		
	//Input the same password
		driver.findElement(By.id("password-confirmation")).clear();
		driver.findElement(By.id("password-confirmation")).sendKeys(passkey);
		
		
	//Continue creating account
		driver.findElement(By.xpath("//div[@class=\"primary\"]/button[@class=\"action submit primary\"]")).click();
				
		WebDriverWait waitConfirmationMsg = new WebDriverWait(driver,Duration.ofSeconds(3));
		waitConfirmationMsg.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"message-success success message\"]")));
		
		String welcomeTitle = driver.getTitle();
		assertEquals(welcomeTitle, "My Account");
		
		String loginGreeting = driver.findElement(By.xpath("//span[@class=\"logged-in\"]")).getText();
		assertEquals(loginGreeting, "Welcome, "+fName+" "+lName+"!");

		String creationConfirm = driver.findElement(By.xpath("//div[@class=\"message-success success message\"]")).getText();
		assertEquals(creationConfirm, "Thank you for registering with Main Website Store.");
		
		driver.quit();
	}
}
