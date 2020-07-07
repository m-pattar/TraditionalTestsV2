package com.hackathon.utilgeneric;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;



public class GetData {

	static String data;
	static File f;
	

	
	public static String getDataFromProperties(String filePath,String propName) throws Exception {
		
		f=new File(System.getProperty("user.dir")+filePath);
		FileInputStream fis=new FileInputStream(f);
		Properties p=new Properties();
		p.load(fis);
		data=p.getProperty(propName);
		return data;
	}
	
}
