package com.sauceDemo.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sauceDemo.base.TestBase;
import com.sauceDemo.pages.LoginPage;
import com.sauceDemo.pages.ProductPage;

public class verifyProductsTest extends TestBase{
	
	WebDriver driver;
	LoginPage lp;
	ProductPage pp;
	
	public verifyProductsTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser();
		lp = new LoginPage(driver);
	}
	
	@Test
	public void verifyProductName() {
		pp = lp.LoginApplication(prop.getProperty("userName"), prop.getProperty("pwd"));
		assertTrue(pp.checkProductTitleStatus(),"Navigation to Products page is not successful");
		pp.productName();
		//List<WebElement> pName = driver.findElements(By.xpath("//div[@id='inventory_container']/descendant::a/div"));
		//for(WebElement e:pp.pName) {
		//	System.out.println("Product Name ==>> " +e.getText());			
		//}		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
