package com.crm.qa.tests;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
//import com.crm.qa.util.ExcelReader;
import com.crm.qa.util.TestUtil;

public class DealsPageTest extends TestBase
{
	
	static String configFile = TestBase.class.getResource("/config.properties").getFile();
	DealsPage dealsPg;
	HomePage homePg;
	LoginPage loginPg;
	
	String sheetName = "Userdata";
	
		
	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test
	public void initializeBrowser() throws MalformedURLException{
	try {
		driver=TestBase.initialization();
	} catch (InterruptedException e1) {
	    e1.printStackTrace();
	}
	dealsPg = PageFactory.initElements(driver, DealsPage.class);
	loginPg = PageFactory.initElements(driver, LoginPage.class);
	homePg = PageFactory.initElements(driver, HomePage.class);
	try {
		loginPg.login(TestUtil.readProperty("username", configFile), TestUtil.readProperty("password", configFile));
		} catch (InterruptedException e) {
		e.printStackTrace();
	}
	 TestUtil.switchToFrame(driver, "mainpanel");
	}
	
	@Test(dataProvider="getTestData", groups={"Regression"}, dependsOnMethods="initializeBrowser",priority=1)
	public void validateCreateNewDeal(String title, String company, String contact, String amount, String description) throws InterruptedException, IOException
	{
			homePg.clickOnDealsLink(driver);
			homePg.clickOnNewDealsLink(driver);
			dealsPg.createNewDeals(title, company, contact, amount, description);
			
						
	}
	
	@Test(groups={"Regression"},priority=2)
	public void deleteDeal() throws InterruptedException{
		driver.findElement(By.xpath("//a[contains(text(),'Deals')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td/form/table[2]/tbody/tr[8]/td[7]/a[3]")).click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
	}
		
	@AfterTest(alwaysRun = true)
	public void tearDown(){
		driver.quit();
	}	
}
