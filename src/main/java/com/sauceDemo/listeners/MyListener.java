package com.sauceDemo.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sauceDemo.Utils.commonUtils;
import com.sauceDemo.Utils.extentReporter;

public class MyListener implements ITestListener {
	ExtentReports exReport;
	ExtentTest eTest;
	
	@Override
	public void onStart(ITestContext context) {
		//System.out.println("Test execution started");
		 exReport = extentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		//String sTestName = result.getName();
		eTest = exReport.createTest(result.getName());
		eTest.log(Status.INFO, result.getName()+ " test started execution ");
		//System.out.println("Test "+sTestName+ " started execution ");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		eTest.log(Status.PASS, result.getName()+ " test executed successfully ");
		//System.out.println("Test "+result.getName()+" executed successfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eTest.addScreenCaptureFromPath(commonUtils.CaptureScreenShot(driver, result.getName()));
		eTest.log(Status.INFO, result.getThrowable());
		eTest.log(Status.FAIL, result.getName()+" test got failed");
		
		//System.out.println("Test "+result.getName()+" got failed");
		//System.out.println(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		eTest.log(Status.INFO, result.getThrowable());
		eTest.log(Status.SKIP, result.getName()+" test got skipped");
		//System.out.println("Test "+result.getName()+" test got skipped");
		//System.out.println(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		exReport.flush();
		//System.out.println("Test got finished successfully");
		String exReportPath = System.getProperty("user.dir") +"\\test-output\\ExtentReports\\extentReports.html";
		File exReport = new File(exReportPath);
		try {
			Desktop.getDesktop().browse(exReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
