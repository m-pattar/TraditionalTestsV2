package com.hackathon.utilgeneric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Locators{

	WebDriver driver;
	By by;
	WebElement wb;
	Logger log;
	ExtentTest extentTestChildIE;
	ExtentTest extentTestChildFF;
	ExtentTest extentTestChildAndroidPhone;
	ExtentTest extentTestChildAndroidTab;
	ExtentTest extentTestChildiPhone;
	ExtentTest extentTestChildEdgeTablet;
	ExtentTest extentTestChildEdge;
	ExtentTest extentTestChildFFTablet;
	ExtentTest extentTestChildiPad;
	ExtentTest extentTestChildChrome;
	XmlTest xmlTest;
	String propName;
	
	public Locators(WebDriver driver,ExtentTest extentTest,XmlTest xmlTest)
	{
		this.driver=driver;
		this.xmlTest=xmlTest;
		
		if(driver.getClass().getSimpleName().equalsIgnoreCase("InternetExplorerDriver")) {
			extentTestChildIE=extentTest;
			
		} else if(driver.getClass().getSimpleName().equalsIgnoreCase("FirefoxDriver")) {
			extentTestChildFF=extentTest;
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
			extentTestChildAndroidPhone=extentTest;
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
			extentTestChildAndroidTab=extentTest;
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
			extentTestChildiPhone=extentTest;
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
			extentTestChildiPad=extentTest;
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome")) {
			extentTestChildChrome=extentTest;
		}
		else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
			extentTestChildFFTablet=extentTest;
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
			extentTestChildEdge=extentTest;
			
		}
		 else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
			 extentTestChildEdgeTablet=extentTest;
				
			}
	}
	
	private By getLocators(String loc1,String filename) throws Exception
	{
		String str=GetData.getDataFromProperties("//POM//"+filename, loc1);
		String[] loc=str.split(";");
		
		if(loc[0].equalsIgnoreCase("id"))
		{
			by=By.id(loc[1]);
		}
		else if(loc[0].equalsIgnoreCase("css"))
		{
			by=By.cssSelector(loc[1]);
		}
		else if(loc[0].equalsIgnoreCase("name"))
		{
			by=By.name(loc[1]);
		}
		else if(loc[0].equalsIgnoreCase("class"))
		{
			by=By.className(loc[1]);
		}
		else if(loc[0].equalsIgnoreCase("xpath"))
		{
			by=By.xpath(loc[1]);
		}
		else if(loc[0].equalsIgnoreCase("linktext"))
		{
			by=By.linkText(loc[1]);
			System.out.println(loc[1]);
		}
		else if(loc[0].equalsIgnoreCase("partiallinktext"))
		{
			by=By.partialLinkText(loc[1]);
		}
		else if(loc[0].equalsIgnoreCase("tagname"))
		{
			by=By.tagName(loc[1]);
		}
		
		try
		{
		
		if(xmlTest.getParameter("broName").equalsIgnoreCase("ie"))
		{
			WebDriverWait wait=new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			Thread.sleep(1500);
		}
		else 
		{
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			Thread.sleep(100);
		}
		
		if(driver.getClass().getSimpleName().equalsIgnoreCase("InternetExplorerDriver")) {
			extentTestChildIE.log(LogStatus.PASS, ""+by);
			
		} else if(driver.getClass().getSimpleName().equalsIgnoreCase("Firefoxdriver")) {
			extentTestChildFF.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
			extentTestChildAndroidPhone.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
			extentTestChildAndroidTab.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
			extentTestChildiPhone.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
			extentTestChildiPad.log(LogStatus.PASS, ""+by);
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("chrome")) {
			extentTestChildChrome.log(LogStatus.PASS, ""+by);
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
			extentTestChildFFTablet.log(LogStatus.PASS, ""+by);
			
		}  else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
			extentTestChildEdge.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
			extentTestChildEdgeTablet.log(LogStatus.PASS, ""+by);
			
		}
		
		} 
		catch(Exception e) 
		{
			
			if(driver.getClass().getSimpleName().equalsIgnoreCase("InternetExplorerDriver")) {
				extentTestChildIE.log(LogStatus.FAIL, ""+by);
				
			} else if(driver.getClass().getSimpleName().equalsIgnoreCase("Firefoxdriver")) {
				extentTestChildFF.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
				extentTestChildAndroidPhone.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
				extentTestChildAndroidTab.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
				extentTestChildiPhone.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
				extentTestChildiPad.log(LogStatus.FAIL, ""+by);
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("chrome")) {
				extentTestChildChrome.log(LogStatus.FAIL, ""+by);
			}
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
				extentTestChildFFTablet.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
				extentTestChildEdge.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
				extentTestChildEdgeTablet.log(LogStatus.FAIL, ""+by);
				
			}
			
		}
		
		System.out.println(by);
		return by;
	}

	public WebElement getWebElement(String loc1,String filename) throws Exception 
	{
		wb=driver.findElement(getLocators(loc1, filename));
		if(driver.getClass().getSimpleName().equalsIgnoreCase("ChromeDriver") || driver.getClass().getSimpleName().equalsIgnoreCase("SafariDriver"))
		{
			try
			{
				if(!isElementInViewPort(wb))
				{
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
					Thread.sleep(500); 
				}
				else
				{
					//				System.out.println("not here");
				}
			}
			catch(Exception e)
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
				Thread.sleep(500); 
			}
		}
		
		Thread.sleep(100);
		
		return wb;
	}
	
	public WebElement getWebElement(By by) throws Exception 
	{
		//wb=driver.findElement(getLocators(loc1, filename));
		
		try
		{
		
		if(xmlTest.getParameter("broName").equalsIgnoreCase("ie"))
		{
			WebDriverWait wait=new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		else 
		{
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			Thread.sleep(100);
		}
		
		if(driver.getClass().getSimpleName().equalsIgnoreCase("InternetExplorerDriver")) {
			extentTestChildIE.log(LogStatus.PASS, ""+by);
			
		} else if(driver.getClass().getSimpleName().equalsIgnoreCase("Firefoxdriver")) {
			extentTestChildFF.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
			extentTestChildAndroidPhone.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
			extentTestChildAndroidTab.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
			extentTestChildiPhone.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
			extentTestChildiPad.log(LogStatus.PASS, ""+by);
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("chrome")) {
			extentTestChildChrome.log(LogStatus.PASS, ""+by);
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
			extentTestChildFFTablet.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
			extentTestChildEdge.log(LogStatus.PASS, ""+by);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
			extentTestChildEdgeTablet.log(LogStatus.PASS, ""+by);
			
		}
		
		} catch(Exception e) {
			
			if(driver.getClass().getSimpleName().equalsIgnoreCase("InternetExplorerDriver")) {
				extentTestChildIE.log(LogStatus.FAIL, ""+by);
				
			} else if(driver.getClass().getSimpleName().equalsIgnoreCase("Firefoxdriver")) {
				extentTestChildFF.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
				extentTestChildAndroidPhone.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
				extentTestChildAndroidTab.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
				extentTestChildiPhone.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
				extentTestChildiPad.log(LogStatus.FAIL, ""+by);
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("chrome")) {
				extentTestChildChrome.log(LogStatus.FAIL, ""+by);
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
				extentTestChildFFTablet.log(LogStatus.FAIL, ""+by);
				
			} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
				extentTestChildEdge.log(LogStatus.FAIL, ""+by);
				
			}  else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
				extentTestChildEdgeTablet.log(LogStatus.FAIL, ""+by);
				
			}
			
		}
		
		
		wb=driver.findElement(by);
		if(driver.getClass().getSimpleName().equalsIgnoreCase("ChromeDriver") || driver.getClass().getSimpleName().equalsIgnoreCase("SafariDriver"))
		{
			try
			{
			if(!isElementInViewPort(wb))
			{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
			Thread.sleep(500); 
			}
			else
			{
//				System.out.println("not here");
			}
			}
			catch(Exception e)
			{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wb);
			Thread.sleep(500); 
			}
		}
		return wb;
	}
	
	public List<WebElement> getWebElements(String loc1,String filename) throws Exception
	{
		return driver.findElements(getLocators(loc1, filename));
	}
	
	private boolean isElementInViewPort(WebElement ele)
	{
		Dimension weD =ele.getSize(); //to get the element Dimensions
		Point weP = ele.getLocation(); // getting the location of the element in the page.
		Dimension d = driver.manage().window().getSize(); // To get the browser dimensions
		int x = d.getWidth(); //browser width
		int y = d.getHeight(); //browser height
		int x2 = weD.getWidth() + weP.getX();
		int y2 = weD.getHeight() + weP.getY();
//		System.out.println(x2 <= x && y2 <= y);
		return x2 <= x && y2 <= y;
	}
	
	private String multiLanguage(String properties1,String properties2,String text) throws FileNotFoundException, IOException
	{
		Properties p=new Properties();
		p.load(new FileInputStream(new File(properties1)));
		Iterator<Object> i=p.keySet().iterator();
		//Enumeration e=p.propertyNames();
		
		while (i.hasNext()) {
			String str1=(String)i.next();
			if(p.getProperty(str1).equalsIgnoreCase(text)) {
				propName=str1;
				System.out.println(propName);
			}
		}
		
	Properties p1=new Properties();
	p1.load(new FileInputStream(new File(properties2)));
	System.out.println(p1.getProperty(propName));
	
	return p1.getProperty(propName);
	}
	
	public By getByReference(String loc1, String filename) throws Exception
	{
		By byRef = getLocators(loc1, filename);
		return byRef;
	}
}
