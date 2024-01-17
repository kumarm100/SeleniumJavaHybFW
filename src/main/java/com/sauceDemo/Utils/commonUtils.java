package com.sauceDemo.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class commonUtils {

	public static final int WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 5;

	public static String generateTimeStamp() {
		Date dt = new Date();
		return dt.toString().replace(" ", "_").replace(":", "_");
	}

	public static Object[][] getTestDataFromXL(String sheetName) {
		File xlFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\sauceDemo\\testData\\sauceDemo_TestData.xlsx");
		XSSFWorkbook wBook = null;
		try {
			FileInputStream fis = new FileInputStream(xlFile);
			wBook = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XSSFSheet sht = wBook.getSheet(sheetName);
		int rows = sht.getLastRowNum();
		int cols = sht.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sht.getRow(i + 1);
			for (int j = 0; j < cols; j++) {

				XSSFCell cell = row.getCell(j);
				CellType cType = cell.getCellType();
				switch (cType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
	}
	
	public static String CaptureScreenShot(WebDriver driver,String sTestName) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destFilePath = System.getProperty("user.dir")+"\\Screenshots\\"+sTestName+".png";
		try {
			FileHandler.copy(srcFile, new File(destFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destFilePath;
	}

}
