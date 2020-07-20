package com.test.deckopay.DeckopayTest;



import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.deckopay.TestBase.TestBase;

public class DeckopayLogin extends TestBase
{
	//public static WebDriver driver= null;
	
	@BeforeTest
	public void gotosite() throws IOException {
		initialize();
		driver.get(Config.getProperty("deckopayURL"));
	}
	
	@Test (priority=2)
	
	public void loginTest() throws IOException {
		
		/*The below  simple code to understand the flow of the test
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//chromedriver//chromedriver.exe");
		 driver= new ChromeDriver();
		 driver.get("https://release.dekopay.org/backoffice/v2/#/");
		 driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div/div/div/div/div/form/div[2]/input")).sendKeys("praveen.kakkerla");
		 driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div/div/div/div/div/form/div[3]/input")).sendKeys("DekoQA2020");
		 driver.findElement(By.xpath("//*[@id=\"ng-app\"]/div/div/div/div/div/form/div[6]/button")).click();
				 
				 */
		
		/* The below  code is developed as a framework please refer to TestBase calss to see how URL, locators,test data is passed to test*/
		
		
		getobject("usernameXpath").clear();
		getobject("usernameXpath").sendKeys(getdata("username"));
		getobject("passwordXpath").sendKeys(getdata("password"));
		getobject("SigninButtonXpath").click();
		
		String loggedinUsername=getobject("loggedinUserXpath").getText();
		Assert.assertEquals(loggedinUsername, "Praveen");
		
		}
		
	/* this test is for failed login script executes failed login first and then successful login test*/
	@Test(priority=1)
	public void failedlogin() throws IOException, InterruptedException {
		
		
		
		getobject("usernameXpath").sendKeys(getdata("username"));
		getobject("passwordXpath").sendKeys(getdata("incorrectpassword"));
		getobject("SigninButtonXpath").click();
		Thread.sleep(2000L);
		String errormessage=getobject("errormessage").getText();
		Assert.assertEquals(errormessage, "Sorry, the details you provided were incorrect.");
		
		
	}
	
	@AfterTest
	public void closebrowser()
	{
		driver.quit();
	}
}