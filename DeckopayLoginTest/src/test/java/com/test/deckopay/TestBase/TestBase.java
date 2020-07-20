package com.test.deckopay.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;






public class TestBase {
	public static WebDriver driver= null;
	public static Properties Config = null;
	public static Properties OR = null;
	public static Properties data = null;
		
	public static boolean isInitialized=false;
	
	
	public static void initialize()throws IOException{
		if(!isInitialized)		
		
	{				
		
	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//chromedriver//chromedriver.exe");		
	
	     driver= new ChromeDriver();		
	     driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
			 	
	Config = new Properties();
	FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\test\\deckopay\\properties\\CONFIG.properties");
	Config.load(ip);
	OR=new Properties();
	ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\test\\deckopay\\properties\\OR.properties");
	OR.load(ip);
	
	data =new Properties();
	ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\test\\deckopay\\properties\\Testdata.properties");
	data.load(ip);	
	
	isInitialized=true;
	}
	
	
	}
	public static WebElement getobject(String xpathkey){
		
		try{
			
			return driver.findElement(By.xpath(OR.getProperty(xpathkey)));
		}catch(Throwable t){
		
		
		return null;
		}
	}
public static WebElement getobject_linktext(String text){
		
		try{
			
			return driver.findElement(By.linkText(text));
		}catch(Throwable t){
		
		
		return null;
		}
	}
public static WebElement getobject_Css(String locator){
	try{
		
		return driver.findElement(By.cssSelector(OR.getProperty(locator)));
	}catch(Throwable t){
	
	
	return null;
	}
}

public static boolean isElemntpresent(String xpathkey){
	List<WebElement> we =driver.findElements(By.xpath(OR.getProperty(xpathkey)));
	int count =we.size();
	if(count==1) 
		return true;
	else{
		System.out.println(xpathkey+ "  Element Not present ");
		
		return false;
	}
}
public static boolean isElemntpresentWithText(String text){
	List<WebElement> we =driver.findElements(By.linkText(text));
	int count =we.size();
	if(count==1) 
		return true;
	else{
		System.out.println(text+ "   Page/Text Not present ");
		
		return false;
	}
}
	public static String getdata(String fieldname){
		
		return data.getProperty(fieldname);
		
	}
	
	public static void verifylink_present(String linkname) throws IOException
	{
		if(driver.findElements(By.linkText(linkname)).size()==1){
			getobject_linktext(linkname).click();
			
			
		}else{
			System.out.println(linkname+"   Option is not available");
			
		}
	}
	
	
	
	public static void Dropdown(WebElement Element,int optionnumber)
	{
		List<WebElement> list=Element.findElements(By.tagName("option"));
		System.out.println(list.size());
		list.get(optionnumber).click();
	}
	
	}



