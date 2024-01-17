package com.sauceDemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='user-name']")
	private WebElement eName;
	
	@FindBy(xpath="//input[@id='password']")
	private WebElement ePwd;
	
	@FindBy(xpath="//input[@id='login-button']")
	private WebElement btnLogin;
	
	@FindBy(xpath="//div[@class='error-message-container error']")
	private WebElement errMsg;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterUserName(String userName) {
		eName.sendKeys(userName);
	}
	
	public void enterPassword(String sPwd) {
		ePwd.sendKeys(sPwd);
	}
	
	/*
	 * public ProductPage clickLogin() { btnLogin.click(); return new
	 * ProductPage(driver); }
	 */
	public ProductPage clickLogin() {
		btnLogin.click();
		return new ProductPage(driver);
	}
	
	public ProductPage LoginApplication(String userName,String sPwd) {
		eName.sendKeys(userName);
		ePwd.sendKeys(sPwd);
		btnLogin.click();
		return new ProductPage(driver);
	}
	
	public String getErrorMessage() {
		String eMsg = errMsg.getText();
		return eMsg;
	}
}
