package com.crm.qa.tests;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.support.PageFactory;
//import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

  //Extends TestBase class
 public class LoginPageTest extends TestBase {
	 
	static String configFile = TestBase.class.getResource("/config.properties").getFile();
	LoginPage loginPg;
	HomePage homePg;
	TestUtil testUtil;
		
	/* calling method in TestBase class which initializes browser and launches
	/ application url */
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws InterruptedException, MalformedURLException {
	
		TestBase.initialization();
		loginPg = PageFactory.initElements(driver,LoginPage.class);
		homePg = PageFactory.initElements(driver, HomePage.class);
			
	}
	
	/** Testcases
	 This test case checks the Page title without logging into it 
	 * @throws IOException */
	@Test(groups = {"Smoke"})
	public void loginPageTitleTest() throws IOException {
		String title = loginPg.validateLoginPageTitle();
		Assert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service");
		}

	// This test case checks the top left LoginPage image without logging into
	@Test(groups = {"Smoke"})
	public void crmLogoImageText() throws IOException {
		Boolean flag = loginPg.validateCRMImage();
		Assert.assertTrue(flag);
		
	}

	// This test case opens the application url, enters username, password,
	// clicks on login button and then Home Page is displayed
	@Test(groups = {"Smoke"})
	public void loginTest() throws InterruptedException, IOException {
		
		homePg = loginPg.login(TestUtil.readProperty("username", configFile), TestUtil.readProperty("password", configFile));
		
	}
		
	// This method is used for closing the browser
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
