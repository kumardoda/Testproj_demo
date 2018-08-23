package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//tr/td[1]/font[contains(text(),'User: Pavan Kumar')]")
	WebElement userNameLabel;

	@FindBy(xpath = "//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath = "//a[contains(text(),'New Deal')]")
	WebElement newDealsLink;
	
    // Initializing page objects
	public HomePage(WebDriver driver) {
    	//PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public void clickOnDealsLink(WebDriver driver){
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Deals')]"))).build().perform();
	}
	
	public void clickOnNewDealsLink(WebDriver driver){
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'New Deal')]"))).perform();
		newDealsLink.click();
	}
	
}
