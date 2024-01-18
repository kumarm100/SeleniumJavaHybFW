package com.sauceDemo.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sauceDemo.Utils.commonUtils;
import com.sauceDemo.base.TestBase;
import com.sauceDemo.pages.LoginPage;
import com.sauceDemo.pages.ProductPage;

// testing

public class LoginTest extends TestBase {
	public WebDriver driver;
	LoginPage lp;
	ProductPage pp;
	
	public LoginTest() {
		super();
	}
		
	@BeforeMethod
	public void setUp() {
		//loadPropertiesFile(); // one of the way - explicitly we can write a method to load the property file
		driver = initializeBrowser();
		lp = new LoginPage(driver);
		
		//pp = new ProductPage(wDriver);
		
	}
	
	//@Test (priority=1,dataProvider="loginTestData")
	@Test (priority=1,dataProvider="loginCredentials")
	public void verifyLoginWithValidCredentials(String uName,String sPwd) {
		
		pp = lp.LoginApplication(uName,sPwd);				
		Assert.assertTrue(pp.checkProductTitleStatus(),dataProp.getProperty("products"));
		
		//assertTrue(wDriver.findElement(By.xpath("//span[text()='Products']")).isDisplayed());
	
	}
	
	/*
	 * @DataProvider(name = "loginCredentials") 
	 * public Object[][] loginTestData() {
	 * Object[][] lData = {{"standard_user","secret_sauce"},
	 * {"visual_user","secret_sauce"}, {"performance_glitch_user","secret_sauce"}};
	 * return lData; }
	 */
	
	@DataProvider(name = "loginCredentials") 
	 public Object[][] loginTestData(){
		 Object[][] lData = commonUtils.getTestDataFromXL("Login");
		return lData;
		 
	 }
	
	@Test (priority=2)
	public void verifyLoginWithInvalidUserName() {
		lp.LoginApplication("standard_user_"+commonUtils.generateTimeStamp(),prop.getProperty("pwd"));
		String errMsg = lp.getErrorMessage();
				
		//String errMsg = wDriver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
		Assert.assertEquals(errMsg.contains(dataProp.getProperty("invalidUserCredentialsErrMsg")),"Expected error message is not displayed");
		//Assert.assertEquals(errMsg, "Epic sadface: Username and password do not match any user in this service");
	}
	
	@Test (priority=3,dependsOnMethods = {"verifyLoginWithInvalidUserName"})
	public void verifyLoginWithInvalidPasswd() {
		lp.LoginApplication(prop.getProperty("userName"),"secret_sauce1");
		String errMsg = lp.getErrorMessage();
				
		//String errMsg = wDriver.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
		Assert.assertTrue(errMsg.contains(dataProp.getProperty("invalidUserCredentialsErrMsg")),"Expected error message is not displayed");
		//Assert.assertEquals(errMsg, "Epic sadface: Username and password do not match any user in this service");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
