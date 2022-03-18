package com.hcl.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import com.hcl.Constants.Constants;
import com.hcl.utilities.ExcelReader;
import com.hcl.utilities.MonitoringMail;
import com.hcl.utilities.WebEventListener;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	private Properties OR = new Properties();
	private Properties config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(this.getClass());
	public static EventFiringWebDriver e_driver;
	public static com.hcl.utilities.WebEventListener eventListener;
	public static final String TEST_DATA_SHEET_PATH = System.getProperty("user.dir") + "/src/main/java/com/hcl/TestData/TestData.xlsx";
	public WebElement dropdown;
	public ExcelReader excel = new ExcelReader(TEST_DATA_SHEET_PATH);
	public MonitoringMail mail = new MonitoringMail();
	public WebDriverWait wait;
	public static Navigation topNav;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) {
		
		 driver = null;
		// loading the log file
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");

		// loading the OR and Config properties
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
				log.info("Launching Chrome !!!");

				} 
			else if (browser.equals("firefox")) {


				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Launching FireFox !!!");

			} 
			else if (browser.equals("IE")) {
			
				WebDriverManager.edgedriver().setup();
				
				driver = new EdgeDriver();
				log.info("Launching InternetExplorer !!!");
			}
			else {
				

				System.out.println("Please enter valid browser");
				log.info("Invalid browser !!!");
			
			}
			
			
			
			e_driver = new EventFiringWebDriver(driver);
			
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
			driver.get(config.getProperty("testurl"));
			log.info("Navigated to : " + config.getProperty("testurl"));
			
			
			
			
		}
	}

	@BeforeTest
	public void setLog4j()
{
	com.hcl.utilities.TestUtility.setDateForLog4j();
}

	
	@AfterClass(alwaysRun=true)
	public void tearDown() throws IOException
	{
		driver.quit();
		log.info("Browser Terminated");
		log.info("-----------------------------------------------");
	}

}
