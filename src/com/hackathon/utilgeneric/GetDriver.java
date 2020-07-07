package com.hackathon.utilgeneric;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.Highlighter.Highlight;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlTest;


public class GetDriver {
	
	public static WebDriver driver;
	String driverName;
	
	private static Map<String,WebDriver> drivers=new HashMap<String, WebDriver>();
	
	public WebDriver getDriver(XmlTest xmlTest) throws MalformedURLException, InterruptedException
	{
		try
		{
		if(drivers.get(xmlTest.getParameter("broName").toLowerCase())==null)
		{
			System.out.println("came here: getDriver method executing");
			if(xmlTest.getParameter("broName").equalsIgnoreCase("Firefox"))
			{
				DesiredCapabilities des=DesiredCapabilities.firefox();
				des.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				des.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
				des.setCapability("acceptInsecureCerts", true);
				des.setCapability("requireWindowFocus", true);
				//des.setPlatform(Platform.WIN8_1);
				if(xmlTest.getParameter("GridExecution").equalsIgnoreCase("false"))
				{
				System.out.println("Firefox browser launching");
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
				driver=new FirefoxDriver(des);
				}
				else
				{
					driver=new RemoteWebDriver(new URL(xmlTest.getParameter("GridURL")),des);
				}
				driver.manage().window().maximize();
				System.out.println("FF browser Launched");
			}
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome"))
			{
				DesiredCapabilities des=DesiredCapabilities.chrome();
				des.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				des.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
				//des.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR,ElementScrollBehavior.BOTTOM);
//				des.setPlatform(Platform.VISTA);
				
				if(xmlTest.getParameter("GridExecution").equalsIgnoreCase("false"))
				{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
				driver=new ChromeDriver(des);
   			}
				else
				{
					driver=new RemoteWebDriver(new URL(xmlTest.getParameter("GridURL")),des);
				}
				driver.manage().window().maximize();
				System.out.println("Chrome browser launched");
			}
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("IE"))
			{
				DesiredCapabilities des=DesiredCapabilities.internetExplorer();
				des.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				des.setCapability("ie.forceCreateProcessApi ",true);
				des.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				des.setCapability("ELEMENT_SCROLL_BEHAVIOR", ElementScrollBehavior.BOTTOM);
				des.setCapability("ENABLE_ELEMENT_CACHE_CLEANUP",true);
				des.setCapability("requireWindowFocus", false);
				des.setCapability("IE_ENSURE_CLEAN_SESSION",true);
				des.setCapability("disable-popup-blocking", false);
//				des.setCapability("requireWindowFocus", true);
			//	des.setPlatform(Platform.VISTA);
				
				if(xmlTest.getParameter("GridExecution").equalsIgnoreCase("false"))
				{
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");	
				driver=new InternetExplorerDriver(des);
				}
				else
				{
					driver=new RemoteWebDriver(new URL(xmlTest.getParameter("GridURL")),des);
				}
				System.out.println("IE browser launched");
			}
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge"))
			{
				DesiredCapabilities des=DesiredCapabilities.edge();
				des.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				
				if(xmlTest.getParameter("GridExecution").equalsIgnoreCase("false"))
				{
				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\drivers\\MicrosoftWebDriver.exe");
				driver=new EdgeDriver(des);
				}
				else
				{
					driver=new RemoteWebDriver(new URL(xmlTest.getParameter("GridURL")),des);
				}
				driver.manage().window().maximize();
				System.out.println("Edge browser launched");
			}
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet"))
			{
				DesiredCapabilities des=DesiredCapabilities.edge();
				des.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				
				if(xmlTest.getParameter("GridExecution").equalsIgnoreCase("false"))
				{
				System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\drivers\\MicrosoftWebDriver.exe");
				driver=new EdgeDriver(des);
				}
				else
				{
					driver=new RemoteWebDriver(new URL(xmlTest.getParameter("GridURL")),des);
				}
				driver.manage().window().setSize(new Dimension(768, 700));
				System.out.println("Edge browser launched");
			}
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("Safari"))
			{
				driver=new SafariDriver();
			}
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone"))
			{
				Map<String, Object> deviceMetrics = new HashMap<String, Object>();
				deviceMetrics.put("width", 500);
				deviceMetrics.put("height", 700);
				deviceMetrics.put("pixelRatio", 3.0);
				
				Map<String, Object> mobileEmulation = new HashMap<String, Object>();
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0.1; SM-G920V Build/MMB29K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
				
				Map<String, Object> chromeOption = new HashMap<String, Object>();
				chromeOption.put("mobileEmulation", mobileEmulation);
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOption);
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
				driver = new ChromeDriver(capabilities);
				driver.manage().window().maximize();
			}
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab"))
			{
				Map<String, Object> deviceMetrics = new HashMap<String, Object>();
				deviceMetrics.put("width", 768);
				deviceMetrics.put("height", 700);
				deviceMetrics.put("pixelRatio", 3.0);
				
				Map<String, Object> mobileEmulation = new HashMap<String, Object>();
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 6.0.1; SM-T550 Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 YaBrowser/16.10.2.1487.01 Safari/537.36");
				
				Map<String, Object> chromeOption = new HashMap<String, Object>();
				chromeOption.put("mobileEmulation", mobileEmulation);
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOption);
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
				driver = new ChromeDriver(capabilities);
				driver.manage().window().maximize();
			}
			
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet"))
			{
				System.out.println("************ FIREFOX EMULATOR");

				DesiredCapabilities des=DesiredCapabilities.firefox();
				des.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				des.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
				des.setCapability("acceptInsecureCerts", true);
				des.setCapability("requireWindowFocus", true);
			
				System.out.println("Firefox browser launching");
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
				driver=new FirefoxDriver(des);
				driver.manage().window().setSize(new Dimension(768, 700));
				System.out.println("FF browser Launched");
				
			}
			
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone"))
			{
				System.out.println("************ FIREFOX EMULATOR");
//				Map<String, Object> deviceMetrics = new HashMap<String, Object>();
//				deviceMetrics.put("width", 375);
//				deviceMetrics.put("height", 667);
//				deviceMetrics.put("pixelRatio", 2.0);
//				
//				Map<String, Object> mobileEmulation = new HashMap<String, Object>();
//				mobileEmulation.put("deviceMetrics", deviceMetrics);
//				mobileEmulation.put("userAgent", "Mozilla/5.0 (Apple-iPhone7C2/1202.466; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543 Safari/419.3");
//				
//				Map<String, Object> fireFoxOption = new HashMap<String, Object>();
//				fireFoxOption.put("mobileEmulation", mobileEmulation);
//				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
////				capabilities.setCapability(ChromeOptions.CAPABILITY, fireFoxOption);
////				capabilities.setCapability(FirefoxDriver.PROFILE, fireFoxOption);
//				capabilities.setCapability(FirefoxDriver.PROFILE, fireFoxOption);
//				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
//				driver = new FirefoxDriver(capabilities);
//				
//				
////				-----------------------
//				
//				FirefoxOptions options = new FirefoxOptions();
//				options.addArguments("--width=768");
//	            options.addArguments("--height=700");
//				WebDriverManager.firefoxdriver().setup();
//				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");
//				driver = new FirefoxDriver(options);
				
				
				Map<String, Object> deviceMetrics = new HashMap<>();

				// device with custom width, height and pixel ratio
				deviceMetrics.put("width", 380);
				deviceMetrics.put("height", 670);
				deviceMetrics.put("pixelRatio", 3.0);

				Map<String, Object> mobileEmulation = new HashMap<>();
				mobileEmulation.put("deviceMetrics", deviceMetrics);

				// device having custom user agent
				mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 8.0.0; Pixel 2 XL Build/OPD1.170816.004) AppleWebKit/537.36 (KHTML, like Gecko) Chrome / 66.0.3329.0 Mobile Safari / 537.36");

//				ChromeOptions chromeOptions = new ChromeOptions();
				FirefoxOptions firefoxOption = new FirefoxOptions();

				// ExperimentalOption needs to be set for emulation
//				firefoxOption.setExperimentalOption("mobileEmulation", mobileEmulation);
//				WebDriver driver = new ChromeDriver(chromeOptions);
				driver = new FirefoxDriver(firefoxOption);

				
			}
			
			else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad"))
			{
				Map<String, Object> deviceMetrics = new HashMap<String, Object>();
				deviceMetrics.put("width", 768);
				deviceMetrics.put("height", 1024);
				deviceMetrics.put("pixelRatio", 3.0);
				
				Map<String, Object> mobileEmulation = new HashMap<String, Object>();
				mobileEmulation.put("deviceMetrics", deviceMetrics);
				mobileEmulation.put("userAgent", "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) CriOS/30.0.1599.12 Mobile/11A465 Safari/8536.25 (3B92C18B-D9DE-4CB7-A02A-22FD2AF17C8F)");
				
				Map<String, Object> chromeOption = new HashMap<String, Object>();
				chromeOption.put("mobileEmulation", mobileEmulation);
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOption);
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
				driver = new ChromeDriver(capabilities);
			}
			
//  ******** Don't delete ******** 			
//			else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone"))
//			{
//				DesiredCapabilities des=new DesiredCapabilities();
//				des.setCapability("platfromName","IOS");
//			//	des.setCapability("platformVersion", "11");
//				des.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
//				//des.setCapability("customSSLCert", "");
//				des.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
//				des.setCapability("deviceName", "iPhone");
//				driver=new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), des);
//			}
//			else if(xmlTest.getParameter("broName").equalsIgnoreCase("iOSTab"))
//			{
//				DesiredCapabilities des=new DesiredCapabilities();
//				des.setCapability("platfromName","IOS");
//				//des.setCapability("platformVersion", "11");
//				des.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
//				des.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
//				des.setCapability("deviceName", "iPad");
//				driver=new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), des);
//			}
		}
		
		System.out.println("Collecting all \"drivers\" object according to the broswers");
		drivers.put(xmlTest.getParameter("broName").toLowerCase(), driver);
		System.out.println("getDriver try block ended");
		}
		catch(Exception e)
		{
			System.out.println("getDriver catch block executing");
			e.printStackTrace();
		}
		return driver;
	}
	
	public void closeDriver()
	{
		System.out.println(drivers.size());
		for(String str:drivers.keySet())
		{
			drivers.get(str).quit();
		}
	}

}
