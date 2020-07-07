package com.hackathon.utilgeneric;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

public class SetLogData {
	
	int i;

	public void setLogData(String c) throws Exception
	{
		Date d=new Date();
		System.out.println(c);
		String str="C:/SelFrameWork/"+c+"_Results"+d.getDate()+" "+d.getMonth()+" "+d.getYear()+" "+d.getHours()+" "+d.getMinutes()+" "+d.getSeconds();
		SetData.writeDataToProperties(System.getProperty("user.dir")+"\\src\\Log4j.properties", "log4j.appender.R.File", str+"/"+c+".log");
		SetData.writeDataToProperties(System.getProperty("user.dir")+"\\src\\Log4j.properties", "log4j.appender.HTML.File", str+"/application.html");
		SetData.writeDataToProperties(System.getProperty("user.dir")+"\\src\\Log4j.properties", "log4j.appender.TTCC.File",str+"/"+c+"1.log");
		
		
/*		Properties prop = new Properties();
        prop.setProperty("log4j.rootLogger", "INFO,CONSOLE,R,HTML,TTCC,debug");
        prop.setProperty("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender");
        prop.setProperty("log4j.appender.R", "org.apache.log4j.RollingFileAppender");
        prop.setProperty("log4j.appender.TTCC", "org.apache.log4j.RollingFileAppender");
        prop.setProperty("log4j.appender.HTML", "org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.R.File",str+"/"+c+".log");
        prop.setProperty("log4j.appender.TTCC.File",str+"/application.html");
        prop.setProperty("log4j.appender.HTML.File",str+"/"+c+"1.log");
        prop.setProperty("log4j.appender.CONSOLE.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.CONSOLE.layout.ConversionPattern", "%5p [%t] (%F:%L)- %m%n");
        prop.setProperty("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.R.layout.ConversionPattern", "%M - %d - %c -%p - %m%n");
        prop.setProperty("log4j.appender.TTCC.layout", "org.apache.log4j.TTCCLayout");
        prop.setProperty("log4j.appender.TTCC.layout.DateFormat", "ISO8601");
        prop.setProperty("log4j.appender.HTML.layout", "org.apache.log4j.HTMLLayout");
        prop.setProperty("log4j.appender.HTML.layout.Title", "Application log");
        prop.setProperty("log4j.appender.HTML.layout.LocationInfo", "true");
        
        FileOutputStream os = new FileOutputStream(System.getProperty("user.dir")+"\\src\\Log4j.properties");
        prop.store(os, "Dynamic Property File");*/
        
        Thread.sleep(10000);
        
	}
	
}
