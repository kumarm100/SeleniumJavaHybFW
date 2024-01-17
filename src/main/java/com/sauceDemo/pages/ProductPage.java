package com.sauceDemo.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

public WebDriver driver;
	
	@FindBy(xpath="//span[text()='Products']")
	private WebElement eProductTitle;
	
	@FindAll (@FindBy(xpath = "//div[@id='inventory_container']/descendant::a/div"))
	private List<WebElement> pName;
	
		
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean checkProductTitleStatus() {
		boolean displayStatus = eProductTitle.isDisplayed();
		return displayStatus;
	}
	
	public void productName() {
		for(WebElement e:pName) {
			System.out.println("Product Name ==>> " +e.getText());	
			
		}		
	}
	
}
