package com.sauceDemo.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReporter {
	public static ExtentReports generateExtentReport() {
		ExtentReports erpt = new ExtentReports();
		File ePrtFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReports.html");
		ExtentSparkReporter sparkRptr = new ExtentSparkReporter(ePrtFile);
		
		sparkRptr.config().setTheme(Theme.DARK);
		sparkRptr.config().setReportName("SauceDemo Test Report");
		sparkRptr.config().setDocumentTitle("SD Automation Report");
		sparkRptr.config().setTimeStampFormat("dd-mm-yyyy hh:mm:ss");
		
		erpt.attachReporter(sparkRptr);
		Properties props = new Properties();
		File confPropFile = new File(System.getProperty("user.dir")+"\\\\src\\\\main\\\\java\\\\com\\\\sauceDemo\\\\config\\\\config.properties");
		try {
			
			FileInputStream fis = new FileInputStream(confPropFile);
			props.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		erpt.setSystemInfo("Application URL", props.getProperty("Url"));
		erpt.setSystemInfo("Browsser Name", props.getProperty("browser"));
		return erpt;
		
	}
}
