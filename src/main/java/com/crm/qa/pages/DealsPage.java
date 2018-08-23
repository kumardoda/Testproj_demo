package com.crm.qa.pages;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;



public class DealsPage extends TestBase{
	@FindBy(xpath = "//input[@id='title']")
	WebElement deals_title;
	
	@FindBy(xpath = "//input[@name='client_lookup']")
	WebElement deals_company;
	
	@FindBy(xpath = "//input[@name='contact_lookup']")
	WebElement deals_contact;
	
	@FindBy(xpath = "//input[@id='amount']")
	WebElement deals_amount;
	
	@FindBy(xpath = "//textarea[@id='description']")
	WebElement deals_description;
	
	@FindBy(xpath = "//form[@id='prospectForm']//input[@type='submit' and @value='Save']")
	WebElement deals_submit;
	
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logout;
	
		
	public DealsPage(WebDriver driver) {
		this.driver=driver;
	}
	
		
	public void createNewDeals(String title, String company, String contact, String amount, String description ) throws InterruptedException, IOException{
			
		deals_title.sendKeys(title);
		deals_company.clear();
		deals_company.sendKeys(company);
		deals_contact.sendKeys(contact);
		deals_amount.sendKeys(amount);
		deals_description.sendKeys(description);
		
		deals_submit.click();
		Thread.sleep(2000);
		TestUtil.takeScreenShot(driver, ".\\test1.jpg");
		Thread.sleep(1000);
		//logout.click();
}
}