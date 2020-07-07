package com.hackathon.utilgeneric;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;


import org.openqa.selenium.WebDriver;

public class SetData {

	static String data;
	static File f;
	

	
	public static void writeDataToProperties(String filePath,String propName,String propVal) throws Exception {
		
		f=new File(filePath);
		FileInputStream fis=new FileInputStream(f);
		Properties p=new Properties();
		p.load(fis);
		p.put(propName, propVal);
		
		FileOutputStream fos=new FileOutputStream(f);
		p.store(fos, "");
	}

	
}
