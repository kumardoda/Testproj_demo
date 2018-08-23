package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.crm.qa.base.TestBase;

public class TestUtil {

	public static long IMPLICIT_WAIT =40;	
	public static String TESTDATA_SHEET_PATH = TestBase.class.getResource("/dealsdata.xlsx").getFile();

	static Workbook book;
	static Sheet sheet;
	public static Properties prop;
	public static WebDriver driver;
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				
		 for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}
		}
		return data;
	}
	
	public static String readProperty(String propName, String filePath)
	{
		String propValue="";
			try {
			prop = new Properties();
			FileInputStream fp = new FileInputStream(filePath);
			prop.load(fp);
			 propValue = prop.getProperty(propName);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return propValue;
	}

	public static void switchToFrame(WebDriver driver, String name){
		driver.switchTo().frame(name);
		}
	
	public static void takeScreenShot(WebDriver driver, String filePath) throws IOException 
	{
		TakesScreenshot scr = ((TakesScreenshot)driver);
		File scrFile = scr.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);
		FileUtils.copyFile(scrFile, destFile);
	}
	
	@SuppressWarnings("deprecation")
	public static void excelRead(String dataFile) {
		try {
			//String file = ExcelReader.class.getResource(dataFile).getFile();
			String file = new File(dataFile).getPath();
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			Workbook wb=null;
			Sheet sheet;
			//System.out.println(file.indexOf("."));
			String fileExtensionName = file.substring(file.indexOf("."));

		    //Check condition if the file is xlsx file

		    if(fileExtensionName.equals(".xls")){
			
			wb = new HSSFWorkbook(fis);
			
			}
			else if(fileExtensionName.equals(".xlsx"))
			{
			wb = new XSSFWorkbook(fis);
			
			}
			sheet = wb.getSheet("Userdata");
			int lastRowNum = sheet.getLastRowNum();
			
			for(int i=0; i<=lastRowNum; i++)
			{
				Row row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for(int j=0; j<lastCellNum; j++)
				{
					Cell cell = row.getCell(j);
					
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
						   String stringCellValue = sheet.getRow(i).getCell(j).getStringCellValue().trim();
						 System.out.println(stringCellValue);
					}
					/*else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						  double numericCellValue = sheet.getRow(i).getCell(j).getNumericCellValue();
						 System.out.println(numericCellValue);
					} */
							
					else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
						   int intCellValue = (int) sheet.getRow(i).getCell(j).getNumericCellValue();
						 System.out.println(intCellValue);
					}
			
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
