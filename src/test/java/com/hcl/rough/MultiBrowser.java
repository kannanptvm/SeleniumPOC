package com.hcl.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hcl.Constants.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultiBrowser {

	public WebDriver driver;
	private Properties OR = new Properties();
	private Properties config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(this.getClass());
	public static EventFiringWebDriver e_driver;
	public static com.hcl.utilities.WebEventListener eventListener;


  @Parameters("browser")

  @BeforeClass

  // Passing Browser parameter from TestNG xml

  public void beforeTest(String browser) {

	  
	  if (driver == null) {

			try {
				fis = new FileInputStream("./src/test/resources/properties/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream("./src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR properties loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

  // If the browser is Firefox, then do this

	  if (browser.equals("chrome")) {

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
          System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
				
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

			} 
		else if (browser.equals("firefox")) {


			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} 
		else if (browser.equals("IE")) {
		
			WebDriverManager.edgedriver().setup();
			
			driver = new EdgeDriver();
		}
		else {
			

			System.out.println("Please enter valid browser");
		
		}
		
		

  // Enter the website address in the browser

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(config.getProperty("testurl"));
		
	  }
		
  }

  // Once Before method is completed, Test method will start

	@AfterClass(alwaysRun=true)
	public void tearDown() throws IOException
	{
		driver.quit();
		
	}

}