package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.crm.qa.util.TestUtil;


public class TestBase {
    
	//WebDriver and properties file object initialization
	public static WebDriver driver;
	public static Properties prop;
	
	static String configFile = TestBase.class.getResource("/config.properties").getFile();
	static String chromeDriverPath = TestBase.class.getResource("/chromedriver.exe").getFile();
	static String geckoDriverPath = TestBase.class.getResource("/geckodriver.exe").getFile(); 
	static String ieDriverPath = TestBase.class.getResource("/IEDriverServer.exe").getFile();
	
	//Browser initialization and launching url
	public static WebDriver initialization() throws InterruptedException, MalformedURLException {
		String browserName =TestUtil.readProperty("browser", configFile);
		//System.out.println(browserName);
		//DesiredCapabilities dc = new DesiredCapabilities();

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
			//dc.setBrowserName("Chrome");
			//dc.setPlatform(Platform.WINDOWS);
			//WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"),dc);
			browserConfig();
		} 
		else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", geckoDriverPath);
			driver = new FirefoxDriver();
			browserConfig();
		}			
			else if (browserName.equals("IE")) {
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				driver = new InternetExplorerDriver();
				browserConfig();
		}
		return driver;
			
	}
	
	public static void browserConfig(){
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(TestUtil.readProperty("url", configFile));

	}
}
