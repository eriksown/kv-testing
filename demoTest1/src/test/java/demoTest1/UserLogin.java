package demoTest1;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class UserLogin extends Driver{
	WebDriver driver = Driver.driver;
	
	String userEmail = CreateAccount.email;
	String passkey = "Password123!!!";
	String fName = CreateAccount.fName;
	String lName = CreateAccount.lName;
	
	@Test (groups = "mainRun")
	public void login() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		try {
			driver.get("https://magento.softwaretestingboard.com/");
		} catch (TimeoutException e) {
			System.out.println("The demo store website is unavailable");
		}
		
	//Login
		driver.findElement(By.partialLinkText("Sign In")).click();
		driver.findElement(By.id("email")).sendKeys(userEmail);
		driver.findElement(By.id("pass")).sendKeys(passkey);
		driver.findElement(By.id("send2")).click();
		
		WebDriverWait waitAccountPage = new WebDriverWait(driver, Duration.ofSeconds(2));
		waitAccountPage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class=\"logged-in\"]")));
	
	//Verify page and welcome message
		String welcomeTitle = driver.getTitle();
		assertEquals(welcomeTitle, "Home Page");
		
		String loginGreeting = driver.findElement(By.xpath("//span[@class=\"logged-in\"]")).getText();
		assertEquals(loginGreeting, "Welcome, "+fName+" "+lName+"!");

		driver.quit();
	}
}