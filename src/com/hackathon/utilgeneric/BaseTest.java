package com.hackathon.utilgeneric;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.xml.XmlTest;

import com.hackathon.projectspec.Functions;
import com.hackathon.projectspec.GlobalFunctions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest{

	public static WebDriver driver;
	public static Locators l1;
	public static String c;
	public static ExtentReports extentReportIE;
	public static ExtentReports extentReportFF;
	public static ExtentReports extentReportAndroidPhone;
	public static ExtentReports extentReportAndroidTab;
	public static ExtentReports extentReportiPhone;
	public static ExtentReports extentReportEdgeTablet;
	public static ExtentReports extentReportEdge;
	public static ExtentReports extentReportFFTablet;
	public static ExtentReports extentReportiPad;
	public static ExtentReports extentReportChrome;
	public static ExtentTest extentTestIE;
	public static ExtentTest extentTestFF;
	public static ExtentTest extentTestAndroidPhone;
	public static ExtentTest extentTestAndroidTab;
	public static ExtentTest extentTestiPhone;
	public static ExtentTest extentTestEdgeTablet;
	public static ExtentTest extentTestEdge;
	public static ExtentTest extentTestFFTablet;
	public static ExtentTest extentTestiPad;
	public static ExtentTest extentTestChrome;
	public static ExtentTest extentTestChildIE;
	public static ExtentTest extentTestChildFF;
	public static ExtentTest extentTestChildAndroidPhone;
	public static ExtentTest extentTestChildAndroidTab;
	public static ExtentTest extentTestChildiPhone;
	public static ExtentTest extentTestChildEdgeTablet;
	public static ExtentTest extentTestChildiEdge;
	public static ExtentTest extentTestChildFFTablet;
	public static ExtentTest extentTestChildiPad;
	public static ExtentTest extentTestChildChrome;
	public static Logger log;
	public static SoftAssert sa;

	public static Actions act;
	public int i;
	public static GlobalFunctions gVar;
	public static Functions fun;
	public static String pName;
	public static String logFileName=new Date().getDate()+" "+new Date().getMonth()+" "+new Date().getYear()+" "+new Date().getHours()+" "+new Date().getMinutes()+" "+new Date().getSeconds();
	public static String filePath="\\Drive\\Results "+logFileName;
	File source;
	File dest;
	public static XmlTest xmlTest;
	static int counter;
	
	@BeforeSuite(groups={"reg","sanity_guest","sanity_reg"})
	public void cleanUp(XmlTest xmlTest)
	{
		BaseTest.xmlTest=xmlTest;
		//delete log directory
		try
		{
		FileUtils.deleteDirectory(new File(System.getProperty("user.dir")+"//log"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Leave it");
		}
		
		//create log derectories
		new File(filePath).mkdirs();
		source = new File(System.getProperty("user.dir")+"\\log");
		dest = new File(filePath);
	}
	
	@BeforeClass(groups={"reg","sanity_guest","sanity_reg"})
	public void initialize(XmlTest xmlTest) throws Exception
	{
		counter++;
		if(xmlTest.getParameter("broName").equalsIgnoreCase("ie")) {
			//Report declaration code 
			extentReportIE = new ExtentReports("\\SelFrameWork\\MyReportIE.html", false);
			extentReportIE.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestIE=extentReportIE.startTest(this.getClass().getSimpleName());
		
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("firefox")) {
			extentReportFF = new ExtentReports("\\SelFrameWork\\MyReportFF.html", false);
			extentReportFF.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestFF=extentReportFF.startTest(this.getClass().getSimpleName());
		
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
			extentReportAndroidPhone = new ExtentReports("\\SelFrameWork\\MyReportAP.html", false);
			extentReportAndroidPhone.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestAndroidPhone=extentReportAndroidPhone.startTest(this.getClass().getSimpleName());
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
			extentReportAndroidTab = new ExtentReports("\\SelFrameWork\\MyReportAT.html", false);
			extentReportAndroidTab.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestAndroidTab=extentReportAndroidTab.startTest(this.getClass().getSimpleName());

		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
			extentReportiPhone = new ExtentReports("\\SelFrameWork\\MyReportiPhone.html", false);
			extentReportiPhone.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestiPhone=extentReportiPhone.startTest(this.getClass().getSimpleName());
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
			extentReportiPad = new ExtentReports("\\SelFrameWork\\MyReportiPad.html", false);
			extentReportiPad.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestiPad=extentReportiPad.startTest(this.getClass().getSimpleName());
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("chrome")) {
			extentReportChrome = new ExtentReports("\\SelFrameWork\\MyReportiPad.html", false);
			extentReportChrome.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestChrome=extentReportChrome.startTest(this.getClass().getSimpleName());
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
			extentReportFFTablet = new ExtentReports("\\SelFrameWork\\MyReportiPhone.html", false);
			extentReportFFTablet.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestFFTablet=extentReportFFTablet.startTest(this.getClass().getSimpleName());
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
			extentReportEdge = new ExtentReports("\\SelFrameWork\\MyReportiPhone.html", false);
			extentReportEdge.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestEdge=extentReportEdge.startTest(this.getClass().getSimpleName());
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
			extentReportEdgeTablet = new ExtentReports("\\SelFrameWork\\MyReportiPhone.html", false);
			extentReportEdgeTablet.loadConfig(new File(System.getProperty("user.dir")+"\\ExtentReports\\extent-config.xml"));
			extentTestEdgeTablet=extentReportEdgeTablet.startTest(this.getClass().getSimpleName());
			
		}
		
		driver=new GetDriver().getDriver(xmlTest);
		System.out.println(driver);
		gVar=new GlobalFunctions(driver);
		fun = new Functions();
		gVar.navigateToSite(xmlTest);
//		Dimension screenSize = new Dimension(900, 900);
//		driver.manage().window().setSize(screenSize);
//		driver.manage().window().maximize();
		
		log=Logger.getLogger(this.getClass().getName());
		act=new Actions(driver);
	}
	
	@BeforeMethod(groups={"reg","sanity_guest","sanity_reg"})
	public void extReport(Method m,XmlTest xmlTest)
	{
		if(xmlTest.getParameter("broName").equalsIgnoreCase("ie")) {
			
			extentTestChildIE=extentReportIE.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestIE.appendChild(extentTestChildIE);
			l1=new Locators(driver,extentTestChildIE, xmlTest);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("firefox")) {
			
			extentTestChildFF=extentReportFF.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestFF.appendChild(extentTestChildFF);
			l1=new Locators(driver,extentTestChildFF,xmlTest);
			System.out.println("Firefox before method");
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
			extentTestChildAndroidPhone=extentReportAndroidPhone.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestAndroidPhone.appendChild(extentTestChildAndroidPhone);
			l1=new Locators(driver, extentTestChildAndroidPhone, xmlTest);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
			extentTestChildAndroidTab=extentReportAndroidTab.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestAndroidTab.appendChild(extentTestChildAndroidTab);
			l1=new Locators(driver, extentTestChildAndroidTab, xmlTest);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
			extentTestChildiPhone=extentReportiPhone.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestiPhone.appendChild(extentTestChildiPhone);
			l1=new Locators(driver, extentTestChildiPhone, xmlTest);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
			extentTestChildiPad=extentReportiPad.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestiPad.appendChild(extentTestChildiPad);
			l1=new Locators(driver, extentTestChildiPad, xmlTest);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("chrome")) {
			extentTestChildChrome=extentReportChrome.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestChrome.appendChild(extentTestChildChrome);
			l1=new Locators(driver, extentTestChildChrome, xmlTest);

		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
			extentTestChildFFTablet=extentReportFFTablet.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestFFTablet.appendChild(extentTestChildFFTablet);
			l1=new Locators(driver, extentTestChildFFTablet, xmlTest);
			
		}  else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
			extentTestChildiEdge=extentReportEdge.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestEdge.appendChild(extentTestChildiEdge);
			l1=new Locators(driver, extentTestChildiEdge, xmlTest);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
			extentTestChildEdgeTablet=extentReportEdgeTablet.startTest(m.getName()+" "+driver.getClass().getSimpleName());
			extentTestEdgeTablet.appendChild(extentTestChildEdgeTablet);
			l1=new Locators(driver, extentTestChildEdgeTablet, xmlTest);
			
		}
		sa=new SoftAssert();
		log.info("Test Case "+m.getName()+"  Started in "+driver.getClass().getSimpleName()+"driver");
		log.info("-------------------------------------------");

	}
	
	@AfterMethod(groups={"reg","sanity_guest","sanity_reg"})
	public void endTest(Method m,XmlTest xmlTest)
	{
		log.info("Test Case "+m.getName()+"  Ended in "+driver.getClass().getSimpleName()+"driver");
		log.info("-------------------------------------------");
/*		if(xmlTest.getParameter("broName").equalsIgnoreCase("ie")) {
			
			extentReportIE.endTest(extentTestIE);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("firefox")) {
			
			extentReportFF.endTest(extentTestFF);
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
			
			extentReportAndroidPhone.endTest(extentTestAndroidPhone);
		}*/
	}
	
	
//	@AfterTest(groups={"reg","sanity_guest","sanity_reg"})
	@AfterTest()
	public void endTest(XmlTest xmlTest) throws IOException
	{
		if(xmlTest.getParameter("broName").equalsIgnoreCase("ie")) {
			
			extentReportIE.endTest(extentTestIE);
			extentReportIE.flush();
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("firefox")) {
			
			extentReportFF.endTest(extentTestFF);
			extentReportFF.flush();
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
			
			extentReportAndroidPhone.endTest(extentTestAndroidPhone);
			extentReportAndroidPhone.flush();
			
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
			
			extentReportAndroidTab.endTest(extentTestAndroidTab);
			extentReportAndroidTab.flush();
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
			
			extentReportiPhone.endTest(extentTestiPhone);
			extentReportiPhone.flush();
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
			
			extentReportiPad.endTest(extentTestiPad);
			extentReportiPad.flush();
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("chrome")) {
			
			extentReportChrome.endTest(extentTestChrome);
			extentReportChrome.flush();
		} else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {

			extentReportFFTablet.endTest(extentTestFFTablet);
			extentReportFFTablet.flush();
		}  else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {

			extentReportEdge.endTest(extentTestEdge);
			extentReportEdge.flush();
		}   else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
			
			extentReportEdgeTablet.endTest(extentTestEdgeTablet);
			extentReportEdgeTablet.flush();
		}
		
		 FileWriter writer = new FileWriter("./Result/Traditional-V2-TestResults.txt", true);
         BufferedWriter bufferedWriter = new BufferedWriter(writer);
         bufferedWriter.write("-------------------------------------------------------------------------------------------------------------------------------------------------");
         bufferedWriter.newLine();
         bufferedWriter.close();
	}
	
	@AfterSuite(groups={"reg","sanity_guest","sanity_reg"})
	public void tearDown(XmlTest xmlTest) throws IOException
	{
		new GetDriver().closeDriver();
		
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}


	}
	
}
