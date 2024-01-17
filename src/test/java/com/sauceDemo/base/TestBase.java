package com.sauceDemo.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.sauceDemo.Utils.commonUtils;

public class TestBase {
	WebDriver wd;
	public Properties prop;
	public Properties dataProp;
	
	// Constructor
	public TestBase() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\sauceDemo\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\sauceDemo\\testData\\testData.properties");
		try {
			FileInputStream fis1 = new FileInputStream(dataPropFile);
			dataProp.load(fis1);
			
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	// 2nd way of loading the properties file
	public void loadPropertiesFile() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\sauceDemo\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public WebDriver initializeBrowser() {
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			wd = new ChromeDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			wd = new FirefoxDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
			wd = new EdgeDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("safari")) {
			wd = new SafariDriver();
		}
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(commonUtils.WAIT_TIME));
		wd.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(commonUtils.PAGE_LOAD_TIME));
		wd.get(prop.getProperty("Url"));
		return wd;
	}
}
