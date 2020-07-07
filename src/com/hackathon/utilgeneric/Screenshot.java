package com.hackathon.utilgeneric;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.BufferOverflowException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Screenshot implements ITestListener{
	
	WebDriver driver;
	Method m;
	XmlTest xmlTest;
	public void onTestStart(ITestResult result) {
		
	}

	public void onTestSuccess(ITestResult result) {
        driver=BaseTest.driver;         
        xmlTest=BaseTest.xmlTest;
        String imgName=result.getInstanceName()+" "+result.getMethod().getMethodName()+" "+driver.getClass().getSimpleName();
        String taskName=result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        String element = BaseTest.l1.by.toString();
        String browserName = xmlTest.getParameter("broName");
        String viewPort = xmlTest.getParameter("viewport");
        String deviceType = xmlTest.getParameter("executingIn");
        String status = "Pass";
        String executionResult;
        
        
        if(xmlTest.getParameter("broName").equalsIgnoreCase("ie")) {
            BaseTest.extentTestChildIE.log(LogStatus.PASS,"");
           
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("firefox")) {
            BaseTest.extentTestChildFF.log(LogStatus.PASS,"");
        
           
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
            BaseTest.extentTestChildAndroidPhone.log(LogStatus.PASS,"");
           
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
            BaseTest.extentTestChildAndroidTab.log(LogStatus.PASS,"");
           
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
            BaseTest.extentTestChildiPhone.log(LogStatus.PASS,"");
           
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
            BaseTest.extentTestChildiPad.log(LogStatus.PASS,"");
           
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome")) {
            BaseTest.extentTestChildChrome.log(LogStatus.PASS,"");
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
            BaseTest.extentTestChildFFTablet.log(LogStatus.PASS,"");
           
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
            BaseTest.extentTestChildiEdge.log(LogStatus.PASS,"");
           
        } else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
            BaseTest.extentTestChildEdgeTablet.log(LogStatus.PASS,"");
           
        }
        element = element.toString().split(":")[1].trim();
        executionResult = "Task: "+description+", Test Name: "+taskName+", DOM Id:: "+element+", Browser: "+browserName+", Viewport: "+viewPort+", Device: "+deviceType+", Status: "+status;
        try
		{
            FileWriter writer = new FileWriter("./Result/Traditional-V2-TestResults.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(executionResult);
            bufferedWriter.newLine();
            bufferedWriter.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
   }


public void onTestFailure(ITestResult result) {
        
        try
        {
            driver=GetDriver.driver;
            xmlTest=BaseTest.xmlTest;
            String imgName=result.getInstanceName()+" "+result.getMethod().getMethodName()+" "+driver.getClass().getSimpleName();
            String taskName=result.getMethod().getMethodName();
            String description = result.getMethod().getDescription();
            String element = BaseTest.l1.by.toString();
            String browserName = xmlTest.getParameter("broName");
            String viewPort = xmlTest.getParameter("viewport");
            String deviceType = xmlTest.getParameter("executingIn");
            String status = "Fail";
            String executionResult;
            //write take screen shot code
             
            System.out.println(driver.getClass().getName());
            System.out.println("on failure");
            //((JavascriptExecutor) driver).executeScript("window.focus();");
            
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            FileUtils.copyFile(scrShot.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
            
            if(xmlTest.getParameter("broName").equalsIgnoreCase("ie")) {
                BaseTest.extentTestChildIE.log(LogStatus.FAIL,"",BaseTest.extentTestChildIE.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                
             } else if(xmlTest.getParameter("broName").equalsIgnoreCase("firefox")) {
                    BaseTest.extentTestChildFF.log(LogStatus.FAIL, "",BaseTest.extentTestChildFF.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
          
                           
             } else if(xmlTest.getParameter("broName").equalsIgnoreCase("Phone")) {
                    BaseTest.extentTestChildAndroidPhone.log(LogStatus.FAIL, "",BaseTest.extentTestChildAndroidPhone.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                
             } else if(xmlTest.getParameter("broName").equalsIgnoreCase("Chrome Tab")) {
                    BaseTest.extentTestChildAndroidTab.log(LogStatus.FAIL, "",BaseTest.extentTestChildAndroidTab.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                
             } else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPhone")) {
                    BaseTest.extentTestChildiPhone.log(LogStatus.FAIL, "",BaseTest.extentTestChildiPhone.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                
             } else if(xmlTest.getParameter("broName").equalsIgnoreCase("iPad")) {
                    BaseTest.extentTestChildiPad.log(LogStatus.FAIL, "",BaseTest.extentTestChildiPad.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                
             } else if(xmlTest.getParameter("broName").equalsIgnoreCase("chrome")) {
                 BaseTest.extentTestChildChrome.log(LogStatus.FAIL, "",BaseTest.extentTestChildChrome.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                 
             } else if(xmlTest.getParameter("broName").equalsIgnoreCase("FirefoxTablet")) {
                 BaseTest.extentTestChildFFTablet.log(LogStatus.FAIL, "",BaseTest.extentTestChildFFTablet.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                 
             } else if(xmlTest.getParameter("broName").equalsIgnoreCase("Edge")) {
                 BaseTest.extentTestChildiEdge.log(LogStatus.FAIL, "",BaseTest.extentTestChildiEdge.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                 
             }  else if(xmlTest.getParameter("broName").equalsIgnoreCase("EdgeTablet")) {
                 BaseTest.extentTestChildEdgeTablet.log(LogStatus.FAIL, "",BaseTest.extentTestChildEdgeTablet.addScreenCapture(System.getProperty("user.dir")+"\\log\\"+imgName+".png"));
                 
             }
            System.out.println("***********taskName:- "+ taskName);
            System.out.println("***********description:- "+ description);
            element = element.toString().split(":")[1].trim();
            System.out.println("***********element:- "+ element);
            System.out.println("************browserName:-"+browserName);
            System.out.println("************viewPort:-"+viewPort);
            System.out.println("************deviceType:- "+ deviceType);
            System.out.println("************status:- "+ status);
            executionResult = "Task: "+description+", Test Name: "+taskName+", DOM Id:: "+element+", Browser: "+browserName+", Viewport: "+viewPort+", Device: "+deviceType+", Status: "+status;
            
            FileWriter writer = new FileWriter("./Result/Traditional-V2-TestResults.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(executionResult);
            bufferedWriter.newLine();
            bufferedWriter.close();
            
        }
        catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error bro here");
			}
	}

	public void onTestSkipped(ITestResult result) {
		
	//	extentTest.log(LogStatus.SKIP, "Its skipped bro please check the reason");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onStart(ITestContext context) {
		
		
	}

	public void onFinish(ITestContext context) {

	//	extentReport.flush();
		
	}
}
