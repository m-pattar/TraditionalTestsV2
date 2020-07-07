package com.hackathon.projectspec;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.apache.bcel.generic.BASTORE;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.xml.XmlTest;

import com.hackathon.utilgeneric.BaseTest;
import com.hackathon.utilgeneric.GetData;
import com.hackathon.utilgeneric.GetDriver;

public class GlobalFunctions {
	
	WebDriver driver;
	String assertEqual;
	boolean isVisible;
	public String emTextColor = "rgb(208, 2, 27)";
	boolean view;
	
	public GlobalFunctions(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void navigateToSite(XmlTest xmlTest) throws Exception
	{
		driver.get(new GetData().getDataFromProperties("//data//config.properties", "URL"));
		
	}
	
	public Select selectObj(WebElement element)
	{
		return new Select(element);
	}
	
	public boolean assertNotVisible(String str1,String str2)
	{
		try{
			BaseTest.l1.getWebElement(str1, str2).isDisplayed();
			
		}catch(Exception e){
			isVisible=true;
		}
		return isVisible;
	}
	
	public boolean assertVisible(String str1,String str2)
	{
		try
		{
			BaseTest.l1.getWebElement(str1, str2).isDisplayed();
			isVisible=true;
			
		}
		catch(Exception e)
		{
			isVisible=false;
		}
		return isVisible;
	}
	
	public boolean assertVisible(String str1,String str2,int i)
	{
		try{
			BaseTest.l1.getWebElements(str1, str2).get(i).isDisplayed();
			isVisible=true;
			
		}catch(Exception e){
			isVisible=false;
		}
		return isVisible;
	}
	
	public boolean assertVisibleIn(List<WebElement> webElementsList, int loop, String identifier, String path)
	{
		try
		{
			webElementsList.get(loop).findElement(BaseTest.l1.getByReference("PDP_tabs_ACCESSORIES_Items_Names", path)).isDisplayed();
			isVisible=true;
		}
		catch (Exception e)
		{
			isVisible=false;
		}
		return isVisible;
	}
	public String assertEqual(String str1,String str2)
	{
		try
		{
			assertEqual=BaseTest.l1.getWebElement(str1, str2).getText();
		}
		catch(Exception e)
		{
			assertEqual="No Such Element";	
		}
		return assertEqual;
	}
	
	public String assertEqual(String str1,String str2,int i)
	{
		try{
			assertEqual=BaseTest.l1.getWebElements(str1, str2).get(i).getText();
		}catch(Exception e){
			assertEqual="No Such Element";	
		}
		return assertEqual;
	}
	
	public String assertEqual(String str1,String str2,String str3)
	{
		try{
			assertEqual=BaseTest.l1.getWebElement(str1, str2).getAttribute(str3);
		}catch(Exception e){
			assertEqual="No Such Element";	
		}
		return assertEqual;
	}
	
	public String assertEqual(List<WebElement> webElementsList, int loop, String identifier, String path)
	{
		try
		{
			assertEqual = webElementsList.get(loop).findElement(BaseTest.l1.getByReference(identifier, path)).getText();
			
		}
		catch (Exception e)
		{
			assertEqual="No Such Element";	
		}
		return assertEqual;
		
	}
	
	public String getCssValue(String str1,String str2,String str3)
	{
		try{
			assertEqual=BaseTest.l1.getWebElement(str1, str2).getCssValue(str3);
		}catch(Exception e){
			assertEqual="No Such Element";	
		}
		return assertEqual;
	}
	
	public String assertEqual(String str1,String str2,String str3,int i)
	{
		try{
			assertEqual=BaseTest.l1.getWebElements(str1, str2).get(i).getAttribute(str3);
		}catch(Exception e){
			assertEqual="No Such Element";	
		}
		return assertEqual;
	}
	
	public void assertequalsIgnoreCase(String actual, String expected, String message)
	{
		BaseTest.sa.assertEquals(actual.toLowerCase(), expected.toLowerCase(), message);
	}
	
	public void assertequalsIgnoreCase(String actual, String expected)
	{
		BaseTest.sa.assertEquals(actual.toLowerCase(), expected.toLowerCase());
	}
	
	public String getSystemDate(String dateFormate)
	{
		//Fetch the system details and compair
		SimpleDateFormat dFormate = new SimpleDateFormat(dateFormate);
		Date date = new Date();
		dFormate.setTimeZone(TimeZone.getTimeZone("GMT"));
		String systemDate = dFormate.format(date);
		
		return systemDate;
	}
	
	//Generate 6digit random number
	public int generateRandomNum()
	{
		Random rand = new Random();
		int randomNumber = rand.nextInt(9000000) + 1000000;
		return randomNumber;
	}
	
	public int randomNum()
	{
		Random rand = new Random();
		int randomNumber = rand.nextInt(90000) + 10000;
		return randomNumber;
	}
	
	public boolean mobileView()
	{
		return BaseTest.xmlTest.getParameter("executingIn").equalsIgnoreCase("mobile");
	}
	
	public boolean tabletView()
	{
		return BaseTest.xmlTest.getParameter("executingIn").equalsIgnoreCase("Tablet");
		
	}
	public boolean desktopView()
	{
		return BaseTest.xmlTest.getParameter("executingIn").equalsIgnoreCase("browser");
	}
	
	public boolean ieBrowser()
	{
		return BaseTest.xmlTest.getParameter("broName").equalsIgnoreCase("ie");
	}
	public void mouseHover(WebElement element) throws InterruptedException
	{
		Thread.sleep(7000);
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(mouseOverScript, element);
	}
	
	public void overRideInIe()
	{
		try
		{
			Thread.sleep(2000);
			driver.findElement(By.id("overridelink")).click();
			System.out.println("SSL is overrided in IE browser");
			Thread.sleep(5000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("SSL is not overrided IE");
		}
	}
	
	public void hover() throws InterruptedException {
		WebElement element = driver.findElement(By.id("product_1"));
		BaseTest.act.moveToElement(element).perform();
		Thread.sleep(2000);
	}
	
public void saveOrderId(String orderId, String message) throws Exception
{
	String systemDate = BaseTest.gVar.getSystemDate("dd/mm/yyyy");
	try
	{
		writeOrderIdToExcel(systemDate, orderId, message);
	}
	catch (Exception e)
	{
		BaseTest.log.info("Unable to save Order ID in EXCEL sheet");
	}
	
}
	
public void writeOrderIdToExcel(String date, String orderId, String message) throws Exception 
{

	File f = new File(System.getProperty("user.dir")+"//data//OrderIds.xlsx");
	FileInputStream fis=new FileInputStream(f);
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh;
	try
	{
		sh=wb.createSheet("OrderId");
	} 
	catch (Exception e)
	{
		sh=wb.getSheet("OrderId");
	}

	Row r;
	int lastRowNum = sh.getLastRowNum()+1;
	System.out.println("lastRowNum:-  "+ lastRowNum);
	
	try 
	{
		r=sh.getRow(lastRowNum);
//		r.getCell(colNum, Row.CREATE_NULL_AS_BLANK);
		r.getCell(0).setCellValue(date);
		r.getCell(1).setCellValue(orderId);
		r.getCell(2).setCellValue(message);
	}
	catch (Exception e)
	{
		r=sh.createRow(lastRowNum);
		r=sh.getRow(lastRowNum);
		r.createCell(0).setCellValue(date);
		r.createCell(1).setCellValue(orderId);
		r.createCell(2).setCellValue(message);
	}

	FileOutputStream fos=new FileOutputStream(f);
	wb.write(fos);

}

	public void click(String loc1,String filename) throws Exception
	{
		WebElement webElement = BaseTest.l1.getWebElement(loc1, filename);
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, 7);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
	}
	
	public void waitForElement(String loc1,String filename,int timeUnit) throws Exception
	{
		WebElement webElement = BaseTest.l1.getWebElement(loc1, filename);
		WebDriverWait wait = new WebDriverWait(BaseTest.driver, timeUnit);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	
}
