package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{

	//Xpath details using Page factory method 
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	//Used constructor to initialize page objects
	public LoginPage(WebDriver driver){
		
		this.driver=driver;
	}
	
	//Actions
	//This method validates the page title
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	
	// This method validates the Login page logo
	public boolean validateCRMImage(){
		return crmLogo.isDisplayed();
	}
	
	// This method logins with username, password and clicks on login button, returns Homepage object
	public HomePage login(String un, String pwd) throws InterruptedException{
		username.sendKeys(un);
		password.sendKeys(pwd);
		Thread.sleep(2000);
		loginBtn.click();
		return new HomePage(driver);
	}
	
		
}
